package com.citypay.pos.drivers;

import com.citypay.pos.model.ConnectionType;

public abstract class BaseDeviceDriver implements DeviceDriver {

    private final String name;
    private final ConnectionType connectionType;

    public BaseDeviceDriver(String name, ConnectionType connectionType) {
        this.name = name;
        this.connectionType = connectionType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ConnectionType getConnectionType() {
        return connectionType;
    }

}
