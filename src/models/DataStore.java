
package models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Bulmer
 */
public class DataStore {

    private HashMap<UUID, Project> projects;
    public static Project currentProject;
    
    
    private DataStore() {
        
    }
    
    /**
     * Get the projects
     * @return all the projects
     */
    synchronized HashMap<UUID, Project> getProjects(){
        return this.projects;
    }
    
    synchronized void setProject(Project project){
        this.projects.put(project.getUuid(), project);
        this.save();
    }
    
    synchronized void removeProject(Project project){
        System.out.println(projects);
        this.projects.remove(project.getUuid());
        this.save();
        System.out.println(projects);

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
                projects = (HashMap<UUID, Project>)xstream.fromXML(new FileReader("datastore.xml"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            projects = new HashMap<UUID, Project>();
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
