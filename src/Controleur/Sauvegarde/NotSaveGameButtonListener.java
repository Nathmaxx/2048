package Controleur.Sauvegarde;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Fenetre;
import Vue.FenetreFermetureJeu;

/**
 * La classe NotSaveGameButtonListener, permet de fermer la fenêtre principale du jeu lorsque l'utilisateur clique sur ne pas sauvegarder
 */
public class NotSaveGameButtonListener implements ActionListener{

    private Fenetre fenetre;
    private FenetreFermetureJeu ffj;

    /**
     * Constructeur de NotSaveGameButtonListener, le listener du bouton qui ferme la fenêtre si on clique sur ne pas sauvegarder
     * @param fenetre la fenêtre principale du jeu
     * @param ffj la fenetre qui s'ouvre lors de la fermeture du jeu
     */
    public NotSaveGameButtonListener(Fenetre fenetre, FenetreFermetureJeu ffj){
        this.fenetre = fenetre;
        this.ffj = ffj;
    }

    /**
     * Fermeture des fenêtres lorsque l'utilisateur appuie sur le bouton ne pas sauvegarder
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ffj.dispose();
        fenetre.dispose();
    }
    
}
