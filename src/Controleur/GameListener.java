package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modele.Jeu2048;
import Vue.AffichageGrille;

/**
 * Classe qui permet de gérer le jeu 2048 avec les flêches du clavier
 */
public class GameListener implements KeyListener{

    /**
     * Le jeu 2048
     */
    private Jeu2048 jeu;

    /**
     * La grille pour l'affichage du jeu
     */
    private AffichageGrille grille;

    /**
     * Constructeur du Listener pour faire fonctionner le jeu avec les flêches du clavier
     * @param jeu le jeu 2048
     * @param grille la grille pour afficher le jeu
     */
    public GameListener(Jeu2048 jeu, AffichageGrille grille){

        //Enregistrement des paramètres dans les variables globales
        this.grille = grille;
        this.jeu = jeu;
    }

    /**
     * Permet de faire fonctionner le 2048 avec les flêches du clavier
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Switch case en fonction de la touche pressée
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // Décalage et actualisation de la partie
                jeu.decaler(0);
                grille.refresh();
                break;
            case KeyEvent.VK_DOWN:
                jeu.decaler(2);
                grille.refresh();
                break;
            case KeyEvent.VK_LEFT:
                jeu.decaler(3);
                grille.refresh();
                break;
            case KeyEvent.VK_RIGHT:
                jeu.decaler(1);
                grille.refresh();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
