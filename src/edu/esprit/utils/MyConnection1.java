/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class MyConnection1 {
    public static MyConnection1 instance;
     private final String LOGIN = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/agroeasydatabase";
    
Connection cnx;
    private MyConnection1() {
         try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
             System.out.println("connexion etablie");
                     } catch (SQLException ex) {
             System.err.println(ex.getMessage());   //pour afficher le msg d'exception
         }
    }
   public static MyConnection1 getInstance() {
        if(instance == null)
            instance = new MyConnection1();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }  
    
}
