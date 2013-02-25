/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.DataStore;

/**
 *
 * @author Chris
 */
public class Main {
    public static void main(String[] args) {
        
        // Create datastore and load the data (if there is any).
        DataStore dataStore = DataStore.getInstance();
        dataStore.load();
        
//        Project proj1 = new Project("Project 1");
//        Project proj2 = new Project("Project 2");
//        
//        proj1.addTimeLog(new models.TimeLog());
//        
//        ArrayList<Project> projects = new ArrayList<Project>();
//        projects.add(proj1);
//        projects.add(proj2);
//
//        dataStore.setProjects(projects);
//        
//        dataStore.save();
        
        
        System.out.println(dataStore.getProjects());
    }
}
