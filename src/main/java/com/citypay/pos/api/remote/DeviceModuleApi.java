/*
 * CityPay POS API
 * CityPay Point of Sale API for payment with card present devices including EMV readers and contactless POS readers.  The API is available from https://github.com/citypay/citypay-pos-api  The API makes it simple to add EMV and contactless card acceptance to iOS, Android, Tablet and desktop applicaitons using a HTTPS protocol. It segregates the complexity of payment processing from the sales environment and eliminates any necessity for the target system to handle card data. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: dev@citypay.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.citypay.pos.api.remote;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiClient;
import com.citypay.invoker.ApiException;
import com.citypay.invoker.ApiResponse;
import com.citypay.invoker.Configuration;
import com.citypay.invoker.Pair;
import com.citypay.invoker.ProgressRequestBody;
import com.citypay.invoker.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.citypay.pos.model.DeviceInfo;
import com.citypay.pos.model.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceModuleApi {
    private ApiClient apiClient;

    public DeviceModuleApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DeviceModuleApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for deviceInfo
     * @param deviceId The name of the target device used by the API. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call deviceInfoCall(String deviceId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/device/{deviceId}/info"
            .replaceAll("\\{" + "deviceId" + "\\}", apiClient.escapeString(deviceId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call deviceInfoValidateBeforeCall(String deviceId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'deviceId' is set
        if (deviceId == null) {
            throw new ApiException("Missing the required parameter 'deviceId' when calling deviceInfo(Async)");
        }
        

        com.squareup.okhttp.Call call = deviceInfoCall(deviceId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Device Information
     * Obtains information regarding the device and to review the current status of a device such as the battery charge, serial number and device type. 
     * @param deviceId The name of the target device used by the API. (required)
     * @return DeviceInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DeviceInfo deviceInfo(String deviceId) throws ApiException {
        ApiResponse<DeviceInfo> resp = deviceInfoWithHttpInfo(deviceId);
        return resp.getData();
    }

    /**
     * Device Information
     * Obtains information regarding the device and to review the current status of a device such as the battery charge, serial number and device type. 
     * @param deviceId The name of the target device used by the API. (required)
     * @return ApiResponse&lt;DeviceInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DeviceInfo> deviceInfoWithHttpInfo(String deviceId) throws ApiException {
        com.squareup.okhttp.Call call = deviceInfoValidateBeforeCall(deviceId, null, null);
        Type localVarReturnType = new TypeToken<DeviceInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Device Information (asynchronously)
     * Obtains information regarding the device and to review the current status of a device such as the battery charge, serial number and device type. 
     * @param deviceId The name of the target device used by the API. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deviceInfoAsync(String deviceId, final ApiCallback<DeviceInfo> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = deviceInfoValidateBeforeCall(deviceId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DeviceInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for ping
     * @param deviceId The name of the target device used by the API. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call pingCall(String deviceId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/device/{deviceId}/ping"
            .replaceAll("\\{" + "deviceId" + "\\}", apiClient.escapeString(deviceId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call pingValidateBeforeCall(String deviceId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'deviceId' is set
        if (deviceId == null) {
            throw new ApiException("Missing the required parameter 'deviceId' when calling ping(Async)");
        }
        

        com.squareup.okhttp.Call call = pingCall(deviceId, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Device Ping
     * To monitor or to check whether a device is available, the host can send a simple &#x60;GET&#x60; request to the URL at &#x60;/device/{deviceId}/ping&#x60; to see if the device is responding and available. 
     * @param deviceId The name of the target device used by the API. (required)
     * @return Result
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Result ping(String deviceId) throws ApiException {
        ApiResponse<Result> resp = pingWithHttpInfo(deviceId);
        return resp.getData();
    }

    /**
     * Device Ping
     * To monitor or to check whether a device is available, the host can send a simple &#x60;GET&#x60; request to the URL at &#x60;/device/{deviceId}/ping&#x60; to see if the device is responding and available. 
     * @param deviceId The name of the target device used by the API. (required)
     * @return ApiResponse&lt;Result&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Result> pingWithHttpInfo(String deviceId) throws ApiException {
        com.squareup.okhttp.Call call = pingValidateBeforeCall(deviceId, null, null);
        Type localVarReturnType = new TypeToken<Result>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Device Ping (asynchronously)
     * To monitor or to check whether a device is available, the host can send a simple &#x60;GET&#x60; request to the URL at &#x60;/device/{deviceId}/ping&#x60; to see if the device is responding and available. 
     * @param deviceId The name of the target device used by the API. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call pingAsync(String deviceId, final ApiCallback<Result> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = pingValidateBeforeCall(deviceId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Result>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
