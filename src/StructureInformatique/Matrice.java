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
 * @param <Element>
 */
public class Matrice <Element>{
    private final ArrayList<ArrayList<Element>> matrice;
    private final static int LARGEUR_COLONNE = 17;

    public Matrice(Element e) {
        ArrayList<ArrayList<Element>> mat;
        mat = new ArrayList<>();
        mat.add(new ArrayList<>());
        mat.get(0).add(e);
        this.matrice = mat;
    }
    
    public Matrice(Matrice m){
        this.matrice =m.matrice;
    }
    
    
    public Matrice() {
        ArrayList<ArrayList<Element>> mat;
        mat = new ArrayList<>();
        this.matrice = mat;
    }
    
    public Matrice(int nbLigne,int nbColonne, Element elementInit){
        this();
        for(int i=0;i<nbLigne;i+=1){
            ArrayList<Element> nouvelleLigne = new ArrayList<>();
            for(int j=0;j<nbColonne;j+=1){
                nouvelleLigne.add(elementInit);
            }
            this.matrice.add(nouvelleLigne);
        }
    }
    
    public Couple<Integer,Integer> shape(){
        if(matrice.size()>0){
            return new Couple(matrice.size(),matrice.get(0).size());
        } else {
            return new Couple(matrice.size(),0);
        }
    }
    public int shape(String param){
        switch (param) {
            case "ligne":
                return matrice.size();
            case "colonne":
                if(matrice.size()>0){
                    return matrice.get(0).size();
                } else {
                    return 0;
                }
            default:
                return -1;
        }
    }
    
    public void addLigne(){
        ArrayList<Element> nouvelleLigne = new ArrayList<>();
        for(int i =0; i<this.shape("colonne"); i+=1){
            nouvelleLigne.add(null);
        }
        matrice.add(nouvelleLigne);
    }
    
    public void removeLigne(){
        this.matrice.remove(this.matrice.size()-1);
    }
    
    public ArrayList<Element> getLigne(int i){
        return this.matrice.get(i);
    }
    
    public void addColonne(int rangColonne){
        if(this.matrice.isEmpty()){
            this.addLigne();
        }
        for(int i =0; i<this.shape("ligne"); i+=1){
            this.matrice.get(i).add(rangColonne, null);
        }
    }
    
    public void addColonne(){
        if(this.matrice.isEmpty()){
            this.addColonne(0);
        }else{
            this.addColonne(this.matrice.get(0).size());
        }
    }
    
    public Element get(int rangLigne,int rangColonne){
        return this.matrice.get(rangLigne).get(rangColonne);
    }
    public void set(int rangLigne,int rangColonne, Element e){
        this.matrice.get(rangLigne).set(rangColonne, e);
    }
    
    @Override
    public String toString(){
        String resultat = "Matrice = { " + System.getProperty("line.separator");
        for(ArrayList<Element> ligne : this.matrice){
            resultat = resultat + "|";
            for(Element e : ligne){
                if(e==null){
                    for(int i=0;i<LARGEUR_COLONNE;i+=1){
                        resultat = resultat + " ";
                    }
                } else {
                    int whiteSpaces = LARGEUR_COLONNE-e.toString().length();
                    resultat = resultat + e;
                    if(whiteSpaces>0){
                        for(int i = whiteSpaces;i>0;i-=1){
                            resultat = resultat + " ";
                        }
                    }
                }
                resultat = resultat + "|";
            }
            resultat = resultat + System.getProperty("line.separator");
        }
        return resultat + "}";
    }

    public ArrayList<ArrayList<Element>> getMatrice() {
        return matrice;
    }
    
    public boolean isEmpty(int ligne, int colonne){
        return this.get(ligne, colonne)==null;
    }
    
    
}
