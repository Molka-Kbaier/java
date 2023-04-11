/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;


import java.util.Date;

/**
 *
 * @author louaj
 */
public class Comptabilite {
    private int id;
    private Date date;
    private float valeur;

    

    public Comptabilite(int id, Date date_comptabilite, float valeur) {
        this.id = id;
        this.date = date;
        this.valeur = valeur;
    }

    public Comptabilite(Date date, float valeur) {
        this.date = date;
        this.valeur = valeur;
    }

    public Comptabilite() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Date getDate_comptabilite() {
        return date;
    }
    

    public float getValeur() {
        return valeur;
    }

    

    public void setDate_comptabilite(Date date) {
        this.date = date;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Comptabilite{" + "id=" + id + ", date=" + date + ", montant=" + valeur + '}';
    }
    
   
    
}
