package Controleur.Sauvegarde;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;


import Modele.Jeu2048;
import Vue.Fenetre;

/**
 * La classe SaveListener, permet de sauvegarder la partie lorsque l'utilisateur clique sur sauvegarder
 */
public class SaveListener implements ActionListener{

    /**
     * La fenêtre principale du jeu 
     */
    private Fenetre fenetre;

    /**
     * Le modèle du jeu 2048
     */
    private Jeu2048 jeu;
    
    /**
     * Constructeur SaveListener, permet de sauvegarder la partie lorsque l'utilisateur clique sur sauvegarder
     * @param fenetre la fenêtre principale du jeu
     */
    public SaveListener(Fenetre fenetre){
        this.fenetre = fenetre;
    }

    /**
     * Permet d'enregistrer le jeu lorsque l'utilisateur a cliqué sur le bouton sauvegarder
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String directoryName = "Sauvegarde";
        File directory = new File(directoryName);
        if(!directory.exists()){
            directory.mkdir();
        }

        String pathName = directory.getAbsolutePath();

        //Récupération du jeu 
        this.jeu = fenetre.getJeu();

        //Ouvertur de l'explorateur de fichiers à la bonne localisation
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(pathName));
        int file = fileChooser.showSaveDialog(fenetre);

        fenetre.requestFocusInWindow();

        //Enregistre le fichier si l'utilisateur a cliqué sur le bouton enregistrer de l'explorateur de fichier
        if(file == JFileChooser.APPROVE_OPTION){
            try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile())) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(jeu);
                fenetre.requestFocusInWindow();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Permet de setter le jeu
     * @param jeu le jeu à setter
     */
    public void setJeu(Jeu2048 jeu){
        this.jeu = jeu;
    }
}

