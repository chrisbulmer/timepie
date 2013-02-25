/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.xml.crypto.Data;
import models.Project;

/**
 *
 * @author Chris
 */
public class ResetTimeListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        HashMap<UUID, Project> projects = Project.getAllProjects();
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Project project = (Project)pairs.getValue();
            project.setTotalTime(0);
            project.save();
        }
        
        views.TaskIcon.getInstance().updatePopupMenu();
    }
    
}
