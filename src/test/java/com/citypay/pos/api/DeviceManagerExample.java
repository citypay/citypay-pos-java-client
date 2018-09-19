package com.citypay.pos.api;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiException;
import com.citypay.pos.drivers.Device;
import com.citypay.pos.drivers.DeviceDriverRegistry;
import com.citypay.pos.api.DeviceModule;
import com.citypay.pos.drivers.HashMapDeviceManager;
import com.citypay.pos.model.AuthenticationType;
import com.citypay.pos.model.Result;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class DeviceManagerExample {


    @Test
    public void testDeviceManager() throws ApiException, InterruptedException {


        HashMapDeviceManager manager = new HashMapDeviceManager();

        Device device = new Device();
        device.setAddress("192.168.7.169");
        device.setUsername("kinetic_admin");
        device.setPassword("E9aKEkjf");
        device.setAuthenticationType(AuthenticationType.Basic);
        device.setName("k1");
        device.setDriver(DeviceDriverRegistry.getDeviceDriver("Kinetic/IP"));

        manager.save(device);

        DeviceModule module = new DeviceModule(manager);

        CountDownLatch latch = new CountDownLatch(1);
        module.ping("k1", new ApiCallback<Result>() {
            @Override
            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                System.out.println(e);
                latch.countDown();
            }

            @Override
            public void onSuccess(Result result, int statusCode, Map<String, List<String>> responseHeaders) {
                System.out.println(statusCode);
                System.out.println(result);
                latch.countDown();
            }

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
            }

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
            }
        });

        latch.await();

    }

}
