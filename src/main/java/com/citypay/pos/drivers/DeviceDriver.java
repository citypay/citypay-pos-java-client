package com.citypay.pos.drivers;

import com.citypay.pos.model.ConnectionType;

public interface DeviceDriver {

    String getName();

    ConnectionType getConnectionType();

}
