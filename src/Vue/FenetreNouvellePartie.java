package Vue;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import Controleur.NouvellePartie.CloseListener;
import Controleur.NouvellePartie.NewGameWindowListener;



/**
 * Classe qui permet de gérer la fenêtre de configuration d'une nouvelle partie
 */
public class FenetreNouvellePartie extends JFrame{
    
    // Valeurs des largeurs de hauteurs de la fênetre
    final static int hauteurFenetreNouvellePartie = 500;
    final static int largeurFenetreNouvellePartie = 550;

    /**
     * JSlider qui permet de déterminer la taille de la grille
     */
    private JSlider sliderTailleGrille;

    /**
     * La valeur du JSlider
     */
    private int sliderValue;

    /**
     * La liste des JRadioButtons
     */
    private JRadioButton[] radioButtons;

    /**
     * La valeur du score à atteindre
     */
    private int scoreValue;

    /**
     * La police de la fenêtre
     */
    private Font police;


    /**
     * Constructeur de la fenêtre de configuration d'une nouvelle partie 
     * @param fenetre la fenêtre principale où se déroule le jeu
     */
    public FenetreNouvellePartie(Fenetre fenetre){

        // Enregistrement de la même police que sur le fenêtre principale
        this.police = fenetre.getPolice();

        //Caractéristiques de la fenêtre
        setTitle("Options de partie");
        setSize(largeurFenetreNouvellePartie, hauteurFenetreNouvellePartie);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //Création du panel d'affichage des composants
        JPanel panelAffichage = new JPanel();
        panelAffichage.setLayout(new GridLayout(5,1));

        // Création du label expliquant que le JSlider permet de déterminer la taille de la grille pour la nouvelle partie
        JLabel sliderLabel = new JLabel("Nombre de lignes et colonnes : ");
        sliderLabel.setFont(police);
        sliderLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Création d'un JSlider
        JSlider sliderTailleGrille = new JSlider(4,8,4);
        this.sliderTailleGrille = sliderTailleGrille;
        sliderTailleGrille.setMajorTickSpacing(1);
        sliderTailleGrille.setPaintTicks(true);
        sliderTailleGrille.setPaintLabels(true);
        sliderTailleGrille.setPreferredSize(new Dimension(250,50));
        sliderTailleGrille.setFont(police);

        //Ajout du JSlider dans un nouveau JPanel pour pouvoir mofifier sa taille
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(sliderTailleGrille);

        // Création du label expliquant que les boutons radio permettent de déterminer le score à atteindre pour la nouvelle partie
        JLabel radioButtonLabel = new JLabel("Score à atteindre : ");
        radioButtonLabel.setFont(police);
        radioButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Création des JRadioButton avec le score à atteindre associé
        JRadioButton b1 = new JRadioButton("256");
        JRadioButton b2 = new JRadioButton("512");
        JRadioButton b3 = new JRadioButton("1024");
        JRadioButton b4 = new JRadioButton("2048");
        JRadioButton b5 = new JRadioButton("4096");
        JRadioButton b6 = new JRadioButton("8192");
        JRadioButton b7 = new JRadioButton("16384");

        // Création de la liste des JRadioButtons
        JRadioButton[] radioButtons = {b1, b2, b3, b4, b5, b6, b7};

        // Création du groupe des JRadioButtons
        ButtonGroup group = new ButtonGroup();

        // Création du Jpanel qui contiendra les JRadioButton
        JPanel radioButtonPanel = new JPanel();

        // Création d'une police pour les JRadioButton
        Font font = new Font("Arial", Font.BOLD, 18);

        //Boucle pour ajouter les boutons au groupe, ajouter la police et ajouter les boutons dans le panel
        for (JRadioButton button : radioButtons) {
            group.add(button);
            button.setFont(font);
            radioButtonPanel.add(button);
        }

        // Bouton 4 séléctionné par défaut
        b4.setSelected(true);

        // Enregistrement de la liste dans la variable globale associée
        this.radioButtons = radioButtons;
        
        //Création d'un JButton
        JButton okButton = new JButton("Valider et fermer la fenêtre");
        okButton.setPreferredSize(new Dimension(250,50));
        okButton.setFont(police);
        
        //Ajout du JButton dans un nouveau JPanel pour pouvoir modifier sa taille
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        
        //Ajout des JPanels des composants dans le JPanel principal
        panelAffichage.add(radioButtonLabel);
        panelAffichage.add(radioButtonPanel);
        panelAffichage.add(sliderLabel);
        panelAffichage.add(sliderPanel);
        panelAffichage.add(buttonPanel);

        //Ajout du JPanel principal dans la JFrame
        this.getContentPane().add(panelAffichage);

        // Ajout du Listener pour faire fonctionner le bouton de validation de création d'une nouvelle partie
        okButton.addActionListener(new CloseListener(this));

        // AJout du Listener pour faire fonctionner la création d'une nouvelle partie 
        this.addWindowListener(new NewGameWindowListener(fenetre,this));
    }

    /*
     * Getteurs et setteurs
     */

    /**
     * Permet de récupérer la valeur du JSlider et de l'enregistrer dans la variable globale
     */
    public void setSliderValue(){
        sliderValue = sliderTailleGrille.getValue();
    }

    /**
     * Permet de récupérer la valeur du JSlider
     * @return la valeur du JSlider
     */
    public int getSliderValue(){
        return sliderValue;
    }

    /**
     * Permet de récupérer la valeur du score à atteindre et de l'enregistrer dans la variable globale
     */
    public void setValueButton(){
        int scoreValue = 0;
        for (JRadioButton button : radioButtons){
            if(button.isSelected()){
                scoreValue = Integer.parseInt(button.getText());
                break;
            }
        }
        this.scoreValue = scoreValue;
    }

    /**
     * Permet de récupérer la valeur du score à atteindre
     * @return la valeur du score à atteindre
     */
    public int getValueButton(){
        return scoreValue;
    }

}

