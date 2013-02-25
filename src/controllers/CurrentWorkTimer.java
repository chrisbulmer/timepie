/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingWorker;
import models.Project;

/**
 *
 * @author Chris
 */
public class CurrentWorkTimer extends SwingWorker<String, Object>{
    
    private Project project;
    private Timer timer;
    
    public CurrentWorkTimer(Project project){
        this.project = project;
    }

    @Override
    protected String doInBackground() throws Exception {
        timer = new Timer();
        timer.scheduleAtFixedRate(new Updater(this.project), 60000, 60000);
        return "";
    }
    
    class Updater extends TimerTask {
        private Project project;
        
        Updater(Project project){
            this.project = project;
        }

        @Override
        public void run() {
            if (Project.exists(this.project.getUuid())){ // Check the project hasn't been deleted
                
                this.project = Project.getProject(this.project.getUuid()); //Update the project stored 
                this.project.setTotalTime(this.project.getTotalMinutes() + 1);
                this.project.save();

                if (models.DataStore.currentProject == null || models.DataStore.currentProject.getUuid() != this.project.getUuid()){
                    // If the project has changed since the last run
                    this.cancel();
                } else {
                    views.TaskIcon.getInstance().setIconColour(project);
                }
                
                views.TaskIcon.getInstance().updatePopupMenu();
                
            } else {
                this.cancel();
            }
        }
        
    }
    
}
