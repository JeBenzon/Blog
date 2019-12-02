package com.example.demo;

import com.example.demo.Repositories.UserRepository;

public class Test {
    public static void main(String[] args) {
        UserRepository UP = new UserRepository();


            System.out.println(UP.login("admin","123").getUsername());


    }
}
