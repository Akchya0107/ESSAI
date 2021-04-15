/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionGraphique;

import StructureInformatique.Couple;
import StructureInformatique.Matrice;
import java.awt.Color;
import labyrinthes.ArbreLabyrinthe;
import labyrinthes.MatriceLabyrinthe;

/**
 *
 * @author ENSMM
 */
public class AffichageLevel {

    
    private final static int SCALE_X = 44; //14 44
    private final static int SCALE_Y = 34; //14 34
    private FenetreGraphique fenetreLabyrinthe = null;
    private FenetreGraphique fenetreArbreLabyrinthe = null;
    private FenetreGraphique fenetreSolution = null;

    public AffichageLevel() {
    }
    
    public FenetreGraphique getFenetreLabyrinthe(){
        return fenetreLabyrinthe;
    }
    public boolean isFenetreLabyrintheExists(){
        return fenetreLabyrinthe!=null;
    }
    
    public boolean isFenetreArbreLabyrintheExists(){
        return fenetreArbreLabyrinthe!=null;
    }
    
    public boolean isFenetreSolutionExists(){
        return fenetreSolution!=null;
    }
    
    public void fermerFenetreSolution(){
        this.fenetreSolution.fermer();
        this.fenetreSolution = null;
    }
    
    public void fermerFenetreArbreLabyrinthe(){
        this.fenetreArbreLabyrinthe.fermer();
        this.fenetreArbreLabyrinthe = null;
    }
    
    public void fermerFenetreLabyrinthe(){
        this.fenetreLabyrinthe.fermer();
        this.fenetreLabyrinthe = null;
    }
    
    public void fondBlancFenetreLabyrinthe(){
        this.fenetreLabyrinthe.getJPanelLevel().fondBlanc();
    }
    
    public void setDefaultColor(Color couleur){
        this.fenetreLabyrinthe.getJPanelLevel().setDefaultColor(couleur);
    }
    
    public void affichageArbreVertical(String titre, Matrice<Couple> matrice){
        if(this.fenetreArbreLabyrinthe==null){
            JPanelLevel grapheLab = new JPanelLevel((matrice.shape("colonne")+2)*SCALE_X, (matrice.shape("ligne")+2)*SCALE_Y);
            this.fenetreArbreLabyrinthe = new FenetreGraphique("Arbre du labyrinthe -"+titre+"-", grapheLab);
        }
        this.fenetreArbreLabyrinthe.getJPanelLevel().afficherArbre(matrice);
        this.fenetreArbreLabyrinthe.repaint();
    }
    
    public void affichageArbreVertical(Matrice<Couple> matrice){
        this.affichageArbreVertical("Default",matrice); 
    }
    
    public void affichageArbreVertical(String titre, ArbreLabyrinthe arbre){
        Matrice<Couple> matrice = arbre.getMatriceDesPositions2DOptimisees();
        this.affichageArbreVertical(titre,matrice); 
    }
    
    public void affichageArbreVertical(ArbreLabyrinthe arbre){
        Matrice<Couple> matrice = arbre.getMatriceDesPositions2DOptimisees();
        this.affichageArbreVertical("Default",matrice); 
    }
    
    public void affichageSolutionVertical(String titre, Matrice<Couple> matrice){
        if(this.fenetreSolution==null){
            JPanelLevel grapheLab = new JPanelLevel((matrice.shape("colonne")+2)*SCALE_X, (matrice.shape("ligne")+2)*SCALE_Y);
            this.fenetreSolution = new FenetreGraphique("Solution du labyrinthe -"+titre+"-", grapheLab);
        }
        this.fenetreSolution.getJPanelLevel().afficherArbre(matrice);
        this.fenetreSolution.repaint();
    }
    
    public void affichageSolutionVertical(Matrice<Couple> matrice){
        this.affichageSolutionVertical("Default",matrice); 
    }
    
    public void affichageSolutionVertical(String titre, ArbreLabyrinthe arbre){
        Matrice<Couple> matrice = arbre.getMatriceDesPositions2DOptimisees();
        this.affichageSolutionVertical(titre,matrice); 
    }
    
    public void affichageSolutionVertical(ArbreLabyrinthe arbre){
        Matrice<Couple> matrice = arbre.getMatriceDesPositions2DOptimisees();
        this.affichageSolutionVertical("Default",matrice); 
    }
    
    public void affichageArbreDansMatrice(String titre, ArbreLabyrinthe arbre){
        if(this.fenetreLabyrinthe==null){
            JPanelLevel grapheLab = new JPanelLevel(10*SCALE_X, 10*SCALE_Y); //80 30
            this.fenetreLabyrinthe = new FenetreGraphique("Labyrinthe -"+titre+"-", grapheLab);
        }
        this.fenetreLabyrinthe.getJPanelLevel().afficherArbre(arbre);
        this.fenetreLabyrinthe.repaint();
    }
    
    public void affichageArbreDansMatrice(ArbreLabyrinthe arbre){
        this.affichageArbreDansMatrice("Aléatoire" ,arbre);
    }
    
    
    public void affichageMursDuLabyrinthe(String titre, MatriceLabyrinthe matrice){
        if(this.fenetreLabyrinthe==null){
            JPanelLevel grapheLab = new JPanelLevel((matrice.shape("colonne")+4)*SCALE_X, (matrice.shape("ligne")+4)*SCALE_Y); //80 30
            this.fenetreLabyrinthe = new FenetreGraphique("Labyrinthe -"+titre+"-", grapheLab);
        }
        this.fenetreLabyrinthe.getJPanelLevel().afficherMurs(matrice);
        this.fenetreLabyrinthe.repaint();
    }
    
    public void affichageMursDuLabyrinthe(MatriceLabyrinthe matrice){
        affichageMursDuLabyrinthe("The cake is a lie", matrice);
    }
    
    
    public void afficherArbreEtMursDuLabyrinthe(String titre, ArbreLabyrinthe arbre, MatriceLabyrinthe matrice){
        if(this.fenetreLabyrinthe==null){
            JPanelLevel grapheLab = new JPanelLevel((matrice.shape("colonne")+4)*SCALE_X, (matrice.shape("ligne")+4)*SCALE_Y); //80 30
            this.fenetreLabyrinthe = new FenetreGraphique("Labyrinthe -"+titre+"-", grapheLab);
        }
        this.fenetreLabyrinthe.getJPanelLevel().afficherMurs(matrice);
        this.fenetreLabyrinthe.getJPanelLevel().afficherArbre(arbre);
        this.fenetreLabyrinthe.repaint();
    }
    
    public void afficherArbreEtMursDuLabyrinthe(ArbreLabyrinthe arbre, MatriceLabyrinthe matrice){
        afficherArbreEtMursDuLabyrinthe("The cake is a lie", arbre, matrice);
    }
}