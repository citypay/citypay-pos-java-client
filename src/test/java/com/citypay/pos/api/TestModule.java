package com.citypay.pos.api;

import com.citypay.pos.drivers.Device;
import com.citypay.pos.drivers.HashMapDeviceManager;
import com.citypay.pos.api.Module;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestModule {


    @Test
    public void testKineticBaseAddress() {

        HashMapDeviceManager deviceManager = new HashMapDeviceManager();

        Device device = new Device();
        device.setAddress("127.0.0.1");
        deviceManager.save(device);

        new Module(deviceManager) {

            void test() {

                String address = this.kineticBaseAddress(device);
                assertEquals("https://ip-127-0-0-1.devices.kineticsmart.com:44443/api/v1/", address);

            }

        }.test();

    }

}
