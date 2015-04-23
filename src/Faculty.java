/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class Faculty {

    private String fName;
    private String lName;
    private int teachingLoad;
    private String preferredDays;
    private int chalkAllergy;
    private String startTime;
    private String endTime;

    public Faculty() 
    {
        this.fName = "";
        this.lName = "";
        this.preferredDays = "";
    }
    
    public Faculty(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public Faculty(String fName, String lName, String preferredDays) {
        this.fName = fName;
        this.lName = lName;
        this.preferredDays = preferredDays;
    }

    public Faculty(String fName, String lName, int teachingLoad, String preferredDays, int chalkAllergy, String startTime, String endTime) {
        this.fName = fName;
        this.lName = lName;
        this.teachingLoad = teachingLoad;
        this.preferredDays = preferredDays;
        this.chalkAllergy = chalkAllergy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    
    
    public int getChalkAllergy() {
        return chalkAllergy;
    }

    public void setChalkAllergy(int chalkAllergy) {
        this.chalkAllergy = chalkAllergy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    

    
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getTeachingLoad() {
        return teachingLoad;
    }

    public void setTeachingLoad(int teachingLoad) {
        this.teachingLoad = teachingLoad;
    }

    public String getPreferredDays() {
        return preferredDays;
    }

    public void setPreferredDays(String preferredDays) {
        this.preferredDays = preferredDays;
    }

    @Override
    public String toString() {
        return fName + " " + lName;
    }
    
    
}
