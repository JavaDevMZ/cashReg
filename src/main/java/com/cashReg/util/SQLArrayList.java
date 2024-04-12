package com.cashReg.util;

import com.cashReg.dao.SQLExecutor;

import java.util.ArrayList;
import java.util.List;

public class SQLList<T> extends ArrayList {

    private List<T> origin;
    private SQLExecutor = new SQLExecutor();

    public SQLList(List<T> origin){
        this.origin = origin;
    }

    @Override
    public boolean add(T object){
        boolean result = origin.add(object);

    }

}
