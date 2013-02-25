package models;

import java.util.ArrayList;

/**
 * Object for storing the data about projects
 * @author Chris Bulmer
 */
public class Project {
    private String name;
    private int totalTime;
    private ArrayList<TimeLog> timeLogs;
    
    
    public Project(String name){
        this.name = name;
        this.timeLogs = new ArrayList<TimeLog>();
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
    
    public String getFormattedTotalTime(){
        int hours = this.totalTime / 60;
        int minutes = this.totalTime % 60;
        return hours + ":" + minutes;
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
}
