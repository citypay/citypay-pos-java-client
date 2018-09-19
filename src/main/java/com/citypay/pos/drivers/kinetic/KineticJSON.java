package com.citypay.pos.drivers.kinetic;

import com.citypay.invoker.JSON;
import com.citypay.pos.model.kinetic.TransactionData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class KineticJSON extends JSON {

    private final String identifier;

    public KineticJSON(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T> T deserialize(String body, Type returnType) {

        if (KineticTransactionResult.class.getName().equals(returnType.getTypeName())) {

            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(body).getAsJsonObject();
            JsonElement element = root.get(identifier);

            KineticTransactionResult result = getGson().fromJson(root, new TypeToken<KineticTransactionResult>() {}.getType());

            if (element != null) {
                TransactionData data = getGson().fromJson(element, new TypeToken<TransactionData>() {}.getType());
                result.setData(data);
            }
            return (T)result;
        } else {
            return super.deserialize(body, returnType);
        }


    }
}
