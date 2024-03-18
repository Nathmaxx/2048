package Vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controleur.Sauvegarde.NotSaveGameButtonListener;
import Controleur.Sauvegarde.SaveGameButtonListener;



/**
 * Classe qui permet de gérer la fenêtre de fermeture du jeu
 */
public class FenetreFermetureJeu extends JFrame{
    
    // Largeur et hauteur de la fenêtre
    final static int hauteurFenetre = 200;
    final static int largeurFenetre = 600;


    /**
     * Constructeur de FermetureFenetreJeu 
     * @param fenetre la fenetre principale du jeu 
     */
    public FenetreFermetureJeu(Fenetre fenetre){

        // Caractéristiques de la fenêtre
        setTitle("2048");
        setSize(largeurFenetre, hauteurFenetre);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Création de la police
        Font police = new Font("Arial", Font.PLAIN, 18);

        // Création du panel d'affichage des éléments
        JPanel panelAffichage = new JPanel();
        panelAffichage.setLayout(new GridLayout(2,1));

        //Création du panel qui contiendra les deux boutons 
        JPanel panelButtons = new JPanel();
        
        //création du JLabel qui demande si on souhaite enregistrer la partie
        JLabel saveQuestion = new JLabel("Voulez vous sauvegarder la partie en cours ?");
        saveQuestion.setFont(police);
        saveQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        panelAffichage.add(saveQuestion);

        //Création du bouton sauvegarder
        JButton saveGame = new JButton("Sauvegarder");
        saveGame.setPreferredSize(new Dimension(150,50));
        saveGame.setFont(police);
        
        //Création du bouton ne pas sauvegarder
        JButton notSaveGame = new JButton("Ne pas sauvegarder");
        notSaveGame.setPreferredSize(new Dimension(220,50));
        notSaveGame.setFont(police);

        //Ajout des boutons dans le panel
        panelButtons.add(saveGame);
        panelButtons.add(notSaveGame);

        //Ajout des éléments dans le panel principal
        panelAffichage.add(saveQuestion);
        panelAffichage.add(panelButtons);

        //Ajout du panel dans la JFrame
        this.getContentPane().add(panelAffichage);

        //Ajout des listeners pour les boutons sauvegarder et ne pas sauvegarder
        saveGame.addActionListener(new SaveGameButtonListener(fenetre, this));
        notSaveGame.addActionListener(new NotSaveGameButtonListener(fenetre,this));


    }
    

}
