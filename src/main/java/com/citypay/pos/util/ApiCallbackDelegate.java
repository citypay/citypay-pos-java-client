package com.citypay.pos.util;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiException;

import java.util.List;
import java.util.Map;

public class ApiCallbackDelegate<T> implements ApiCallback<T> {

    private final ApiCallback<T> target;

    public ApiCallbackDelegate(ApiCallback<T> target) {
        this.target = target;
    }

    @Override
    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
        target.onFailure(e, statusCode, responseHeaders);
    }

    @Override
    public void onSuccess(T result, int statusCode, Map<String, List<String>> responseHeaders) {
        target.onSuccess(result, statusCode, responseHeaders);
    }

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
        target.onUploadProgress(bytesWritten, contentLength, done);
    }

    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        target.onDownloadProgress(bytesRead, contentLength, done);
    }
}
