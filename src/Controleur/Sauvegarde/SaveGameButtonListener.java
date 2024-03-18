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
import Vue.FenetreFermetureJeu;

/**
 * La classe SaveGameButtonListener, permet de sauvegarder la partie lorsque l'utilisateur clique sur sauvegarder
 */
public class SaveGameButtonListener implements ActionListener{

    /**
     * La fenêtre principale du jeu
     */
    private Fenetre fenetre;

    /**
     * La fenêtre s'ouvrant lors de la fermeture du jeu 
     */
    private FenetreFermetureJeu ffj;

    /**
     * Le modèle du jeu 2048
     */
    private Jeu2048 jeu;

    /**
     * Le constructeur de SaveGameButtonListener, permet d'enregistrer le 2048 lorsque l'utilisateur clique sur la croix
     * @param fenetre la fenêtre principale du jeu
     * @param ffj la fenêtre qui s'ouvre lors de la fermeture du jeu 
     */
    public SaveGameButtonListener(Fenetre fenetre, FenetreFermetureJeu ffj){
        this.fenetre = fenetre;
        this.ffj = ffj;

    }

    /**
     * Permet d'enregistrer la partie lorsque l'utilisateur clique sur le bouton enregistrer
     */
    @Override
    public void actionPerformed(ActionEvent e){

        String directoryName = "Sauvegarde";
        File directory = new File(directoryName);
        if(!directory.exists()){
            directory.mkdir();
        }

        String pathName = directory.getAbsolutePath();

        //Récupération du jeu
        this.jeu = fenetre.getJeu();

        // Ouverture de l'explorateur de fichiers
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(pathName));
        int file = fileChooser.showSaveDialog(fenetre);

        fenetre.requestFocusInWindow();

        //Enregistrement du fichier lorsque l'utilisateur valide l'enregistrement dans l'explorateur de fichiers
        if(file == JFileChooser.APPROVE_OPTION){
            try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile())) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(jeu);
                fenetre.requestFocusInWindow();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            ffj.dispose();
            fenetre.dispose();
        }

        
    }
    
}
