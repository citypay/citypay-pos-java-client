package com.citypay.pos.drivers.kinetic;

import com.citypay.pos.model.kinetic.TransactionData;
import com.citypay.pos.model.kinetic.TransactionResult;

public class KineticTransactionResult extends TransactionResult {

    private TransactionData data;

    public TransactionData getData() {
        return data;
    }

    public void setData(TransactionData data) {
        this.data = data;
    }
}
