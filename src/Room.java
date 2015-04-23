
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
public class Room {

    private String building;
    private String number;
    private int capacity;
    private int whiteboard;
    private int chalkboard;
    private int compLab;
    private int scienceLab;

    public Room(String building, String number, int capacity) {
        this.building = building;
        this.number = number;
        this.capacity = capacity;
    }

    public Room(String building, String number, int capacity, int whiteboard, int chalkboard, int compLab, int scienceLab) {
        this.building = building;
        this.number = number;
        this.capacity = capacity;
        this.whiteboard = whiteboard;
        this.chalkboard = chalkboard;
        this.compLab = compLab;
        this.scienceLab = scienceLab;
    }

   
    
    public int getWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(int whiteboard) {
        this.whiteboard = whiteboard;
    }

    public int getChalkboard() {
        return chalkboard;
    }

    public void setChalkboard(int chalkboard) {
        this.chalkboard = chalkboard;
    }

    public int getCompLab() {
        return compLab;
    }

    public void setCompLab(int compLab) {
        this.compLab = compLab;
    }

    public int getScienceLab() {
        return scienceLab;
    }

    public void setScienceLab(int scienceLab) {
        this.scienceLab = scienceLab;
    }
    
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return building + " " + number;
    }
    
}
