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
public class File<Element> {
    
    private final ArrayDeque<Element> file;
    
    public File(){
        this.file = new ArrayDeque<>();
    }
    
    
    public void push(Element e) {
        this.file.addLast(e);
    }
    
    public Element pop(){
        return this.file.removeFirst();
    }
    
    public Element peek(){
        return this.file.getFirst();
    }
    
    public boolean isEmpty(){
        return this.file.isEmpty();
    }
    
    public int size(){
        return this.file.size();
    }
    
    @Override
    public String toString() {
        return "File = " + file;
    }
}
