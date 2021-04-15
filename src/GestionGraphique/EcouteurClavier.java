/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionGraphique;

import StructureInformatique.DirectionAbsolue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author nico
 */
public class EcouteurClavier implements KeyListener {
    @Override
    public void keyPressed(KeyEvent event){
    }
    
    @Override
    public void keyTyped(KeyEvent event){
    }
    
    @Override
    public void keyReleased(KeyEvent event){
        int keyCode = (Integer) event.getKeyCode();
        InterfaceGraphique interfaceGraphique = InterfaceGraphique.getLAST_INTERFACE_CREATED();
        switch (keyCode) {
            case 65:
            case 37:
                interfaceGraphique.seDeplacer(DirectionAbsolue.OUEST);
                break;
            case 87:
            case 38:
                interfaceGraphique.seDeplacer(DirectionAbsolue.NORD);
                break;
            case 68:
            case 39:
                interfaceGraphique.seDeplacer(DirectionAbsolue.EST);
                break;
            case 83:
            case 40:
                interfaceGraphique.seDeplacer(DirectionAbsolue.SUD);
                break;
            default:
                break;
        }
    }

}
