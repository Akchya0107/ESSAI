/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.Couple;
import GestionGraphique.AffichageLevel;
import GestionnaireFichier.Explorer;
import GestionnaireFichier.LevelFilesExplorer;

/**
 *
 * @author nico
 */
public class Level {
    
    
    
    private final MatriceLabyrinthe matriceDuNiveau;
    private final ArbreLabyrinthe arbreDuNiveau;
    private String titre;
    private final Position positionEntree;
    private final Position positionSortie;
    private final String nomDuFichier;
    
    

    /**
     * Uniquement pour les niveaux <b>qui existent déjà</b> dans les fichiers.
     * @param matriceDuNiveau
     * @param arbreDuNiveau
     * @param titre
     * @param positionEntreeSortie
     * @param nomDuFichier 
     */
    public Level(MatriceLabyrinthe matriceDuNiveau, ArbreLabyrinthe arbreDuNiveau, String titre, Couple<Position,Position> positionEntreeSortie, String nomDuFichier) {
        this.titre = titre;
        this.arbreDuNiveau = arbreDuNiveau;
        this.matriceDuNiveau = matriceDuNiveau;
        this.positionEntree = positionEntreeSortie.getFirst();
        this.positionSortie = positionEntreeSortie.getLast(); 
        this.nomDuFichier = nomDuFichier;
    }
    
    
    
    /**
     * Uniquement pour <b>la creation</b> d'un niveau généré, il est enregistré imédiatement après dans les fichiers.
     * @param titre
     * @param hauteur
     * @param largeur 
     */
    public Level(String titre, int hauteur, int largeur){
        this.titre = titre;
        Couple<MatriceLabyrinthe, Couple<Position, Position>> dataMatrice = MatriceLabyrinthe.genererNouvelleMatrice(hauteur, largeur);
        MatriceLabyrinthe matrice = dataMatrice.getFirst();
        ArbreLabyrinthe arbre = new ArbreLabyrinthe(matrice,dataMatrice.getLast());
        this.arbreDuNiveau = arbre;
        this.matriceDuNiveau = matrice;
        this.positionEntree = dataMatrice.getLast().getFirst();
        this.positionSortie = dataMatrice.getLast().getLast();
        this.nomDuFichier = LevelFilesExplorer.genererNouveauNomDeFichier();
        LevelFilesExplorer.saveLevel(this);
    }
    
    /**
     * Uniquement pour <b>la creation</b> d'un niveau généré, il est enregistré imédiatement après dans les fichiers.
     * @param hauteur
     * @param largeur 
     */
    public Level (int hauteur, int largeur){
        this(generateurDeNom(),hauteur,largeur);
    }

    public static String generateurDeNom(){
        return Explorer.generateurDeNom();
    }
    
    public String getNomDuFichier() {
        return nomDuFichier;
    }

    public String getTitre() {
        return titre;
    }

    public ArbreLabyrinthe getArbreDuNiveau() {
        return arbreDuNiveau;
    }

    public MatriceLabyrinthe getMatriceDuNiveau() {
        return matriceDuNiveau;
    }

    public Position getPositionEntree() {
        return positionEntree;
    }

    public Position getPositionSortie() {
        return positionSortie;
    }
    
    public String getMetaData(){
        return LevelFilesExplorer.writeMetaData(this);
    }
    
    public void lancerPartie(){
        
    }
    
}
