package com.citypay.pos.api;

import com.citypay.invoker.JSON;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Addition of a logical web hook which can be fired to a url based on a JSON serializable data object.
 *
 * @param <T> the type t used as the data object
 */
public class WebHook<T> {

    private static final Executor ex = Executors.newCachedThreadPool();

    private final URL url;

    public WebHook(URL url) {
        this.url = url;
    }

    public void deliver(T data) {
        String json = new JSON().serialize(data);
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        ex.execute(() -> {
            try {
                httpClient.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
