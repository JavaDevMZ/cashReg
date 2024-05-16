package com.cashReg.util;

import com.cashReg.models.HasQuantity;
import com.cashReg.models.Product;
import com.cashReg.util.sql.SQLExecutor;

import java.util.HashMap;
import java.util.Iterator;
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
    private SQLExecutor sqlExecutor = new SQLExecutor();
    private Map<K, V> origin;

    public SQLMap(Map origin){
        this.origin = origin;
    }

    @Override
    public V put(K key, V quantity){
        if(!origin.containsKey(key)) {
            sqlExecutor.insertHasQuantity(key, quantity);
        }else{
            sqlExecutor.updateProductQty(((Product)key).getId(), quantity);
        }
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

   @Override
    public boolean containsKey(Object key){
        return origin.containsKey(key);
   }

   @Override
    public boolean containsValue(Object value){
        return origin.containsValue(value);
   }

   @Override
   public int size(){
        return origin.size();
   }
}
