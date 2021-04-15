/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.Couple;
import StructureInformatique.DirectionRelative;
import StructureInformatique.Matrice;
import java.util.EnumSet;

/**
 *
 * @author nico
 */
public class TestArbreLabyrinthe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArbreLabyrinthe arbreTest2;
        ArbreLabyrinthe arbreTest;
        //arbreTest = new ArbreLabyrinthe(new Position(0,4));
        arbreTest2 = new ArbreLabyrinthe("[(0,4); ;[(1,4); ;[(2,4);[(2,3);[(2,2);[(1,2); ;[(1,3); ;[(0,3); ;[(0,2); ; ; ]; ]; ]; ]; ;[(3,2); ;[(3,1);[(2,1);[(1,1); ;[(1,0); ;[(0,0); ;[(0,1); ; ; ]; ]; ]; ]; ;[(2,0); ;[(3,0); ;[(4,0); ; ; ]; ]; ]]; ;[(4,1); ; ; ]]; ]]; ;[(3,3); ; ; ]]; ;[(3,4); ;[(4,4); ;[(4,3); ;[(4,2); ;[(5,2);[(5,1); ;[(5,0); ; ; ]; ]; ;[(5,3); ;[(5,4); ; ; ]; ]]; ]; ]; ]; ]]; ]; ]");
        arbreTest = new ArbreLabyrinthe("[(4,0); ;[(4,1); ;[(4,2);[(3,2); ;[(2,2);[(2,1);[(3,1); ; ;[(3,0); ; ;[(2,0); ; ; ]]]; ; ]; ;[(2,3);[(1,3);[(1,2); ;[(1,1); ; ;[(0,1);[(0,0);[(1,0); ; ; ]; ; ]; ; ]];[(0,2); ; ;[(0,3); ;[(0,4); ; ; ]; ]]]; ;[(1,4); ; ; ]]; ; ]];[(3,3); ; ; ]];[(4,3); ;[(4,4);[(3,4); ;[(2,4); ; ;[(2,5);[(1,5); ;[(0,5); ; ; ]; ]; ;[(3,5); ;[s(4,5); ; ; ]; ]]]; ]; ; ]; ]; ]; ]; ]");
        //arbreTest.parcoursLargeur();
        
        //arbreTest.afficherArbre("Labyrinthe From String");
        //System.out.println(arbreTest);
        //arbreTest.parcoursProfondeur();
        //ArbreLabyrinthe arbreTest2;
        //Couple<MatriceLabyrinthe,Couple> dataFichier = ArbreLabyrinthe.readFile("labyrinthe.txt");
        //Matrice matrice = dataFichier.getFirst();
        //Couple<Position,Position> positionEntreeSortie = dataFichier.getLast();
        //arbreTest2 = ArbreLabyrinthe.obtenirArbre(matrice, positionEntreeSortie);
        
        
        /*Matrice matSE = ArbreLabyrinthe.readFile("labyrinthe.txt").getFirst();
        System.out.println(matSE);
        MatriceLabyrinthe matLab = new MatriceLabyrinthe(matSE);
        System.out.println("........................................");
        System.out.println(matLab);
        AffichageLabyrinthe.afficherArbreEtMurs(arbreTest,matLab);
        arbreTest2.afficherArbre("Arbre Wikipédia");
        //arbreTest2.afficherArbre("Labyrinthe From File");
*/
        
        
        
        
        

        
    }
    
}
