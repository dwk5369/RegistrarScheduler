/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class Conflict {
    private Schedule conflict1;
    private Schedule conflict2;
    private String conflictType;
    
    public enum ConflictType {
        Room,Faculty,Timeslot
    };
}
