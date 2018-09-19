package com.citypay.pos.drivers;

import com.citypay.pos.drivers.kinetic.KineticDeviceDriver;
import com.citypay.pos.model.ConnectionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Contains a registry of {@link DeviceDriver}s that are configured within the API
 */
public class DeviceDriverRegistry {

    private static Map<String, DeviceDriver> map = new HashMap<>();

    static {
        map.put("CityPay/Cloud", new CloudDeviceDriver(ConnectionType.IP));
        map.put("Kinetic/IP", new KineticDeviceDriver(ConnectionType.IP));
        map.put("Kinetic/BT", new KineticDeviceDriver(ConnectionType.BLUETOOTH));
    }

    public static DeviceDriver getDeviceDriver(String name) {
        return map.get(name);
    }

    public static Set<String> getAllDriverNames() {
        return new TreeSet<>(map.keySet());
    }

}
