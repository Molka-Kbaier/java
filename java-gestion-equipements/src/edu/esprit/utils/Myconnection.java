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
 * @author amine
 */
public class Myconnection {
    private static Myconnection instance ;
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/agroeasydatabase";
    Connection cnx;    
    public Myconnection() {
              try {
                  cnx = DriverManager.getConnection(URL, USER, PWD);
                  System.out.println("connexion établie");    
                          } catch (SQLException ex) {
                 System.err.println(ex.getMessage()); //pour affiche le msg d'exepction
              }
    }
 public static Myconnection getInstance() {
        if(instance == null)
            instance = new Myconnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

   
}
