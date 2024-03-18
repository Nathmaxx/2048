package Vue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import Modele.Jeu2048;

/**
 * Classe qui permet de gérer l'affichage de la grille du jeu 2048
 
 */
public class AffichageGrille{

    /*
     * Initialisation des variables
     */

    
    /**
      * Panel sur lequel l'interface du jeu est affichée
      */
    private JPanel panelJeu;

    /**
     * Variable contenant le jeu
     */
    private Jeu2048 jeu;

    /**
     * Nombre de lignes de la grille
     */
    private int nbLignes;

    /**
     * Nombre de colonnes de la grille
     */
    private int nbCols;

    /**
     * Matrice en String du 2048
     */
    private String[][] stringGrille;

    /**
     * Matrice en int du 2048
     */
    private int[][] intGrille;

    /**
     * Matrice de JLabels en fonction du nombre de lignes et de colonnes du 2048 
     */
    private JLabel[][] matLabels;

    /**
     * Valeur à atteindre pour gagner la partie
     */
    private int nbButs;

    /**
     * Label sur lequel est affiché le score de la partie 
     */
    private JLabel labelScore;

    /**
     * Lavel sur lequel est affiché le meilleur score obtenu
     */
    private JLabel labelMeilleurScore;

    /**
     * Coefficient qui permet de déterminer la couleur à la plus rouge possible à afficher sur la case en fonction de l'objectif. 
     */
    private int intCoef;


    /**
     * Constructeur de la grille
     * @param panelJeu le panel sur lequel est affiché la partie
     * @param jeu le jeu en question
     * @param labelScore le score 
     * @param labelMeilleurScore le meilleur score
     */
    public AffichageGrille(JPanel panelJeu, Jeu2048 jeu, JLabel labelScore, JLabel labelMeilleurScore){

        // Enregistrement des valeurs des paramètres dans les variables globales 
        this.panelJeu = panelJeu;
        this.jeu = jeu;
        this.labelScore = labelScore;
        this.labelMeilleurScore = labelMeilleurScore;

        this.nbLignes = jeu.getNbLignes();
        this.nbCols = jeu.getNbCols();
        this.nbButs = jeu.getNbBut();

        this.stringGrille = jeu.getGrilleString();
        this.intGrille = jeu.getGrilleInt();

        this.matLabels = new JLabel[nbLignes][nbCols];

        //Calcule le plus grand coefficient en fonction du score à atteindre pour la couleur des cases
        int puissanceDe2NbButs = (int) (Math.log(nbButs)/Math.log(2));
        double doubleCoef = 255/puissanceDe2NbButs;
        int intCoef = (int) Math.floor(doubleCoef);
        this.intCoef = intCoef;
    }

    /*
     * Méthodes
     */


    /**
     * Trace la grille lors du lancement d'une partie ou d'un changement de taille de grille
     */
    public void tracerGrille(){

        //Mise en page du panel sous forme de grille
        panelJeu.setLayout(new GridLayout(nbLignes, nbCols));

        // Double boucle pour parcourir toutes les cases de la matrice
        for(int i = 0 ; i < nbLignes; i++)
        {
            for(int j = 0; j < nbCols ; j++)
            {
                //Récupération de la valeur de la case en int
                int valeurIntCase = intGrille[i][j];

                //Création du JLabel
                matLabels[i][j] = new JLabel(stringGrille[i][j]);

                //Mise en forme de la case
                matLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                matLabels[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                matLabels[i][j].setOpaque(true);
                matLabels[i][j].setBackground(donneCouleurCase(valeurIntCase));

                //Création de la couleur de bordure et ajout de la bordure
                Color borderColor = new Color(235,235,235);
                
                Border lineBorder = BorderFactory.createLineBorder(borderColor,1);
                matLabels[i][j].setBorder(lineBorder);

                //Ajout de la case dans le panel
                panelJeu.add(matLabels[i][j]);
            }
        }
    }



    /**
     * Permet d'actualiser les valeurs de la matrice sur l'affichage de la grille.
     */
    public void refresh(){

        //Actualisation des variables globales des valeurs de la grille
        stringGrille = jeu.getGrilleString();
        intGrille = jeu.getGrilleInt();

        // Double boucle pour parcourir toutes les cases de la matrice
        for(int i = 0 ; i < nbLignes ; i++)
        {
            for(int j = 0 ; j < nbCols ; j++)
            {
                //Mise à jour de la valeur et couleur
                int valeurIntCase = intGrille[i][j];
                if(valeurIntCase == 0){
                    matLabels[i][j].setText("");
                } else {
                    matLabels[i][j].setText(stringGrille[i][j]);
                }
                matLabels[i][j].setBackground(donneCouleurCase(valeurIntCase));
            }
        }

        //Vérification si je jeu est terminé
        if(jeu.estTermine()){
            //Ouverture de la fenêtre indiquant si la partie est gagnée ou perdue
            FenetrePartieTerminee fpt = new FenetrePartieTerminee(jeu);
            fpt.setVisible(true);
        }

        //Actualisation du score et du meilleur score
        labelScore.setText("  Score : " + String.valueOf(jeu.getScore()));
        labelMeilleurScore.setText("  Meilleur score : " + String.valueOf(jeu.getBestScore()));

        
    }


    /**
     * Donne la couleur à afficher sur une case du jeu 
     * @param nombre : la valeur de la case 
     * @return : la couleur qui sera affichée sur la case (nuance de rouge)
     */
    public Color donneCouleurCase(int nombre){

        if(nombre == 0){
            return Color.WHITE;
        } else{
            //Retourne la valeur de la couleur en là calculant par rapport au coefficient et à la valeur de la case (nuances de rouge)
            int puissance = (int) (Math.log(nombre)/Math.log(2));
            return new Color(255,255-intCoef*puissance,255-intCoef*puissance);
        }
    }


    /*
     * Getteurs et setteurs
     */

    
    /**
     * Retourne le panel sur lequel est affiché le jeu
     * @return le panel sur lequel est affiché le jeu
     */
    public JPanel getPanelJeu(){
        return panelJeu;
    }

    /**
     * Permet de modifier le panel sur lequel est affiché le jeu
     * @param panelJeu le nouveau panel sur lequel est affiché le jeu
     */
    public void setPanelJeu(JPanel panelJeu){
        this.panelJeu = panelJeu;
    }

    /**
     * Permet de modifier le jeu
     * @param jeu le nouveau jeu
     */
    public void setJeu(Jeu2048 jeu){
        this.jeu = jeu;
        this.nbCols = jeu.getNbCols();
        this.nbLignes = jeu.getNbLignes();
        this.nbButs = jeu.getNbBut();
    }

    /**
     * Retourne le jeu
     * @return le jeu
     */
    public Jeu2048 getJeu(){
        return this.jeu;
    }

    /**
     * Retourne le nombre de colonnes de la grille
     * @return le nombre de colonnes de la grille
     */
    public int getNbLignes(){
        return this.nbLignes;
    }
}
