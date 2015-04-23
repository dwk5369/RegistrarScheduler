
import java.util.Objects;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class Section {
    private String number;
    private Course course;
    private Faculty faculty;
    private int courseSize;

    public Section()
    {
        number = "";
        course = new Course();
        faculty = new Faculty();
    }
    
    public Section(String number, Course course, Faculty faculty, int courseSize) 
    {
        this.number = number;
        this.course = course;
        this.faculty = faculty;
        this.courseSize = courseSize;
    }

    public Section(Course course, Faculty faculty, int courseSize) {
        this.course = course;
        this.faculty = faculty;
        this.courseSize = courseSize;
    }    
    
    public Faculty getFaculty() 
    {
        return faculty;
    }

    public void setFaculty(Faculty faculty) 
    {
        this.faculty = faculty;
    }   
    
    public String getNumber() 
    {
        return number;
    }

    public Course getCourse() 
    {
        return course;
    }

    public void setCourse(Course course) 
    {
        this.course = course;
    }

    public int getCourseSize() 
    {
        return courseSize;
    }

    public void setCourseSize(int courseSize) 
    {
        this.courseSize = courseSize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.faculty, other.faculty)) {
            return false;
        }
        if (this.courseSize != other.courseSize) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return course.getMajor().getName() + " " + course.getNumber() + ":" + number;
    }
}
