package com.citypay.pos.drivers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple hash map device manager used for testing
 */
public class HashMapDeviceManager implements DeviceManager {

    private static Map<String, Device> map = new HashMap<>();

    @Override
    public List<Device> list() {
        return new LinkedList<>(map.values());
    }

    @Override
    public Device find(String name) {
        return map.get(name);
    }

    @Override
    public Device save(Device device) {
        return map.put(device.getName(), device);
    }

    @Override
    public Device delete(String name) {
        Device device = find(name);
        if (device != null) {
            map.remove(name);
        }
        return device;
    }
}
