package Controleur.NouvellePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.FenetreNouvellePartie;

/**
 * Classe qui permet de gérer le bouton pour valider la configuration d'une nouvelle partie
 */
public class CloseListener implements ActionListener{
    
    /**
     * La fenêtre de configuration d'une nouvelle partie 
     */
    private FenetreNouvellePartie fnp;

    /**
     * Constructeur du CloseListener
     * @param fnp la fenêtre de configuration d'une nouvelle partie 
     */
    public CloseListener(FenetreNouvellePartie fnp){

        // Enregistrement dans la variable globale
        this.fnp = fnp;
    }

    /**
     * Action réalisée lors de l'appui sur le bouton pour valider la configuration
     */
    public void actionPerformed(ActionEvent e) {

        //Enregistrement de la valeur pour la taille de la nouvelle grille 
        fnp.setSliderValue();

        // Enregistrement de la valeur du score à atteindre pour la nouvelle partie
        fnp.setValueButton();

        // Fermeture de la fênetre
        fnp.dispose();
    }



}
