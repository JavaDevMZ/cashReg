package com.cashReg.util;

import com.cashReg.models.HasQuantity;
import com.cashReg.util.sql.SQLExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * A Map wrapper
 * All the changes are up-to-date with the DB
 * @param <K> Product, not OrderItem, because only an order, not written to the database(non-closed)
 *          can be modified
 * @param <V> Long, the quantity
 */
public class SQLMap<K extends HasQuantity, V extends Long> extends HashMap<K, V> {
    private SQLExecutor sqlExecutor;
    private Map<K, V> origin;

    public SQLMap(Map origin){
        this.origin = origin;
    }

    @Override
    public V put(K key, V quantity){
      sqlExecutor.insertHasQuantity(key, quantity);
      return origin.put(key, quantity);
    }

    @Override
    public V get(Object key) {
        return origin.get(key);
    }

    @Override
    public Set<K> keySet(){
        return origin.keySet();
    }

    @Override
    public Set<Entry<K, V>> entrySet(){
        return origin.entrySet();
    }

    @Override
    public void forEach(BiConsumer action){
        origin.forEach(action);
    }
}
