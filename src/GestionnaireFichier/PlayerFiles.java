/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireFichier;

import java.io.File;

/**
 *
 * @author nico
 */
public class PlayerFiles extends Explorer {
    private static String ADRESSE_DOSSIER_PLAYER = getADRESSE_DOSSIER_JEU() +"players"+ System.getProperty("file.separator");
    
    protected static void setADRESSE_DOSSIER_PLAYER(String nouvelleAdresseDossier){
        String separation = System.getProperty("file.separator");
        String adresseDossierJeu = getADRESSE_DOSSIER_JEU();
        if(!nouvelleAdresseDossier.startsWith(adresseDossierJeu)){
            nouvelleAdresseDossier = adresseDossierJeu + nouvelleAdresseDossier;
        }
        if(nouvelleAdresseDossier.endsWith(separation)){
            ADRESSE_DOSSIER_PLAYER = nouvelleAdresseDossier;
        } else {
            ADRESSE_DOSSIER_PLAYER = nouvelleAdresseDossier + separation;
        }
    }
    
    
    public static String genererNouveauNomDeFichier(){
        File repertoire = new File(ADRESSE_DOSSIER_PLAYER);
        File[] files = repertoire.listFiles();
        String nomDuFichier = files[files.length-1].getName().replace("Player.txt", "");
        nomDuFichier = Integer.toString(Integer.parseInt(nomDuFichier) + 1);
        return  nomDuFichier + "Player.txt";
    }
    
}
