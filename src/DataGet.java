
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class DataGet {
    public static Major ist = new Major("IST","Information Science & Technology");
    public static Major sra = new Major("SRA","Security and Risk Analysis");
    public static Major eng = new Major("ENG","English");
    public static Major math = new Major("MATH","Mathematics");
    public static Major cas = new Major("CAS","Communication Arts & Sciences");
    public static Major cmpsc = new Major("CMPSC","Computer Science");
    public static Major stat = new Major("STAT","Statistics");
    
    public static Course c1 = new Course("Introduction to Information, People, and Technology", DataGet.ist,"110",3);
    public static Course c2 = new Course("Introduction to Application Development", DataGet.ist,"140",3);
    public static Course c3 = new Course("Organization of Data", DataGet.ist,"210",4);
    public static Course c4 = new Course("Intermediate & Object-Oriented Application Development", DataGet.ist,"242",3);
    public static Course c5 = new Course("Rhetoric and Composition", DataGet.eng,"15",3);
    public static Course c6 = new Course("Honors Freshman Composition", DataGet.eng,"30",3);
    public static Course c7 = new Course("Techniques of Calculus", DataGet.math,"110",4);
    public static Course c8 = new Course("Calculus w/ Analytic Geometry", DataGet.math,"140",4);
    public static Course c9 = new Course("Introduction to Programming Techniques", DataGet.cmpsc,"121",3);
    public static Course c10 = new Course("Effective Speech", DataGet.cas,"100A",3);
    public static Course c11 = new Course("Introduction to Security and Risk Analysis", DataGet.sra,"111",3);
    public static Course c12 = new Course("Threat of Terrorism and Crime", DataGet.sra,"211",3);
    public static Course c13 = new Course("Elementary Statistics", DataGet.stat,"200",4);

    public static Course courseData[] = {c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13};
    
    public static RAP rap1 = new RAP(ist,c1,1);
    public static RAP rap2 = new RAP(sra,c1,1);
    public static RAP rap3 = new RAP(ist,c2,1);
    public static RAP rap4 = new RAP(ist,c3,2);
    public static RAP rap5 = new RAP(ist,c4,2);
    public static RAP rap6 = new RAP(ist,c5,1);
    public static RAP rap7 = new RAP(sra,c5,1);
    public static RAP rap8 = new RAP(ist,c6,1);
    public static RAP rap9 = new RAP(sra,c6,1);
    public static RAP rap10 = new RAP(ist,c7,1);
    public static RAP rap11 = new RAP(ist,c8,1);
    public static RAP rap12 = new RAP(ist,c9,1);
    public static RAP rap13 = new RAP(ist,c10,2);
    public static RAP rap14 = new RAP(sra,c10,2);
    public static RAP rap15 = new RAP(sra,c11,1);
    public static RAP rap16 = new RAP(sra,c12,2);
    public static RAP rap17 = new RAP(sra,c13,2);
    public static RAP rap18 = new RAP(ist,c13,4);
    
    public static RAP RAPData[] = {rap1,rap2,rap3,rap4,rap5,rap6,rap7,rap8,rap9,rap10,rap11,rap12,rap13,rap14,rap15,rap16,rap17,rap18};
    
    public static Timeslot m1 = new Timeslot("8:00AM","8:50AM","M");
    public static Timeslot w1 = new Timeslot("8:00AM","8:50AM","W");
    public static Timeslot f1 = new Timeslot("8:00AM","8:50AM","F");
    public static Timeslot m2 = new Timeslot("9:00AM","9:50AM","M");
    public static Timeslot w2 = new Timeslot("9:00AM","9:50AM","W");
    public static Timeslot f2 = new Timeslot("9:00AM","9:50AM","F");
    public static Timeslot m3 = new Timeslot("10:00AM","10:50AM","M");
    public static Timeslot w3 = new Timeslot("10:00AM","10:50AM","W");
    public static Timeslot f3 = new Timeslot("10:00AM","10:50AM","F");
    public static Timeslot m4 = new Timeslot("11:00AM","11:50AM","M");
    public static Timeslot w4 = new Timeslot("11:00AM","11:50AM","W");
    public static Timeslot f4 = new Timeslot("11:00AM","11:50AM","F");
    public static Timeslot m5 = new Timeslot("12:00PM","12:50PM","M");
    public static Timeslot w5 = new Timeslot("12:00PM","12:50PM","W");
    public static Timeslot f5 = new Timeslot("12:00PM","12:50PM","F");
    public static Timeslot m6 = new Timeslot("2:30PM","3:20PM","M");
    public static Timeslot w6 = new Timeslot("2:30PM","3:20PM","W");
    public static Timeslot f6 = new Timeslot("2:30PM","3:20PM","F");
    public static Timeslot m7 = new Timeslot("3:30PM","4:20PM","M");
    public static Timeslot w7 = new Timeslot("3:30PM","4:20PM","W");
    public static Timeslot f7 = new Timeslot("3:30PM","4:20PM","F");
    public static Timeslot m8 = new Timeslot("4:30PM","5:45PM","M");
    public static Timeslot w8 = new Timeslot("4:30PM","5:45PM","W");
    public static Timeslot f8 = new Timeslot("4:30PM","5:45PM","F");
    public static Timeslot m9 = new Timeslot("6:30PM","8:55PM","M");
    public static Timeslot w9 = new Timeslot("6:30PM","8:55PM","W");
    public static Timeslot f9 = new Timeslot("6:30PM","8:55PM","F");
    
    public static Timeslot t1 = new Timeslot("8:00AM","9:15AM","T");
    public static Timeslot h1 = new Timeslot("8:00AM","9:15AM","H");
    public static Timeslot t2 = new Timeslot("9:25AM","10:40AM","T");
    public static Timeslot h2 = new Timeslot("9:25AM","10:40AM","H");
    public static Timeslot t3 = new Timeslot("10:50AM","12:05PM","T");
    public static Timeslot h3 = new Timeslot("10:50AM","12:05PM","H");
    public static Timeslot t4 = new Timeslot("12:15PM","1:30PM","T");
    public static Timeslot h4 = new Timeslot("12:15PM","1:30PM","H");
    public static Timeslot t5 = new Timeslot("1:40PM","2:55PM","T");
    public static Timeslot h5 = new Timeslot("1:40PM","2:55PM","H");
    public static Timeslot t6 = new Timeslot("3:05PM","4:20PM","T");
    public static Timeslot h6 = new Timeslot("3:05PM","4:20PM","H");
    public static Timeslot t7 = new Timeslot("4:30PM","5:45PM","T");
    public static Timeslot h7 = new Timeslot("4:30PM","5:45PM","H");
    public static Timeslot t8 = new Timeslot("6:30PM","8:55PM","T");
    public static Timeslot h8 = new Timeslot("6:30PM","8:55PM","H");
    
    public static Timeslot[] timeslotData = {m1,m2,m3,m4,m5,m6,m7,m8,m9,t1,
        t2,t3,t4,t5,t6,t7,t8,w1,w2,w3,w4,w5,w6,w7,w8,w9,h1,h2,h3,h4,h5,h6,
        h7,h8,f1,f2,f3,f4,f5,f6,f7,f8,f9};
    
    public static Room r1 = new Room("Gaige","209",20);
    public static Room r2 = new Room("Gaige","204",34);
    public static Room r3 = new Room("Gaige","205",32);
    
    public static Room[] roomData = {r1,r2,r3};
    
    public static Faculty bowers = new Faculty("William","Bowers","MWF");
    public static Faculty nasereddin = new Faculty("Mahdi","Nasereddin","TH");
    public static Faculty konak = new Faculty("Abdullah","Konak","MWF");
    public static Faculty hassler = new Faculty("Ryan","Hassler","MWF");
    
    /**
     *
     */
    public static Schedule scheduleData[] = new Schedule[43*3];
    public static Schedule roomSchedule[][];
    public static Schedule allRooms[][][];
    
    public static void populateData()
    {
        int i = 0;
        for(Room room: roomData)
        {
            for(Timeslot timeslot: timeslotData)
            {
                scheduleData[i] = new Schedule(room,timeslot);
                i++;
            }
        }
        scheduleData[20].setSection(new Section("001",c1,nasereddin,23));
        scheduleData[23].setSection(new Section("002",c1,nasereddin,23));
        scheduleData[100].setSection(new Section("001",c3,konak,20));
        scheduleData[110].setSection(new Section("001",c3,konak,20));
        scheduleData[11].setSection(new Section("001",c2,bowers,24));
        scheduleData[18].setSection(new Section("002",c2,bowers,28));
        scheduleData[43].setSection(new Section("001",c13,hassler,30));
        scheduleData[53].setSection(new Section("002",c13,hassler,30));
        scheduleData[99].setSection(new Section("001",c9,nasereddin,18));
        scheduleData[8].setSection(new Section("002",c9,nasereddin,18));
    }
    
    public static Schedule[][] populateRoomData(String room, String num)
    { 
        populateData();
        roomSchedule = new Schedule[5][9];
        int m = 0;
        int t = 0;
        int w = 0;
        int h = 0;
        int f = 0;
        
        for(Schedule s: scheduleData)
        {
            Room r = s.getCourseRoom();
            if(r.getBuilding().equals(room) && r.getNumber() == num)
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
    
    public static Schedule[][][] getAllRooms()
    {
        allRooms = new Schedule[roomData.length][5][9];
        int i = 0;
        for(Room r: roomData)
        {
            allRooms[i] = populateRoomData(r.getBuilding(),r.getNumber());
            i++;
        }
        return allRooms;
    }
            
    public static Course[] getRAPCourses(String major, int intSemester)
    {
        Course RAPCourse[] = new Course[RAPData.length];
        for(int i = 0;i<RAPData.length;i++)
        {
            if(RAPData[i].getMajor().getFullname().equals(major) && RAPData[i].getSemester() == intSemester)
                RAPCourse[i] = RAPData[i].getCourse();
        }
        return RAPCourse;
    }
    
}
