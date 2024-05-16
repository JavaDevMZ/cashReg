package com.cashReg.util;

import java.util.ArrayList;
import java.util.List;

public class UncaughtExceptionPrinter implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Throwable current = e;
        while(current!=null){
            System.out.println(current);
            current = current.getCause();
        }
        for(StackTraceElement ste : e.getStackTrace()){
            System.out.println(ste.getFileName()+ ste.getLineNumber());
        }
    }
}
