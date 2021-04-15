/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

import labyrinthes.Position;

/**
 *
 * @author nico
 */
public enum DirectionRelative {
    GAUCHE, HAUT, DROITE, BAS;
    
    public DirectionRelative precedent(){
        DirectionRelative[] temp = DirectionRelative.values();
        return temp[(this.ordinal()+3)%temp.length];
    }
    
    public DirectionRelative suivant(){
        DirectionRelative[] temp = DirectionRelative.values();
        return temp[(this.ordinal()+1)%temp.length];
    }
    
    public static DirectionRelative init(Position positionEntree, Matrice matrice){
        int rangEntreeLigne = positionEntree.getLigne();
        int rangEntreeColonne = positionEntree.getColonne();
        int rangMaxColonne = matrice.shape("colonne");
        if(rangEntreeColonne==0){
            return DirectionRelative.DROITE;
        } else if (rangEntreeColonne==rangMaxColonne-1){
            return DirectionRelative.GAUCHE;
        } else if(rangEntreeLigne==0){
            return DirectionRelative.BAS;
        } else {
            return DirectionRelative.HAUT;
        }
    }
}
