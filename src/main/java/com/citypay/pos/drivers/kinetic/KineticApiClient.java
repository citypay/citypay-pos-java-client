package com.citypay.pos.drivers.kinetic;

import com.citypay.invoker.ApiCallback;
import com.citypay.invoker.ApiClient;
import com.squareup.okhttp.Call;

import java.lang.reflect.Type;

public class KineticApiClient extends ApiClient {

    public KineticApiClient() {
        super();
    }

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public KineticApiClient setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public <T> void executeAsync(Call call, Type returnType, ApiCallback<T> callback) {
        if (KineticTransactionResult.class.getName().equals(returnType.getTypeName())) {
            setJSON(new KineticJSON(getIdentifier()));
        }
        super.executeAsync(call, returnType, callback);
    }
}
