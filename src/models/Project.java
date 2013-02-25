package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

/**
 * Object for storing the data about projects
 * @author Chris Bulmer
 */
public class Project {
    private String name;
    private int totalTime;
    private int maxWeekly;
    private ArrayList<TimeLog> timeLogs;
    private final UUID uuid;
    
    
    public Project(String name){
        this.name = name;
        this.timeLogs = new ArrayList<TimeLog>();
        this.uuid = UUID.randomUUID();
    }

    public int getMaxWeekly() {
        return maxWeekly;
    }

    public void setMaxWeekly(int maxWeekly) {
        this.maxWeekly = maxWeekly;
    }

    public UUID getUuid() {
        return uuid;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }
    
    public int getTotalMinutes(){
        return this.totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
    
    public String getFormattedTotalTime(){      
        
        String hours = String.valueOf(this.totalTime / 60);
        int mins = this.totalTime % 60;
        if (mins < 10){
            return hours + ":0" + Integer.toString(mins); 
        } else {
            return hours + ":" + Integer.toString(mins); 
        }
    }
    
    public void addTimeLog(TimeLog timeLog){
        this.timeLogs.add(timeLog);
    }

    public ArrayList<TimeLog> getTimeLogs() {
        return timeLogs;
    }
    

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    public static Project getProject(UUID uuid){
        DataStore ds = DataStore.getInstance();
        return ds.getProjects().get(uuid);
    }
    
    public static HashMap<UUID, Project> getAllProjects(){
        DataStore ds = DataStore.getInstance();
        return ds.getProjects();
    }
    
    public void save(){
        DataStore ds = DataStore.getInstance();
        ds.setProject(this);
    }
    
    public void delete(){
        DataStore ds = DataStore.getInstance();
        ds.removeProject(this);
    }
    
    public static boolean exists(UUID uuid){
        DataStore ds = DataStore.getInstance();
        return ds.getProjects().containsKey(uuid);
    }
}
