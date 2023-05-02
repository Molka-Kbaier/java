/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import edu.esprit.utils.MyConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author USER
 */

public class Terrain {
    
     private int id;
     
     private Culture culture;
     private int numero;   
      private int surface ;
      private String lieu ;
      private String image;
    public Terrain() {
    }

    /* public Terrain(int id, List<Culture> cultures, int numero, int surface, String lieu) {
    this.id = id;
    this.cultures = cultures;
    this.numero = numero;
    this.surface = surface;
    this.lieu = lieu;
    }*/

    public Terrain(int id, Culture culture, int numero, int surface, String lieu, String image) {
        this.id = id;
        this.culture = culture;
        this.numero = numero;
        this.surface = surface;
        this.lieu = lieu;
        this.image = image;
    }

    public Terrain(Culture culture, int numero, int surface, String lieu, String image) {
        this.culture = culture;
        this.numero = numero;
        this.surface = surface;
        this.lieu = lieu;
        this.image = image;
    }

    public Terrain(Culture culture, int numero, int surface, String lieu) {
        this.culture = culture;
        this.numero = numero;
        this.surface = surface;
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

  

    
    public int getId() {
        return id;
    }

   public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Culture getCulture() {
        return culture;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
    }

 

   
    /*public List<Culture> getCultures() {
    return cultures;
    }*/
    /*public List<Culture> getCultures(int terrainId) {
    ArrayList<Culture> cultures = new ArrayList<>();
    
    try {
    
    PreparedStatement ps = cnx.prepareStatement("SELECT * FROM culture WHERE terrain_id = ?");
    ps.setInt(1, this.getId());
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
    Culture c = new Culture();
    c.setId(rs.getInt("id"));
    c.setType(rs.getString("type"));
    c.setDate_planting(rs.getDate("date_planting"));
    c.setQuantite(rs.getFloat("quantite"));
    cultures.add(c);
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
    return cultures;
    }*/
/* public void setCultures(List<Culture> cultures) {
this.cultures = cultures;
}*/

    @Override
    public String toString() {
        return "Terrain{" + "id=" + id + ", culture=" + culture + ", numero=" + numero + ", surface=" + surface + ", lieu=" + lieu + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.culture);
        hash = 67 * hash + this.numero;
        hash = 67 * hash + this.surface;
        hash = 67 * hash + Objects.hashCode(this.lieu);
        hash = 67 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Terrain other = (Terrain) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.surface != other.surface) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.culture, other.culture)) {
            return false;
        }
        return true;
    }


  


  
  

   
}