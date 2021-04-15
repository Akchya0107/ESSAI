/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

/**
 *
 * @author nico
 */
public class Triplet<Element1,Element2,Element3> {
    private Element1 first;
    private Element2 second;
    private Element3 last;
    
    public Triplet(Element1 first, Element2 second, Element3 last){
        this.first = first;
        this.second = second;
        this.last = last;
    }

    public Element1 getFirst() {
        return first;
    }

    public void setFirst(Element1 first) {
        this.first = first;
    }

    public Element2 getSecond() {
        return second;
    }

    public void setSecond(Element2 second) {
        this.second = second;
    }

    public Element3 getLast() {
        return last;
    }

    public void setLast(Element3 last) {
        this.last = last;
    }
    
    @Override
    public String toString(){
        return "{" + this.first + "," + this.second + "," + this.last + "}";
    }
}
