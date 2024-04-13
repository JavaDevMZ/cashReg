package com.cashReg.models;

import com.cashReg.CashRegister;
import com.cashReg.Warehouse;
import com.cashReg.dao.SQLExecutor;

public abstract class Model {
    protected long id;
    protected SQLExecutor sqlExecutor = new SQLExecutor();
    protected CashRegister cashRegister = CashRegister.getInstance();
    protected Warehouse warehouse = Warehouse.getInstance();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
