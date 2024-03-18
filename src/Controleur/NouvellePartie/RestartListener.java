package Controleur.NouvellePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Fenetre;

/**
 * Classe qui permet de gérer le bouton pour recommencer une partie
 */
public class RestartListener implements ActionListener{
    
    /**
     * La fenêtre principale de la partie 
     */
    private Fenetre fenetre;

    /**
     * Constructeur du Listener
     * @param fenetre La fenêtre principale de la partie 
     */
    public RestartListener(Fenetre fenetre){

        // Enregistrement de la fenêtre dans la variable globale associée.
        this.fenetre = fenetre;
    }

    /**
     * Permet de remettre la partie à zéro avec les mêmes paramètres que la partie précédente
     */
    public void actionPerformed(ActionEvent e){
        // Recommence la partie
        fenetre.restartGame();
    }
}
