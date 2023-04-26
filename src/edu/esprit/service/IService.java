/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.service;

import edu.esprit.entity.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface IService<user> {
    public void ajouter(user u) throws SQLException;
    public void supprimer(Utilisateur user) throws SQLException;
    public void modifier(user u) throws SQLException;
    public List<user> getAll() throws SQLException;
    public user getOneById(int id) throws SQLException;
    
   
    
    
}
