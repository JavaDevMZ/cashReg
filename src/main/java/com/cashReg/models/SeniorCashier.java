package com.cashReg.models;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SeniorCashier extends Cashier {


    public SeniorCashier(String username, String password){
        super(username, password);
    }

    public void cancelOrder(){
        order.cancel();
        order=null;
    }

    public void cancelProductInOrder(long id){
        order.remove(id);
    }


    public String xReport(){
        String report = "";
        for(Order order : cashRegister.getOrders()){
          if(order.getCashierId()==cashRegister.getCurrentUser().getId()) {
              report += order.toString() + ":</br>";
          }
        }
        return "X Report for" + getUsername() + ": </br>" + report;

    }

    public String zReport(){
        String report = "";
        Date now = new Date();
        for(Order order : cashRegister.getOrders()){
            LocalDate date = order.getDate();
            if(date!=null&&(date.isAfter(LocalDate.now().minusDays(1)))) {
                report += order.toString() + "</br>";
            }
        }
        return " Z report </br>" + report;
    }
}
