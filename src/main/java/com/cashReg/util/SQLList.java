package com.cashReg.util;

import com.cashReg.dao.SQLExecutor;
import com.cashReg.models.Model;

import java.util.ArrayList;
import java.util.List;

/** This a wrapper for List
 * it synchronizes the database with modification made in the list
 * Stores Products, OrderItems and Users
 * @param <T>
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
}
