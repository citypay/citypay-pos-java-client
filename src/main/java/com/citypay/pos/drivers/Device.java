package com.citypay.pos.drivers;

import com.citypay.invoker.ApiClient;
import com.citypay.pos.model.AuthenticationType;
import com.citypay.pos.model.ConnectionType;

import java.io.Serializable;
import java.util.Base64;

/**
 * Defines a device for use in the pos Api
 */
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private DeviceDriver driver;
    private String address;
    private String username;
    private String password;
    private long lastSaved;
    private AuthenticationType authenticationType;
    private ApiClient apiClient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConnectionType getConnectionType() {
        return driver.getConnectionType();
    }

    public DeviceDriver getDriver() {
        return driver;
    }

    public void setDriver(DeviceDriver driver) {
        this.driver = driver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getLastSaved() {
        return lastSaved;
    }

    public void setLastSaved(long lastSaved) {
        this.lastSaved = lastSaved;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApiClient getApiClient() {
        if (apiClient == null) {
            ApiClient client = new ApiClient();
            if (getAuthenticationType() == AuthenticationType.Basic) {
                client.addDefaultHeader("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", getUsername(), getPassword()).getBytes())
                );
            }
            client.setBasePath(getAddress());
            client.setVerifyingSsl(false);
            apiClient = client;
        }

        return apiClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (!name.equals(device.name)) return false;
        if (!driver.equals(device.driver)) return false;
        return address.equals(device.address);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + driver.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
