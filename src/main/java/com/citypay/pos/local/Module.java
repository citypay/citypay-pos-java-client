package com.citypay.pos.local;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiClient;
import com.citypay.invoker.ApiException;
import com.citypay.invoker.Configuration;
import com.citypay.pos.api.kinetic.DeviceApi;
import com.citypay.pos.api.kinetic.PaymentApi;
import com.citypay.pos.api.remote.DeviceModuleApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.citypay.pos.drivers.CloudDeviceDriver.CLOUD_REMOTE_DEVICE_LABEL;
import static com.citypay.pos.drivers.KineticDeviceDriver.KINETIC_DEVICE_LABEL;

/**
 * An abstraction of a module used in the pos api to obtain and use devices.
 */
public abstract class Module {

    protected final DeviceManager deviceManager;

    protected static final Map<String, List<String>> NIL_MAP = new HashMap<>();

    Module(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    void preconditionCheck(Object obj, String message) {
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
     * @throws ApiException
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



//    void preconditionCheckListener(Response.Listener<?> listener) {
//        preconditionCheck(listener, "Response listener not provided");
//    }
//
//    private void preconditionCheckErrorListener(Response.ErrorListener listener) {
//        preconditionCheck(listener, "Response listener not provided");
//    }

    protected DeviceModuleApi initPosApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        return new DeviceModuleApi(device.getApiClient());
    }

    protected DeviceApi initKineticDeviceApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        DeviceApi api = new DeviceApi(device.getApiClient());
        return api;
    }

    protected PaymentApi initKineticPaymentApi(Device device) {
        preconditionCheck(device, "Device cannot be null");
        PaymentApi paymentApi = new PaymentApi();
        paymentApi.getApiClient().setBasePath(String.format("https://ip-%s.devices.kineticsmart.com:44443/api/v1", device.getAddress().replaceAll(".", "-")));
        if (device.getAuthenticationType() == AuthenticationType.Basic) {
//            paymentApi.getApiClient().addDefaultHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
//                    String.format("%s:%s", device.getUsername(), device.getPassword()).getBytes())
//            );
            paymentApi.getApiClient().setUsername(device.getUsername());
            paymentApi.getApiClient().setPassword(device.getPassword());
        }

        return paymentApi;
    }


//
//    protected void handle(final Response.ErrorListener errorListener, String str, Object... args) {
//        errorListener.onErrorResponse(new VolleyError(String.format(str, args)));
//    }
//
//    /**
//     * Utility method for actioning against a device id and actioning a driver action. Should a driver not be found
//     * or is unsupported by the driver, the method will provide error handling internally
//     *
//     * @param deviceId      the device id to run the action against
//     * @param errorListener an error listener to push failures to
//     * @param actions       a {@link DriverActionBuilder} who's actions will be run
//     */
//    void _withDevice(final String deviceId,
////                     final Response.ErrorListener errorListener,
//                     final DriverActionBuilder actions) {
//
////        if (deviceId == null || deviceId.trim().length() == 0) {
////            handle(errorListener, "Invalid deviceId");
////            return;
////        }
//
//
//
//        Device device = deviceManager.find(deviceId);
//        if (device == null) {
//            errorListener.onErrorResponse(new VolleyError("Device not found"));
//        } else {
//            if (actions != null && actions.size() > 0) {
//                for (Map.Entry<String, Consumer<Device>> actionEntry : actions.entrySet()) {
//                    if (device.getDriver() != null && actionEntry.getKey().equals(device.getDriver().getName())) {
//                        actionEntry.getValue().accept(device);
//                        return;
//                    }
//                }
//            } else {
//                handle(errorListener, "No actions available for device '%s'", deviceId);
//            }
//            handle(errorListener, "\"%s\" Invalid device driver or action not supported", deviceId);
//        }
//    }

}
