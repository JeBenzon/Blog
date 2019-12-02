package com.example.demo.Repositories;

import com.example.demo.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.*;


@Repository("UserRepository")
public class UserRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User login(String username, String password) {
        log.info("Find User by username and password");

        User user = new User();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Users where username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setUsertype(resultSet.getString(4));
            };

            if(user.getUsername() == null) {
                log.info("user does not excists");
            }
            log.info("User Found" + user.getUsername() + user.getPassword());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
