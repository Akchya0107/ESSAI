/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

import java.util.ArrayList;
import labyrinthes.Mur;

/**
 *
 * @author nico
 */
public class ZoneConnexe {
    private ArrayList<Mur> mursQuiDelimiteLaZone;
    
    public ZoneConnexe(Mur murQuiDelimiteLaZone){
        this.mursQuiDelimiteLaZone = new ArrayList<>();
        this.mursQuiDelimiteLaZone.add(murQuiDelimiteLaZone);
    }
    
    public void merge(ZoneConnexe autreZone){
        for(var mur : autreZone.mursQuiDelimiteLaZone){
            if(this.mursQuiDelimiteLaZone.contains(mur)){
                mur.out();
            } else {
                mur.mettreAJour(autreZone,this);
                this.add(mur);
            }
        }
    }
    
    public void add(Mur mur){
        this.mursQuiDelimiteLaZone.add(mur);
    }
}
