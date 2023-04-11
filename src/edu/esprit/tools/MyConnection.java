/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author louaj
 */
public class MyConnection {
      private static MyConnection instance;

    public String url="jdbc:mysql://localhost:3306/agroeasydatabase";
    public String login="root";
    public String pwd="";
    Connection cnx;
    
    public MyConnection() {
        try {
           cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("connection etablie!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
         public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
                         
     public Connection getCnx(){
         return cnx;
     }
        
        
    
    
}
