package Vue;

import javax.swing.UIManager;

import Modele.Jeu2048;


public class LancerFenetre {

	/**
	 * Méthode principale qui permet de lancer la fenêtre de jeu
	 * @param args les arguments de la ligne de commande
	 */
    public static void main(String[] args) {

		// Permet d'améliorer l'apparence des boutons et de l'explorateur de fichiers pour correspondre au système 
		// d'exploitation utilisé sur l'ordinateur
		try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception ex) {
        	ex.printStackTrace();
    	}
		
		// Création d'une nouvelle partie (par défaut une grille 4x4)
		Jeu2048 jeu = new Jeu2048();

		// Création de la fenêtre de jeu
		Fenetre fenetre = new Fenetre(jeu);

		// Affichage de la fenêtre
		fenetre.setVisible(true);
	}
}
