
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
public class Course {

    private String name;
    private Major major;
    private String number;
    private int credits;
    private int sciencelab;
    private int computerlab;

    public Course()
    {
        this.name = "";
        this.major = new Major();
        this.number = "";
        this.credits = 0;
    }
    
    public Course(String name, Major major, String number, int credits) {

        this.name = name;
        this.major = major;
        this.number = number;
        this.credits = credits;
    }

    public Course(String name, Major major, String number, int credits, int sciencelab, int computerlab) {
        this.name = name;
        this.major = major;
        this.number = number;
        this.credits = credits;
        this.sciencelab = sciencelab;
        this.computerlab = computerlab;
    }    
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if(!this.major.getFullname().equals(other.major.getFullname()))
            return false;
        if(!this.number.equals(other.number))
            return false;
        if (this.credits != other.credits) {
            return false;
        }
        return true;
    }

    public int getSciencelab() {
        return sciencelab;
    }

    public void setSciencelab(int sciencelab) {
        this.sciencelab = sciencelab;
    }

    public int getComputerlab() {
        return computerlab;
    }

    public void setComputerlab(int computerlab) {
        this.computerlab = computerlab;
    }
    
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    } 

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getCredits() 
    {
        return credits;
    }

    public void setCredits(int credits) 
    {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return major.getName() + " " + number;
    }
    
    

    
}
