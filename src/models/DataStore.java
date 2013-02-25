/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Bulmer
 */
public class DataStore {
    
    private ArrayList<Project> projects;
    
    
    
    private DataStore() {
        
    }
    
    
    /**
     * Get the projects
     * @return all the projects
     */
    public synchronized ArrayList<Project> getProjects(){
        return this.projects;
    }
    
    /**
     * Set the projects
     * @param projects 
     */
    public synchronized void setProjects(ArrayList<Project> projects){
        this.projects = projects;
    }
    
    /**
     * Save the all of the data
     */
    public synchronized void save(){
        XStream xstream = new XStream(new StaxDriver()); // does not require XPP3 library starting with Java 6

        try  {
            FileOutputStream fs = new FileOutputStream("datastore.xml");
            xstream.toXML(projects, fs);
        } catch (Exception e){
            
        }
    }
    
    public synchronized void load(){
        File file = new File("datastore.xml");

        if (file.exists()){
        
            XStream xstream = new XStream(new StaxDriver()); // does not require XPP3 library starting with Java 6

            try {
                projects = (ArrayList<Project>)xstream.fromXML(new FileReader("datastore.xml"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            projects = new ArrayList<Project>();
        }
    }
    
    
    /***
     * Get the instance of DataStore
     * @return DataStore object
     */
    public static DataStore getInstance() {
        return DataStoreHolder.INSTANCE;
    }
    
    private static class DataStoreHolder {

        private static final DataStore INSTANCE = new DataStore();
    }
    
    
}
