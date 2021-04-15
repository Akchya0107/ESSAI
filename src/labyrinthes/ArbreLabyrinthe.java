/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthes;

import StructureInformatique.File;
import Clavier.Clavier;
import StructureInformatique.Couple;
import StructureInformatique.DirectionRelative;
import StructureInformatique.Matrice;
import StructureInformatique.Pile;
import StructureInformatique.RangMemoire;

/**
 *
 * @author nico
 */
public class ArbreLabyrinthe {
    private Position etiquette;
    private ArbreLabyrinthe aine;
    private ArbreLabyrinthe cadet;
    private ArbreLabyrinthe benjamin;

    public ArbreLabyrinthe(Position etiquette, ArbreLabyrinthe aine, ArbreLabyrinthe cadet, ArbreLabyrinthe benjamin) {
        this.etiquette = etiquette;
        this.aine = aine;
        this.cadet = cadet;
        this.benjamin = benjamin;
    }
    
    public ArbreLabyrinthe(int hauteur, int largeur){
        this(MatriceLabyrinthe.genererNouvelleMatrice(hauteur, largeur));
    }
    
    public ArbreLabyrinthe(Position etiquette){
        this.etiquette = etiquette;
        this.aine = null;
        this.cadet = null;
        this.benjamin = null;
        System.out.println("Vous êtes au sommet : " + this.etiquette);
        System.out.println("Veuillez écrire l'étiquette sous forme de position (x,y) ou null si il n'y en a pas.");
        System.out.print("Fils Aîné : ");
        Position filsAine = Clavier.getPosition();
        System.out.print("Fils Cadet : ");
        Position filsCadet = Clavier.getPosition();
        System.out.print("Fils Benjamin : ");
        Position filsBenjamin = Clavier.getPosition();
        if(filsAine!=null){
            this.aine = new ArbreLabyrinthe(filsAine);
        }
        if(filsCadet!=null) {
            this.cadet = new ArbreLabyrinthe(filsCadet);
        }
        if(filsBenjamin!=null) {
            this.benjamin = new ArbreLabyrinthe(filsBenjamin);
        }
    }
    
    // le constructeur(String sequence) et la méthode toString() sont inverse l'un de l'autre
    public ArbreLabyrinthe(String sequence){
        int profondeur = 0;
        int indexDebut =1;
        int indexFin;
        sequence = sequence.replace(" ","");
        String arbre[] = sequence.split("");
        int i = 0;
        String etape = "etiquette";
        while(i<arbre.length && (etape.equals("filsBenjamin")!=true)){
           if(arbre[i].equals("[")){
               profondeur +=1;
           } else if (arbre[i].equals("]")){
               profondeur -=1;
           } else if(profondeur==1 && arbre[i].equals(";")){
               indexFin = i;
               String subsequence = sequence.substring(indexDebut, indexFin);
               switch (etape) {
                   case "etiquette":
                       this.etiquette = Position.parsePosition(subsequence);
                       etape = "filsAine";
                       break;
                   case "filsAine":
                       if(subsequence.equals("")){
                           this.aine = null;
                       } else {
                           
                           this.aine = new ArbreLabyrinthe(subsequence);
                       }   etape = "filsCadet";
                       break;
                   case "filsCadet":
                       if(subsequence.equals("")){
                           this.cadet = null;
                       } else {
                           this.cadet = new ArbreLabyrinthe(subsequence);
                       }   etape = "filsBenjamin";
                       break;
                   default:
                       break;
               }
               indexDebut = indexFin + 1;
           }
           i+=1;
        }
        String subsequence = sequence.substring(indexDebut, arbre.length-1);
        if(subsequence.equals("")){
            this.benjamin = null;
        } else {
            this.benjamin = new ArbreLabyrinthe(sequence.substring(indexDebut, arbre.length-1));
        }     
    }
    
/*    @Override
    public String toString(){
        return this.etiquette.toString();
    }
*/
    

    // le constructeur(String sequence) et la méthode toString() sont inverse l'un de l'autre
    @Override
    public String toString() {  
        String stringEtiquette;
        String stringAine;
        String stringCadet;
        String stringBenjamin;
        if(this.aine==null){
            stringAine = " ";
        } else {
            stringAine = this.aine.toString();
        }if(this.cadet==null){
            stringCadet = " ";
        } else {
            stringCadet = this.cadet.toString();
        }if(this.benjamin==null){
            stringBenjamin = " ";
        } else {
            stringBenjamin = this.benjamin.toString();
        }
        if(this.etiquette.equals(Position2D.POSITION_SORTIE)){
            stringEtiquette = "s" + this.etiquette;
        } else {
            stringEtiquette = this.etiquette.toString();
        }
        return "[" + stringEtiquette + ";" + stringAine + ";" + stringCadet + ";" + stringBenjamin + ']';
    }


    public Position getEtiquette() {
        return etiquette;
    }

    public ArbreLabyrinthe getAine() {
        return aine;
    }

    public ArbreLabyrinthe getCadet() {
        return cadet;
    }

    public ArbreLabyrinthe getBenjamin() {
        return benjamin;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj.getClass().equals(this.getClass())){
            ArbreLabyrinthe a = (ArbreLabyrinthe) obj;
            return this.etiquette.equals(a.etiquette);
        } else {
            return false;
        }
    }
    
    public boolean isFeuille(){
        return this.aine==null && this.cadet==null && this.benjamin==null;
    }
    public boolean isIntersection(){
        int nbDeFils = 0;
        if(this.aine!=null){
            nbDeFils +=1;
        }
        if(this.cadet!=null){
            nbDeFils +=1;
        }
        if(this.benjamin!=null){
            nbDeFils +=1;
        }
        return nbDeFils>1;
    }
    
    
    public void parcoursLargeur(){
        File<ArbreLabyrinthe> file = new File<>();
        file.push(this);
        ArbreLabyrinthe arbreEnCours;
        
        while(file.isEmpty()==false){
            arbreEnCours = file.pop();
            System.out.println(arbreEnCours.etiquette);
            
            if(arbreEnCours.aine!=null){
                file.push(arbreEnCours.aine);
            }
            if(arbreEnCours.cadet!=null){
                file.push(arbreEnCours.cadet);
            }
            if(arbreEnCours.benjamin!=null){
                file.push(arbreEnCours.benjamin);
            }
          
        }
    }
    
    
    public void parcoursProfondeur(){
        Pile<ArbreLabyrinthe> pile = new Pile<>();
        pile.push(this);
        ArbreLabyrinthe arbreEnCours;
        
        while(pile.isEmpty()==false){
            arbreEnCours = pile.pop();
            
            System.out.println(arbreEnCours.etiquette);
            
            if(arbreEnCours.benjamin!=null){
                pile.push(arbreEnCours.benjamin);
            }
            if(arbreEnCours.cadet!=null){
                pile.push(arbreEnCours.cadet);
            }
            if(arbreEnCours.aine!=null){
                pile.push(arbreEnCours.aine);
            }
            
            
        }
    }
    

    
    // un constructeur qui donne l'arbre correspondant à une MatriceLabyrinthe (elle décrit où sont les murs)
    // il utilise la méthode : 
    //private static ArbreLabyrinthe obtenirArbre(Direction directionInitiale, MatriceLabyrinthe matrice, Position positionEnCours)
    
    public ArbreLabyrinthe(MatriceLabyrinthe matrice, Couple<Position,Position> positionEntreeSortie){
        Position positionEntree = positionEntreeSortie.getFirst();
        Position2D.POSITION_SORTIE = positionEntreeSortie.getLast();
        DirectionRelative directionInitiale = DirectionRelative.init(positionEntree, matrice);
        this.etiquette = positionEntree;
        this.aine = null;
        this.cadet = null;
        this.benjamin = null;
        if(positionEntree.hasNext(directionInitiale.precedent(), matrice)){
            Position positionFilsAine = positionEntree.next(directionInitiale.precedent(),matrice);
            if(positionFilsAine!=null){
                this.aine = obtenirArbre(directionInitiale.precedent(), matrice, positionFilsAine);
            }
        }
        if(positionEntree.hasNext(directionInitiale, matrice)){
            Position positionFilsCadet = positionEntree.next(directionInitiale,matrice);
            if(positionFilsCadet!=null){
                this.cadet = obtenirArbre(directionInitiale, matrice, positionFilsCadet);
            }
        }
        if(positionEntree.hasNext(directionInitiale.suivant(), matrice)){
            Position positionFilsBenjamin = positionEntree.next(directionInitiale.suivant(),matrice);
            if(positionFilsBenjamin!=null){
                this.benjamin = obtenirArbre(directionInitiale.suivant(), matrice, positionFilsBenjamin);
            }
        }
    }
    
    public ArbreLabyrinthe(Couple<MatriceLabyrinthe, Couple<Position,Position>> dataMatrice){
        this(dataMatrice.getFirst(),dataMatrice.getLast());
    }
    
    private static ArbreLabyrinthe obtenirArbre(DirectionRelative directionInitiale, MatriceLabyrinthe matrice, Position positionEnCours){
        ArbreLabyrinthe FilsAine = null;
        ArbreLabyrinthe FilsCadet = null;
        ArbreLabyrinthe FilsBenjamin = null;
        if(positionEnCours.hasNext(directionInitiale.precedent(), matrice)){
            Position positionFilsAine = positionEnCours.next(directionInitiale.precedent(),matrice);
            if(positionFilsAine!=null){
                FilsAine = obtenirArbre(directionInitiale.precedent(), matrice, positionFilsAine);
            }
        }
        if(positionEnCours.hasNext(directionInitiale, matrice)){
            Position positionFilsCadet = positionEnCours.next(directionInitiale,matrice);
            if(positionFilsCadet!=null){
                FilsCadet = obtenirArbre(directionInitiale, matrice, positionFilsCadet);
            }
        }
        if(positionEnCours.hasNext(directionInitiale.suivant(), matrice)){
            Position positionFilsBenjamin = positionEnCours.next(directionInitiale.suivant(),matrice);
            if(positionFilsBenjamin!=null){
                FilsBenjamin = obtenirArbre(directionInitiale.suivant(), matrice, positionFilsBenjamin);
            }
        }
        return new ArbreLabyrinthe(positionEnCours, FilsAine, FilsCadet, FilsBenjamin);
    }
    
    
    
    // afficher un arbre en optimisant l'espace entre les branches
    // on fait un parcours en largeur de l'arbre et on ajuste la largeur du graphique pour que les étiquettes ne se superposent pas
    
    public Matrice<Couple> getMatriceDesPositions2DOptimisees(){
        File<Couple> file = new File<>();
        int cursorLigne = 0;
        RangMemoire cursorColonne = new RangMemoire();
        Couple<ArbreLabyrinthe,Position2D> dataArbreEnCours = new Couple(this, new Position2D(this,cursorLigne,cursorColonne,null));
        Matrice<Couple> matrice = new Matrice();
        file.push(dataArbreEnCours);
        
        while(file.isEmpty()==false){
            dataArbreEnCours = file.pop();
            ArbreLabyrinthe arbreEnCours;
            Position2D positionEnCours;
            arbreEnCours = dataArbreEnCours.getFirst();
            positionEnCours = dataArbreEnCours.getLast();
            cursorLigne = positionEnCours.getCursorLigne();
            cursorColonne = positionEnCours.getCursorColonne();
            
            arbreEnCours.ajusterLargeurMatriceDesPositions2DOptimisees(matrice,positionEnCours);
            if(arbreEnCours.aine!=null){
                Position2D position2DAine = new Position2D(arbreEnCours.aine,cursorLigne +1, new RangMemoire(cursorColonne.getRangReel(),-1,cursorLigne +1), cursorColonne);
                dataArbreEnCours = new Couple(arbreEnCours.aine,position2DAine);
                file.push(dataArbreEnCours);
            }
            if(arbreEnCours.cadet!=null){
                Position2D position2DCadet = new Position2D(arbreEnCours.cadet,cursorLigne +1,new RangMemoire(cursorColonne.getRangReel(),0,cursorLigne +1), cursorColonne);
                dataArbreEnCours = new Couple(arbreEnCours.cadet,position2DCadet);
                file.push(dataArbreEnCours);
            }
            if(arbreEnCours.benjamin!=null){
                Position2D position2DBenjamin = new Position2D(arbreEnCours.benjamin,cursorLigne +1, new RangMemoire(cursorColonne.getRangReel(),1,cursorLigne +1), cursorColonne);
                dataArbreEnCours = new Couple(arbreEnCours.benjamin,position2DBenjamin);
                file.push(dataArbreEnCours);
            }
            //System.out.println(matrice);
        }
        RangMemoire.clear();
        return matrice;
    }
    
    private void ajusterLargeurMatriceDesPositions2DOptimisees(Matrice<Couple> matrice, Position2D position2D){
        int cursorLigne = position2D.getCursorLigne();
        RangMemoire cursorColonne = position2D.getCursorColonne();
        
        //je rajoute une ligne si nécessaire
        if(cursorLigne==matrice.shape("ligne")){  
            matrice.addLigne();
        }
        
        if(cursorColonne.getRangReel()<0){
            matrice.addColonne(cursorColonne.rangReelPlus(1));
            cursorColonne.incr();
        }
        
        if(cursorColonne.getRangReel()==matrice.shape("colonne")){
            matrice.addColonne(cursorColonne.getRangReel());
        }
        
        if(cursorColonne.getRangRelatif()<=0){
            if(!matrice.isEmpty(cursorLigne,cursorColonne.getRangDuPere())){
                Couple<Position,Position2D> caseSousLeParent = matrice.get(cursorLigne, cursorColonne.getRangDuPere());
                matrice.set(cursorLigne, cursorColonne.getRangDuPere(),null);
                matrice.addColonne(cursorColonne.getRangDuPere());
                matrice.set(cursorLigne, cursorColonne.getRangDuPere(),caseSousLeParent);
                cursorColonne.incr(); //change la valeur de cursorColonne.getRangDuPere()
            }
            if(!matrice.isEmpty(cursorLigne,cursorColonne.getRangReel())){
                matrice.addColonne(cursorColonne.getRangDuPere());
                cursorColonne.incr();
            }
        }
        
        matrice.set(cursorLigne, cursorColonne.getRangReel(), new Couple(this.etiquette,position2D));
    }
    
    
    
    // il faut que la position sortie existe dans l'arbre
    public ArbreLabyrinthe resoudre(Position positionSortie){
        Pile<ArbreLabyrinthe> pileParcours = new Pile<>();
        Pile<ArbreLabyrinthe> pileSolutionDuLabyrinthe = new Pile<>();
        pileParcours.push(this);
        ArbreLabyrinthe arbreEnCours;
        boolean solutionTrouvee = false;
        while(!pileParcours.isEmpty() && !solutionTrouvee){
            arbreEnCours = pileParcours.pop();
            pileSolutionDuLabyrinthe.push(arbreEnCours);
            if(!arbreEnCours.etiquette.equals(positionSortie)){
                if(arbreEnCours.isFeuille()){
                    ArbreLabyrinthe arbrePrecedent = pileSolutionDuLabyrinthe.pop();
                    ArbreLabyrinthe arbreProchain = pileParcours.peek();
                    while (!arbrePrecedent.isIntersection() || !(arbreProchain.equals(arbrePrecedent.aine) || arbreProchain.equals(arbrePrecedent.cadet) || arbreProchain.equals(arbrePrecedent.benjamin))){
                        arbrePrecedent = pileSolutionDuLabyrinthe.pop();
                    }
                    pileSolutionDuLabyrinthe.push(arbrePrecedent);
                }
                
                if(arbreEnCours.benjamin!=null){
                    pileParcours.push(arbreEnCours.benjamin);
                }
                if(arbreEnCours.cadet!=null){
                    pileParcours.push(arbreEnCours.cadet);
                }
                if(arbreEnCours.aine!=null){
                    pileParcours.push(arbreEnCours.aine);
                }
            } else {
                solutionTrouvee = true;
            }
            
        }
        
        //on suppose qu'il y a au moins un arbre dans pile car il y a la Position Solution
        ArbreLabyrinthe noeudPlusBas = pileSolutionDuLabyrinthe.pop();
        noeudPlusBas = new ArbreLabyrinthe(noeudPlusBas.etiquette,null,null,null);        
        
        while(!pileSolutionDuLabyrinthe.isEmpty()){
            ArbreLabyrinthe noeudEnCours = pileSolutionDuLabyrinthe.pop();
            ArbreLabyrinthe filsAine = null;
            ArbreLabyrinthe filsCadet = null;
            ArbreLabyrinthe filsBenjamin = null;
            if(noeudEnCours.aine!=null && noeudEnCours.aine.equals(noeudPlusBas)){
                filsAine = noeudPlusBas;
            } else if(noeudEnCours.cadet!=null && noeudEnCours.cadet.equals(noeudPlusBas)){
                filsCadet = noeudPlusBas;
            } else if(noeudEnCours.benjamin!=null && noeudEnCours.benjamin.equals(noeudPlusBas)){
                filsBenjamin = noeudPlusBas;
            }
            noeudPlusBas = new ArbreLabyrinthe(noeudEnCours.etiquette, filsAine, filsCadet, filsBenjamin);
        }
        
        return noeudPlusBas;
    }
    
    
    
}
