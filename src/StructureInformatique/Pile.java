/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureInformatique;

import java.util.ArrayDeque;

/**
 *
 * @author nico
 * @param <Element>
 */
public class Pile<Element> {
    
    private final ArrayDeque<Element> pile;
    
    public Pile(){
        this.pile = new ArrayDeque<>();
    }
    
    public void push(Element e) {
        this.pile.addLast(e);
    }
    
    public Element pop(){
        return this.pile.removeLast();
    }
    
    public Element peek(){
        return this.pile.getLast();
    }
    
    public boolean isEmpty(){
        return this.pile.isEmpty();
    }
    
    public int size(){
        return this.pile.size();
    }
    
    @Override
    public String toString() {
        return "Pile = " + this.pile;
    }
}
