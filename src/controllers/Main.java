
package controllers;

import models.DataStore;
import models.Project;
import views.TaskIcon;

/**
 *
 * @author Chris Bulmer
 */
public class Main {   
    public static void main(String[] args) {
        
        // Create datastore and load the data (if there is any).
        DataStore dataStore = DataStore.getInstance();
        dataStore.load();
        
        TaskIcon.getInstance(); //Setup task icon
    }
}
