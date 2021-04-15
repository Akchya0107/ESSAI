package GestionGraphique;

import java.awt.Graphics2D;
import javax.swing.JFrame;


public class FenetreGraphique extends JFrame {

    private JPanelLevel panel;

    public FenetreGraphique(String titre, int largeur, int hauteur) {
        this(titre, new JPanelLevel(largeur, hauteur));
    }
    
    public FenetreGraphique(String titre, JPanelLevel panel){
        super();
        super.addKeyListener(new EcouteurClavier());
        setTitle(titre);
        getContentPane().add(panel);
        setSize(panel.getSize().width, panel.getSize().height);
        setVisible(true);
        setResizable(false);
        this.panel = panel;
    }
    
    public JPanelLevel getJPanelLevel(){
        return this.panel;
    }
    
    public void setJPanelLevel(String titre, JPanelLevel nouveauPanel){
        setTitle(titre);
        getContentPane().remove(panel);
        this.panel = nouveauPanel;
        getContentPane().add(panel);
        setSize(panel.getSize().width, panel.getSize().height);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public Graphics2D getGraphics2D() {
        return panel.getGraphics2D();
    }

    public void fermer() {
        dispose();
    }
    
    
}
