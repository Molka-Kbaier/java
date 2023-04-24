/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;


import java.util.Collection;
import java.util.Date;

/**
 *
 * @author louaj
 */
public class Comptabilite {
    private int id;
    private Date date_comptabilite;
    private float valeur;
     private Collection facture;

    public Comptabilite(int id, Date date, float valeur, Collection facture) {
        this.id = id;
        this.date_comptabilite = date;
        this.valeur = valeur;
        this.facture = facture;
    }

    public Comptabilite(Date date, float valeur, Collection facture) {
        this.date_comptabilite = date;
        this.valeur = valeur;
        this.facture = facture;
    }

    public Comptabilite(Date date, float valeur) {
        this.date_comptabilite = date;
        this.valeur = valeur;
    }

    public Comptabilite(int id) {
        this.id = id;
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
        return date_comptabilite;
    }
    

    public float getValeur() {
        return valeur;
    }

    

    public void setDate_comptabilite(Date date) {
        this.date_comptabilite = date;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }
  public void setFacture(Collection facture) {
        this.facture = facture;
    }

    public Collection getFacture() {
        return facture;
    }

    @Override
    public String toString() {
        return "Comptabilite{" + "id=" + id + ", date=" + date_comptabilite + ", valeur=" + valeur + ", facture=" + facture + '}';
    }
   
    
   
    
}
