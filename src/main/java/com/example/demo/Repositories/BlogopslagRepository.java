package com.example.demo.Repositories;

import com.example.demo.Models.Blogopslag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;


public class BlogopslagRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public ArrayList<Blogopslag> blogopslagList() {
        log.info("GetOpslagList");

        ArrayList<Blogopslag> blogopslag = new ArrayList<Blogopslag>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from blogopslag;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Blogopslag bo = new Blogopslag();
                bo.setId(resultSet.getInt(1));
                bo.setTitel(resultSet.getString(2));
                bo.setContent(resultSet.getString(3));
                blogopslag.add(bo);
            };

            return blogopslag;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void CreateAndUpdateOpslag(int id, String titel, String content) {
        String SQLString = null;
        //Tjekker om der er kaldt en Create eller en Update
        if(id>0){
            log.info("Updating");
            SQLString = "UPDATE blogopslag SET titel = ?, content = ? WHERE id = ?;";
        }
        if(id==-1) {
            log.info("Creating");
            SQLString = "INSERT INTO blogopslag (titel, content) VALUES(?, ?)";
        }

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");
            PreparedStatement pstms = null;
            //kalder connect() metoden.
            log.info("Connection establised");
            //forbereder sql statement, og putter min SQLstring ind.
            pstms = con.prepareStatement(SQLString);
            log.info("Statement Prepared");
            //sætter de forskelige værdier i min forberedte SQLstring.
            pstms.setString(1, titel);
            pstms.setString(2, content);
            if(id>0){pstms.setInt(3, id);}
            log.info("String Prepared");
            //Sender kald til Database om at updatere/oprette et nyt element.
            int opret = pstms.executeUpdate();

        } catch (Exception E) {
            log.error("Kunne ikke lave en QueryConnection til Create/Update");
        }
    }

    public void DeleteOpslag(int id) {

        PreparedStatement pstmt = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");
            Class.forName("com.mysql.cj.jdbc.Driver");

            pstmt = con.prepareStatement("DELETE FROM blogopslag WHERE id = ?");

            log.info("statement prepared");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            log.info("opslag med id: " + id + " er blevet slettet");
        } catch (SQLException e) {
            log.error("Kunne ikke Delete en movie");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Blogopslag SelectOpslag(int id) {
        Blogopslag blogopslag = new Blogopslag();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://blogdb.cvcn8khmodul.eu-central-1.rds.amazonaws.com:3306/Blog", "admin", "Pcu64ayh");
            pstmt = con.prepareStatement("SELECT * FROM blogopslag Where id = ?");
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                blogopslag.setId(rs.getInt(1));
                blogopslag.setTitel(rs.getString(2));
                blogopslag.setContent(rs.getString(3));

            }
        } catch (SQLException e) {
            log.error("Kunne ikke lave en QueryConnection");
        }
        finally {
            try { rs.close(); } catch (Exception e) {}
            try { pstmt.close(); } catch (Exception e) {}

        }
        return blogopslag;
    }


}
