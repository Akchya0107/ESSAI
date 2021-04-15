/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.File;
import StructureInformatique.ZoneConnexe;
import java.util.ArrayList;

/**
 *
 * @author nico
 */
public class Mur {
    private static ArrayList<Mur> ENSEMBLE_DES_MURS = new ArrayList<>();
    private final int ligne;    
    private final int colonne;
    private final char orientation;
    private ZoneConnexe zone1;
    private ZoneConnexe zone2;

    public Mur(int ligne, int colonne, char orientation, ZoneConnexe zone1, ZoneConnexe zone2) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.orientation = orientation;
        this.zone1 = zone1;
        zone1.add(this);
        this.zone2 = zone2;
        zone2.add(this);
        ENSEMBLE_DES_MURS.add(this);
    }
    
    public Mur(int ligne, int colonne, char orientation, ZoneConnexe zone1) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.orientation = orientation;
        this.zone1 = zone1;
        zone1.add(this);
        this.zone2 = new ZoneConnexe(this);
        ENSEMBLE_DES_MURS.add(this);
    }
    
    public Mur(int ligne, int colonne, char orientation) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.orientation = orientation;
        this.zone1 = new ZoneConnexe(this);
        this.zone2 = new ZoneConnexe(this);
        ENSEMBLE_DES_MURS.add(this);
    }
    
    public void out(){
        ENSEMBLE_DES_MURS.remove(this);
    }
    
    public void mettreAJour(ZoneConnexe ancienneZone, ZoneConnexe nouvelleZone){
        if(this.zone1==ancienneZone){
            this.zone1 = nouvelleZone;
        } else {
            this.zone2 = nouvelleZone;
        }
    }
    
    //Fourni la liste des murs qui délimitent 2 zones différentes
    private static void initialiserLabyrinthe(int hauteur, int largeur){
        var file = new File<ZoneConnexe>();
        Mur murDEnBas;
        Mur murDeDroite;
        ZoneConnexe zoneEnCours=null;
        ZoneConnexe zoneADroite;
        ZoneConnexe zoneEnBas;
        
        if(hauteur>1){
            murDEnBas = new Mur(0,0,'S');
            zoneEnBas = murDEnBas.zone2;
            file.push(zoneEnBas);
            if(largeur>1){
                murDeDroite = new Mur(0,0,'E', murDEnBas.zone1);
                zoneADroite = murDeDroite.zone2;
                zoneEnCours = zoneADroite;
            }
        } else{
            if(largeur>1){
                murDeDroite = new Mur(0,0,'E');
                zoneADroite = murDeDroite.zone2;
                zoneEnCours = zoneADroite;
            }
        }
        
        for(int j=1;j<largeur;j+=1){
            if(0!=hauteur-1){
                murDEnBas = new Mur(0,j,'S',zoneEnCours);
                zoneEnBas = murDEnBas.zone2;
                file.push(zoneEnBas);
            }
            if(j!=largeur-1){
                murDeDroite = new Mur(0,j,'E',zoneEnCours);
                zoneADroite = murDeDroite.zone2;
                zoneEnCours = zoneADroite;
            }
        }
        for(int i=1;i<hauteur;i+=1){
            zoneEnCours = file.pop();
            for(int j=0;j<largeur;j+=1){
                if(i!=hauteur-1){
                    murDEnBas = new Mur(i,j,'S',zoneEnCours);
                    zoneEnBas = murDEnBas.zone2;
                    file.push(zoneEnBas);                    
                }
                if(j!=largeur-1){
                    zoneADroite = file.pop();
                    murDeDroite = new Mur(i,j,'E',zoneEnCours,zoneADroite);
                    zoneEnCours = murDeDroite.zone2;
                }
            }
        }
    }
    
    private static MatriceLabyrinthe supprimerUnMur(MatriceLabyrinthe matrice){
        int nbDeMurs = ENSEMBLE_DES_MURS.size();
        Mur murSelectionne = ENSEMBLE_DES_MURS.remove((int)(Math.random()*nbDeMurs));
        murSelectionne.zone1.merge(murSelectionne.zone2);
        String caseEnCours = matrice.get(murSelectionne.ligne, murSelectionne.colonne);
        caseEnCours =caseEnCours + Character.toString(murSelectionne.orientation);
        matrice.set(murSelectionne.ligne, murSelectionne.colonne,caseEnCours);
        return matrice;
    }
    
    /**
     * Il vaut mieux utiliser la fonction genererNouvelleMatrice de la Class MatriceLabyrinthe. Cette matrice n'a ni entrée ni sortie.
     * @param hauteur
     * @param largeur
     * @return 
     */
    protected static MatriceLabyrinthe genererNouvelleMatrice(int hauteur, int largeur){
        MatriceLabyrinthe matrice = new MatriceLabyrinthe(hauteur,largeur);
        initialiserLabyrinthe(hauteur, largeur);
        while(!ENSEMBLE_DES_MURS.isEmpty()){
            matrice = supprimerUnMur(matrice);
        }
        return matrice;
    }
}
