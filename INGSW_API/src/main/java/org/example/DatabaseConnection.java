package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DatabaseConnection {

    private String URLDB = "jdbc:mysql://ingswdatabase.czrrnx3ltups.eu-west-1.rds.amazonaws.com:3306/ingsw?user=admin&password=6x1li30di3IoU2Mgwaoy";
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
    public ResultSet eseguiQuery(String query) throws SQLException{
        ResultSet resultSet = null;
        resultSet = stmt.executeQuery(query);
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
