/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireFichier;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author nico
 */
public class Explorer {
    private static String ADRESSE_DOSSIER_JEU = System.getProperty("user.dir")+System.getProperty("file.separator")+"dataJeuLabyrinthe" + System.getProperty("file.separator");
    
    public Explorer(){
    }
    
    protected static void setADRESSE_DOSSIER_JEU(String nouvelleAdresseDossier){
        String separation = System.getProperty("file.separator");
        if(nouvelleAdresseDossier.endsWith(separation)){
            ADRESSE_DOSSIER_JEU = nouvelleAdresseDossier;
        } else {
            ADRESSE_DOSSIER_JEU = nouvelleAdresseDossier + separation;
        }
    }

    public static String getADRESSE_DOSSIER_JEU() {
        return ADRESSE_DOSSIER_JEU;
    }
    
    public static String generateurDeNom(){
        String nom = "The cake is a lie";
        try(BufferedReader fichier = new BufferedReader( new FileReader(ADRESSE_DOSSIER_JEU + "nom.txt"))){
            Object[] noms = fichier.lines().toArray();
            fichier.close();
            nom = (String) noms[(int) (Math.random()*noms.length)]; //au hasard entre 1 et nbDeChoix
        } catch(IOException e){
            e.printStackTrace();
        }
        return nom;
    }
    
    
    public static void miseEnFormeDuFichierNom(){
        String aEcrire = "";
        String separator = System.getProperty("line.separator");
        try (BufferedReader fichier = new BufferedReader( new FileReader(ADRESSE_DOSSIER_JEU + "nom.txt"))){
            String contenuDuFichier = "";
            while(fichier.ready()){
                String ligne;
                ligne = fichier.readLine();
                if(!ligne.isBlank()){
                    ligne = ligne + separator;
                    ligne = ligne.replace(".", "").replace(",", "").replace(" ", separator);
                    contenuDuFichier = contenuDuFichier + ligne;
                }
            }
            for(var mot : contenuDuFichier.split(separator)){
                char premiereLettre = Character.toUpperCase(mot.charAt(0));
                mot = Character.toString(premiereLettre) + mot.substring(1).toLowerCase(new Locale ("FR"));
                if(aEcrire.indexOf(mot)<0){
                    aEcrire = aEcrire + separator + mot;
                }
            }
            aEcrire = aEcrire.replaceFirst(separator,"");
           fichier.close();
        } catch (IOException e){
         e.printStackTrace();
        }
        try (FileWriter fichier = new FileWriter(ADRESSE_DOSSIER_JEU + "nom.txt")) {
            fichier.write(aEcrire);
            fichier.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void checkIfFolderExist(String adresseDuDossier){
        File dossier = new File(adresseDuDossier);
        if(!dossier.exists()){
            if(dossier.mkdir()){
                System.out.println("Dossier créé : ");
                System.out.println(adresseDuDossier);
            } else {
                System.out.println("Echec de la création du dossier en : ");
                System.out.println(adresseDuDossier);
            }
        }
    }
}
