/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Chris
 */
public class TimeLog {
    private Date startTime;
    private Date endTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public TimeLog(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeLog() {
    }
   
    
}
