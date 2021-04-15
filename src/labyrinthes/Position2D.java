/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.RangMemoire;

/**
 *
 * @author nico
 */
public class Position2D {
    public static Position POSITION_SORTIE;
    private final int cursorLigne;
    private final RangMemoire cursorColonne;
    private final int cursorLigneParent;
    private final RangMemoire cursorColonneParent;
    private final boolean Feuille;
    private final boolean Intersection;
    private final boolean Exit;

    public Position2D(ArbreLabyrinthe arbre, int cursorLigne, RangMemoire cursorColonne, RangMemoire cursorColonneParent) {
        this.cursorLigne = cursorLigne;
        this.cursorColonne = cursorColonne;
        this.cursorLigneParent = cursorLigne - 1;
        this.cursorColonneParent = cursorColonneParent;
        this.Feuille = arbre.isFeuille();
        this.Intersection = arbre.isIntersection();
        this.Exit = arbre.getEtiquette().equals(POSITION_SORTIE);
        //this.Exit = true;
        //this.Exit = false;
        //this.Exit = (arbre.getEtiquette().getX()==3 && arbre.getEtiquette().getY()==4) || (arbre.getEtiquette().getX()==5 && arbre.getEtiquette().getY()==4) || (arbre.getEtiquette().getX()==5 && arbre.getEtiquette().getY()==2) || (arbre.getEtiquette().getX()==4 && arbre.getEtiquette().getY()==4) || (arbre.getEtiquette().getX()==3 && arbre.getEtiquette().getY()==2) || (arbre.getEtiquette().getX()==1 && arbre.getEtiquette().getY()==3);
    }


    public int getCursorLigne() {
        return cursorLigne;
    }

    public RangMemoire getCursorColonne() {
        return cursorColonne;
    }

    public int getCursorLigneParent() {
        return cursorLigneParent;
    }

    public RangMemoire getCursorColonneParent() {
        return cursorColonneParent;
    }

    public boolean isFeuille() {
        return Feuille;
    }

    public boolean isIntersection() {
        return Intersection;
    }

    public boolean isExit() {
        return Exit;
    }
    

    @Override
    public String toString() {
        return "(" + cursorLigne + "," + cursorColonne.getRangReel()+ "," + cursorColonne + ')';
    }
    
    
}
