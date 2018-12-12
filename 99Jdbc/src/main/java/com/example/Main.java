package com.example;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");

            JdbcDataSource dataSource = new JdbcDataSource();
            Statement statement =connection.createStatement();
            statement.execute("CREATE TABLE Person(id INTEGER, name VARCHAR )");

            statement.execute("INSERT INTO Rerson VALUES (0, 'lily')");
            statement.execute("INSERT INTO Rerson VALUES (1, 'Robin')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");

            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }





    }


}
