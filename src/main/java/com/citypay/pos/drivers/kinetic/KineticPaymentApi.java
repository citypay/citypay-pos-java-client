package com.citypay.pos.drivers.kinetic;

import com.citypay.invoker.*;
import com.citypay.pos.api.kinetic.PaymentApi;
import com.citypay.pos.model.kinetic.TransactionResult;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;

import java.lang.reflect.Type;

/**
 * A transaction result now returns a JSON packet which has the data properties returned as a dynamic key
 * based on the identifier i.e.
 * <p>
 * {
 * "success": true,
 * "testing7": {
 * "base_amount": 1,
 * "card_presented": false,
 * "created_at": 1537352942,
 * <p>
 * This class extends the standard {@link com.citypay.pos.api.kinetic.PaymentApi} to forceably parse this
 */
public class KineticPaymentApi extends PaymentApi {

    private KineticApiClient kineticApiClient;

    public KineticPaymentApi(KineticApiClient apiClient) {
        super(apiClient);
        this.kineticApiClient = apiClient;
    }


    public Call transactionJsonGetAsyncFull(String uuid, ApiCallback<KineticTransactionResult> callback) throws ApiException {
        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = (bytesRead, contentLength, done) -> callback.onDownloadProgress(bytesRead, contentLength, done);
            progressRequestListener = (bytesWritten, contentLength, done) -> callback.onUploadProgress(bytesWritten, contentLength, done);

        }

        kineticApiClient.setIdentifier(uuid);
        com.squareup.okhttp.Call call = transactionJsonGetCall(uuid, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<TransactionResult>() {
        }.getType();
        getApiClient().executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
