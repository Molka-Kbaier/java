/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import connection.MyConnection;
import entité.Evenement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 *
 * @author ASUS
 */
public class EvenementService implements IService{
    Connection cnx = MyConnection.getInstance().getCnx();
     

    public void ajouter(Evenement event) {
        Evenement e = event;
        try {
            String sql = "insert into evenement(titre,lieu,image)values ('"+event.getTitre()+"','"+event.getLieu()+"','"+event.getImage()+"')";  
            PreparedStatement ste =  cnx.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("Votre evenement est ajouté avec succés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
      
        try {
            String sql = "DELETE FROM evenement WHERE id='"+id+"'";
            PreparedStatement ste = cnx.prepareStatement(sql);
            int rowsAffected = ste.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun enregistrement supprimé dans la base de données.");
            } else {
                System.out.println("Enregistrement supprimé avec succès de la base de données.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'événement: " + ex.getMessage());
        }
    }

    public void modifier(Evenement e) {
        System.out.println(e.getId());
        try {
            String sqlup = "UPDATE `evenement` SET `titre`=?, `lieu`=?, `image`=?WHERE `id`='"+e.getId()+"'";
            PreparedStatement ste = cnx.prepareStatement(sqlup);
            ste.setString(1, e.getTitre());
            ste.setString(2, e.getLieu());
            ste.setString(3, e.getImage());
            ste.executeUpdate();
            int rowsAffected = ste.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun enregistrement mis à jour dans la base de données.");
            } else {
                System.out.println("Enregistrement mis à jour avec succès dans la base de données.");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de l'événement: " + ex.getMessage());
        }
    }

    @Override
    public List getAll() {
    List<Evenement> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM evenement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Evenement ev = new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            list.add(ev);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return null; // or return an empty list: return new ArrayList<>();
    }
    return list;
}
    

        /**
         *
         * @param id
         * @return
         * @throws SQLException
         */
       @Override
     public Evenement getOneById(int id) {
    
        Evenement e = new Evenement();
        try {
            String requete = "select * from evenement where id='" + id + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                e.setTitre(rs.getString("titre"));
                e.setLieu(rs.getString("lieu"));
                e.setImage(rs.getString("image"));

                System.out.println(e.getTitre());
            }

        } catch (SQLException ex) {

        }

        return e;
    
      }

    @Override
    public void ajouter(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    public Image loadImage(String path) {
    return new Image(new File(path).toURI().toString());
}
    
    public void genererPDF(Evenement evenement) {
        try {
            // Créer un nouveau document PDF
            Document document = new Document();
            OutputStream file = new FileOutputStream(new File("C:/Users/ASUS/Desktop/pdf_uploading/event2.pdf"));

            PdfWriter.getInstance(document, file);
            document.open();
            // Ajouter le titre de l'événement
            Paragraph p = new Paragraph("Titre: " + evenement.getTitre());
            p.setAlignment(Element.ALIGN_CENTER);
            Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            p.setFont(font);
            document.add(p);
            document.add(new Paragraph("Lieu: " + evenement.getLieu()));

            // Fermer le document et écrire le contenu dans un fichier PDF
            document.close();

            // Afficher un message de confirmation
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("PDF généré");
            alert.setHeaderText(null);
            alert.setContentText("Le PDF a été généré avec succès.");
            alert.showAndWait();
        } catch (Exception ex) {
            // Afficher un message d'erreur en cas de problème
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la génération du PDF: " + ex.getMessage());
            alert.showAndWait();
        }
    }
}

  
 






