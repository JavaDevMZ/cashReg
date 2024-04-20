package com.cashReg.util.dao;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static Properties properties = new Properties();

    static{
        load();
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
    private static void load() {
        try (InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
        properties.load(in);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
        catch(Throwable e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
