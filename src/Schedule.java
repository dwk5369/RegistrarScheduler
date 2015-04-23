
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class Schedule implements Serializable{
    private Section sCourse;
    private Room courseRoom;
    private Timeslot timeslot;

    public Schedule(Room courseRoom, Timeslot timeslot) {
        this.courseRoom = courseRoom;
        this.timeslot = timeslot;
        this.sCourse = new Section();
    }

    public boolean hasSection()
    {
        if(sCourse.getCourseSize() == 0)
            return false;
        else
            return true;
    }
    
    public Section getSection() {
        return sCourse;
    }

    public void setSection(Section sCourse) {
        this.sCourse = sCourse;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(Room courseRoom) {
        this.courseRoom = courseRoom;
    }

    @Override
    public String toString() 
    {
        if(sCourse.getNumber().equals(""))
            return "<html>" + timeslot.toString();
        else
            return "<html><div style='text-align:center'>" + timeslot.toString() + "<br>" + sCourse.toString() + "<br>" + sCourse.getFaculty().getfName().substring(0,1) + " " + sCourse.getFaculty().getlName();
    }
    
    
}
