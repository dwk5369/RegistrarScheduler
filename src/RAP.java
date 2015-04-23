/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class RAP {
    private Major major;
    private Course course;
    private int semester;

    public RAP(Major major, Course course, int semester) {
        this.major = major;
        this.course = course;
        this.semester = semester;
    }

    public Major getMajor() {
        return major;
    }

    public Course getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }
    
    
}
