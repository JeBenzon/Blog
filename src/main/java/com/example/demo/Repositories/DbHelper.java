package com.example.demo.Repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbHelper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Connection connection;

    public Connection createConnection() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        log.info("Closing connection...");

        try {
            connection.close();

            log.info("MySQL connection closed...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
