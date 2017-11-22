/*
 * CityPay POS API
 * CityPay Point of Sale API for payment with card present devices including EMV readers and contactless POS readers.  The API makes it simple to add EMV and contactless card acceptance to iOS, Android, Tablet and desktop applicaitons using a HTTPS protocol. It segregates the complexity of payment processing from the sales environment and eliminates any necessity for the target system to handle card data. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: dev@citypay.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.DeviceInfo;
import io.swagger.client.model.Result;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DeviceModuleApi
 */
@Ignore
public class DeviceModuleApiTest {

    private final DeviceModuleApi api = new DeviceModuleApi();

    
    /**
     * Device Information
     *
     * Obtains information regarding the device and to review the current status of a device such as the battery charge, serial number and device type. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deviceInfoTest() throws ApiException {
        String deviceId = null;
        DeviceInfo response = api.deviceInfo(deviceId);

        // TODO: test validations
    }
    
    /**
     * Device Ping
     *
     * To monitor or to check whether a device is available, the host can send a simple &#x60;GET&#x60; request to the URL at &#x60;/device/{deviceId}/ping&#x60; to see if the device is responding and available. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void pingTest() throws ApiException {
        String deviceId = null;
        Result response = api.ping(deviceId);

        // TODO: test validations
    }
    
}