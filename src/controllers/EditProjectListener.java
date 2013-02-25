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
 * @author Chris Bulmer
 */
public class EditProjectListener implements ActionListener {
    private Project project;
    
    public EditProjectListener(Project project){
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new views.ProjectEditor(this.project).init();
    }
    
}
