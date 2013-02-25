/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Project;

/**
 *
 * @author Chris
 */
public class StartWorkListener implements ActionListener {
    private Project project;    
    
    public StartWorkListener(Project project){
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        models.DataStore.currentProject = this.project;
        views.TaskIcon.getInstance().setIconColour(project);
        views.TaskIcon.getInstance().updatePopupMenu();
        new CurrentWorkTimer(project).run();
    }
    
}
