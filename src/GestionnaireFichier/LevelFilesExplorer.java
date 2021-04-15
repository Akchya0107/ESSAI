/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireFichier;

import StructureInformatique.Couple;
import StructureInformatique.Triplet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import labyrinthes.ArbreLabyrinthe;
import labyrinthes.Level;
import labyrinthes.MatriceLabyrinthe;
import labyrinthes.Position;

/**
 *
 * @author nico
 */
public class LevelFilesExplorer extends Explorer {
    private static String ADRESSE_DOSSIER_LEVEL = getADRESSE_DOSSIER_JEU() +"levels"+ System.getProperty("file.separator");
    
    public static String getADRESSE_DOSSIER_LEVEL(){
        return ADRESSE_DOSSIER_LEVEL;
    }
    
    protected static void setADRESSE_DOSSIER_LEVEL(String nouvelleAdresseDossier){
        String separation = System.getProperty("file.separator");
        String adresseDossierJeu = getADRESSE_DOSSIER_JEU();
        if(!nouvelleAdresseDossier.startsWith(adresseDossierJeu)){
            nouvelleAdresseDossier = adresseDossierJeu + nouvelleAdresseDossier;
        }
        if(nouvelleAdresseDossier.endsWith(separation)){
            ADRESSE_DOSSIER_LEVEL = nouvelleAdresseDossier;
        } else {
            ADRESSE_DOSSIER_LEVEL = nouvelleAdresseDossier + separation;
        }
    }
    
    public static void saveLevel(Level level){
        checkIfFolderExist();
        String nomDuFichier = level.getNomDuFichier();
        String contenuDuFichier = level.getMetaData();
        contenuDuFichier = contenuDuFichier + level.getMatriceDuNiveau().toString();
        try(FileWriter fichier = new FileWriter(ADRESSE_DOSSIER_LEVEL + nomDuFichier)){
            fichier.write(contenuDuFichier);
            fichier.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        
        // sauvegarde du level
    }
    
    public static Level getLevel(String adresse){
        boolean leFichierEstComplet = true;
        Triplet<MatriceLabyrinthe,String,Couple> dataMatrice = readMatriceInFile(adresse);
        MatriceLabyrinthe matriceDuNiveau = dataMatrice.getFirst();
        ArbreLabyrinthe arbreDuNiveau;
        String titreDuNiveau;
        Couple positionEntreeSortie = dataMatrice.getLast();
        Object[] metaData = readMetaData(dataMatrice.getSecond());
        if(metaData[0]!=null){
            arbreDuNiveau = (ArbreLabyrinthe) metaData[0];
        } else {
            arbreDuNiveau = new ArbreLabyrinthe(matriceDuNiveau, dataMatrice.getLast());
            leFichierEstComplet = false;
        }
        if(metaData[1]!=null){
            titreDuNiveau = (String) metaData[1];
        } else {
            titreDuNiveau = "Donnée de titre manquante.";
            leFichierEstComplet = false;
        }
        Level niveauEnCours = new Level(matriceDuNiveau,arbreDuNiveau,titreDuNiveau, positionEntreeSortie, adresse);
        if(!leFichierEstComplet){
            saveLevel(niveauEnCours);
        }
        return niveauEnCours;
    }
    
    
    /**
     * La fonction li un fichier pour en extraire la matriceLabyrinthe et les metaData.
     * 
     * @param adresseDuFichier adresse relative dans le dossier des Levels !
     * @return Triplet de (MatriceLabyrinthe, String metaData, Couple de (Position positionEntree positionSortie)) metaData = "" si non trouvées.
     */
    private static Triplet<MatriceLabyrinthe,String,Couple> readMatriceInFile(String adresseDuFichier){
        MatriceLabyrinthe matrice =  new MatriceLabyrinthe();
        int rangEntreeLigne = Integer.MIN_VALUE;
        int rangEntreeColonne = Integer.MIN_VALUE;
        int rangSortieLigne = Integer.MIN_VALUE;
        int rangSortieColonne = Integer.MIN_VALUE;
        String metaData = "";
        try(BufferedReader fichier = new BufferedReader( new FileReader(ADRESSE_DOSSIER_LEVEL + adresseDuFichier))){
            int i = -1;
            String caseIJ = "";
            while(fichier.ready()){
                String ligne;
                ligne = fichier.readLine();
                while(ligne.startsWith("#")&& fichier.ready()){
                    metaData = metaData + ligne + System.getProperty("line.separator");
                    ligne = fichier.readLine();
                }
                matrice.addLigne();
                if(ligne.startsWith("e")){
                    rangEntreeLigne = Integer.max(i,0);
                    rangEntreeColonne = 0;
                    ligne = ligne.substring(1);
                } else if(ligne.startsWith("s")){
                    rangSortieLigne = Integer.max(i,0);
                    rangSortieColonne = 0;
                    ligne = ligne.substring(1);
                } else {
                    ligne = ligne.substring(1);
                }
                int longueurLigne = ligne.length(); //la longueur de ma ligne évolue dans la boucle for
                for(int j=0; j<longueurLigne; j+=2){
                    if(ligne.startsWith("e ")){           //e ou s en haut ou en bas
                        rangEntreeLigne = Integer.max(i,0);
                        rangEntreeColonne = j/2;
                        caseIJ = "E";
                    } else if(ligne.startsWith("s ")){
                        rangSortieLigne = Integer.max(i,0);
                        rangSortieColonne = j/2;
                        caseIJ = "E";
                    }else if(ligne.startsWith("e|")){
                        rangEntreeLigne = Integer.max(i,0);
                        rangEntreeColonne = j/2;
                        caseIJ = "";
                    } else if(ligne.startsWith("s|")){
                        rangSortieLigne = Integer.max(i,0);
                        rangSortieColonne = j/2;
                        caseIJ = "";
                    } else if(ligne.startsWith(" e")){         // e ou s à droite
                        rangEntreeLigne = Integer.max(i,0);
                        rangEntreeColonne = j/2;
                        caseIJ = "S";
                    } else if(ligne.startsWith(" s")){
                        rangSortieLigne = Integer.max(i,0);
                        rangSortieColonne = j/2;
                        caseIJ = "S";
                    } else if(ligne.startsWith("_e")){
                        rangEntreeLigne = Integer.max(i,0);
                        rangEntreeColonne = j/2;
                        caseIJ = "";
                    } else if(ligne.startsWith("_s")){
                        rangSortieLigne = Integer.max(i,0);
                        rangSortieColonne = j/2;
                        caseIJ = "";
                    } else if(ligne.startsWith("_ ")){       // les autres configurations
                        caseIJ = "E";
                    } else if(ligne.startsWith(" |")){
                        caseIJ = "S";
                    } else if(ligne.startsWith("  ")){
                        caseIJ = "SE";
                    } else if(ligne.startsWith("_|")){
                        caseIJ = "";
                    } else {                                // on ne sais jammais
                        ligne = "..";
                    }
                    ligne = ligne.substring(2);
                    if(i<0){
                        matrice.addColonne();
                    } else {
                        matrice.set(i, j/2, caseIJ);
                    }
                }
                i+=1;
            }
            matrice.addEntree(rangEntreeLigne, rangEntreeColonne);
            matrice.addSortie(rangSortieLigne, rangSortieColonne);
            matrice.removeLigne();
            fichier.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        Position positionEntree = new Position(rangEntreeLigne,rangEntreeColonne);
        Position positionSortie = new Position(rangSortieLigne,rangSortieColonne);
        Couple<Position,Position> positionEntreeSortie = new Couple(positionEntree,positionSortie);
        return new Triplet(matrice,metaData,positionEntreeSortie);
    }
    
    
    /**
     * La méthode est conçue pour pouvoir facilement rajouter plus de metaData.
     * @param meta 
     * @return [0] renvoie un arbre <br></br> [1] renvoie le titre  <br></br> Si un élément est manquant [i] renvoie null.
     */
    private static Object[] readMetaData(String meta){
        meta = meta.replace("#","");
        meta = meta.replace(" ","");
        meta = meta.toLowerCase();
        String champs[] = meta.split(System.getProperty("line.separator"));
        Object data[] = new Object[2];
        for(int i=0;i<champs.length;i+=1){
            if(champs[i].startsWith("codearbre:")){
                data[0] = new ArbreLabyrinthe(champs[i].replace("codearbre:", ""));
            }
            if(champs[i].startsWith("titre:")){
                data[1] = champs[i].replace("titre:","");
            }
        }
        return data;
    }
    
    public static String writeMetaData(Level level){
        String metaData = "";
        if(!level.getTitre().isBlank()){
            metaData = metaData + "# Titre : " + level.getTitre() + System.getProperty("line.separator");
        }
        if(level.getArbreDuNiveau()!=null){
            metaData = metaData + "# Code Arbre : " + level.getArbreDuNiveau() + System.getProperty("line.separator");
        }
        return metaData;
    }
    
    public static String genererNouveauNomDeFichier(){
        checkIfFolderExist();
        File repertoire = new File(ADRESSE_DOSSIER_LEVEL);
        File[] files = repertoire.listFiles();
        String nomDuFichier;
        if(files!=null && files.length>0){
            nomDuFichier = files[files.length-1].getName().replace("Labyrinthe.txt", "");
            nomDuFichier = Integer.toString(Integer.parseInt(nomDuFichier) + 1);
            nomDuFichier = nomDuFichier + "Labyrinthe.txt";
            
        } else {
            nomDuFichier = "0Labyrinthe.txt";
        }
        while(nomDuFichier.length()<= 5 + "Labyrinthe.txt".length()){
            nomDuFichier = "0" + nomDuFichier;
        }
        return  nomDuFichier;
    }
    
    public static void checkIfFolderExist(){
        Explorer.checkIfFolderExist(ADRESSE_DOSSIER_LEVEL);
    }
}
