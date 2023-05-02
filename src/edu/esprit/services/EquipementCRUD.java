/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Employe;
import edu.esprit.entities.Equipement;
import edu.esprit.utils.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class EquipementCRUD {

  Connection cnx = Myconnection.getInstance().getCnx();
 public int isDisponible(int id) {
        try {
            String req = "SELECT `disponnible` FROM `Equipement` WHERE `id` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int disponible = rs.getInt("disponnible");
                return disponible;
            } else {
                return -1; // or any other value to indicate that the equipment with the given id doesn't exist
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1; // or any other value to indicate an error occurred
        }
    }
  public void ajouterEquipement(Equipement e){


try {
           String req = "INSERT INTO `Equipement` ( `employe_id`,`type`, `marque`, `disponnible`, `etat`, `matricule`) VALUES ('" + e.getId() + "', '"+e.getEmploye_().getId()+ "', '"  + e.getType()+ "', '"  +e.getMarque()+ "', '" +e.isDisponnible()+ "', '" +e.getEtat()+ "', '" +e.getMatricule()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Equipement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  /* public void ajouterEquipement2(Equipement e) {
  try {
  String req = "INSERT INTO `Equipement` (`id`, `employe_id`,type`, `marque`, `disponnible`, `etat`, `matricule`) VALUES (?, ?, ?, ?, ?, ?, ?)";
  PreparedStatement ps = cnx.prepareStatement(req);
  ps.setInt(1, e.getId());
  ps.setInt(2, e.getEmploye_().getId());
  ps.setString(3, e.getType());
  ps.setString(4, e.getMarque());
  // ps.setBoolean(5, e.isDisponnible());
  ps.setInt(5, e.isDisponnible() );
  ps.setString(6, e.getEtat());
  ps.setString(7, e.getMatricule());
  ps.executeUpdate();
  System.out.println("votre equipement est ajouté");
  } catch (SQLException ex) {
  System.out.println(ex.getMessage());}}*/
  public void ajouterEquipement3(Equipement e) {
try {
String req = "INSERT INTO Equipement ( employe_id, type, marque, disponnible, etat, matricule) VALUES (?, ?, ?, ?, ?, ?)";
PreparedStatement ps = cnx.prepareStatement(req);
//ps.setInt(1, e.getId());
ps.setInt(1, e.getEmploye_().getId());
ps.setString(2, e.getType());
ps.setString(3, e.getMarque());
ps.setInt(4, e.isDisponnible());
ps.setString(5, e.getEtat());
ps.setString(6, e.getMatricule());
ps.executeUpdate();
System.out.println("Equipement ajouté !");
} catch (SQLIntegrityConstraintViolationException ex) {
//System.out.println("Impossible de créer l'équipement : clé primaire dupliquée.");
System.out.println("Impossible de créer l'équipement :cet employé est liée à un ou plusieurs equipements . veillez choisir un autre employé.");
} catch (SQLException ex) {
System.out.println("Une erreur s'est produite lors de la création de l'équipement : " + ex.getMessage());
}
}
  public void ajouterEquipement2(Equipement e) {
        try {
            String req = "INSERT INTO `Equipement` ( `employe_id`, `type`, `marque`, `disponnible`, `etat`, `matricule`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, e.getId());
            ps.setInt(1, e.getEmploye_().getId());
            ps.setString(2, e.getType());
            ps.setString(3, e.getMarque());
            ps.setInt(4, e.isDisponnible());
            ps.setString(5, e.getEtat());
            ps.setString(6, e.getMatricule());
            ps.executeUpdate();
            System.out.println("Equipement created!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  }
  /* public void supprimer(Equipement e) {
  try {
  String req = "DELETE FROM `Equipement` WHERE id = ? " ;
  Statement st = cnx.createStatement();
  st.executeUpdate(req);
  System.out.println("Equipement supprimée !");
  } catch (SQLException ex) {
  System.out.println(ex.getMessage());
  }
  }*/ 
  public void supprimer(Equipement e) {
    try {
        String req = "DELETE FROM `Equipement` WHERE id =  "+ e.getId();
         Statement st = cnx.createStatement();
       st.executeUpdate(req);
        System.out.println("Equipement supprimé !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

  /*public void supprimer(Equipement e) {
  try {
  String req = "DELETE FROM `Equipement` WHERE id = ? ";
  PreparedStatement ps = cnx.prepareStatement(req);
  ps.setInt(1, e.getId());
  ps.executeUpdate();
  System.out.println("Equipement supprimé !");
  } catch (SQLException ex) {
  System.out.println(ex.getMessage());
  }
  }*/
public void modifier(Equipement e) {
    try {
        String req = "UPDATE `Equipement` SET `employe_id` = ?, `type` = ?, `marque` = ?, `disponnible` = ?, `etat` = ?, `matricule` = ? WHERE `id` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getEmploye_().getId());
        ps.setString(2, e.getType());
        ps.setString(3, e.getMarque());
        ps.setInt(4, e.isDisponnible());

        ps.setString(5, e.getEtat());
        ps.setString(6, e.getMatricule());
        ps.setInt(7, e.getId());
        ps.executeUpdate();
        System.out.println("Equipement modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    public void modifier2(Equipement e) {
    try {
        // Vérifier si le terrain existe dans la base de données
        String selectReq = "SELECT id FROM equipement WHERE id = ?";
        PreparedStatement selectPs = cnx.prepareStatement(selectReq);
        selectPs.setInt(1, e.getId());
        ResultSet selectResult = selectPs.executeQuery();
        
        if (!selectResult.next()) {
            System.out.println("Erreur: Equipement avec ID " + e.getId() + " n'existe pas dans la base de données !");
            return;
        }
        
        // Modifier le terrain
        String updateReq = "UPDATE equipement SET employe_id = ?, type = ?, marque = ?, disponnible = ?, etat = ?, matricule = ? WHERE id = ?";
        PreparedStatement updatePs = cnx.prepareStatement(updateReq);
        updatePs.setInt(1, e.getEmploye_().getId());
        updatePs.setString(2, e.getType());
        updatePs.setString(3, e.getMarque());
        updatePs.setInt(4, e.isDisponnible());
        updatePs.setString(5, e.getEtat());
        updatePs.setString(6, e.getMatricule());
         updatePs.setInt(7, e.getId());
        int result = updatePs.executeUpdate();
        
        if (result == 1) {
            System.out.println("equipement modifié !");
        } else {
            System.out.println("Erreur: equipement non modifié !");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur: " + ex.getMessage());
    }
}


  /*public void modifier2(Equipement e) {
  try {
  String req = "UPDATE `Equipement` SET `employe_id` = ?, `type` = ?, `marque` = ?, `disponnible` = ?, `etat` = ?, `matricule` = ? WHERE `id` = ?";
  PreparedStatement ps = cnx.prepareStatement(req);
  ps.setInt(1, e.getEmploye_().getId());
  ps.setString(2, e.getType());
  ps.setString(3, e.getMarque());
  ps.setInt(4, e.isDisponnible()); // convert boolean value to integer
  ps.setString(5, e.getEtat());
  ps.setString(6, e.getMatricule());
  ps.setInt(7, e.getId());
  ps.executeUpdate();
  System.out.println("Equipement modifié !");
  } catch (SQLException ex) {
  System.out.println(ex.getMessage());
  }
  }*/
  /*public void modifier(Equipement e) {
  try {
  // Vérifier si l'équipement existe dans la base de données
  String selectReq = "SELECT id FROM Equipement WHERE id = ?";
  PreparedStatement selectPs = cnx.prepareStatement(selectReq);
  selectPs.setInt(1, e.getId());
  ResultSet selectResult = selectPs.executeQuery();
  
  if (!selectResult.next()) {
  System.out.println("Erreur: Equipement avec ID " + e.getId() + " n'existe pas dans la base de données !");
  return;
  }
  
  // Modifier l'équipement
  String updateReq = "UPDATE `Equipement` SET `employe_id` = ?, `type` = ?, `marque` = ?, `disponnible` = ?, `etat` = ?, `matricule` = ? WHERE `id` = ?";
  PreparedStatement updatePs = cnx.prepareStatement(updateReq);
  updatePs.setInt(1, e.getEmploye_().getId());
  updatePs.setString(2, e.getType());
  updatePs.setString(3, e.getMarque());
  updatePs.setInt(4, e.isDisponnible());
  updatePs.setString(5, e.getEtat());
  updatePs.setString(6, e.getMatricule());
  updatePs.setInt(7, e.getId());
  int result = updatePs.executeUpdate();
  
  if (result == 1) {
  System.out.println("Equipement modifié !");
  } else {
  System.out.println("Erreur: Equipement non modifié !");
  }
  } catch (SQLException ex) {
  System.out.println("Erreur: " + ex.getMessage());
  }
  }*/
  /*public void modifier(Equipement e) {
  try {
  System.out.println("ID de l'équipement : " + e.getId()); // Ajout de cette ligne
  // Vérifier si le terrain existe dans la base de données
  String selectReq = "SELECT id FROM Equipement WHERE id = ?";
  PreparedStatement selectPs = cnx.prepareStatement(selectReq);
  selectPs.setInt(1, e.getId());
  ResultSet selectResult = selectPs.executeQuery();
  
  if (!selectResult.next()) {
  System.out.println("Erreur: Equipement avec ID " + e.getId() + " n'existe pas dans la base de données !");
  return;
  }
  
  // Modifier le terrain
  String updateReq = "UPDATE `Equipement` SET `employe_id` = ?, `type` = ?, `marque` = ?, `disponnible` = ?, `etat` = ?, `matricule` = ? WHERE `id` = ?";
  PreparedStatement updatePs = cnx.prepareStatement(updateReq);
  updatePs.setInt(1, e.getEmploye_().getId());
  updatePs.setString(2, e.getType());
  updatePs.setString(3, e.getMarque());
  updatePs.setInt(4, e.isDisponnible());
  updatePs.setString(3, e.getEtat());
  updatePs.setString(5, e.getMatricule());
  updatePs.setInt(6, e.getId()); // Ajout de cette ligne
  int result = updatePs.executeUpdate();
  
  if (result == 1) {
  System.out.println("Equipement modifié !");
  } else {
  System.out.println("Erreur: Equipement non modifié !");
  }
  } catch (SQLException ex) {
  System.out.println("Erreur: " + ex.getMessage());
  }
  }*/
    
    /*public void modifierm(Equipement e) {
    try {
    String req = "UPDATE `Equipement` SET  `type` = ?, `marque` = ?, `disponnible` = ?, `etat` = ?, `matricule` = ? WHERE `id` = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    //ps.setInt(1, e.getEmploye_().getId());
    ps.setString(1, e.getType());
    ps.setString(2, e.getMarque());
    ps.setInt(3, e.isDisponnible() ); // convert boolean value to integer
    ps.setString(4, e.getEtat());
    ps.setString(5, e.getMatricule());
    ps.setInt(6, e.getId());
    ps.executeUpdate();
    System.out.println("Equipement modifié !");
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
    }*/
    
    /*public void modifier(Equipement e) {
    try {
    String req = "UPDATE `Equipement` SET `employe_id` = '" + e.getEmploye_().getId() + ",` type` = '" + e.getType() + "', `marque` = '" + e.getMarque() +"', `disponnible` = '" + e.isDisponnible() + "', `etat` = '" + e.getEtat()+ "', `matricule` = '" + e.getMatricule() + "' WHERE `Equipement`.`id` = " + e.getId();
    Statement st = cnx.createStatement();
    st.executeUpdate(req);
    System.out.println("equipement modifiée !");
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
    } */        
         //    @Override
    public List<Equipement> getAll() {
        List<Equipement> list = new ArrayList<>();
        try {
            String req = "Select * from Equipement";
           // Statement st = new Myconnection().getCnx().createStatement();
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                 Equipement e = new Equipement();
                  e.setId(rs.getInt("id"));
                e.setType(rs.getString("type"));//injecter les valeurs qui se trouve dans la ligne de rs dans l'objet c qui a été créer
                e.setMarque(rs.getString("Marque"));
                e.setDisponnible(rs.getBoolean("disponnible"));
                e.setEtat(rs.getString("etat"));
                e.setMatricule(rs.getString("matricule"));
                 int employe_id = rs.getInt("employe_id");
                EmployeCRUD employeCRUD = new EmployeCRUD();
                Employe employe = employeCRUD.getById(employe_id);
                 e.setEmploye(employe);
                 
                list.add(e);
            }
            System.out.println("Equipement affiché");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        }

   
    /* public Equipement rechercherParmatricule(String matricule) {
    Equipement equipement = null;
    String req = "SELECT * FROM equipement WHERE matricule = ?";
    try {
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, matricule);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
    equipement = new Equipement(rs.getInt("id"), rs("employe_id"),rs.getString("type"), rs.getString("marque"), rs.getBoolean("disponnible"), rs.getString("etat"), rs.getString("matricule"));
    }
    } catch (SQLException ex) {
    System.out.println("Erreur lors de la recherche de l'employé par matricule : " + ex.getMessage());
    }
    return equipement;
    }*/

  

   
    }
/*}
}*/
 