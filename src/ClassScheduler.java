
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class ClassScheduler {
    
    private DBConnect dbc;
    private RAP[] rapSheet;
    private Section[] section;
    private Schedule[] schedule;
    private Room[] rooms;
    private Timeslot[] timeslots;
    private String semesterSeason;
    private String semesterYear;
    
    public ClassScheduler()
    {
        dbc = new DBConnect();
        dbc.connect();
        section = dbc.getSections();
        rapSheet = dbc.getRAP();
        rooms = dbc.getRooms();
        timeslots = dbc.getTimeslots();

        try {
            dbc.getConnDB().prepareStatement("delete from classschedule").execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        schedule = dbc.createSchedule(rooms,timeslots);
        dbc.disconnect();
    }
    
    public ClassScheduler(boolean t)
    {
        
    }
    
    public void schedule()
    {
        for(int semester = 1; semester <= 8; semester++)
        {
            for(RAP r : rapSheet)
            {
                if(r.getSemester() == semester)
                    scheduleCourse(r.getCourse());
            }
        }
    }
    
    private void scheduleCourse(Course c)
    {
        
        for(int i = 0; i < section.length; i++)
        {
            for(int j = 0;j < schedule.length; j++)
            {
                String days = section[i].getFaculty().getPreferredDays();
            
                if(section[i].getCourse().equals(c) && !schedule[j].hasSection() && countSection(section[i]) < 2)
                {
                    if(section[i].getCourse().getComputerlab() == 1)
                    {
                        if(schedule[j].getCourseRoom().getCompLab() == 1)
                        {
                            checkChalkAllergy(section[i],schedule[j]);
                        }                        
                    }    
                    else if(section[i].getCourse().getSciencelab() == 1)
                    {
                        if(schedule[j].getCourseRoom().getScienceLab() == 1)
                        {
                            checkChalkAllergy(section[i],schedule[j]);                            
                        }
                    }
                    else
                    {
                        checkChalkAllergy(section[i],schedule[j]);
                    }
                           
                
                }
            }
        }
    }
    
    private void getCompatibleRoom(int intSection)
    {
        for(int i = 0;i < rooms.length;i++)
        {
            if(section[intSection].getCourse().getComputerlab() == 1)
            {
                if(rooms[i].getCompLab() == 1)
                {
                    checkChalkAllergy(intSection,rooms[i]);
                }                        
            }    
            else if(section[intSection].getCourse().getSciencelab() == 1)
            {
                if(rooms[i].getScienceLab() == 1)
                {
                    checkChalkAllergy(intSection,rooms[i]);                       
                }
            }
            else
            {
                checkChalkAllergy(intSection,rooms[i]);
            }            
        }
    }
    
    private void putOnSchedule(int intSection, Room r)
    {
        
    }

    private void checkChalkAllergy(int intSection, Room r)
    {
        if(section[intSection].getFaculty().getChalkAllergy() == 1)
        {
            if(r.getChalkboard() == 0)
            {
                capacityCheck(intSection,r);
            }
        }
        else
        {
            capacityCheck(intSection,r);
        }
    }    

    private void capacityCheck(int intSection, Room r)
    {
        if(section[intSection].getCourseSize() <= r.getCapacity())
        {

        }
    }    
    
    private Schedule checkChalkAllergy(Section sec,Schedule sched)
    {
        if(sec.getFaculty().getChalkAllergy() == 1)
        {
            if(sched.getCourseRoom().getChalkboard() == 0)
                return capacityCheck(sec,sched);
        }
        else
        {
            return capacityCheck(sec,sched);
        }
        return sched;
    }
    
    private Schedule capacityCheck(Section sec,Schedule sched)
    {
        if(sec.getCourseSize() <= sched.getCourseRoom().getCapacity())
        {
            return timeCheck(sec,sched);
        }
        return sched;
    }
    
    private Schedule timeCheck(Section sec,Schedule sched)    
    {
        Schedule sM = searchSchedule(sched,"M");
        Schedule sT = searchSchedule(sched,"T");
        Schedule sW = searchSchedule(sched,"W");
        Schedule sH = searchSchedule(sched,"H");
        Schedule s;
        //Schedule[][] roomSched = populateRoomData(sched.getCourseRoom().getBuilding(),sched.getCourseRoom().getNumber());
        if(compareTimeslots(sec.getFaculty().getStartTime(),sched.getTimeslot().getStartTime()) >= 0 && compareTimeslots(sec.getFaculty().getEndTime(),sched.getTimeslot().getEndTime()) <= 0)
        {
            
            if(sec.getFaculty().getPreferredDays().contains(sched.getTimeslot().getDayOfWeek()))
            //if(sec.getFaculty().getPreferredDays().contains("M") && sec.getFaculty().getPreferredDays().contains("W") && sec.getFaculty().getPreferredDays().contains("F"))    
            {
                
                
                
                switch(sched.getTimeslot().getDayOfWeek())
                {
                    case "M":
                        s = searchSchedule(sched,"W");
                        if(!s.hasSection())
                            s.setSection(sec);
                        s = searchSchedule(sched,"F");
                        if(!s.hasSection())
                            s.setSection(sec);
                        sched.setSection(sec);
                        break;
                    case "T":
                        s = searchSchedule(sched,"H");
                        if(!s.hasSection())
                            s.setSection(sec);
                        sched.setSection(sec);
                        break;
                    case "W":
                        s = searchSchedule(sched,"M");
                        if(!s.hasSection())
                            s.setSection(sec);
                        s = searchSchedule(sched,"F");
                        if(!s.hasSection())
                            s.setSection(sec);
                        sched.setSection(sec);
                        break;
                    case "H":
                        s = searchSchedule(sched,"T");
                        if(!s.hasSection())
                            s.setSection(sec);
                        sched.setSection(sec);
                        break;
                    case "F":
                        s = searchSchedule(sched,"M");
                        if(!s.hasSection())
                            s.setSection(sec);
                        s = searchSchedule(sched,"W");
                        if(!s.hasSection())
                            s.setSection(sec);
                        sched.setSection(sec);
                        break;
                }
                
            }
        }            
        return sched;
    }
    
    public Section[] getUnscheduled()
    {
        ArrayList<Section> aS = new ArrayList();
        boolean isScheduled = false;
        for(Section s: section)
        {
            for(Schedule sched: schedule)
            {
                if(sched.getSection().equals(s))
                    isScheduled = true;
            }
            if(!isScheduled)
                aS.add(s);
            isScheduled = false;
        }
        Section[] sec = new Section[aS.size()];
        int i = 0;
        for(Section s: aS)
        {
            sec[i] = s;
            i++;
        }
        return sec;
    }
    
    public int countSection(Section sec)
    {
        int count = 0;
        for(Schedule s: schedule)
        {
            if(s.getSection().equals(sec))
                    count++;
        }
        return count;
    }
    
    public Major[] getMajors()
    {
        dbc.connect();
        Major[] maj = dbc.getMajors();
        dbc.disconnect();
        return maj;
    }
    
    public void saveSchedule()
    {
        
    }


   
    
    /**
     * 
     * 
     * @param t1
     * @param t2
     * @return 1 if t1 comes before t2, -1 if t1 comes after t2, and 0 if they are the same time 
     */
    public int compareTimeslots(String t1, String t2)
    {
        int hour1 = Integer.parseInt(t1.split(":")[0]);
        int hour2 = Integer.parseInt(t2.split(":")[0]);
        int min1 = Integer.parseInt(t1.split(":")[1].substring(0,2));
        int min2 = Integer.parseInt(t2.split(":")[1].substring(0,2));
        String ampm1 = t1.substring(t1.length()-2);
        String ampm2 = t2.substring(t2.length()-2);
   
        if(ampm1.contains("A"))
        {
            if(ampm2.contains("P"))
                return 1;
            else if(hour1 == 12)
            {
                if(hour2 == 12)
                {
                    if(min1 == min2)
                        return 0;
                    else if(min1 > min2)
                        return -1;
                    else
                        return 1;                    
                }
                else
                    return 1;
            }
            else
            {
                if(hour1 == hour2)
                {
                    if(min1 == min2)
                        return 0;
                    else if(min1 > min2)
                        return -1;
                    else
                        return 1;
                }
                else if(hour1 > hour2)
                    return -1;
                else
                    return 1;                
            }
        }
        else if(ampm1.contains("P"))
        {
            if(ampm2.contains("A"))
                return -1;
            else if(hour1 == 12)
            {
                if(hour2 == 12)
                {
                    if(min1 == min2)
                        return 0;
                    else if(min1 > min2)
                        return -1;
                    else
                        return 1;                    
                }
                else
                    return 1;
            }
            else
            {
                if(hour1 == hour2)
                {
                    if(min1 == min2)
                        return 0;
                    else if(min1 > min2)
                        return -1;
                    else
                        return 1;
                }
                else if(hour1 > hour2)
                    return -1;
                else
                    return 1;                
            }
        }
        
        
        
        return 0;
    }

    public String[] getBuildingNames()
    {
        dbc.connect();
        String names[] = dbc.getRoomName();
        dbc.disconnect();
        return names;
    }
    
    public String[] getBuildingNumbers(String buildingname)
    {
        dbc.connect();
        String nums[] = dbc.getRoomNumber(buildingname);
        dbc.disconnect();
        return nums;
    }
    
    public Schedule[][] populateRoomData(String room, String num)
    { 
        Schedule[][] roomSchedule = new Schedule[5][9];
        int m = 0;
        int t = 0;
        int w = 0;
        int h = 0;
        int f = 0;
        
        for(Schedule s: schedule)
        {
            Room r = s.getCourseRoom();
            if(r.getBuilding().equals(room) && r.getNumber().equals(num))
            {
                switch(s.getTimeslot().getDayOfWeek())
                {
                    case "M":
                        roomSchedule[0][m] = s;
                        m++;
                        break;
                    case "T":
                        roomSchedule[1][t] = s;
                        t++;
                        break;
                    case "W":
                        roomSchedule[2][w] = s;
                        w++;
                        break;
                    case "H":
                        roomSchedule[3][h] = s;
                        h++;
                        break;
                    case "F":
                        roomSchedule[4][f] = s;
                        f++;
                        break;
                }
            }
        }
        return roomSchedule;
    }
    
    public Schedule[][][] getAllRooms()
    {
        Schedule[][][] allRooms = new Schedule[rooms.length][5][9];
        int i = 0;
        for(Room r: rooms)
        {
            allRooms[i] = populateRoomData(r.getBuilding(),r.getNumber());
            i++;
        }
        return allRooms;
    }
            
    public Course[] getRAPCourses(String major, int intSemester)
    {
        Course RAPCourse[] = new Course[rapSheet.length];
        for(int i = 0;i<rapSheet.length;i++)
        {
            if(rapSheet[i].getMajor().getFullname().equals(major) && rapSheet[i].getSemester() == intSemester)
                RAPCourse[i] = rapSheet[i].getCourse();
        }
        return RAPCourse;
    }    
    
    public Schedule searchSchedule(Schedule s, String day)
    {
        for(Schedule sched: schedule)
        {
            if (sched.getTimeslot().compare(s.getTimeslot()) && sched.getTimeslot().getDayOfWeek().equals(day) && sched.getCourseRoom().equals(s.getCourseRoom()))
            {
                return sched;
            }
        }
        return null;
    }

    public Room searchRooms(String building, String num)
    {
        for(Room r: rooms)
        {
            if(building.equals(r.getBuilding()) && num.equals(r.getNumber()))
                return r;
        }
        return null;
    }    
    
    public RAP[] getRapSheet() {
        return rapSheet;
    }

    public void setRapSheet(RAP[] rapSheet) {
        this.rapSheet = rapSheet;
    }

    public Section[] getSection() {
        return section;
    }

    public void setSection(Section[] section) {
        this.section = section;
    }

    public Schedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule[] schedule) {
        this.schedule = schedule;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Timeslot[] getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(Timeslot[] timeslots) {
        this.timeslots = timeslots;
    }

    public String getSemesterSeason() {
        return semesterSeason;
    }

    public void setSemesterSeason(String semesterSeason) {
        this.semesterSeason = semesterSeason;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }
    
    
}
