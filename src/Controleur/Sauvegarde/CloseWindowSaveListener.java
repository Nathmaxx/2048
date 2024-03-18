package Controleur.Sauvegarde;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


import Vue.Fenetre;
import Vue.FenetreFermetureJeu;

/**
 * Classe qui permet de gérer la fermeture de la fenêtre principale du jeu
 */
public class CloseWindowSaveListener implements WindowListener{

    /**
     * Fenêtre principale du jeu
     */
    private Fenetre fenetre;

    /**
     * Constructeur de CloseWindowListener, permet de demander si l'utilisateur souhaite sauvegarder la partie lorsqu'il appuie sur la croix
     * @param fenetre la fenêtre principale du jeu
     */
    public CloseWindowSaveListener(Fenetre fenetre){
        this.fenetre = fenetre;
    }


    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    /**
     * Permet d'ouvrir la fenêtre pour demander si l'utilisateur souhaite enregistrer sa partie en cours
     */
    @Override
    public void windowClosing(WindowEvent e) {
        //Bloquer la fermeture de la fenêtre 
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Ouvrir la fenetre de fermeture du jeu
        FenetreFermetureJeu ffj = new FenetreFermetureJeu(fenetre);
        ffj.setVisible(true);
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
