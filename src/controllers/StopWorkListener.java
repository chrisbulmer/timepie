/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Chris
 */
public class StopWorkListener implements ActionListener  {

    @Override
    public void actionPerformed(ActionEvent ae) {
        models.DataStore.currentProject = null;
        views.TaskIcon.getInstance().setIconColour(null);
        views.TaskIcon.getInstance().updatePopupMenu();
    }
    
}
