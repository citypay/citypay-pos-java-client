package com.citypay.pos.drivers;

import com.citypay.pos.model.ConnectionType;

public class CloudDeviceDriver extends BaseDeviceDriver {

    public static final String CLOUD_REMOTE_DEVICE_LABEL = "remote";

    public CloudDeviceDriver(ConnectionType connectionType) {
        super(CLOUD_REMOTE_DEVICE_LABEL, connectionType);
    }

}
