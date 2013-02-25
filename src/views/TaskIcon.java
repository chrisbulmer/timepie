package views;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import models.Project;

/**
 *
 * @author Chris Bulmer
 */
public class TaskIcon {
    private TrayIcon trayIcon;
    private PopupMenu popup;    

    private TaskIcon() {
        this.init();
    }
    
    /***
     * Get the instance of DataStore
     * @return DataStore object
     */
    public static TaskIcon getInstance() {
        return TaskIconHolder.INSTANCE;
    }

    private static class TaskIconHolder {

        private static final TaskIcon INSTANCE = new TaskIcon();
    }
    
    private void setGreyIcon(){
        this.setIcon(new ImageIcon(getClass().getResource("/greyicon.png")));
//        this.setIcon(new ImageIcon);
    }
    
    private void setRedIcon(){
        this.setIcon(new ImageIcon(getClass().getResource("/redicon.png")));
    }
    
    private void setGreenIcon(){
        this.setIcon(new ImageIcon(getClass().getResource("/greenicon.png")));
    }
    
    private void setOrangeIcon(){
        this.setIcon(new ImageIcon(getClass().getResource("/orangeicon.png")));
    }
    
    private void setIcon(ImageIcon img){
        if (trayIcon != null) {
            trayIcon.setImage(img.getImage());
        }
    }
       
    private void init(){
        SystemTray sysTray = SystemTray.getSystemTray();
        
        // create a popup menu
        popup = new PopupMenu();
        
        this.updatePopupMenu();
        trayIcon = new TrayIcon(new ImageIcon(getClass().getResource("/greyicon.png")).getImage(), "TimePie", popup);
               
        try {
            sysTray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(TaskIcon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void updatePopupMenu(){
        popup.removeAll();
        
        if (models.DataStore.currentProject != null){
            MenuItem currentProject = new MenuItem("Current work: " + models.DataStore.currentProject.getName() + " (" + (models.DataStore.currentProject.getFormattedTotalTime() + ")"));
            currentProject.setEnabled(false);
            popup.add(currentProject);
            
            MenuItem stopCurrentWork = new MenuItem("Stop current work");
            stopCurrentWork.addActionListener(new controllers.StopWorkListener());
            popup.add(stopCurrentWork);

        } else {
            MenuItem currentProject = new MenuItem("No current work");
            currentProject.setEnabled(false);
            popup.add(currentProject);
        }
        
        popup.addSeparator();
        
        HashMap<UUID, Project> projects = Project.getAllProjects();
        Iterator it = projects.entrySet().iterator();
        
        if (models.DataStore.currentProject == null){
            MenuItem projectLabel = new MenuItem("Start working on");
            projectLabel.setEnabled(false);
            popup.add(projectLabel);
            
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                popup.add(createStartProjectItem((Project)pairs.getValue()));
            }

            popup.addSeparator();
        }
        
        MenuItem editProjectLabel = new MenuItem("Edit projects");
        editProjectLabel.setEnabled(false);
        popup.add(editProjectLabel);
        
        it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            popup.add(createEditProjectItem((Project)pairs.getValue()));
        }
        
        popup.addSeparator();
        MenuItem newProjectItem = new MenuItem("New project");
        newProjectItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               new ProjectEditor().init();
           } 
        });
        popup.add(newProjectItem);
        
        popup.addSeparator();
        MenuItem resetTime = new MenuItem("Reset all time");
        resetTime.addActionListener(new controllers.ResetTimeListener());
        popup.add(resetTime);
        
        // Exit option
        
        popup.addSeparator();

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               models.DataStore.getInstance().save();
               System.exit(0);
           } 
        });
        
        popup.add(exitItem);
    }
    
    private MenuItem createEditProjectItem(Project project){
        MenuItem mi = new MenuItem(project.getName());
        mi.addActionListener(new controllers.EditProjectListener(project));
        return mi;
    }
    
    private MenuItem createStartProjectItem(Project project){
        MenuItem mi = new MenuItem(project.getName() + " (" + project.getFormattedTotalTime() + ")");
        mi.addActionListener(new controllers.StartWorkListener(project));
        return mi;
    }
    
    public void setIconColour(Project project){
        if (project != null){
            int timeRemaining = project.getMaxWeekly() - project.getTotalMinutes();

            if (timeRemaining > 120) {
                this.setGreenIcon();
            } else if (timeRemaining > 0) {
                this.setOrangeIcon();
            } else {
                this.setRedIcon();
            }
        } else {
            this.setGreyIcon();
        }
    }
}
