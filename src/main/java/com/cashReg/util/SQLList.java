package com.cashReg.util;

import com.cashReg.util.sql.SQLExecutor;
import com.cashReg.models.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/** This a wrapper for List
 * it synchronizes the database with modification made in the list
 * @param <T> User or Order
 */
public class SQLList<T extends Model> extends ArrayList<T> {

    private List<T> origin;
    private SQLExecutor sqlExecutor = new SQLExecutor();

    public SQLList(List<T> origin){
        this.origin = origin;
    }

    @Override
    public boolean add(T object){
        sqlExecutor.insertModel(object);
        return origin.add(object);
    }

    @Override
    public boolean contains(Object object){
        return origin.contains(object);
    }

    public T getById(long id){
      T result = null;
        for(T el : origin){
          if(el.getId()==id){
              result = el;
              break;
          }
        }
        return result;
    }

    public T removeById(long productId){
        for(int i = 0; i < origin.size(); i++){
            if(origin.get(i).getId()==productId){
               return remove(i);
            }
        }
        return null;
    }

    @Override
    public T remove(int index){
       return origin.remove(index);
    }

    @Override
    public Iterator<T> iterator(){
        return origin.iterator();
    }

    @Override
    public int size(){
        return origin.size();
    }

    @Override
    public void forEach(Consumer action){
        origin.forEach(action);
    }
}
