package com.citypay.pos.api;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiException;
import com.citypay.pos.drivers.DeviceManager;
import com.citypay.pos.model.DeviceInfo;
import com.citypay.pos.model.Result;

import java.util.Map;

import static com.citypay.pos.drivers.kinetic.KineticDeviceDriver.AdaptDeviceInfo;
import static com.citypay.pos.drivers.kinetic.KineticDeviceDriver.AdaptPingResponse;

/**
 * Java device module for working with devices programmatically. The {@link DeviceModule} class only supports
 * asynchronous API calls.
 * <p>
 * The device module will require an initialised {@link DeviceManager} and will check for valid devices on method calls.
 *
 */
public class DeviceModule extends Module {

    public DeviceModule(DeviceManager deviceManager) {
        super(deviceManager);
    }

    public final void deviceInformation(String deviceId, final ApiCallback<DeviceInfo> callback) throws ApiException {
        withDevice(deviceId, callback,
                (remote) -> initRemoteDeviceApi(remote).deviceInfoAsync(deviceId, callback),
                (kinetic) -> initKineticDeviceApi(kinetic).infoJsonGetAsync(AdaptDeviceInfo(callback))
        );
    }

    /**
     * Device Ping
     * To monitor or to check whether a device is available
     *
     * @param deviceId The name of the target device used by the API.
     * @param callback the callback function containing the result
     * @throws ApiException should any exception occur however the callback should handle failures, see {@link ApiCallback#onFailure(ApiException, int, Map)}
     */
    public final void ping(String deviceId, final ApiCallback<Result> callback) throws ApiException {
        withDevice(deviceId, callback,
                (remote) -> initRemoteDeviceApi(remote).pingAsync(deviceId, callback),
                (kinetic) -> initKineticDeviceApi(kinetic).pingJsonGetAsync(AdaptPingResponse(callback))
        );
    }

}
