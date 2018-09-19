package com.citypay.pos.drivers;

import java.util.List;

/**
 * Specifies an interface for interacting with a manager for device storage
 */
public interface DeviceManager {

    /**
     * @return a list of all stored devices
     */
    List<Device> list();

    /**
     * Obtains a device by name
     *
     * @param name the name of a device, case sensitive
     * @return a device or null if not found
     */
    Device find(String name);

    /**
     * Stores a device
     *
     * @param device the device that is to be saved
     * @return the device instance
     */
    Device save(Device device);

    /**
     * Removes a device with the given name
     *
     * @param name the name of a device
     * @return the device deleted or null if not found
     */
    Device delete(String name);

}
