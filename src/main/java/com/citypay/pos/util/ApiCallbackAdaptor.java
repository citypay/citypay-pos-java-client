package com.citypay.pos.util;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ApiCallbackAdaptor<T, K> implements ApiCallback<T> {

    private static final Map<String, List<String>> NIL_MAP = new HashMap<>();

    private final ApiCallback<K> callback;

    public ApiCallbackAdaptor(ApiCallback<K> callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
        callback.onFailure(e, statusCode, NIL_MAP);
    }

    @Override
    public void onSuccess(T result, int statusCode, Map<String, List<String>> responseHeaders) {
        callback.onSuccess(adapt(result), statusCode, NIL_MAP);
    }

    /**
     * Adapts from an api callback type to another
     * @param result the result of the adaption process
     * @return an adapted instance of type K
     */
    public abstract K adapt(T result);

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
        callback.onUploadProgress(bytesWritten, contentLength, done);
    }

    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        callback.onDownloadProgress(bytesRead, contentLength, done);
    }
}
