/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JTextField;
import models.Project;

/**
 *
 * @author Chris
 */
public class SaveProjectListener implements ActionListener {
    private Project project;
    private JTextField projectName;
    private JTextField maxHours;
    private JDialog parentWindow;

    public SaveProjectListener(JDialog parentWindow, Project project, JTextField projectName, JTextField maxHours) {
        this.parentWindow = parentWindow;
        this.project = project;
        this.projectName = projectName;
        this.maxHours = maxHours;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String projectName = this.projectName.getText();
        int maxHours = Integer.parseInt(this.maxHours.getText());
        
        if (project != null){
            project.setName(projectName);
        } else {
            // Creating a new project
            project = new Project(projectName);            
        }
        
        project.setMaxWeekly(maxHours * 60);
        project.save();
        views.TaskIcon.getInstance().updatePopupMenu();
        parentWindow.dispose();
    }
    
}
