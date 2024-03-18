package Controleur.Sauvegarde;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;

import Modele.Jeu2048;
import Vue.Fenetre;

/**
 * La classe OpenListener, permet d'ouvrir un 2048 enregistré
 */
public class OpenListener implements ActionListener {
    private Fenetre fenetre;

    /**
     * Constructeur OpenListener, permet de pouvoir ouvrir un 2048 enregistré
     * @param fenetre la fenêtre principale du jeu 
     */
    public OpenListener(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Permet d'ouvrir un 2048 enregistré lorsque l'utilisateur clique sur le bouton "ouvrir"
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    String directoryName = "SaveGame";
    File directory = new File(directoryName);
    if(!directory.exists()){
        directory.mkdir();
    }
    String pathName = directory.getAbsolutePath();
    
    // Ouvre l'explorateur de fichiers à la bonne destination
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(pathName));
    int returnValue = fileChooser.showOpenDialog(fenetre);

    fenetre.requestFocusInWindow();

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        //Désérialisation du fichier
        File fileToOpen = fileChooser.getSelectedFile();
        try (FileInputStream fis = new FileInputStream(fileToOpen);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Obtention du jeu enregistré
            Jeu2048 jeu = (Jeu2048) ois.readObject();

            // Setteurs des variables dans la classe fenetre avec les attributs de la partie enregistrée
            fenetre.setTailleGrille(jeu.getNbCols());
            fenetre.setNbBut(jeu.getNbBut());
            fenetre.setJeu(jeu);

            //Ouverture de la partie
            fenetre.ouvertureJeu();


        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
}
