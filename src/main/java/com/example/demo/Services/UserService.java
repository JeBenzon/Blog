package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    UserRepository up = new UserRepository();

public User checkUser(User user) {
    return up.login(user.getUsername(), user.getPassword());
}


}
