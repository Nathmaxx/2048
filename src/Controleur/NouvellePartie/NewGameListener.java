package Controleur.NouvellePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Fenetre;


/**
 * Classe qui permet de gérer le bouton pour commencer une nouvelle partie
 */
public class NewGameListener implements ActionListener{

    /**
     * La fenêtre principale de la partie 
     */
    private Fenetre fenetre;

    /**
     * Constructeur du Listener
     * @param fenetre La fenêtre principale de la partie 
     */
    public NewGameListener(Fenetre fenetre){
        // Enregistrement de la fenêtre dans la variable globale associée.
        this.fenetre = fenetre;
    }

    /**
     * Permet d'ouvrir la fenêtre de configuration d'une nouvelle partie lorsque le bouton est cliqué
     */
    public void actionPerformed(ActionEvent e) {
        // Ouvre la fenêtre de configuration
        fenetre.ouvrirFNP();
    }



}
