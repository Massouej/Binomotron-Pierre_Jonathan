import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String user = "root";
        String pwd = "root";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/binomotron"; // connection à la BDD
        String requete = "";

        Connection con = null;
        Statement st = null;
        ResultSet list_app = null;


        try {
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            list_app = st.executeQuery("SELECT * FROM apprenants"); // on récupère la liste des apprenants dans la BDD
            List<String> rdm_list = new ArrayList<>();


            while (list_app.next()) {
                rdm_list.add(list_app.getString("nom") + " " + list_app.getString("prenom"));
                // on ajoute les apprenants dans la liste random


            }
            Collections.shuffle(rdm_list); // Mélanger la liste_app
            System.out.println(rdm_list); // Afficher la liste_rdm


            int num_groupes = 1; // On déclare numéro de groupe à 1 pour pouvoir incrémenter les numéros de groupe ensuite

            for (int i = 0; i < rdm_list.size(); i++) {
                List<String> list_groupe = new ArrayList<>(); // création de la liste groupe
                list_groupe.add(rdm_list.get(i));
                i++; // Ajout dans la liste groupe des binomes

                if (i + 1 < rdm_list.size()) {
                    list_groupe.add(rdm_list.get(i)); // Ajout a la fin de la liste l'apprenant qui n'est pas en groupe
                }

                System.out.println("Groupes :" + " " + (num_groupes) + " " + list_groupe);
                // Affichage des groupes dans la console

                num_groupes++;
            }

        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());

        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
                if (list_app != null) list_app.close();
                //if(rdm_list != null) rs2.close();
            } catch (Exception e) {

            }

        }
    }
}





