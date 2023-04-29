/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class MyConnection {
    
    static MyConnection instance;
     Connection cnx;
    private MyConnection() 
    {
         try {
            String url="jdbc:mysql://localhost:3306/agroeasydatabase";
            String login="root";
            String pwd="";
            
            cnx=  DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion Etablie ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    public Connection getCnx() {
        return cnx;
    }
      public static MyConnection getInstance()
    {
        if(instance == null)
        {
            instance= new MyConnection();
            
        }
        return instance;
    }

}



