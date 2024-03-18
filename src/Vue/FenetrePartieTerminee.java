package Vue;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Jeu2048;



/**
 * Classe qui permet de gérer la fenêtre de fin de partie
 */
public class FenetrePartieTerminee extends JFrame{

    //Largeur et hauteur de la fenêtre 
    final static int hauteurFenetre = 100;
    final static int largeurFenetre = 400;

    /**
     * Création de la police
     */
    Font police = new Font("Arial", Font.BOLD, 22);
    
    /**
     * Constructeur de FenetrePartieTerminee, fenêtre qui s'ouvre lorsque l'utilisateur à terminé une partie 
     * @param jeu le modèle du jeu 2048
     */
    public FenetrePartieTerminee(Jeu2048 jeu){

        //Caractéristiques de la JFrame
        setTitle("Partie Terminée");
        setSize(largeurFenetre, hauteurFenetre);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //Affichage du message en fonction de si l'utilisateur a gagné ou perdu
        JLabel label = new JLabel();
        if(jeu.estVainqueur()){
            label.setText("Vous avez gagné la partie !");
        } else {
            label.setText("Vous avez perdu la partie !");
        }
        label.setFont(police);
        
        //Création et ajout du panel avec le message dans la JFrame
        JPanel endPanel = new JPanel();
        endPanel.add(label);

        this.getContentPane().add(endPanel);
    }

}
