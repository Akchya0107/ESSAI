/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionGraphique;



import StructureInformatique.DirectionAbsolue;
import GestionnaireFichier.LevelFilesExplorer;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import labyrinthes.ArbreLabyrinthe;
import labyrinthes.Level;
import labyrinthes.MatriceLabyrinthe;
import labyrinthes.Position;

/**
 *
 * @author ENSMM
 */
public class InterfaceGraphique extends javax.swing.JFrame {
    private static InterfaceGraphique LAST_INTERFACE_CREATED = null;
    /**
     * Creates new form interfaceTest1
     */
    public InterfaceGraphique() {
        initComponents();
        LAST_INTERFACE_CREATED = this;
    }
    private Level niveauChargee = null;
    private AffichageLevel affichage = null;
    private Position positionDuJoueur;
    private boolean solutionAfficheeDansLeLabyrinthe = false;

    
    public static InterfaceGraphique getLAST_INTERFACE_CREATED() {
        return LAST_INTERFACE_CREATED;
    }
    
    public void seDeplacer(DirectionAbsolue prochaineDirection){
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    MatriceLabyrinthe matrice = niveauChargee.getMatriceDuNiveau();
                    if(positionDuJoueur.hasNext(prochaineDirection, matrice)){
                        positionDuJoueur = positionDuJoueur.next(prochaineDirection);
                    }
                    this.repaintFenetreLabyrinthe();
                    if(positionDuJoueur.equals(niveauChargee.getPositionSortie())){
                        JOptionPane.showMessageDialog(affichage.getFenetreLabyrinthe(), jPanel2, "Félicitation ! Voulez-vous rejouer ?",JOptionPane.INFORMATION_MESSAGE, null);
                    }
                }
            }
        }
    }
    
    public void repaintFenetreLabyrinthe(){
        affichage.fondBlancFenetreLabyrinthe();
        ArbreLabyrinthe arbre = niveauChargee.getArbreDuNiveau();
        ArbreLabyrinthe progression = arbre.resoudre(positionDuJoueur);
        MatriceLabyrinthe matrice = niveauChargee.getMatriceDuNiveau();
        affichage.affichageMursDuLabyrinthe(matrice);
        if(solutionAfficheeDansLeLabyrinthe){
            ArbreLabyrinthe solution = arbre.resoudre(niveauChargee.getPositionSortie());
            affichage.affichageArbreDansMatrice(solution);
        }
        affichage.setDefaultColor(Color.BLUE);
        affichage.affichageArbreDansMatrice(progression);
        affichage.setDefaultColor(Color.RED);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jeu du Labyrinthe - PICARD DESTELAN Nicolas");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion des fenêtres  "));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Activé");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Afficher un arbre associé au labyrinthe actuel");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Désactivé");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Afficher labyrinthe");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("Afficher solution");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel1.setText("Afficher temporairement la solution");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Choisir un niveau pour jouer  "));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(10, 1, null, 1));

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(5, 1, null, 1));

        jButton3.setText("Créer un nouveau labyrinthe");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("générer un nom");
        jButton4.setToolTipText("Génère un nouveau nom");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Charger un nouveau labyrinthe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setToolTipText("Titre du Labyrinte");
        jTextField1.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jButton2.setText("gauche");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("bas");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("haut");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("droite");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel2.setText("Vous pouvez également utiliser les flèches du clavier ou les touches ASDW");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton6)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jMenu3.setText("Fonctionnalités console");

        jMenu4.setText("ArbreLabyrinthe");

        jMenuItem2.setText("Constructeur récursif");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem3.setText("Constructeur String");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem1.setText("Constructeur aléatoire");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem4.setText("Parcours en largeur");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Parcours en profondeur");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu3.add(jMenu4);

        jMenu5.setText("MatriceLabyrinthe");

        jMenuItem6.setText("Générer une matrice");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenu3.add(jMenu5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(LevelFilesExplorer.getADRESSE_DOSSIER_LEVEL()));
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Vous avez ouvert : " + selectedFile.getName());
            niveauChargee = LevelFilesExplorer.getLevel(selectedFile.getName());
            System.out.println("C'est parti !");
            
            //on met à jour l'affichage
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    affichage.fermerFenetreLabyrinthe();
                }
                if(affichage.isFenetreArbreLabyrintheExists()){
                    affichage.fermerFenetreArbreLabyrinthe();
                }
                if(affichage.isFenetreSolutionExists()){
                    affichage.fermerFenetreSolution();
                }
            } else {
                affichage = new AffichageLevel();
            }

            MatriceLabyrinthe matrice = niveauChargee.getMatriceDuNiveau();
            String titre = niveauChargee.getTitre();
            affichage.affichageMursDuLabyrinthe(titre, matrice);
            this.positionDuJoueur = niveauChargee.getPositionEntree();
            this.repaintFenetreLabyrinthe();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText(Level.generateurDeNom());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        this.solutionAfficheeDansLeLabyrinthe = true;
        System.out.println("La solution est maintenant affichée dans le labyrinthe par défaut.");
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    this.repaintFenetreLabyrinthe();
                }
            }
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.seDeplacer(DirectionAbsolue.EST);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        System.out.print("La taille du labyrinthe est indiqué grâce aux deux 'jSpinner' : ");
        System.out.println(jSpinner2.getValue() + "x" + jSpinner1.getValue() + ".");
        MatriceLabyrinthe newMatrice = MatriceLabyrinthe.genererNouvelleMatrice((Integer) jSpinner2.getValue(),(Integer) jSpinner1.getValue()).getFirst();
        System.out.println(newMatrice);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.out.print("la racine de l'arbre (l'entrée du labyrinthe) est générée aléatoirement en fonction de la taille du labyrinthe indiqué dans les deux jSpinner : ");
        System.out.println(jSpinner2.getValue() + "x" + jSpinner1.getValue()+ ".");
        ArbreLabyrinthe newArbre = new ArbreLabyrinthe(Position.genererNouvellePosition((Integer) jSpinner2.getValue(),(Integer) jSpinner1.getValue()));
        System.out.println(newArbre);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        String arbreEnString = JOptionPane.showInputDialog("Entrer le String correspondant à votre arbre");
        if(arbreEnString.length()>0){
            ArbreLabyrinthe newArbre = new ArbreLabyrinthe(arbreEnString);
            System.out.println(newArbre);
        } else {
            System.out.println("Vous n'avez pas tapé de chaine de caractère !");
            System.out.print("Voici un exemple pour vous aider : ");
            System.out.println("[(4,0); ;[(4,1); ;[(4,2);[(3,2); ;[(2,2);[(2,1);[(3,1); ; ;[(3,0); ; ;[(2,0); ; ; ]]]; ; ]; ;[(2,3);[(1,3);[(1,2); ;[(1,1); ; ;[(0,1);[(0,0);[(1,0); ; ; ]; ; ]; ; ]];[(0,2); ; ;[(0,3); ;[(0,4); ; ; ]; ]]]; ;[(1,4); ; ; ]]; ; ]];[(3,3); ; ; ]];[(4,3); ;[(4,4);[(3,4); ;[(2,4); ; ;[(2,5);[(1,5); ;[(0,5); ; ; ]; ]; ;[(3,5); ;[s(4,5); ; ; ]; ]]]; ]; ; ]; ]; ]; ]; ]");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        String arbreEnString = JOptionPane.showInputDialog("Entrer le String correspondant à votre arbre");
        if(arbreEnString.length()>0){
            ArbreLabyrinthe newArbre = new ArbreLabyrinthe(arbreEnString);
            System.out.println("Vous parcourez en largeur cet arbre : ");
            System.out.println(arbreEnString);
            newArbre.parcoursLargeur();
        } else {
            System.out.println("Vous n'avez pas tapé de chaine de caractère !");
            System.out.print("Voici un exemple pour vous aider : ");
            System.out.println("[(4,0); ;[(4,1); ;[(4,2);[(3,2); ;[(2,2);[(2,1);[(3,1); ; ;[(3,0); ; ;[(2,0); ; ; ]]]; ; ]; ;[(2,3);[(1,3);[(1,2); ;[(1,1); ; ;[(0,1);[(0,0);[(1,0); ; ; ]; ; ]; ; ]];[(0,2); ; ;[(0,3); ;[(0,4); ; ; ]; ]]]; ;[(1,4); ; ; ]]; ; ]];[(3,3); ; ; ]];[(4,3); ;[(4,4);[(3,4); ;[(2,4); ; ;[(2,5);[(1,5); ;[(0,5); ; ; ]; ]; ;[(3,5); ;[s(4,5); ; ; ]; ]]]; ]; ; ]; ]; ]; ]; ]");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        String arbreEnString = JOptionPane.showInputDialog("Entrer le String correspondant à votre arbre");
        if(arbreEnString.length()>0){
            ArbreLabyrinthe newArbre = new ArbreLabyrinthe(arbreEnString);
            System.out.println("Vous parcourez en profondeur cet arbre : ");
            System.out.println(arbreEnString);
            newArbre.parcoursProfondeur();
        } else {
            System.out.println("Vous n'avez pas tapé de chaine de caractère !");
            System.out.print("Voici un exemple pour vous aider : ");
            System.out.println("[(4,0); ;[(4,1); ;[(4,2);[(3,2); ;[(2,2);[(2,1);[(3,1); ; ;[(3,0); ; ;[(2,0); ; ; ]]]; ; ]; ;[(2,3);[(1,3);[(1,2); ;[(1,1); ; ;[(0,1);[(0,0);[(1,0); ; ; ]; ; ]; ; ]];[(0,2); ; ;[(0,3); ;[(0,4); ; ; ]; ]]]; ;[(1,4); ; ; ]]; ; ]];[(3,3); ; ; ]];[(4,3); ;[(4,4);[(3,4); ;[(2,4); ; ;[(2,5);[(1,5); ;[(0,5); ; ; ]; ]; ;[(3,5); ;[s(4,5); ; ; ]; ]]]; ]; ; ]; ]; ]; ]; ]");
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        //on récupère les données saisies
        if(jTextField1.getText().isBlank()){
            niveauChargee = new Level((Integer) jSpinner2.getValue(),(Integer) jSpinner1.getValue());
        } else {
            niveauChargee = new Level(jTextField1.getText(), (Integer) jSpinner2.getValue(),(Integer) jSpinner1.getValue());
        }
        
        //on met à jour l'affichage
        if(affichage!=null){
            if(affichage.isFenetreLabyrintheExists()){
                affichage.fermerFenetreLabyrinthe();
            }
            if(affichage.isFenetreArbreLabyrintheExists()){
                affichage.fermerFenetreArbreLabyrinthe();
            }
            if(affichage.isFenetreSolutionExists()){
                affichage.fermerFenetreSolution();
            }
        } else {
            affichage = new AffichageLevel();
        }
        
        MatriceLabyrinthe matrice = niveauChargee.getMatriceDuNiveau();
        String titre = niveauChargee.getTitre();
        affichage.affichageMursDuLabyrinthe(titre, matrice);
        this.positionDuJoueur = niveauChargee.getPositionEntree();
        this.repaintFenetreLabyrinthe();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        ArbreLabyrinthe newArbre = new ArbreLabyrinthe((Integer) jSpinner2.getValue(),(Integer) jSpinner1.getValue());
        System.out.print("Vous avez onstruit l'arbre d'un labyrinthe de taille : ");
        System.out.println(jSpinner2.getValue() + "x" + jSpinner1.getValue()+ ". La taille du labyrinthe est indiqué grâce aux deux 'jSpinner'.");
        System.out.println("Son code est : ");
        System.out.println(newArbre);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreArbreLabyrintheExists()){
                    affichage.fermerFenetreArbreLabyrinthe();
                }
            } else {
                affichage = new AffichageLevel();
            }
            ArbreLabyrinthe arbre = niveauChargee.getArbreDuNiveau();
            String titre = niveauChargee.getTitre();
            affichage.affichageArbreVertical(titre, arbre);
        } else {
            System.out.println("Vous n'avez pas encore chargé de niveau actuellement !");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreSolutionExists()){
                    affichage.fermerFenetreSolution();
                }
            } else {
                affichage = new AffichageLevel();
            }
            ArbreLabyrinthe arbre = niveauChargee.getArbreDuNiveau();
            ArbreLabyrinthe solution = arbre.resoudre(niveauChargee.getPositionSortie());
            String titre = niveauChargee.getTitre();
            affichage.affichageSolutionVertical(titre, solution);
        } else {
            System.out.println("Vous n'avez pas encore chargé de niveau actuellement !");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        this.solutionAfficheeDansLeLabyrinthe = false;
        System.out.println("Par défaut, la solution n'est pas affichée dans le labyrinthe.");
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    this.repaintFenetreLabyrinthe();
                }
            }
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
        this.solutionAfficheeDansLeLabyrinthe = true;
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    ArbreLabyrinthe arbre = niveauChargee.getArbreDuNiveau();
                    ArbreLabyrinthe solution = arbre.resoudre(niveauChargee.getPositionSortie());
                    String titre = niveauChargee.getTitre();
                    affichage.affichageArbreDansMatrice(titre, solution);
                }
            }
        }
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        // TODO add your handling code here:
        if(!jRadioButton1.isSelected()){
            this.solutionAfficheeDansLeLabyrinthe = false;
        }
        if(niveauChargee!=null){
            if(affichage!=null){
                if(affichage.isFenetreLabyrintheExists()){
                    this.repaintFenetreLabyrinthe();
                }
            }
        }
    }//GEN-LAST:event_jPanel4MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.seDeplacer(DirectionAbsolue.OUEST);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.seDeplacer(DirectionAbsolue.NORD);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.seDeplacer(DirectionAbsolue.SUD);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceGraphique().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}