/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import models.Project;

/**
 *
 * @author Chris
 */
public class DeleteProjectListener implements ActionListener {
    private Project project;
    private JDialog parentFrame;

    public DeleteProjectListener(JDialog parentFrame, Project project) {
        this.parentFrame = parentFrame;
        this.project = project;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        project.delete();
        views.TaskIcon.getInstance().updatePopupMenu();
        parentFrame.dispose();
    }
    
}
