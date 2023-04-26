/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class MyConnection {
    private Connection cnx;
    
    private static MyConnection instance;
    
    private final String url= "jdbc:mysql://localhost:3306/agroeasydatabase";
    private final String user = "root" ;
    private final String password="";
    
     public MyConnection(){
        try {
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("Connexion !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MyConnection getInstance(){
    if(instance == null)
            instance = new MyConnection();
        return instance;
    
    }

  
   
    public Connection getCnx(){
    return cnx;
    }
    
}
