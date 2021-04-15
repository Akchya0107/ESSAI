/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

import java.util.ArrayList;

/**
 *
 * @author nico
 */
public class RangMemoire {
    
    private static ArrayList<RangMemoire> LISTE_DES_INSTANCES = new ArrayList<>();
    private int rangAbsolu;
    private int rangRelatif;
    private int profondeur;
    
    public RangMemoire(int rangAbsolu, int rangRelatif, int profondeur){
        this.rangAbsolu = rangAbsolu;
        this.rangRelatif = rangRelatif;
        this.profondeur = profondeur;
        LISTE_DES_INSTANCES.add(this);
    }
    
    public RangMemoire(){
        this(0,0,0);
    }
    
    public static void clear(){
        LISTE_DES_INSTANCES.clear();
    }
    
    public int getRangDuPere() {
        return this.rangAbsolu;
    }
    
    public int getRangReel(){
        return this.rangAbsolu + this.rangRelatif;
    }
    
    public int getRangRelatif(){
        return this.rangRelatif;
    }

    public int getProfondeur() {
        return this.profondeur;
    }
    
    public void valider(){
        this.rangAbsolu = this.rangAbsolu + this.rangRelatif;
        this.rangRelatif = 0;
    }
    
    
    // priver ?
    public void incrOnly(int increment) {
        this.rangAbsolu += increment;
    }
    
    public void incr(){
        int indexDuRangMemoire = LISTE_DES_INSTANCES.indexOf(this);
        for(int i=0; i<indexDuRangMemoire;i+=1){
            RangMemoire rangEnCours = LISTE_DES_INSTANCES.get(i);
            if(this.compareTo(rangEnCours)<=0 && rangEnCours.profondeur<this.profondeur){
                rangEnCours.incrOnly(1);
            }
        }
        this.incrOnly(1);
        for(int i=indexDuRangMemoire+1; i<LISTE_DES_INSTANCES.size();i+=1){
            RangMemoire rangEnCours = LISTE_DES_INSTANCES.get(i);
            if(rangEnCours.profondeur<=this.profondeur){
                rangEnCours.incrOnly(1);
            }
        }
    }
    
    public int rangDuPerePlus(int i){
        return this.getRangDuPere() +i;
    }
    
    public int rangReelPlus(int i){
        return this.getRangReel() +i;
    }
    
    public int compareTo(RangMemoire r){
        return this.getRangDuPere() - r.getRangReel();
    }
    
    public int compareTo(Object obj){
        if(obj.getClass()==this.getClass()){
            RangMemoire r = (RangMemoire) obj;
            return this.compareTo(r);
        } else {
            return Integer.MIN_VALUE;
        }
        
    }
    
    public RangMemoire copy(){
        return new RangMemoire(this.rangAbsolu, this.rangRelatif, this.profondeur);
    }
    
    @Override
    public String toString(){
        return "" + LISTE_DES_INSTANCES.indexOf(this);
    }
}
