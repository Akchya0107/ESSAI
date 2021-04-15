/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

/**
 *
 * @author ENSMM
 */
public enum DirectionAbsolue {
    OUEST, NORD,EST, SUD;
    
    public DirectionAbsolue conversiontRelativeAbsolue(DirectionAbsolue directionAbsoluePrecedente, DirectionRelative directionEnCours){
        DirectionAbsolue[] directionsAbsolues = DirectionAbsolue.values();
        return directionsAbsolues[(this.ordinal() + directionEnCours.ordinal() + 2)%3];
    }
}
