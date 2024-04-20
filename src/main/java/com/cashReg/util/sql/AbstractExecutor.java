package com.cashReg.util.sql;

import com.cashReg.util.dao.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractExecutor {

    protected final static String SELECT_ALL = "SELECT * FROM ";
    protected final static String UPDATE = "UPDATE \"%s\" SET(%s) WHERE %s";
    protected final static String INSERT = "INSERT INTO %s VALUES(%s)";

    protected static Connection connection;

    static {
        try {
            connection = DataSource.getConnection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * Executes a query which returns a selection
     * @param query
     * @return the selection retrieved
     * @throws SQLException
     */
    public ResultSet executeSelect(String query) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    /**
     * Executes any non-select query
     * returns what has been inserted
     * @param query
     * @return the rows inserted
     * @throws SQLException
     */
    public ResultSet execute(String query) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(query);
        statement.execute();
        return statement.getGeneratedKeys();
    }
}
