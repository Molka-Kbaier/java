/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author ASUS
 * @param <evenement>
 */
public interface IService<objet> {
    public void ajouter(objet e);
    public void supprimer(int id);
    public void modifier(objet e);
    public List<objet> getAll();
    public objet getOneById(int id);
    
    
}
