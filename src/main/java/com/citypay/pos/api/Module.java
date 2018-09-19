package com.citypay.pos.api;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiClient;
import com.citypay.invoker.ApiException;
import com.citypay.pos.api.kinetic.DeviceApi;
import com.citypay.pos.api.kinetic.PaymentApi;
import com.citypay.pos.api.remote.DeviceModuleApi;
import com.citypay.pos.api.remote.PaymentModuleApi;
import com.citypay.pos.drivers.Device;
import com.citypay.pos.drivers.DeviceManager;
import com.citypay.pos.model.AuthenticationType;
import com.citypay.pos.drivers.kinetic.KineticApiClient;
import com.citypay.pos.drivers.kinetic.KineticPaymentApi;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.citypay.pos.drivers.CloudDeviceDriver.CLOUD_REMOTE_DEVICE_LABEL;
import static com.citypay.pos.drivers.kinetic.KineticDeviceDriver.KINETIC_DEVICE_LABEL;

/**
 * An abstraction of a module used in the pos api to obtain and use devices.
 */
@SuppressWarnings("WeakerAccess")
public abstract class Module {

    protected final DeviceManager deviceManager;

    protected static final Map<String, List<String>> NIL_MAP = new HashMap<>();

    protected Module(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    protected void preconditionCheck(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected boolean isRemote(Device device) {
        return CLOUD_REMOTE_DEVICE_LABEL.equalsIgnoreCase(device.getDriver().getName());
    }

    protected boolean isKinetic(Device device) {
        return KINETIC_DEVICE_LABEL.equalsIgnoreCase(device.getDriver().getName());
    }


    interface RemoteFn {
        void op(Device device) throws ApiException;
    }

    interface KineticFn {
        void op(Device device) throws ApiException;
    }

    /**
     * Utility method for working with a device based on its device id. It will load the device from a
     * device manager and handle any devices not found
     *
     * @param deviceId the device as stored and referenced in the {@link DeviceManager}
     * @param callback the callback function to retain asynchronicity
     * @param rfn the remote function to call
     * @param kfn the kinetic function to call.
     *
     * Note that this method will require refactoring with the addition of multiple API structures
     * @throws ApiException should any exception occur however the callback should handle failures, see {@link ApiCallback#onFailure(ApiException, int, Map)}
     */
    protected void withDevice(String deviceId,
                              ApiCallback<?> callback,
                              RemoteFn rfn,
                              KineticFn kfn) throws ApiException {
        Device device = deviceManager.find(deviceId);
        if (device == null) {
            callback.onFailure(new ApiException("Device not found"), 404, NIL_MAP);
        } else {

            if (isRemote(device)) {
                rfn.op(device);
            } else if (isKinetic(device)) {
                kfn.op(device);
            } else {
                throw new UnsupportedOperationException(String.format("Device type '%s' not supported", device.getDriver().getName()));
            }

        }
    }


    protected DeviceModuleApi initRemoteDeviceApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        return new DeviceModuleApi(device.getApiClient());
    }

    protected PaymentModuleApi initRemotePaymentApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        return new PaymentModuleApi(device.getApiClient());
    }

    protected String kineticBaseAddress(Device device) {
        return String.format("https://ip-%s.devices.kineticsmart.com:44443/api/v1/", device.getAddress().replaceAll("\\.", "-"));
    }

    private String basicAuthentication(Device device) {
        return "Basic " + Base64.getEncoder().encodeToString(
                String.format("%s:%s", device.getUsername(), device.getPassword()).getBytes());
    }

    protected DeviceApi initKineticDeviceApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        DeviceApi deviceApi = new DeviceApi();
        deviceApi.getApiClient().setBasePath(kineticBaseAddress(device));
        deviceApi.getApiClient().setVerifyingSsl(false); // todo to remove
        deviceApi.getApiClient().setDebugging(true);
        if (device.getAuthenticationType() == AuthenticationType.Basic) {
            deviceApi.getApiClient().addDefaultHeader("Authorization", basicAuthentication(device));
        }
        return deviceApi;
    }

    protected KineticPaymentApi initKineticPaymentApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        KineticPaymentApi paymentApi = new KineticPaymentApi(new KineticApiClient());
        paymentApi.getApiClient().setBasePath(kineticBaseAddress(device));
        paymentApi.getApiClient().setVerifyingSsl(false); // todo to remove
        paymentApi.getApiClient().setDebugging(true);
        if (device.getAuthenticationType() == AuthenticationType.Basic) {
            paymentApi.getApiClient().addDefaultHeader("Authorization", basicAuthentication(device));
        }
        return paymentApi;
    }


}
