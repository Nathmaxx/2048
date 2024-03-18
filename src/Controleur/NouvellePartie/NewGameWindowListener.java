package Controleur.NouvellePartie;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import Vue.Fenetre;
import Vue.FenetreNouvellePartie;

/**
 * Classe qui permet de gérer la fenêtre de configuration d'une nouvelle partie
 */
public class NewGameWindowListener implements WindowListener {
    
    /**
     * La fenêtre principale de la partie 
     */
    private Fenetre fenetre;

    /**
     * La fenêtre de configuration d'une nouvelle partie 
     */
    private FenetreNouvellePartie fnp;

    /**
     * Constructeur du Listener
     * @param fenetre La fenêtre principale de la partie 
     * @param fnp La fenêtre de configuration d'une nouvelle partie 
     */
    public NewGameWindowListener(Fenetre fenetre, FenetreNouvellePartie fnp){

        // Enregistrement des paramètres dans les variables globales associées
        this.fenetre = fenetre;
        this.fnp = fnp;
    }

    /**
     * Met à jour les nouvelles valeurs de taille de grille et score à atteindre dans la fenêtre principale
     */
    @Override
    public void windowClosed(WindowEvent e){

        // Récupération des nouvelles valeurs de taille de grille et score
        int tailleGrille = fnp.getSliderValue();
        int score = fnp.getValueButton();

        // Enregristrement de ces valeurs dans la fenêtre principale de jeu
        fenetre.setTailleGrille(tailleGrille);
        fenetre.setNbBut(score);

        // Remise à zéro de la partie
        fenetre.resetGame();
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    /**
     * Permet de ne pas pouvoir fermer la fenêtre en cliquant sur la croix
     */
    @Override
    public void windowClosing(WindowEvent e) {
        fnp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
}
