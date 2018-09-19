package com.citypay.pos.model;

/**
 * Defines the connection type to connect to a device
 */
public enum ConnectionType {

    /**
     * Normally connects via an IP network such as WiFi
     */
    IP,

    /**
     * Connects over a bluetooth connection
     */
    BLUETOOTH,

    /**
     * Connects directly via a cabled connection
     */
    USB

}
