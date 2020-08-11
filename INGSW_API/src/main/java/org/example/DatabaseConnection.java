package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DatabaseConnection {

    private String URLDB = "";
    private Connection conn;
    private Statement stmt;

    public DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URLDB);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public ResultSet eseguiQuery(String query){
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public int updateEntries(String query){
        int result = 0;
        try {
            result = stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
