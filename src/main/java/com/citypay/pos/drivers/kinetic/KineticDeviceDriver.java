package com.citypay.pos.drivers.kinetic;

import com.citypay.invoker.ApiCallback;
import com.citypay.pos.drivers.BaseDeviceDriver;
import com.citypay.pos.model.ConnectionType;
import com.citypay.pos.model.DeviceInfo;
import com.citypay.pos.model.Receipt;
import com.citypay.pos.model.Result;
import com.citypay.pos.model.kinetic.*;
import com.citypay.pos.util.ApiCallbackAdaptor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KineticDeviceDriver extends BaseDeviceDriver {

    public static final String KINETIC_DEVICE_LABEL = "kinetic";

    public KineticDeviceDriver(ConnectionType connectionType) {
        super(KINETIC_DEVICE_LABEL, connectionType);
    }

    public static ApiCallbackAdaptor<InfoResponse, DeviceInfo> AdaptDeviceInfo(ApiCallback<DeviceInfo> callback) {
        return new ApiCallbackAdaptor<InfoResponse, DeviceInfo>(callback) {
            @Override
            public DeviceInfo adapt(InfoResponse result) {
                return new DeviceInfo()
                        .batteryCharging(result.isBatteryCharging())
                        .batteryPercentage(result.getBatteryPercentage())
                        .busy(result.isBusy())
                        .name(result.getDevice())
                        .portable(result.isPortable())
                        .printer(result.isPrinter())
                        .serialno(result.getSerial())
                        .connectionMode(result.getConnectionMode())
                        .busy(false);
            }
        };
    }

    public static ApiCallbackAdaptor<PingResponse, Result> AdaptPingResponse(ApiCallback<Result> callback) {
        return new ApiCallbackAdaptor<PingResponse, Result>(callback) {
            @Override
            public Result adapt(PingResponse result) {
                return new Result().success(result.isPing()).message("Ping successful");
            }
        };
    }

    public static SaleRequest AdaptSaleRequest(com.citypay.pos.model.SaleRequest request) {
        return new SaleRequest()
                .amount(request.getAmount())
                .uuid(request.getIdentifier());
    }

    public static ReversalRequest AdaptReversalRequest(com.citypay.pos.model.ReversalRequest request) {
        return new ReversalRequest()
                .uuid(request.getIdentifier());
    }

    public static TransactionProgress AdaptTransactionProgress(com.citypay.pos.model.TransactionProgress request) {
        return new TransactionProgress()
                .uuid(request.getIdentifier());
    }

    public static TransactionProgress AdaptTransactionProgress(com.citypay.pos.model.PrintRequest request) {
        return new TransactionProgress()
                .uuid(request.getIdentifier());
    }

    public static ApiCallbackAdaptor<SaleResponse, com.citypay.pos.model.SaleResponse> AdaptSaleResponse(ApiCallback<com.citypay.pos.model.SaleResponse> callback) {
        return new ApiCallbackAdaptor<SaleResponse, com.citypay.pos.model.SaleResponse>(callback) {
            @Override
            public com.citypay.pos.model.SaleResponse adapt(SaleResponse result) {
                com.citypay.pos.model.SaleResponse response = new com.citypay.pos.model.SaleResponse();
                response.setDescription(result.getDescription());
                response.setIdentifier(result.getUuid());
                response.setSuccess(result.isSuccess());
                return response;
            }
        };
    }

    public static ApiCallbackAdaptor<TransactionResult, Receipt> AdaptTransactionReceipt(com.citypay.pos.model.PrintRequest request, ApiCallback<Receipt> callback) {
        return new ApiCallbackAdaptor<TransactionResult, Receipt>(callback) {
            @Override
            public Receipt adapt(TransactionResult result) {
                Receipt receipt = new Receipt();
//                if ("merchant".equalsIgnoreCase(request.getType())) {
//                    receipt.setReceipt(result.getMerchantReceipt());
//                } else {
//                    receipt.setReceipt(result.getCustomerReceipt());
//                }
                return receipt;
            }
        };
    }

    public static com.citypay.pos.model.TransactionResult AdaptTransactionResult(String identifier, KineticTransactionResult result) {
        com.citypay.pos.model.TransactionResult t = new com.citypay.pos.model.TransactionResult();

        t.setSuccess(result.isSuccess());
        t.setIdentifier(identifier);

        if (t.isSuccess() && result.getData() != null) {

            TransactionData src = result.getData();
            t.setStatus(result.getStatus() == null ? src.getResponseText() : result.getStatus());
            t.setIsComplete(src.isIsCompleted() == null ? false : src.isIsCompleted());

            com.citypay.pos.model.TransactionData data = new com.citypay.pos.model.TransactionData();
            data.setAdditionalData(src.getAdditionalData());
            data.setAmount(src.getTotalAmount());
            data.setAuxiliaryData(src.getAuxiliaryData());
            data.setCardPresented(src.isCardPresented());
            data.setCardType(src.getCardType());
            if (src.getCompletedAt() != null) {
                data.setCompletedAt(new SimpleDateFormat("yyyy--MM-dd'T'HH::mm:ssZ").format(new Date(src.getCompletedAt() * 1000)));
            }
            if (src.getCreatedAt() != null) {
                data.setCreatedAt(new SimpleDateFormat("yyyy--MM-dd'T'HH::mm:ssZ").format(new Date(src.getCreatedAt() * 1000)));
            }
            data.setCurrencySymbol(src.getCurrencySymbol());
            data.setCustomerReceipt(src.getCustomerReceipt());
            data.setCvmMode(src.getCvmMode());
            data.setDatasource(src.getDatasource());
            data.setDeclinedByCard(src.isDeclinedByCard());
            data.setEmvAppCryptogram(src.getEmvAppCryptogram());
            data.setEmvAppExpirationDate(src.getEmvAppExpirationDate());
            data.setEmvAppTxnCounter(src.getEmvAppTxnCounter());
            data.setEmvAuthorizedAmount(src.getEmvAuthorizedAmount());
            data.setEmvCardholderVerificationResults(src.getEmvCardholderVerificationResults());
            data.setEmvCountryCode(src.getEmvCountryCode());
            data.setEmvCryptogramInfoData(src.getEmvCryptogramInfoData());
            data.setEmvIccAppid(src.getEmvIccAppid());
            data.setEmvInterchangeProfile(src.getEmvInterchangeProfile());
            data.setEmvIssuerAppData(src.getEmvIssuerAppData());
            data.setEmvPanSeqNum(src.getEmvPanSeqNum());
            data.setEmvTerminalCapabilities(src.getEmvTerminalCapabilities());
            data.setEmvTerminalType(src.getEmvTerminalType());
            data.setEmvTerminalVerificationResults(src.getEmvTerminalVerificationResults());
            data.setEmvTrack2Equivalent(src.getEmvTrack2Equivalent());
            data.setEmvTxnCurrencyCode(src.getEmvTxnCurrencyCode());
            data.setEmvTxnDate(src.getEmvTxnDate());
            data.setEmvTxnStatusInfo(src.getEmvTxnStatusInfo());
            data.setEmvTxnType(src.getEmvTxnType());
            data.setEmvTxnUnpredictableNumber(src.getEmvTxnUnpredictableNumber());
            data.setIdentifier(src.getUuid());
            data.setIsClosed(src.isIsClosed());
            data.setIsCommercialCard(src.isIsCommercialCard());
            data.setIsCompleted(src.isIsCompleted());
            data.setIsContactless(src.isIsContactless());
            data.setIsFallback(src.isIsFallback());
            data.setIsOffline(src.isIsOffline());
            data.setIsVoided(src.isIsVoided());
            data.setMerchantReceipt(src.getMerchantReceipt());
            data.setResponseCode(src.getResponseCode());
            data.setResponseText(src.getResponseText());
            data.setResult(src.getResult());
            data.setScheme(src.getScheme());
            data.setSignatureRequired(src.isSignatureRequired());
            data.setTerminalId(src.getTerminalId());
            data.setTotalAmount(src.getTotalAmount());
            data.setTransactionType(src.getTransactionType());
            data.setVerifiedByPin(src.isVerifiedByPin());
            t.setData(data);

        } else {

            t.setStatus(result.getStatus() == null ? result.getDescription() : result.getStatus());
            t.setIsComplete(false);
        }

        return t;
    }

    public static ApiCallbackAdaptor<KineticTransactionResult, com.citypay.pos.model.TransactionResult> AdaptTransactionResult(String identifier, ApiCallback<com.citypay.pos.model.TransactionResult> callback) {
        return new ApiCallbackAdaptor<KineticTransactionResult, com.citypay.pos.model.TransactionResult>(callback) {
            @Override
            public com.citypay.pos.model.TransactionResult adapt(KineticTransactionResult result) {
                return AdaptTransactionResult(identifier, result);
            }
        };
    }


}
