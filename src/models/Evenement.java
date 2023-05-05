package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;
import javafx.scene.image.Image;

public class Evenement {
    private int id;
    private String titre;
    private String lieu;
    private String image;
    private Image imageFile;

    public Evenement() {
    }

    public Evenement(String titre, String lieu, String image, Image imageFile) {
        this.titre = titre;
        this.lieu = lieu;
        this.image = image;
        this.imageFile = imageFile;
    }

    public Evenement(int id, String titre, String lieu, String image, Image imageFile) {
        this.id = id;
        this.titre = titre;
        this.lieu = lieu;
        this.image = image;
        this.imageFile = imageFile;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Image getImageFile() {
        return imageFile;
    }

    public void setImageFile(Image imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.titre);
        hash = 97 * hash + Objects.hashCode(this.lieu);
        hash = 97 * hash + Objects.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.imageFile);
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.imageFile, other.imageFile)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", lieu=" + lieu + ", image=" + image + ", imageFile=" + imageFile + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement(int id, String titre, String lieu, String image) {
        this.id = id;
        this.titre = titre;
        this.lieu = lieu;
        this.image = image;
    }






}