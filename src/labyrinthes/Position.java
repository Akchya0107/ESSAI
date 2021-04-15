/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.DirectionAbsolue;
import StructureInformatique.DirectionRelative;
import StructureInformatique.Matrice;

/**
 *
 * @author nico
 */
public class Position {
    
    
    private final int ligne;
    private final int colonne;
    
    public Position(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    
    public static Position genererNouvellePosition(int nbLigneMatrice, int nbColonneMatrice){
        int ligne;
        int colonne;
        if(Math.random()<0.5){
            if(Math.random()<0.5){
                ligne = 0;
            } else {
                ligne = nbLigneMatrice-1;
            }
            colonne = (int) (Math.random() * nbColonneMatrice);
        } else {
            if(Math.random()<0.5){
                colonne = 0;
            } else {
                colonne = nbColonneMatrice-1;
            }
            ligne = (int) (Math.random() * nbLigneMatrice);
        }
        return new Position(ligne, colonne);
    }
    
    public static Position genererNouvellePosition(int borneSupLigne, int borneSupColonne, Position positionInterdite){
        if(borneSupLigne + borneSupColonne >2 ){
            Position nouvellePosition = genererNouvellePosition(borneSupLigne, borneSupColonne);
            while(nouvellePosition.equals(positionInterdite)){
                nouvellePosition = genererNouvellePosition(borneSupLigne, borneSupColonne);
            }
            return nouvellePosition;
        } else {
            return positionInterdite;
        }
    }
    
    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
    
    
    
    public Position copy(){
        return new Position(this.ligne,this.colonne);
    }
    
    @Override
    public String toString(){
        return "(" + ligne + "," + colonne + ")";
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj.getClass().equals(this.getClass())){
            Position p = (Position) obj;
            return p.ligne == this.ligne && p.colonne == this.colonne;
        } else{
            return false;
        }
    }
    
    public static Position parsePosition(String s){
        Position position;
        boolean estLaSortie = false;
        s = s.replace("(", "");
        s = s.replace(")", "");
        s = s.replace(";", ",");
        if(s.startsWith("s")){
            estLaSortie = true;
            s = s.replace("s", "");
        }
        if(s.startsWith("e")){
            s = s.replace("e", "");
        }
        String champ[];
        champ = s.split(",");
        position = new Position(Integer.parseInt(champ[0]),Integer.parseInt(champ[1]));
        if(estLaSortie){
            Position2D.POSITION_SORTIE = position;
        }
        return position;
    }

    public Position next(DirectionRelative prochaineDirection, MatriceLabyrinthe matrice){
        switch (prochaineDirection) {
            case GAUCHE:
                if(matrice.get(this.ligne, this.colonne-1).contains("E")){
                    return new Position(this.ligne,this.colonne-1);
                }else {
                    return null;
                }
            case HAUT:
                if(matrice.get(this.ligne-1,this.colonne).contains("S")){
                    return new Position(this.ligne-1,this.colonne);
                }else {
                    return null;
                }
            case DROITE:
                if(matrice.get(this.ligne,this.colonne).contains("E")){
                    return new Position(this.ligne,this.colonne+1);
                }else {
                    return null;
                }
            default:
                if(matrice.get(this.ligne,this.colonne).contains("S")){
                    return new Position(this.ligne+1,this.colonne);
                }else {
                    return null;
                }
        }
    }
    
    public boolean hasNext(DirectionRelative prochaineDirection, Matrice matrice){
        switch (prochaineDirection) {
            case GAUCHE:
                return 0<=this.colonne-1 && this.colonne-1<matrice.shape("colonne");
            case HAUT:
                return 0<=this.ligne-1 && this.ligne-1<matrice.shape("ligne");
            case DROITE:
                return 0<=this.colonne+1 && this.colonne+1<matrice.shape("colonne");
            default:
                return 0<=this.ligne+1 && this.ligne+1<matrice.shape("ligne");
        }
    }
    
    public Position next(DirectionAbsolue prochaineDirection){
        switch (prochaineDirection) {
            case OUEST:
                return new Position(this.ligne, this.colonne-1);
            case NORD:
                return new Position(this.ligne-1, this.colonne);
            case EST:
                return new Position(this.ligne, this.colonne+1);
            default:
                return new Position(this.ligne+1, this.colonne);
        }
    }
    
    public boolean hasNext(DirectionAbsolue prochaineDirection, MatriceLabyrinthe matrice){
        switch (prochaineDirection) {
            case OUEST:
                if(0<=this.colonne-1 && this.colonne-1<matrice.shape("colonne")){
                    return matrice.get(this.ligne, this.colonne-1).contains("E");
                } else {
                    return false;
                }
            case NORD:
                if(0<=this.ligne-1 && this.ligne-1<matrice.shape("ligne")){
                    return matrice.get(this.ligne-1, this.colonne).contains("S");
                } else {
                    return false;
                }
            case EST:
                if(0<=this.colonne+1 && this.colonne+1<matrice.shape("colonne")){
                    return matrice.get(this.ligne, this.colonne).contains("E");
                } else {
                    return false;
                }
            default:
                if(0<=this.ligne+1 && this.ligne+1<matrice.shape("ligne")){
                    return matrice.get(this.ligne, this.colonne).contains("S");
                } else {
                    return false;
                }
        }
    }
    
    
}
