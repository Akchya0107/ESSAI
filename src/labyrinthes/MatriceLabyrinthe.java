/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.Couple;
import StructureInformatique.Matrice;

/**
 *
 * @author nico
 */
public class MatriceLabyrinthe extends Matrice<String>{
    // la différence entre les deux est la méthode toString()
    // toString() renvoie directement une chaine qui peut être enregistrée dans un fichier
    
    
    // je n'ai pas réussi à faire fonctionner le cast (MatriceLabyrinthe)
    // le code suivant ne fonctionne pas :
    // Matrice ancienne matrice = new Matrice();
    // MatriceLabyrinthe nouvelleMatrice = (MatriceLabyrinthe) ancienne matrice;
    //  donc à la place je fait :
    // MatriceLabyrinthe nouvelleMatrice = new MatriceLabyrinthe(ancienneMatrice);
    // j'utilise le constructeur suivant
    public MatriceLabyrinthe(Matrice<String> m){
        super(m);
    }
    
    public MatriceLabyrinthe(){
        super();
    }
    
    /**
     * Créer une matrice vide, toutes les cases sont initialisées avec "".
     * @param nbLigne
     * @param nbColonne 
     */
    public MatriceLabyrinthe(int nbLigne, int nbColonne){
        super(nbLigne,nbColonne,"");
    }
    
    public static Couple<MatriceLabyrinthe,Couple<Position,Position>> genererNouvelleMatrice(int hauteur, int largeur){
        Position positionEntree = Position.genererNouvellePosition(hauteur, largeur);
        Position positionSortie = Position.genererNouvellePosition(hauteur, largeur, positionEntree);
        Couple<Position,Position> positionEntreeSortie = new Couple(positionEntree,positionSortie);
        MatriceLabyrinthe matrice = Mur.genererNouvelleMatrice(hauteur, largeur);
        matrice.addEntree(positionEntree);
        matrice.addSortie(positionSortie);
        return new Couple(matrice,positionEntreeSortie);
    }
    
    @Override
    public String toString(){
        int nbColonne = this.shape("colonne");
        int nbLigne = this.shape("ligne");
        //première ligne et première colonne
        String resultat = " _ ";
        //première ligne
        for(int colonne=1;colonne<nbColonne;colonne+=1){
            String caseEnCours = this.get(0, colonne);
            if(caseEnCours.contains("e") && colonne!=nbColonne-1){
                resultat = resultat + "e ";
                caseEnCours = caseEnCours.replace("e", "");
            } else if(this.get(0, colonne).contains("s") && colonne!=nbColonne-1){
                resultat = resultat + "s ";
                caseEnCours = caseEnCours.replace("s", "");
            } else {
                resultat = resultat + "_ ";
            }
        }
        
        
        //le reste des lignes
        for(int ligne=0;ligne<nbLigne;ligne+=1){
            resultat = resultat + System.getProperty("line.separator");
            //le reste des colonnes
            for(int colonne=0;colonne<nbColonne;colonne+=1){
                String caseEnCours = this.get(ligne, colonne);
                // première colonne
                if(colonne==0){
                    if(caseEnCours.contains("e")){
                        resultat = resultat + "e";
                        caseEnCours = caseEnCours.replace("e", "");
                    } else if(caseEnCours.contains("s")){
                        resultat = resultat + "s";
                        caseEnCours = caseEnCours.replace("s", "");
                    } else {
                        resultat = resultat + "|";
                    }
                }
                
                switch (caseEnCours) {
                    case "SE":
                    case "ES":
                        resultat = resultat + "  ";
                        break;
                    case "S":
                        resultat = resultat + " |";
                        break;
                    case "E":
                        resultat = resultat + "_ ";
                        break;
                    
                    case "Se":
                    case "eS":
                        if(ligne==0 && colonne!=nbColonne-1){
                            resultat = resultat + " |";
                        } else {
                            resultat = resultat + " e";
                        }
                        break;

                    case "Ss":
                    case "sS":
                        if(ligne==0 && colonne!=nbColonne-1){
                            resultat = resultat + " |";
                        } else {
                            resultat = resultat + " s";
                        }
                        break;

                        
                    case "Ee":
                    case "eE":
                        if(ligne==0){
                            resultat = resultat + "_ ";
                        } else {
                            resultat = resultat + "e ";
                        }
                        break;

                    case "Es":
                    case "sE":
                        if(ligne==0){
                            resultat = resultat + "_ ";
                        } else {
                            resultat = resultat + "s ";
                        }
                        break;

                    case "e":
                        if(colonne==nbColonne-1){
                            resultat = resultat + "_e";
                        } else {
                            if(ligne==0){
                                resultat = resultat + "_|";
                            } else {
                                resultat = resultat + "e|";
                            }
                        }
                        break;
                    case "s":
                        if(colonne==nbColonne-1){
                            resultat = resultat + "_s";
                        } else {
                            if(ligne==0){
                                resultat = resultat + "_|";
                            } else {
                                resultat = resultat + "s|";
                            }
                        }
                        break;
                    default:
                        resultat = resultat + "_|";
                        break;
                }
            }
        }
        return resultat;
    }
    
    
    private void addStringToCase(int ligne, int colonne,String ajout){
        this.set(ligne, colonne, this.get(ligne, colonne) + ajout);
    }
    
    private void addStringToCase(Position position,String ajout){
        this.set(position.getLigne(), position.getColonne(), this.get(position.getLigne(), position.getColonne()) + ajout);
    }
    
    public void addEntree(int ligne, int colonne){
        this.addStringToCase(ligne, colonne, "e");
    }
    
    public void addEntree(Position positionEntree){
        this.addStringToCase(positionEntree, "e");
    }
    
    public void addSortie(int ligne, int colonne){
        this.addStringToCase(ligne, colonne, "s");
    }
    
    public void addSortie(Position positionSortie){
        this.addStringToCase(positionSortie, "s");
    }
    
    @Override
    public boolean isEmpty(int ligne, int colonne){
        return this.get(ligne, colonne).equals("");
    }
    
    
    //il fau faire l'affichage ...
}
