package com.example.demo.Services;

import com.example.demo.Models.Blogopslag;
import com.example.demo.Repositories.BlogopslagRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


@Service
public class BlogopslagService {

    private BlogopslagRepository BR = new BlogopslagRepository();

    public ArrayList<Blogopslag> blogopslagList(){
        BR.blogopslagList();
        ArrayList<Blogopslag> test = BR.blogopslagList();
        System.out.println(test.size());
        return test;
    }

    public void CreateOpslag(Blogopslag opslag) {
        int id = -1;
        BR.CreateAndUpdateOpslag(id, opslag.getTitel(), opslag.getContent());
    }

    public void UpdateOpslag(Blogopslag opslag) {
        BR.CreateAndUpdateOpslag(opslag.getId(),opslag.getTitel(),opslag.getContent());
    }

    public void DeleteOpslag(int id) {
        BR.DeleteOpslag(id);
    }

    public Blogopslag SelectOpslag(int id) {
        return BR.SelectOpslag(id);
    }
}
