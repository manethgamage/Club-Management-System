package com.example.oopp;

import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String databaseName = "ood";
    private static final String databaseUser = "root";
    private static final String databasePassword = "1234";
    private static final String url = "jdbc:mysql://localhost/"+databaseName;


    private Database(){

    }

    public static Connection getDBConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        try{
            connection = DriverManager.getConnection(url, databaseUser, databasePassword);
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
