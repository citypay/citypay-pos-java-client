package com.citypay.pos.api;


import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiException;
import com.citypay.pos.drivers.DeviceManager;
import com.citypay.pos.model.*;
import com.citypay.pos.util.ApiCallbackDelegate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.citypay.pos.drivers.kinetic.KineticDeviceDriver.*;

/**
 * Java payment module for working with device payments programmatically. The {@link PaymentModule} class only supports
 * asynchronous API calls.
 * <p>
 * The device module will require an initialised {@link DeviceManager} and will check for valid devices on method calls.
 * </p>
 */
@SuppressWarnings("unused")
public class PaymentModule extends Module {

    private List<WebHook<TransactionResult>> webhooks = new LinkedList<>();

    public PaymentModule(DeviceManager deviceManager, WebHook<TransactionResult>... webHooks) {
        super(deviceManager);
        for (WebHook<TransactionResult> hook : webHooks) {
            webhooks.add(hook);
        }
    }

    public final void sale(SaleRequest sale, final ApiCallback<SaleResponse> callback) throws ApiException {
        withDevice(sale.getDevice(), callback,
                (remote) -> initRemotePaymentApi(remote).saleAsync(sale, callback),
                (kinetic) -> initKineticPaymentApi(kinetic).saleJsonPostAsync(AdaptSaleRequest(sale), AdaptSaleResponse(callback))
        );
    }

    public final void refund(SaleRequest sale, final ApiCallback<SaleResponse> callback) throws ApiException {
        withDevice(sale.getDevice(), callback,
                (remote) -> initRemotePaymentApi(remote).refundAsync(sale, callback),
                (kinetic) -> initKineticPaymentApi(kinetic).refundJsonPostAsync(AdaptSaleRequest(sale), AdaptSaleResponse(callback))
        );
    }

    public final void reversal(ReversalRequest reversal, final ApiCallback<SaleResponse> callback) throws ApiException {
        withDevice(reversal.getDevice(), callback,
                (remote) -> initRemotePaymentApi(remote).reversalAsync(reversal, callback),
                (kinetic) -> initKineticPaymentApi(kinetic).reversalJsonPostAsync(AdaptReversalRequest(reversal), AdaptSaleResponse(callback))
        );
    }

    public final void transactionStatus(TransactionProgress progress, final ApiCallback<TransactionResult> callback) throws ApiException {

        // delegate the callback off to a wrapper implementation that performs the web hooks on each time the status is returned
        // and the status is deemed as complete
        ApiCallbackDelegate<TransactionResult> wrappedCallback = new ApiCallbackDelegate<TransactionResult>(callback) {

            @Override
            public void onSuccess(TransactionResult result, int statusCode, Map<String, List<String>> responseHeaders) {
                super.onSuccess(result, statusCode, responseHeaders);
                if (result != null && result.isSuccess() && result.isIsComplete()) {
                    for (WebHook<TransactionResult> webhook : webhooks) {
                        webhook.deliver(result);
                    }
                }
            }
        };

        withDevice(progress.getDevice(), callback,
                (remote) -> initRemotePaymentApi(remote).transactionAsync(progress, wrappedCallback),
                (kinetic) -> initKineticPaymentApi(kinetic).transactionJsonGetAsyncFull(
                        progress.getIdentifier(),
                        AdaptTransactionResult(progress.getIdentifier(), wrappedCallback)
                )
        );
    }


    public final void receipt(PrintRequest request, final ApiCallback<Receipt> callback) throws ApiException {
        withDevice(request.getDevice(), callback,
                (remote) -> initRemotePaymentApi(remote).receiptAsync(request, callback),
                (kinetic) -> initKineticPaymentApi(kinetic).transactionJsonGetAsync(
                        request.getIdentifier(),
                        AdaptTransactionReceipt(request, callback)
                )
        );
    }

}
