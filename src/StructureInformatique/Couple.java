/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

/**
 *
 * @author nico
 * @param <Element1>
 * @param <Element2>
 */
public class Couple <Element1,Element2> {
    private Element1 a;
    private Element2 b;

    public Couple(Element1 a, Element2 b) {
        this.a = a;
        this.b = b;
    }
    
    public Element1 getFirst(){
        return a;
    }
    
    public Element2 getLast(){
        return b;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj.getClass().equals(this.getClass())){
            Couple<Element1,Element2> c = (Couple) obj;
            return this.getFirst().equals(c.getFirst()) && this.getLast().equals(c.getLast());
        } else {
            return false;
        }
    }
    
    
    @Override
    public String toString(){
        return "(" + this.a + "/" + this.b +")";
    }
}
