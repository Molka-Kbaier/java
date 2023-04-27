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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class EmployeCRUD {
      Connection cnx = Myconnection.getInstance().getCnx();
      public void ajouterEmploye(Employe p) {
    try {
        String req = "INSERT INTO `employe` (`nom`, `prenom`, `cin`, `age`, `salaire`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getPrenom());
        ps.setString(3, p.getCin());

        ps.executeUpdate();
        System.out.println("Employe created !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void ajouter2(Employe p) {
    try {
        String req = "INSERT INTO `employe` (`nom`, `prenom`, `cin`, `age`, `salaire`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getPrenom());
        ps.setString(3, p.getCin());
        ps.setInt(4, p.getAge());
        ps.setFloat(5, p.getSalaire());
        ps.executeUpdate();
        System.out.println("votre employe est ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
} 


      /* public void ajouterEmploye(Employe p) {
      //String req = "INSERT INTO `Employe` (`id`, `nom`,`prenom`,`cin`) VALUES ('" + p.getId() + "', '"+ p.getNom()+ "', '"  +p.getPrenom()+   "', '" +p.getCin() "')";
      //String req = "INSERT INTO `Employe` (`id`, `nom`,`prenom`,`cin`) VALUES ('" + p.getId() + "', '"+ p.getNom()+ "', '"  +p.getPrenom()+   "', '" +p.getCin() + "')";
      
      try {
      String req = "INSERT INTO `Employe` ( `nom`,'prenom','cin') VALUES ('"+ p.getNom()+ "', '"  +p.getPrenom()+   "', '" +p.getCin() + "')";
      Statement st = cnx.createStatement();
      st.executeUpdate(req);
      System.out.println("Employe created !");
      } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      }
      }
      public void ajouter2(Employe p) {
      try {
      // String req = "INSERT INTO `Equipement` (`id`, `nom`,'prenom','cin') VALUES ('" + p.getId() + "', '"+ p.getNom()+ "', '"  +p.getPrenom()+   "', '" +p.getCin() "')";
      String req = "INSERT INTO `Employe` ( `nom`,`prenom`,`cin`) VALUES ( ?, ?, ?)";
      PreparedStatement ps = cnx.prepareStatement(req);
      // ps.setInt(1, p.getId());
      ps.setString(1, p.getNom());
      ps.setString(2, p.getPrenom());
      ps.setString(3, p.getCin());
      
      ps.executeUpdate();
      System.out.println("votre employe est ajouté");
      } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      //    @Override
      }
      } */
/*public void supprimer(Employe p) {
try {

String req = "DELETE FROM `Employe` WHERE id =? ";
Statement st = cnx.createStatement();
st.executeUpdate(req);
System.out.println("Employe supprimée !");
} catch (SQLException ex) {
System.out.println(ex.getMessage());
}
}

public void modifier(Employe p) {
try {
String req = "UPDATE `Employe` SET ` nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom() +"', `cin` = '" + p.getCin() + "' WHERE `Employe`.`id` = " + p.getId();
Statement st = cnx.createStatement();
st.executeUpdate(req);
System.out.println("Employe modifiée !");
} catch (SQLException ex) {
System.out.println(ex.getMessage());
}
}
*/
 public void supprimer(Employe p) {
   try {
       String req = "DELETE FROM `employe` WHERE id = " + p.getId();
       Statement st = cnx.createStatement();
       st.executeUpdate(req);
       System.out.println("Employé supprimée !");
   } catch (SQLException ex) {
       System.out.println(ex.getMessage());
   }
}
 public void supprimer2(Employe p) {
        try {
            String req = "DELETE FROM `employe` WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            System.out.println("Employe supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 /* public void modifier(Employe p) {
 try {
 String req = "UPDATE `Employe` SET `nom` = ?, `prenom` = ?, `cin` = ? WHERE `Employe`.`id` = ?";
 PreparedStatement ps = cnx.prepareStatement(req);
 ps.setString(1, p.getNom());
 ps.setString(2, p.getPrenom());
 ps.setString(3, p.getCin());
 ps.setInt(4, p.getId());
 ps.executeUpdate();
 System.out.println("Employe modifié !");
 } catch (SQLException ex) {
 System.out.println(ex.getMessage());
 }
 }*/
 public void modifier(Employe p) {
  //       Connection cnx = new Myconnection().getCnx();
    try {
        
        String query = "UPDATE `employe` SET `nom` = '" + p.getNom() + "', `prenom` = '" + p.getPrenom() + "', `cin` = '" + p.getCin() +"', `age` = '" + p.getAge() + "', `salaire` = '" + p.getSalaire() + "' WHERE `employe`.`id` = " + p.getId();
        Statement st = cnx.createStatement();
        st.executeUpdate(query);
        System.out.println("Employe modifié !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public void modifier2(Employe p) {
        try {
            String req = "UPDATE `employe` SET `nom` = ?, `prenom` = ?, `cin` = ? WHERE `employe`.`id` = ?";
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getCin());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            System.out.println("Employe modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Employe> getAll() {
        List<Employe> list = new ArrayList<>();
        try {
            String req = "Select * from Employe";
            //Statement st = new Myconnection().getCnx().createStatement();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
             //  Employe p = new Employe(rs.getInt(1), rs.getString("id"), rs.getString(3));
             //  Employe p = new Employe(rs.getInt(1), rs.getInt("id"), rs.getString(3));
                Employe p = new Employe();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getNString("prenom"));
                p.setCin(rs.getString("cin"));
                p.setAge(rs.getInt("age"));
                p.setSalaire(rs.getFloat("salaire"));
                
                list.add(p);
            }
            System.out.println("votre employe est affiché");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        }  
 public Employe getById(int id) {
        try {
            String req = "SELECT * FROM employe WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"),rs.getInt("age"), rs.getFloat("salaire"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
  
   public Employe rechercherParCin(String cin) {
    Employe employe = null;
    String req = "SELECT * FROM employe WHERE cin = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, cin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getInt("age"), rs.getFloat("salaire"));
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la recherche de l'employé par CIN : " + ex.getMessage());
    }
    return employe;
}

}
