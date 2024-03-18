package Vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controleur.GameListener;
import Controleur.NouvellePartie.NewGameListener;
import Controleur.NouvellePartie.RestartListener;
import Controleur.Sauvegarde.CloseWindowSaveListener;
import Controleur.Sauvegarde.OpenListener;
import Controleur.Sauvegarde.SaveListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;

import Modele.Jeu2048;

/**
 * Classe qui permet de gérer l'affichage de la fenêtre du jeu 2048
 */
public class Fenetre extends JFrame{

    /*
     * Initialisation des variables
     */

     
    // Définition des valeurs de la hauteur et largeur de la fenêtre
    final static int hauteurFenetrePrincipale = 800;
    final static int largeurFenetrePrincipale = 950;
    
   
    /**
     * Le jeu 2048
     */
    private Jeu2048 jeu;

    /**
     * Paramètre de la taille de la grille
     */
    private int tailleGrille;

    /**
     * Paramètre du score à atteindre
     */
    private int nbBut;

    /**
     * Paramètre du meilleur score atteint
     */
    private int bestScore;

    /**
     * Le panel sur lequel la partie se déroule
     */
    private JPanel panelJeu;

    /**
     * La grille du 2048
     */
    private AffichageGrille grille;

    /**
     * Label sur lequel le score est affiché
     */
    private JLabel score;

    /**
     * Label sur lequel la valeur à atteindre pour gagner la partie est affichée
     */
    private JLabel objectifScore;

    /**
     * Label sur lequel le meilleur score est affiché
     */
    private JLabel meilleurScore;

    /**
     * Police des éléments
     */
    private Font police;

    /**
     * Constructeur Fenetre
     * @param jeu le modèle du jeu 2048
     */
    public Fenetre(Jeu2048 jeu){

        //enregistre le jeu dans la variable globale
        this.jeu = jeu;


        //détermine la taille de la grille 
        int tailleGrille = jeu.getNbCols();
        this.tailleGrille = tailleGrille;


        //détermine le score à atteindre
        int nbBut = jeu.getNbBut();
        this.nbBut = nbBut;


        //Caractéristiques de la page
        setTitle("2048");
        setSize(largeurFenetrePrincipale, hauteurFenetrePrincipale);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(largeurFenetrePrincipale, hauteurFenetrePrincipale));


        //Création de la police 
        Font police = new Font("Arial", Font.PLAIN, 18);
        this.police = police;


        //Création du panel de sauvegarde
        JPanel savePanel = new JPanel();

        //Création du bouton pour enregistrer le 2048
        JButton saveButton = new JButton("Sauvegarder");
        saveButton.setPreferredSize(new Dimension(150,50));
        saveButton.setFont(police);
        savePanel.add(saveButton);

        //Création du bouton pour ouvrir un 2048
        JButton openButton = new JButton("Ouvrir");
        openButton.setPreferredSize(new Dimension(150,50));
        openButton.setFont(police);
        savePanel.add(openButton);

        //Création du panel de score
        JPanel scorePanel = new JPanel();


        //Création du bouton nouvelle partie
        JButton newGameButton = new JButton("Nouvelle partie");
        newGameButton.setPreferredSize(new Dimension(200,50));
        newGameButton.setFont(police);


        // Création du bouton de configuration de partie 
        JButton gameConfigButton = new JButton("Configurer une nouvelle partie");
        gameConfigButton.setPreferredSize(new Dimension(300,50));
        gameConfigButton.setFont(police);


        //Création du label avec l'affichage du score
        JLabel score = new JLabel("  Score : " + jeu.getScore());
        this.score = score;
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(police);


        //Création du label avec l'affichage du meilleur score
        JLabel meilleurScore = new JLabel("  Meilleur score : " + jeu.getBestScore());
        this.meilleurScore = meilleurScore;
        meilleurScore.setHorizontalAlignment(SwingConstants.CENTER);
        meilleurScore.setFont(police);

        //Création du label avec la valeur à atteindre pour gagner la partie 
        JLabel objectifScore = new JLabel("  Valeur à atteindre : " + jeu.getNbBut());
        this.objectifScore = objectifScore;
        objectifScore.setHorizontalAlignment(SwingConstants.CENTER);
        objectifScore.setFont(police);


        //Ajout des labels et boutons sur le panel
        scorePanel.add(gameConfigButton);
        scorePanel.add(newGameButton);
        scorePanel.add(score);
        scorePanel.add(meilleurScore);
        scorePanel.add(objectifScore);


        //Création du panel de jeu
        JPanel panelJeu = new JPanel();
        this.panelJeu = panelJeu;
        

        //Création de la grille
        AffichageGrille grille = new AffichageGrille(panelJeu, jeu, score, meilleurScore);
        this.grille = grille;
        grille.tracerGrille();
        grille.refresh();


        //Ajout des panels dans la Frame
        this.getContentPane().add(savePanel, BorderLayout.NORTH);
        this.getContentPane().add(panelJeu, BorderLayout.CENTER);
        this.getContentPane().add(scorePanel, BorderLayout.SOUTH);
        
        //Ajout du listener pour faire fonctionner le bouton qui permet de sauvegarder une partie
        saveButton.addActionListener(new SaveListener(this));

        //Ajout du listener pour faire fonctionner le bouton qui permet de d'ouvrir une partie
        openButton.addActionListener(new OpenListener(this));

        //Ajout du listener pour faire fonctionner le bouton qui configure une nouvelle partie
        gameConfigButton.addActionListener(new NewGameListener(this));
        
        //Ajout du listener pour faire fonctionner le bouton pour recommencer la partie
        newGameButton.addActionListener(new RestartListener(this));

        //Ajout du listener pour demander la sauvegarde lorsque je jeu est fermé
        this.addWindowListener(new CloseWindowSaveListener(this));

        //Ajout du keyListener pour faire fonctionner le jeu avec les flêches
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new GameListener(jeu,grille));


    }

    /**
     * Fonction qui permet de recommencer une partie avec le même score à atteindre et la même taille de grille que la partie précédente
     */
    public void restartGame(){

        // Enregistrement du meilleur score de la partie précédente
        this.bestScore = jeu.getBestScore();

        // Création du nouveau jeu avec les mêmes attributs que la partie précédente
        this.jeu = new Jeu2048(tailleGrille, tailleGrille, nbBut);

        //Affichage du meilleur score
        this.jeu.setBestScore(bestScore);

        // Enregistrement et actualisation de la grille avec le nouveau jeu
        grille.setJeu(jeu);
        grille.refresh();

        // Enlèvement des KeyListeners et rajout du nouveau avec la nouvelle grille
        KeyListener[] keyListeners = this.getKeyListeners();
        if (keyListeners.length > 0) {
            this.removeKeyListener(keyListeners[0]);
        }
        addKeyListener(new GameListener(jeu,grille));
        requestFocusInWindow(); 
    }

    /**
     * Fonction qui permet de configurer une nouvelle partie avec un score et une grille différente de la partie précédente.
     */
    public void resetGame() {

        //Paramètres de taille de grille et de score actualisés dans le listener

        //Création du nouveau jeu avec les nouveaux paramètres
        this.jeu = new Jeu2048(tailleGrille,tailleGrille,nbBut);
        
        //Récupération du panel de jeu et suppression de son contenu
        JPanel panelJeu = getPanelJeu();
        panelJeu.removeAll();
        panelJeu.revalidate();
        panelJeu.repaint();

        //Création, affichage et actualisation de la nouvelle grille en fonction des paramètres du jeu
        this.grille = new AffichageGrille(panelJeu, jeu, score, meilleurScore);
        grille.tracerGrille();
        grille.refresh();
        setAffichageGrille(grille);

        //Actualisation du l'objectif
        objectifScore.setText(String.valueOf("  Valeur à atteindre : " + String.valueOf(jeu.getNbBut())));

        // Enlèvement des KeyListeners et rajout du nouveau avec la nouvelle grille
        KeyListener[] keyListeners = this.getKeyListeners();
        if (keyListeners.length > 0) {
            this.removeKeyListener(keyListeners[0]);
        }

        addKeyListener(new GameListener(jeu,grille));
        requestFocusInWindow(); 

    }

    /**
     * Fonction qui permet d'ouvrir un jeu préalablement suvegardé
     */
    public void ouvertureJeu(){

        //Récupération et suppression des éléments sur le panel de jeu 
        JPanel panelJeu = getPanelJeu();
        panelJeu.removeAll();
        panelJeu.revalidate();
        panelJeu.repaint();

        //Création de la grille avec les paramètres du jeu à ouvrir
        grille = new AffichageGrille(panelJeu, jeu, score, meilleurScore);
        grille.tracerGrille();
        grille.refresh();
        setAffichageGrille(grille);

        //Actualisation de l'objectif
        objectifScore.setText(String.valueOf("  Valeur à atteindre : " + String.valueOf(jeu.getNbBut())));

        // Enlèvement des KeyListeners et rajout du nouveau avec la nouvelle grille
        KeyListener[] keyListeners = this.getKeyListeners();
        if (keyListeners.length > 0) {
            this.removeKeyListener(keyListeners[0]);
        }

        addKeyListener(new GameListener(jeu,grille));
        requestFocusInWindow(); 

    }

    /**
     * Création et ouverture de la fenetre qui permet de configurer une nouvelle partie.
     */
    public void ouvrirFNP(){
        FenetreNouvellePartie fnp = new FenetreNouvellePartie(this);
        fnp.setVisible(true);
    }



    /*
     * Getteurs et setteurs
     */


    /**
     * Fonction qui permet de récupérer la police
     * @return la police
     */
    public Font getPolice(){
        return this.police;
    }

    /**
     * Fonction qui permet de récupérer le jeu
     * @return le jeu
     */
    public Jeu2048 getJeu(){
        return jeu;
    }

    /**
     * Fonction qui permet de modifier le jeu
     * @param new2048 le nouveau jeu
     */
    public void setJeu(Jeu2048 new2048){
        this.jeu = new2048;
    }

    /**
     * Fonction qui permet de récupérer le panel de jeu
     * @return le panel de jeu
     */
    public JPanel getPanelJeu(){
        return panelJeu;
    }

    /**
     * Fonction qui permet de modifier le panel de jeu
     * @param panelJeu le nouveau panel de jeu
     */
    public void setPanelJeu(JPanel panelJeu){
        this.panelJeu = panelJeu;
    }

    /**
     * Fonction qui permet de récupérer la grille
     * @return la grille
     */
    public AffichageGrille getAffichageGrille(){
        return grille;
    }

    /**
     * Fonction qui permet de modifier la grille
     * @param grille la nouvelle grille
     */
    public void setAffichageGrille(AffichageGrille grille){
        this.grille = grille;
    }

    /**
     * Fonction qui permet de récupérer le label sur lequel le score est affiché
     * @param tailleGrille la nouvelle taille de la grille
     */
    public void setTailleGrille(int tailleGrille){
        this.tailleGrille = tailleGrille;
    }

    /**
     * Fonction qui permet de récupérer la taille de la grille
     * @return la taille de la grille
     */
    public int getTailleGrille(){
        return this.tailleGrille;
    }

    /**
     * Fonction qui permet de modifier le label sur lequel le score est affiché
     * @param nbBut le nouveau label sur lequel le score est affiché
     */
    public void setNbBut(int nbBut){
        this.nbBut = nbBut;
    }

    /**
     * Fonction qui permet de récupérer le label sur lequel le score est affiché
     * @return le label sur lequel le score est affiché
     */
    public int getNbBut(){
        return this.nbBut;
    }
}





