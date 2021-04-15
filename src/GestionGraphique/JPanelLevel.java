/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionGraphique;

import StructureInformatique.Couple;
import StructureInformatique.Matrice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import labyrinthes.ArbreLabyrinthe;
import labyrinthes.MatriceLabyrinthe;
import labyrinthes.Position;
import labyrinthes.Position2D;

/**
 *
 * @author ENSMM
 */
public class JPanelLevel extends JPanel {
    private final BufferedImage image;
    private final Graphics2D graphics;
    private Color defaultColor = Color.RED;

    public JPanelLevel(int width, int height) {
        super();
        setSize(width, height);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHints(rh);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public Graphics2D getGraphics2D() {
        return graphics;
    }
    
    
    
    public void fondBlanc(){
        int width = this.getWidth();
        int height = this.getHeight();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
    }
    
    
    public void setDefaultColor(Color couleur){
        this.defaultColor = couleur;
    }
    
    
    
    
    
    
    
        
    private final static int SCALE_X = 44; //14 44
    private final static int SCALE_Y = 34; //14 34
    
    
    
 
    
    public void afficherArbre(Matrice<Couple> matrice){
        this.setSize((matrice.shape("colonne")+2)*SCALE_X, (matrice.shape("ligne")+2)*SCALE_Y);
        // on construit le dessin du bas vers le haut
        for(int i=matrice.shape("ligne")-1;i>=0;i-=1){
            for(var element : matrice.getLigne(i)){
                if(element!=null){
                    this.dessiner(element);
                }
            }
        }
        //affiche.fenetre.repaint(); -----------------------------------------------------------------------------------------
    }
    
    
    
    private void dessiner(Couple<Position,Position2D> sommet){
        Position position = sommet.getFirst();
        Position2D position2D = sommet.getLast();
        this.tracerBranche(position2D);
        this.tracerPoint(position2D);
        this.tracerEtiquette(position, position2D);
    }
    
    private void tracerBranche(Position2D position2D){
        int cursorLigne = (position2D.getCursorLigne()+1)*SCALE_Y;
        int cursorColonne = (position2D.getCursorColonne().getRangReel()+1)*SCALE_X;
        if(position2D.getCursorColonneParent()!=null){ // si j'ai un parent, je trace la branche qui nous relie
            int cursorLigneParent = (position2D.getCursorLigneParent()+1)*SCALE_Y;
            int cursorColonneParent = (position2D.getCursorColonneParent().getRangReel()+1)*SCALE_X;
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawLine(cursorColonneParent, cursorLigneParent, cursorColonne, cursorLigne);
            
        }
    }
    
    private void tracerBranche(ArbreLabyrinthe arbre){
        int cursorLigne = (arbre.getEtiquette().getLigne()+2)*SCALE_Y;
        int cursorColonne = (arbre.getEtiquette().getColonne()+2)*SCALE_X;
        int cursorLigneFils;
        int cursorColonneFils;
        if(arbre.getAine()!=null){
            cursorLigneFils = (arbre.getAine().getEtiquette().getLigne()+2)*SCALE_Y;
            cursorColonneFils = (arbre.getAine().getEtiquette().getColonne()+2)*SCALE_X;
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawLine(cursorColonne, cursorLigne, cursorColonneFils, cursorLigneFils);
        }
        if(arbre.getCadet()!=null){
            cursorLigneFils = (arbre.getCadet().getEtiquette().getLigne()+2)*SCALE_Y;
            cursorColonneFils = (arbre.getCadet().getEtiquette().getColonne()+2)*SCALE_X;
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawLine(cursorColonne, cursorLigne, cursorColonneFils, cursorLigneFils);
        }
        if(arbre.getBenjamin()!=null){
            cursorLigneFils = (arbre.getBenjamin().getEtiquette().getLigne()+2)*SCALE_Y;
            cursorColonneFils = (arbre.getBenjamin().getEtiquette().getColonne()+2)*SCALE_X;
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawLine(cursorColonne, cursorLigne, cursorColonneFils, cursorLigneFils);
        }
    }
    
    private void tracerEtiquette(Position position,Position2D position2D){
        int cursorLigne = (position2D.getCursorLigne()+1)*SCALE_Y;
        int cursorColonne = (position2D.getCursorColonne().getRangReel()+1)*SCALE_X;
        //on efface les branches qui pourraient apparaitre derrière le texte
        this.getGraphics2D().setColor(Color.WHITE);
        this.getGraphics2D().fillOval(cursorColonne+8, cursorLigne-7, 25, 15);
        // on écrit le texte
        this.getGraphics2D().setColor(Color.BLACK);
        this.getGraphics2D().drawString(position.toString(), cursorColonne+8, cursorLigne+4);
    }
    
    private void tracerPoint(Position2D position2D){
        int cursorLigne = (position2D.getCursorLigne()+1)*SCALE_Y;
        int cursorColonne = (position2D.getCursorColonne().getRangReel()+1)*SCALE_X;
        this.getGraphics2D().setColor(defaultColor);
        if(position2D.isIntersection()){
            this.getGraphics2D().fillRect(cursorColonne-4, cursorLigne-4, 9, 9);
        } else if(position2D.isFeuille() ||cursorLigne==SCALE_Y){
            this.getGraphics2D().fillOval(cursorColonne-4, cursorLigne-4, 9, 9);
        } else {
            this.getGraphics2D().setColor(Color.WHITE);
            this.getGraphics2D().fillOval(cursorColonne-3, cursorLigne-3, 7, 7);
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawOval(cursorColonne-4, cursorLigne-4, 8, 8);
        }
        if(position2D.isExit() || cursorLigne==SCALE_Y){
            this.tracerEntrerSortie(position2D);
        }
    }
    
    private void tracerPoint(ArbreLabyrinthe arbre){
        int cursorLigne = (arbre.getEtiquette().getLigne()+2)*SCALE_Y;
        int cursorColonne = (arbre.getEtiquette().getColonne()+2)*SCALE_X;
        this.getGraphics2D().setColor(defaultColor);
        if(arbre.isIntersection()){
            this.getGraphics2D().fillRect(cursorColonne-4, cursorLigne-4, 9, 9);
        } else if(arbre.isFeuille()){
            this.getGraphics2D().fillOval(cursorColonne-4, cursorLigne-4, 9, 9);
        } else {
            this.getGraphics2D().setColor(Color.WHITE);
            this.getGraphics2D().fillOval(cursorColonne-3, cursorLigne-3, 7, 7);
            this.getGraphics2D().setColor(defaultColor);
            this.getGraphics2D().drawOval(cursorColonne-4, cursorLigne-4, 8, 8);
        }
        if(arbre.getEtiquette().equals(Position2D.POSITION_SORTIE)){
            this.tracerEntrerSortie(arbre);
        }
    }
    
    private void  tracerEntrerSortie(Position2D position2D){
        this.getGraphics2D().setColor(defaultColor);
        int cursorLigne = (position2D.getCursorLigne()+1)*SCALE_Y;
        int cursorColonne = (position2D.getCursorColonne().getRangReel()+1)*SCALE_X;
        if(position2D.isIntersection()){
            this.getGraphics2D().drawRect(cursorColonne-7, cursorLigne-7, 14, 14);
        } else {
            this.getGraphics2D().drawOval(cursorColonne-7, cursorLigne-7, 14, 14);
        }
    }
    
    private void  tracerEntrerSortie(ArbreLabyrinthe arbre){
        this.getGraphics2D().setColor(defaultColor);
        int cursorLigne = (arbre.getEtiquette().getLigne()+2)*SCALE_Y;
        int cursorColonne = (arbre.getEtiquette().getColonne()+2)*SCALE_X;
        if(arbre.isIntersection()){
            this.getGraphics2D().drawRect(cursorColonne-7, cursorLigne-7, 14, 14);
        } else {
            this.getGraphics2D().drawOval(cursorColonne-7, cursorLigne-7, 14, 14);
        }
    }
    
    public void afficherArbre(ArbreLabyrinthe arbre){
        this.afficherArbre_init(arbre);
        //testAffiche.fenetre.repaint(); -----------------------------------------------------------------------------------
    }
    
    private void afficherArbre_init(ArbreLabyrinthe arbre){
        this.tracerEntrerSortie(arbre);
        this.dessiner(arbre);
        int cursorLigne = (arbre.getEtiquette().getLigne()+2)*SCALE_Y;
        int cursorColonne = (arbre.getEtiquette().getColonne()+2)*SCALE_X;
        this.getGraphics2D().setColor(defaultColor);
        this.getGraphics2D().fillOval(cursorColonne-4, cursorLigne-4, 9, 9);
    }
    
    private void dessiner(ArbreLabyrinthe arbre){
        this.tracerBranche(arbre);
        this.tracerPoint(arbre);
        if(arbre.getAine()!=null){
            this.dessiner(arbre.getAine());
        }
        if(arbre.getCadet()!=null){
            this.dessiner(arbre.getCadet());
        }
        if(arbre.getBenjamin()!=null){
            this.dessiner(arbre.getBenjamin());
        }
    }
    
    
    public void afficherMurs(MatriceLabyrinthe matrice){
        this.afficherMurs_init(matrice);
        //testAffiche.fenetre.repaint(); -------------------------------------------------------------------------------------
    }
    
    private void afficherMurs_init(MatriceLabyrinthe matrice){
        this.getGraphics2D().setColor(Color.BLACK);
        
        // Grand mur vertical haut qui fait tout le labyrinthe
        this.getGraphics2D().fillRoundRect(2*SCALE_X - SCALE_X/2-1,  SCALE_Y + SCALE_Y/2-1, matrice.shape("colonne")*SCALE_X +3, 3 ,3,3);
        
        // Grand mur horizontal gauche qui fait tout le labyrinthe
        this.getGraphics2D().fillRoundRect( SCALE_X + SCALE_X/2-1, 2*SCALE_Y - SCALE_Y/2-1 ,3,  matrice.shape("ligne")*SCALE_Y +3,3,3);
        
        
        for(int ligne=0;ligne<matrice.shape("ligne");ligne+=1){
            for(int colonne=0;colonne<matrice.shape("colonne");colonne+=1){
                String caseEnCours = matrice.get(ligne, colonne);
                this.dessiner((ligne+2)*SCALE_Y,(colonne+2)*SCALE_X,caseEnCours);
                if(caseEnCours.contains("e")||caseEnCours.contains("s")){
                    if(colonne==0){
                        // dessiner entree a gauche
                        this.entreeSortieVerticale((ligne+2)*SCALE_Y,(colonne+1)*SCALE_X);
                    } else if (colonne==matrice.shape("colonne")-1){
                        // dessiner entree a droite
                        this.entreeSortieVerticale((ligne+2)*SCALE_Y,(colonne+2)*SCALE_X);
                    } else if (ligne==0){
                        //dessiner entree en haut
                        this.entreeSortieHorizontale((ligne+1)*SCALE_Y,(colonne+2)*SCALE_X);
                    } else {
                        this.entreeSortieHorizontale((ligne+2)*SCALE_Y,(colonne+2)*SCALE_X);
                        //dessiner entree en bas
                    }
                }
            }
        }
    }
    
    private void dessiner(int cursorLigne, int cursorColonne, String caseEnCours){
        caseEnCours = caseEnCours.replace("e", "").replace("s", "");
        switch (caseEnCours) {
            case "SE":
            case "ES":
                this.ligneFineGriseVerticale(cursorLigne, cursorColonne);
                this.ligneFineGriseHorizontale(cursorLigne, cursorColonne);
                break;
            case "S":
                this.ligneEpaisseNoireVerticale(cursorLigne, cursorColonne);
                this.ligneFineGriseHorizontale(cursorLigne, cursorColonne);
                break;
            case "E":
                this.ligneFineGriseVerticale(cursorLigne, cursorColonne);
                this.ligneEpaisseNoireHorizontale(cursorLigne, cursorColonne);
                break;
            default:
                this.ligneEpaisseNoireVerticale(cursorLigne, cursorColonne);
                this.ligneEpaisseNoireHorizontale(cursorLigne, cursorColonne);
                break;
        }
    }
    
    private void ligneFineGriseHorizontale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.GRAY);
        this.getGraphics2D().drawLine(cursorColonne - SCALE_X/2+2, cursorLigne + SCALE_Y/2,cursorColonne + SCALE_X/2-2, cursorLigne + SCALE_Y/2);
    }
    
    private void ligneFineGriseVerticale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.GRAY);
        this.getGraphics2D().drawLine(cursorColonne + SCALE_X/2, cursorLigne - SCALE_Y/2+2,cursorColonne + SCALE_X/2, cursorLigne + SCALE_Y/2-2);
    }
    
    private void ligneEpaisseNoireHorizontale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.BLACK);
        this.getGraphics2D().fillRoundRect(cursorColonne - SCALE_X/2-1, cursorLigne + SCALE_Y/2-1,SCALE_X+3,3,3,3);
    }
    
    private void ligneEpaisseNoireVerticale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.BLACK);
        this.getGraphics2D().fillRoundRect(cursorColonne + SCALE_X/2-1, cursorLigne - SCALE_Y/2-1,3, SCALE_Y+3,3,3);
    }
    
    private void entreeSortieHorizontale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.WHITE);
        this.getGraphics2D().fillRect(cursorColonne - SCALE_X/2-1+SCALE_X/5, cursorLigne + SCALE_Y/2-1,SCALE_X+3-2*SCALE_X/5,3);
        this.getGraphics2D().setColor(Color.GRAY);
        this.getGraphics2D().drawLine(cursorColonne - SCALE_X/2-1+SCALE_X/5, cursorLigne + SCALE_Y/2,cursorColonne + SCALE_X/2-SCALE_X/5, cursorLigne + SCALE_Y/2);
    }
    
    private void entreeSortieVerticale(int cursorLigne, int cursorColonne){
        this.getGraphics2D().setColor(Color.WHITE);
        this.getGraphics2D().fillRect(cursorColonne + SCALE_X/2-1, cursorLigne - SCALE_Y/2-1 +SCALE_Y/5,3, SCALE_Y+3-2*SCALE_Y/5);
        this.getGraphics2D().setColor(Color.GRAY);
        this.getGraphics2D().drawLine(cursorColonne + SCALE_X/2, cursorLigne - SCALE_Y/2-1+SCALE_Y/5,cursorColonne + SCALE_X/2, cursorLigne + SCALE_Y/2-SCALE_Y/5);
    }
    
    public void afficherArbreEtMurs(ArbreLabyrinthe arbre, MatriceLabyrinthe matrice){
        this.afficherMurs_init(matrice);
        this.afficherArbre_init(arbre);
        //testAffiche.fenetre.repaint(); ---------------------------------------------------------------------------------------
        
    }
    
    
    
}
