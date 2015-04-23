import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
public class DBConnect 
{
    private Connection connDB;
    private PreparedStatement psInsert;
    private ResultSet rsResult;
    
    protected String strGetSections = "select * from section left outer join classschedule where sectionID = ''";
    
    protected String strInsertTimeslot = "insert into timeslot (Day, StartTime, EndTime, Evening) values (?,?,?,?)";
    
    protected String strInsertRoom = "";
    
    protected String strInsert = "INSERT INTO course (CourseName,Credits) VALUES (?,?)";
    
    protected String strGetData = "select * from ";
    
    protected String strGetRooms = "select BuildingName, RoomNumber, RoomCapacity, Whiteboard, Chalkboard, ComputerLab, ScienceLab from rooms";
    
    protected String strGetTimeslot = "select Day, StartTime, EndTime, Evening from timeslot;";
    
    protected String strGetTimeslotID = "select TimeslotID from timeslot where Day = ? and StartTime = ? and EndTime = ?";
    
    protected String strGetRoomID = "select RoomID from rooms where BuildingName = ? and RoomNumber = ?";
    
    protected String strGetSectionID = "select SectionID from section where FacultyID = ? and CourseID = ? and SectionNumber = ?";
    
    protected String strSaveSchedule = "update classschedule set SectionID = ? where RoomID = ? and TimeslotID = ?";
    
    protected String strGetCourseID = "select CourseID from course where CourseName = ? and CourseNumber = ?";
    
    protected String strGetFacultyID = "select FacultyID from faculty where FName = ? and LName = ?";    
    
    protected String strGetBuildingNames = "select distinct BuildingName from rooms";
    
    protected String strGetBuildingNums = "select distinct RoomNumber from rooms where BuildingName = ?";
    
    protected String strInsertSchedule = "insert into classschedule (TimeslotID, RoomID) values (?,?)";
    
    protected String strGetSchedule = "select TimeslotID, RoomID, SectionID from classschedule";
    
    protected String strGetFaculty = "select FName, LName, CourseLoad, ChalkAllergy, DayPreference, StartTime, EndTime from faculty";

    protected String strGetFacultyByID = "select FName, LName, CourseLoad, ChalkAllergy, DayPreference, StartTime, EndTime from faculty where FacultyID = ?";    
    
    protected String strGetMajor = "select MajorName, FullName from major";
    
    protected String strGetMajorByID = "select MajorName, FullName from major where MajorID = ?";
    
    protected String strGetCourse = "select CourseName, Credits, CourseNumber, MajorID, ScienceLab, ComputerLab from course";
    
    protected String strGetCourseByID = "select CourseName, Credits, CourseNumber, MajorID, ScienceLab, ComputerLab from course where CourseID = ?";

    protected String strGetSection = "select FacultyID, ClassSize, CourseID from section";
    
    protected String strSectionGet = "SELECT ClassSize, SectionNumber, MajorName, FullName, CourseName, Credits, CourseNumber, ScienceLab, ComputerLab, FName, LName, CourseLoad, ChalkAllergy, DayPreference, StartTime, EndTime FROM SectionInfo;";

    protected String strGetRAPSheet = "SELECT CourseName, Credits, CourseNumber, ScienceLab, ComputerLab, MajorName, FullName, SemesterNumber FROM rap inner join major on rap.MajorID = major.MajorID inner join course on rap.CourseID = course.CourseID;";
    
    //ResultSet, ResultSetMetaData, Statement, PreparedStatement
    public Connection connect()
    { 
            try 
            { 
                Class.forName("com.mysql.jdbc.Driver");
                connDB = DriverManager.getConnection("jdbc:mysql://istdata.bk.psu.edu:3306/nhd5015?" + "user=nhd5015&password=berks5448");
                return connDB;
            } 
            catch (ClassNotFoundException|SQLException ex) 
            { 
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } 

    }// connect

    public Connection getConnDB() {
        return connDB;
    }    
    
    public void saveSchedule(Schedule sched)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strSaveSchedule);
            psInsert.setInt(1, getSectionID(sched.getSection()));
            psInsert.setInt(2, getRoomID(sched.getCourseRoom()));
            psInsert.setInt(3, getTimeslotID(sched.getTimeslot()));
            psInsert.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
    public int getSectionID(Section s)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetSectionID);
            psInsert.setInt(1, getFacultyID(s.getFaculty()));
            psInsert.setInt(2, getCourseID(s.getCourse()));
            psInsert.setString(3, s.getNumber());
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int id = rsResult.getInt(1);
            return id;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }          
        
        
        
    }
    
    public int getFacultyID(Faculty f)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetFacultyID);
            psInsert.setString(1, f.getfName());
            psInsert.setString(2, f.getlName());
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int id = rsResult.getInt(1);
            return id;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }           
    }
    
    public int getCourseID(Course c)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetCourseID);
            psInsert.setString(1, c.getName());
            psInsert.setString(2, c.getNumber());
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int id = rsResult.getInt(1);
            return id;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }          
        
        
        
    }
    
    public RAP[] getRAP()
    {
        String name;
        int credits;
        String coursenum;
        int sciencelab;
        int computerlab;
        Course c;
        String majorname;
        String fullname;
        Major m;
        int semester;
        
        try 
        {
            psInsert = connDB.prepareStatement("select count(CourseID) from rap");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            RAP[] rap = new RAP[length];
            psInsert = connDB.prepareStatement(strGetRAPSheet);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            for(int i = 0; i < length; i++)
            {
                name = rsResult.getString(1).trim();
                credits = rsResult.getInt(2);
                coursenum = rsResult.getString(3).trim();
                sciencelab = rsResult.getInt(4);
                computerlab = rsResult.getInt(5);
                majorname = rsResult.getString(6).trim();
                fullname = rsResult.getString(7).trim();
                semester = rsResult.getInt(8);
                m = new Major(majorname,fullname);
                c = new Course(name,m,coursenum,credits,sciencelab,computerlab);
                rap[i] = new RAP(m,c,semester);
                rsResult.next();
            }
            return rap;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                
    }
    
    public Major[] getMajors()
    {
        String name;
        String fullname;
        
        try 
        {
            psInsert = connDB.prepareStatement("select count(MajorID) from major");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            Major[] maj = new Major[length];           
            psInsert = connDB.prepareStatement(strGetMajor);
            rsResult = psInsert.executeQuery();        
            rsResult.first();
            for(int i = 0; i < length; i++)
            {
                name = rsResult.getString("MajorName");
                fullname = rsResult.getString("FullName");
                maj[i] = new Major(name,fullname);
                rsResult.next();
            }
            return maj;
        }    
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Section[] getSections()
    {
        int classsize;
        String sectionnum;
        String majorname;
        String fullname;
        String coursename;
        int credits;
        String coursenum;
        int sciencelab;
        int computerlab;
        String fname;
        String lname;
        int teachingLoad;
        String preferredDays;
        int chalkAllergy;
        String starttime;
        String endtime;
        Faculty f;
        Course c;
        Major m;
        
        try 
        {
            psInsert = connDB.prepareStatement("select count(SectionID) from section");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            Section[] sec = new Section[length];
            psInsert = connDB.prepareStatement(strSectionGet);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            for(int i = 0; i < sec.length; i++)
            {
                classsize = rsResult.getInt("ClassSize");
                sectionnum = rsResult.getString("SectionNumber").trim();
                majorname = rsResult.getString("MajorName").trim();
                fullname = rsResult.getString("FullName").trim();
                coursename = rsResult.getString("CourseName").trim();
                credits = rsResult.getInt("Credits");
                coursenum = rsResult.getString("CourseNumber").trim();
                sciencelab = rsResult.getInt("ScienceLab");
                computerlab = rsResult.getInt("ComputerLab");
                fname = rsResult.getString("FName").trim();
                lname = rsResult.getString("LName").trim();
                teachingLoad = rsResult.getInt("CourseLoad");
                chalkAllergy = rsResult.getInt("ChalkAllergy");
                preferredDays = rsResult.getString("DayPreference").trim();
                starttime = rsResult.getString("StartTime");
                endtime = rsResult.getString("EndTime");
                m = new Major(majorname,fullname);
                f = new Faculty(fname,lname,teachingLoad,preferredDays,chalkAllergy,starttime,endtime);
                c = new Course(coursename,m,coursenum,credits,sciencelab,computerlab);
                sec[i] = new Section(sectionnum,c,f,classsize);
                rsResult.next();
            }
            return sec;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                     
    }
    
    public String[] getRoomName()
    {
        try 
        {
            psInsert = connDB.prepareStatement("select count(distinct BuildingName) from rooms");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            int i = 0;
            String[] names = new String[length];
            psInsert = connDB.prepareStatement(strGetBuildingNames);
            rsResult = psInsert.executeQuery();
            rsResult.beforeFirst();
            while(rsResult.next())
            {
                names[i] = rsResult.getString(1).trim();
                i++;
            }
            return names;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }

    public String[] getRoomNumber(String name)
    {
        try 
        {
            psInsert = connDB.prepareStatement("select count(distinct RoomNumber) from rooms where BuildingName = ?");
            psInsert.setString(1, name);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            int i = 0;
            String[] numbers = new String[length];
            psInsert = connDB.prepareStatement(strGetBuildingNums);
            psInsert.setString(1,name);
            rsResult = psInsert.executeQuery();
            rsResult.beforeFirst();
            while(rsResult.next())
            {
                numbers[i] = rsResult.getString(1).trim();
                i++;
            }
            return numbers;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }    
    
    public Course getCourseByID(int courseID)
    {
        String name;
        int credits;
        String coursenum;
        int majorID;
        int sciencelab;
        int computerlab;
        Course c;
        
        try 
        {          
            psInsert = connDB.prepareStatement(strGetCourseByID);
            psInsert.setInt(1, courseID);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            name = rsResult.getString(1);
            credits = rsResult.getInt(2);
            coursenum = rsResult.getString(3);
            majorID = rsResult.getInt(4);
            sciencelab = rsResult.getInt(5);
            computerlab = rsResult.getInt(6);
            Major m = getMajorByID(majorID);
            c = new Course(name,m,coursenum,credits,sciencelab,computerlab);
            return c;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Faculty getFacultyByID(int facultyID)
    {
        String fname;
        String lname;
        int teachingLoad;
        String preferredDays;
        int chalkAllergy;
        String starttime;
        String endtime;
        Faculty fac;
        
        try 
        {          
            psInsert = connDB.prepareStatement(strGetFacultyByID);
            psInsert.setInt(1, facultyID);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            fname = rsResult.getString(1);
            lname = rsResult.getString(2);
            teachingLoad = rsResult.getInt(3);
            chalkAllergy = rsResult.getInt(4);
            preferredDays = rsResult.getString(5);
            starttime = rsResult.getString(6);
            endtime = rsResult.getString(7);
            fac = new Faculty(fname,lname,teachingLoad,preferredDays,chalkAllergy,starttime,endtime);
            return fac;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }      
    }
    
    public Major getMajorByID(int majorID)
    {
        String majorname;
        String fullname;
        Major maj;
        
        try 
        {          
            psInsert = connDB.prepareStatement(strGetFacultyByID);
            psInsert.setInt(1, majorID);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            majorname = rsResult.getString(1);
            fullname = rsResult.getString(2);
            maj = new Major(majorname,fullname);
            return maj;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public Faculty[] getFaculty()
    {
        String fname;
        String lname;
        int teachingLoad;
        String preferredDays;
        int chalkAllergy;
        String starttime;
        String endtime;
        
        try 
        {
            psInsert = connDB.prepareStatement("select count(RoomID) from rooms");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            Faculty[] fac = new Faculty[length];
            psInsert = connDB.prepareStatement(strGetFaculty);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            for(int i = 0; i < fac.length; i++)
            {
                fname = rsResult.getString(1);
                lname = rsResult.getString(2);
                teachingLoad = rsResult.getInt(3);
                chalkAllergy = rsResult.getInt(4);
                preferredDays = rsResult.getString(5);
                starttime = rsResult.getString(6);
                endtime = rsResult.getString(7);
                fac[i] = new Faculty(fname,lname,teachingLoad,preferredDays,chalkAllergy,starttime,endtime);
                rsResult.next();
            }
            return fac;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }               
    }
    
    public Schedule[] createSchedule(Room[] room, Timeslot[] timeslot)
    {
        Schedule[] sched = new Schedule[room.length * timeslot.length];
        int i = 0;
        for(Room r: room)
        {
            int rid = getRoomID(r);
            for(Timeslot t: timeslot)
            {
                sched[i] = new Schedule(r,t);
                i++;
                int tid = getTimeslotID(t);
                insertSchedule(tid,rid);
            }
        }   
        return sched;
    }
    
    public Schedule[] createSchedule()
    {
        Room[] room = getRooms();
        Timeslot[] timeslot = getTimeslots();
        
        Schedule[] sched = new Schedule[room.length * timeslot.length];
        int i = 0;
        for(Room r: room)
        {
            int rid = getRoomID(r);
            for(Timeslot t: timeslot)
            {
                sched[i] = new Schedule(r,t);
                i++;
                int tid = getTimeslotID(t);
                insertSchedule(tid,rid);
            }
        }   
        return sched;
    }    
    
    public void insertSchedule(int timeslotID,int roomID)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strInsertSchedule);
            psInsert.setInt(1, timeslotID);
            psInsert.setInt(2, roomID);
            psInsert.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
    public int getRoomID(Room r)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetRoomID);
            psInsert.setString(1, r.getBuilding());
            psInsert.setString(2, r.getNumber());
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int id = rsResult.getInt(1);
            return id;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }     
    }
    
    public int getTimeslotID(Timeslot t)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetTimeslotID);
            psInsert.setString(1, t.getDayOfWeek());
            psInsert.setString(2, t.getStartTime());
            psInsert.setString(3, t.getEndTime());
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int id = rsResult.getInt(1);
            return id;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }   
    }
    public Room[] getRooms()
    {
        String name;
        String number;
        int capacity;
        int whiteboard;
        int chalkboard;
        int complab;
        int scilab;
        
        try 
        {
            psInsert = connDB.prepareStatement("select count(RoomID) from rooms");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            Room[] room = new Room[length];
            psInsert = connDB.prepareStatement(strGetRooms);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            for(int i = 0; i < room.length; i++)
            {
                name = rsResult.getString(1);
                number = rsResult.getString(2);
                capacity = rsResult.getInt(3);
                whiteboard = rsResult.getInt(4);
                chalkboard = rsResult.getInt(5);
                complab = rsResult.getInt(6);
                scilab = rsResult.getInt(7);
                room[i] = new Room(name,number,capacity,whiteboard,chalkboard,complab,scilab);
                rsResult.next();
            }
            return room;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }         
    }
    
    public Timeslot[] getTimeslots()
    {
        String day;
        String sTime;
        String eTime;
        int evening;
        try 
        {
            psInsert = connDB.prepareStatement("select count(TimeslotID) from timeslot as timecount");
            rsResult = psInsert.executeQuery();
            rsResult.first();
            int length = rsResult.getInt(1);
            Timeslot[] time = new Timeslot[length];
            psInsert = connDB.prepareStatement(strGetTimeslot);
            rsResult = psInsert.executeQuery();
            rsResult.first();
            for(int i = 0; i < time.length; i++)
            {
                day = rsResult.getString(1);
                sTime = rsResult.getString(2);
                eTime = rsResult.getString(3);
                evening = rsResult.getInt(4);
                time[i] = new Timeslot(sTime, eTime, day, evening);
                rsResult.next();
            }
            return time;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    
    public void insertCourse(String strClassName, int intCredits)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strInsert);
            psInsert.setString(1, strClassName);
            psInsert.setInt(2, intCredits);
            psInsert.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertTimeslot(String day, String startTime, String endTime, int evening)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strInsertTimeslot);
            psInsert.setString(1, day);
            psInsert.setString(2, startTime);
            psInsert.setString(3, endTime);
            psInsert.setInt(4, evening);
            psInsert.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public ResultSet getData(String tableName)
    {
        try 
        {
            psInsert = connDB.prepareStatement(strGetData + tableName);
            rsResult = psInsert.executeQuery();
            return rsResult;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }    
    
    public void disconnect()
    {
        try 
        {
            connDB.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}