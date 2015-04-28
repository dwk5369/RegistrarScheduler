import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *May 5th 1pm presentation
 * @author dwk5369
 */
public class ScheduleView extends javax.swing.JPanel {

    private ClassScheduler cs;
    private DefaultListModel dlmCourse;
    private ListSelectionListener lslFun;
    private DefaultComboBoxModel dcbmMajor;
    
    private JToggleButton jtbMonday[];
    private JToggleButton jtbTuesday[];
    private JToggleButton jtbWednesday[];
    private JToggleButton jtbThursday[];
    private JToggleButton jtbFriday[];
    private JToggleButton jtbSchedule[][];
    
    private Schedule roomSchedule[][];
    private Schedule allRooms[][][];
    
    private final Color PSUBLUE = new Color(0,56,147);
    private final Color CONFLICTRED = new Color(140,38,51);
    
    /**
     * Creates new form ScheduleView
     */
    public ScheduleView() {
        initComponents();
        jtbMonday = new JToggleButton[]{jtbMonday1, jtbMonday2,jtbMonday3,jtbMonday4,jtbMonday5,jtbMonday6,jtbMonday7,jtbMonday8,jtbMonday9};
        jtbTuesday = new JToggleButton[]{jtbTuesday1, jtbTuesday2,jtbTuesday3,jtbTuesday4,jtbTuesday5,jtbTuesday6,jtbTuesday7,jtbTuesday8};
        jtbWednesday = new JToggleButton[]{jtbWednesday1, jtbWednesday2,jtbWednesday3,jtbWednesday4,jtbWednesday5,jtbWednesday6,jtbWednesday7,jtbWednesday8,jtbWednesday9};
        jtbThursday = new JToggleButton[]{jtbThursday1, jtbThursday2,jtbThursday3,jtbThursday4,jtbThursday5,jtbThursday6,jtbThursday7,jtbThursday8};
        jtbFriday = new JToggleButton[]{jtbFriday1, jtbFriday2,jtbFriday3,jtbFriday4,jtbFriday5,jtbFriday6,jtbFriday7,jtbFriday8,jtbFriday9};
        jtbSchedule = new JToggleButton[][]{jtbMonday,jtbTuesday,jtbWednesday,jtbThursday,jtbFriday};
        dlmCourse = new DefaultListModel();
        //jliCourse.setDragEnabled(true);
        cs = new ClassScheduler();
        allRooms = cs.getAllRooms();
        dcbmMajor = new DefaultComboBoxModel();
        Major[] maj = cs.getMajors();
        for(Major m: maj)
            dcbmMajor.addElement(m);
        jcbMajor.setModel(dcbmMajor);
        fillRoomBoxes();
        initializeList();
        repaintButtons();
        setActionListeners();
        //setTransferHandlers();
    }
    
    private void setActionListeners()
    {
        ActionListener al = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton jtb = (JToggleButton)e.getSource();
                selectSimilar(jtb.getText()); 
            }
            
        };
   
        int k;
        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                jtbSchedule[i][j].addActionListener(al);
            }
        }
    }
    
    public void selectSimilar(String strText)
    {
        int k;
        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                jtbSchedule[i][j].setSelected(false);
            }
        }
        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                if(jtbSchedule[i][j].getText().equals(strText))
                {
                    jtbSchedule[i][j].setSelected(true);
                }
            }
        }        
    }
    
    public void setCS(ClassScheduler cs)
    {
        this.cs = cs;
        allRooms = cs.getAllRooms();
    }
    
    public ClassScheduler getCS()
    {
        return cs;
    }

    public Schedule[][][] getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(Schedule[][][] allRooms) {
        this.allRooms = allRooms;
    }
    
    private void fillRoomBoxes()
    {
        jcbRoom.setModel(new DefaultComboBoxModel(cs.getBuildingNames()));
        fillComboBoxes();
    }
    
    private void fillComboBoxes()
    {
        jcbRoomNo.setModel(new DefaultComboBoxModel(cs.getBuildingNumbers(jcbRoom.getSelectedItem().toString())));
        jcbRoomNo.setSelectedIndex(0);
        fillExtraLabels();
    }
    
    private void fillExtraLabels()
    {
        Room r = cs.searchRooms(jcbRoom.getSelectedItem().toString(), jcbRoomNo.getSelectedItem().toString());
        String size = r.getCapacity() + " seats";
        String extra = "| ";
        if(r.getChalkboard() == 1)
            extra += "Chalkboard | ";
        if(r.getCompLab() == 1)
            extra += "Computer Lab | ";
        if(r.getWhiteboard() == 1)
            extra += "Whiteboard | ";
        if(r.getScienceLab() == 1)
            extra += "Science Lab |";
        jlRoom.setText(size);
        jlExtraInfo.setText(extra);        
    }
    
    private void repaintButtons()
    {
        String room = jcbRoom.getSelectedItem().toString();
        int num = Integer.parseInt(jcbRoomNo.getSelectedItem().toString());
        int roomNum = 0;
        for(int x = 0;x < allRooms.length;x++)
        {
            if(allRooms[x][0][0].getCourseRoom().toString().equals(room + " " + num))
                roomNum = x;
        }
        roomSchedule = allRooms[roomNum];
        int k;

        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                jtbSchedule[i][j].setText(roomSchedule[i][j].toString());
            }
        }
        maintainList();
        setColors();
        rapSheetCheck();
    }
    
    private void initializeList()
    {
        Section[] sec = cs.getUnscheduled();
        for(Section s: sec)
            dlmCourse.addElement(s);
        lslFun = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                if(jliCourse.getSelectedIndex() == -1)
                {
                    jlInstructor.setText("");
                    jlCredits.setText("");
                    jlPreferences.setText("");
                    jlCapacity.setText("");
                    //jliCourse.setSelectedIndex(0);
                }
                else    
                {
                    Section s = (Section)jliCourse.getSelectedValue();
                    Faculty f = s.getFaculty();
                    Course c = s.getCourse();
                    jlInstructor.setText(f.getfName() + " " + f.getlName());
                    jlCredits.setText(c.getCredits() + "");
                    jlPreferences.setText(f.getPreferredDays());
                    jlCapacity.setText(s.getCourseSize() + "");
                }
            }
        };
        jliCourse.addListSelectionListener(lslFun);
        jliCourse.setModel(dlmCourse);        
    }
    
    private void maintainList()
    {
        Section[] sec = cs.getUnscheduled();
        int index = jliCourse.getSelectedIndex();
        jliCourse.removeListSelectionListener(lslFun);
        dlmCourse.removeAllElements();
        for(Section s: sec)
            dlmCourse.addElement(s);
        if(index < sec.length && index > 0)
            jliCourse.setSelectedIndex(index);
        jliCourse.addListSelectionListener(lslFun);
        jliCourse.setModel(dlmCourse);
    }
        
    private void setColors()
    {
        // PSU Blue: new Color(0,56,147)
        // RAP Conflict red: new Color(140,38,51)
        int k;
        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                jtbSchedule[i][j].setBackground(PSUBLUE);
                jtbSchedule[i][j].setForeground(Color.white);
                jtbSchedule[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }
    }
    
    private void rapSheetCheck()
    {   
        String strMajor = jcbMajor.getSelectedItem().toString();
        int num = jcbSemester.getSelectedItem().toString().length();
        int semester = Integer.parseInt(jcbSemester.getSelectedItem().toString().substring(num-1, num));
        Course rap[] = cs.getRAPCourses(strMajor, semester);
        int i;
        int j;
        int k;
        int x;
        for(Course c: rap)
        {

            for(x=0;x<cs.getRooms().length;x++)
            {
                for(i=0;i<5;i++)
                {
                    if(i == 1 || i == 3)
                        k = 8;
                    else
                        k = 9;
                    for(j=0;j<k;j++)
                    {
                        if(allRooms[x][i][j].getSection().getCourse() != null && c != null)
                        {
                            if(c.equals(allRooms[x][i][j].getSection().getCourse()))
                            {
                                jtbSchedule[i][j].setBackground(CONFLICTRED);
                                jtbSchedule[i][j].setToolTipText(allRooms[x][i][j].getSection().getCourse() + " scheduled in " + allRooms[x][i][j].getCourseRoom().toString());
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    private void setTransferHandlers()
    {
        int k;
        for(int i=0;i<5;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j=0;j<k;j++)
            {
                jtbSchedule[i][j].setTransferHandler(new TransferHandler("text"));
            }
        }
    }

    public void doSchedule()
    {
        jcbRoomNo.setSelectedIndex(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpScheduler = new javax.swing.JPanel();
        jtbMonday1 = new javax.swing.JToggleButton();
        jtbMonday2 = new javax.swing.JToggleButton();
        jtbMonday3 = new javax.swing.JToggleButton();
        jtbMonday4 = new javax.swing.JToggleButton();
        jtbMonday5 = new javax.swing.JToggleButton();
        jMondayCommonHour = new javax.swing.JToggleButton();
        jtbMonday6 = new javax.swing.JToggleButton();
        jtbMonday7 = new javax.swing.JToggleButton();
        jtbMonday8 = new javax.swing.JToggleButton();
        jtbMonday9 = new javax.swing.JToggleButton();
        jtbTuesday1 = new javax.swing.JToggleButton();
        jtbTuesday2 = new javax.swing.JToggleButton();
        jtbTuesday4 = new javax.swing.JToggleButton();
        jtbTuesday3 = new javax.swing.JToggleButton();
        jtbTuesday5 = new javax.swing.JToggleButton();
        jtbTuesday6 = new javax.swing.JToggleButton();
        jtbTuesday7 = new javax.swing.JToggleButton();
        jtbTuesday8 = new javax.swing.JToggleButton();
        jtbWednesday1 = new javax.swing.JToggleButton();
        jtbWednesday2 = new javax.swing.JToggleButton();
        jtbWednesday3 = new javax.swing.JToggleButton();
        jtbWednesday4 = new javax.swing.JToggleButton();
        jtbWednesday5 = new javax.swing.JToggleButton();
        jtbWednesdayCommonHour = new javax.swing.JToggleButton();
        jtbWednesday6 = new javax.swing.JToggleButton();
        jtbWednesday7 = new javax.swing.JToggleButton();
        jtbWednesday8 = new javax.swing.JToggleButton();
        jtbWednesday9 = new javax.swing.JToggleButton();
        jtbThursday1 = new javax.swing.JToggleButton();
        jtbThursday2 = new javax.swing.JToggleButton();
        jtbThursday3 = new javax.swing.JToggleButton();
        jtbThursday4 = new javax.swing.JToggleButton();
        jtbThursday5 = new javax.swing.JToggleButton();
        jtbThursday6 = new javax.swing.JToggleButton();
        jtbThursday7 = new javax.swing.JToggleButton();
        jtbThursday8 = new javax.swing.JToggleButton();
        jtbFriday9 = new javax.swing.JToggleButton();
        jtbFriday8 = new javax.swing.JToggleButton();
        jtbFriday7 = new javax.swing.JToggleButton();
        jtbFriday6 = new javax.swing.JToggleButton();
        jtbFridayCommonHour = new javax.swing.JToggleButton();
        jtbFriday5 = new javax.swing.JToggleButton();
        jtbFriday4 = new javax.swing.JToggleButton();
        jtbFriday3 = new javax.swing.JToggleButton();
        jtbFriday2 = new javax.swing.JToggleButton();
        jtbFriday1 = new javax.swing.JToggleButton();
        jbAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jliCourse = new javax.swing.JList();
        jbSave = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();
        jcbMajor = new javax.swing.JComboBox();
        jcbSemester = new javax.swing.JComboBox();
        jcbRoom = new javax.swing.JComboBox();
        jlRoom = new javax.swing.JLabel();
        jlExtraInfo = new javax.swing.JLabel();
        jcbRoomNo = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlPreferences = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlCredits = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlInstructor = new javax.swing.JLabel();
        jlCapacity = new javax.swing.JLabel();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jpScheduler.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpSchedulerFocusGained(evt);
            }
        });

        jtbMonday1.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday1.setText("<html>8:00 - 8:50<br>");
        jtbMonday1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbMonday1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbMonday1ActionPerformed(evt);
            }
        });

        jtbMonday2.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday2.setText("<html>9:00 - 9:50<br>");
        jtbMonday2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday3.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday3.setText("<html>10:00 - 10:50<br>");
        jtbMonday3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday4.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday4.setText("<html>11:00 - 11:50<br>");
        jtbMonday4.setToolTipText("");
        jtbMonday4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday5.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday5.setText("<html>12:00 - 12:50<br>");
        jtbMonday5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMondayCommonHour.setBackground(new java.awt.Color(255, 255, 255));
        jMondayCommonHour.setText("<html>Common Hour <br> 1:00 - 2:30");
        jMondayCommonHour.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday6.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday6.setText("<html>2:30 - 3:20<br>");
        jtbMonday6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday7.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday7.setText("<html>3:30 - 4:20<br>");
        jtbMonday7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday8.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday8.setText("<html>4:30 - 5:45<br>");
        jtbMonday8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbMonday9.setBackground(new java.awt.Color(255, 255, 255));
        jtbMonday9.setText("<html>6:30 - 8:55<br>");
        jtbMonday9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday1.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday1.setText("<html>8:00 - 9:15<br>");
        jtbTuesday1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbTuesday1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbTuesday1ActionPerformed(evt);
            }
        });

        jtbTuesday2.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday2.setText("<html>9:25 - 10:40<br>");
        jtbTuesday2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday4.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday4.setText("<html>12:15 - 1:30<br>");
        jtbTuesday4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday3.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday3.setText("<html>10:50 - 12:05<br>");
        jtbTuesday3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday5.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday5.setText("<html>1:40 - 2:55<br>");
        jtbTuesday5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday6.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday6.setText("<html>3:05 - 4:20<br>");
        jtbTuesday6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday7.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday7.setText("<html>4:30 - 5:45<br>");
        jtbTuesday7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbTuesday8.setBackground(new java.awt.Color(255, 255, 255));
        jtbTuesday8.setText("<html>6:30 - 8:55<br>");
        jtbTuesday8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbTuesday8.setMaximumSize(new java.awt.Dimension(72, 51));

        jtbWednesday1.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday1.setText("<html>8:00 - 8:50<br>");
        jtbWednesday1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday2.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday2.setText("<html>9:00 - 9:50<br>");
        jtbWednesday2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday3.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday3.setText("<html>10:00 - 10:50<br>");
        jtbWednesday3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday4.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday4.setText("<html>11:00 - 11:50<br>");
        jtbWednesday4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday5.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday5.setText("<html>12:00 - 12:50<br>");
        jtbWednesday5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesdayCommonHour.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesdayCommonHour.setText("<html>Common Hour <br> 1:00 - 2:30");
        jtbWednesdayCommonHour.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday6.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday6.setText("<html>2:30 - 3:20<br>");
        jtbWednesday6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday7.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday7.setText("<html>3:30 - 4:20<br>");
        jtbWednesday7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday8.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday8.setText("<html>4:30 - 5:45<br>");
        jtbWednesday8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbWednesday9.setBackground(new java.awt.Color(255, 255, 255));
        jtbWednesday9.setText("<html>6:30 - 8:55<br>");
        jtbWednesday9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday1.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday1.setText("<html>8:00 - 9:15<br>");
        jtbThursday1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday2.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday2.setText("<html>9:25 - 10:40<br>");
        jtbThursday2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday3.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday3.setText("<html>10:50 - 12:05<br>");
        jtbThursday3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday4.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday4.setText("<html>12:15 - 1:30<br>");
        jtbThursday4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday5.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday5.setText("<html>1:40 - 2:55<br>");
        jtbThursday5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday6.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday6.setText("<html>3:05 - 4:20<br>");
        jtbThursday6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday7.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday7.setText("<html>4:30 - 5:45<br>");
        jtbThursday7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbThursday8.setBackground(new java.awt.Color(255, 255, 255));
        jtbThursday8.setText("<html>6:30 - 8:55<br>");
        jtbThursday8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday9.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday9.setText("<html>6:30 - 8:55<br>");
        jtbFriday9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday8.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday8.setText("<html>4:30 - 5:45<br>");
        jtbFriday8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday7.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday7.setText("<html>3:30 - 4:20<br>");
        jtbFriday7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday6.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday6.setText("<html>2:30 - 3:20<br>");
        jtbFriday6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFridayCommonHour.setBackground(new java.awt.Color(255, 255, 255));
        jtbFridayCommonHour.setText("<html>Common Hour <br> 1:00 - 2:30");
        jtbFridayCommonHour.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday5.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday5.setText("<html>12:00 - 12:50<br>");
        jtbFriday5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday4.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday4.setText("<html>11:00 - 11:50<br>");
        jtbFriday4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday3.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday3.setText("<html>10:00 - 10:50<br>");
        jtbFriday3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday2.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday2.setText("<html>9:00 - 9:50<br>");
        jtbFriday2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtbFriday1.setBackground(new java.awt.Color(255, 255, 255));
        jtbFriday1.setText("<html>8:00 - 8:50<br>");
        jtbFriday1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jbAdd.setText("Add To Schedule");
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });

        jliCourse.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jliCourse.setToolTipText("Hi");
        jScrollPane2.setViewportView(jliCourse);

        jbSave.setText("Save Schedule");
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });

        jbRemove.setText("Remove From Schedule");
        jbRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveActionPerformed(evt);
            }
        });

        jcbMajor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Security Risk Analysis", "Computer Science", "Mathematics", "Information Science & Technology" }));
        jcbMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMajorActionPerformed(evt);
            }
        });

        jcbSemester.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8" }));
        jcbSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSemesterActionPerformed(evt);
            }
        });

        jcbRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gaige" }));
        jcbRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRoomActionPerformed(evt);
            }
        });

        jlRoom.setText("32 seats ");

        jlExtraInfo.setText("Computer Lab, Whiteboard");

        jcbRoomNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "209", "204", "205" }));
        jcbRoomNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRoomNoActionPerformed(evt);
            }
        });

        jLabel5.setText("Section Capacity:");

        jLabel6.setText("Preferences:");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jlPreferences.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlPreferences.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlPreferences.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel7.setText("Credits:");

        jlCredits.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlCredits.setToolTipText("");
        jlCredits.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlCredits.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel8.setText("Instructor:");

        jlInstructor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlInstructor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlInstructor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jlCapacity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlCapacity.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jlCapacity.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jlPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jlCredits, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jlPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jlCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpSchedulerLayout = new javax.swing.GroupLayout(jpScheduler);
        jpScheduler.setLayout(jpSchedulerLayout);
        jpSchedulerLayout.setHorizontalGroup(
            jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSchedulerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addComponent(jcbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jbRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addComponent(jtbMonday7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbTuesday6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addComponent(jtbMonday6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbTuesday5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addComponent(jtbMonday3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbTuesday2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jMondayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtbMonday5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtbMonday4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbTuesday3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbTuesday4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbMonday9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbMonday8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbTuesday7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbTuesday8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtbMonday2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jtbMonday1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jcbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbTuesday1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbWednesday7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesday6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesday3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesday1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesday2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesdayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtbWednesday5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtbWednesday4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtbWednesday9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbWednesday8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbThursday6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbFriday7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFriday6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFriday3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFriday1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFriday2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFridayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtbFriday5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtbFriday4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtbFriday9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbFriday8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addComponent(jlRoom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlExtraInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jpSchedulerLayout.setVerticalGroup(
            jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSchedulerLayout.createSequentialGroup()
                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbRoom)
                    .addComponent(jlRoom)
                    .addComponent(jlExtraInfo)
                    .addComponent(jcbRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpSchedulerLayout.createSequentialGroup()
                                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                                        .addComponent(jtbMonday1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jMondayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbMonday7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                                        .addComponent(jtbTuesday1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbTuesday2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbTuesday3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbTuesday4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtbTuesday5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtbTuesday6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpSchedulerLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbAdd))
                                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbRemove)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbSave)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtbMonday8, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jtbTuesday7, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                            .addGroup(jpSchedulerLayout.createSequentialGroup()
                                .addComponent(jtbWednesday1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesdayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbWednesday8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbWednesday9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbMonday9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbTuesday8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpSchedulerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSchedulerLayout.createSequentialGroup()
                                .addComponent(jtbFriday1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFridayCommonHour, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbFriday8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSchedulerLayout.createSequentialGroup()
                                .addComponent(jtbThursday1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbThursday2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbThursday3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbThursday4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbThursday5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jtbThursday6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtbThursday7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpSchedulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbFriday9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbThursday8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpScheduler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpScheduler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveActionPerformed
        Section sToRemove = new Section();
        String room = jcbRoom.getSelectedItem().toString() + " " + jcbRoomNo.getSelectedItem().toString();
        int roomNo = 0;
        
        for(int x = 0;x < allRooms.length;x++)
        {
            if(allRooms[x][0][0].getCourseRoom().toString().equals(room))
                roomNo = x;
        }
        
        
        int k;
        for(int i = 0;i < jtbSchedule.length;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j = 0;j < k;j++)
            {
                if(jtbSchedule[i][j].isSelected())
                {
                    sToRemove = allRooms[roomNo][i][j].getSection();
                    roomSchedule[i][j].setSection(new Section());
                    
                    repaintButtons();
                }
            }
        }
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed
        Section sSelected = (Section)jliCourse.getSelectedValue();
        String room = jcbRoom.getSelectedItem().toString() + " " + jcbRoomNo.getSelectedItem().toString();
        int roomNo = 0;
        
        for(int x = 0;x < allRooms.length;x++)
        {
            if(allRooms[x][0][0].getCourseRoom().toString().equals(room))
                roomNo = x;
        }
        
        
        int k;
        for(int i = 0;i < jtbSchedule.length;i++)
        {
            if(i == 1 || i == 3)
                k = 8;
            else
                k = 9;
            for(int j = 0;j < k;j++)
            {
                if(jtbSchedule[i][j].isSelected())
                {
                    //allRooms[roomNo][i][j].setSection(sSelected);
                    roomSchedule[i][j].setSection(sSelected);
                    
                    repaintButtons();
                }
            }
        }
    }//GEN-LAST:event_jbAddActionPerformed

    private void jcbRoomNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRoomNoActionPerformed
        fillExtraLabels();
        repaintButtons();
    }//GEN-LAST:event_jcbRoomNoActionPerformed

    private void jcbMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMajorActionPerformed
        setColors();
        rapSheetCheck();
    }//GEN-LAST:event_jcbMajorActionPerformed

    private void jcbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSemesterActionPerformed
        setColors();
        rapSheetCheck();
    }//GEN-LAST:event_jcbSemesterActionPerformed

    private void jtbTuesday1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbTuesday1ActionPerformed
    }//GEN-LAST:event_jtbTuesday1ActionPerformed

    private void jtbMonday1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbMonday1ActionPerformed
    }//GEN-LAST:event_jtbMonday1ActionPerformed

    private void jcbRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRoomActionPerformed
        fillComboBoxes();
    }//GEN-LAST:event_jcbRoomActionPerformed

    private void jpSchedulerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpSchedulerFocusGained
        
    }//GEN-LAST:event_jpSchedulerFocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

    }//GEN-LAST:event_formFocusGained

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        cs.saveSchedule(allRooms);
    }//GEN-LAST:event_jbSaveActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToggleButton jMondayCommonHour;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbSave;
    private javax.swing.JComboBox jcbMajor;
    private javax.swing.JComboBox jcbRoom;
    private javax.swing.JComboBox jcbRoomNo;
    private javax.swing.JComboBox jcbSemester;
    private javax.swing.JLabel jlCapacity;
    private javax.swing.JLabel jlCredits;
    private javax.swing.JLabel jlExtraInfo;
    private javax.swing.JLabel jlInstructor;
    private javax.swing.JLabel jlPreferences;
    private javax.swing.JLabel jlRoom;
    private javax.swing.JList jliCourse;
    private javax.swing.JPanel jpScheduler;
    private javax.swing.JToggleButton jtbFriday1;
    private javax.swing.JToggleButton jtbFriday2;
    private javax.swing.JToggleButton jtbFriday3;
    private javax.swing.JToggleButton jtbFriday4;
    private javax.swing.JToggleButton jtbFriday5;
    private javax.swing.JToggleButton jtbFriday6;
    private javax.swing.JToggleButton jtbFriday7;
    private javax.swing.JToggleButton jtbFriday8;
    private javax.swing.JToggleButton jtbFriday9;
    private javax.swing.JToggleButton jtbFridayCommonHour;
    private javax.swing.JToggleButton jtbMonday1;
    private javax.swing.JToggleButton jtbMonday2;
    private javax.swing.JToggleButton jtbMonday3;
    private javax.swing.JToggleButton jtbMonday4;
    private javax.swing.JToggleButton jtbMonday5;
    private javax.swing.JToggleButton jtbMonday6;
    private javax.swing.JToggleButton jtbMonday7;
    private javax.swing.JToggleButton jtbMonday8;
    private javax.swing.JToggleButton jtbMonday9;
    private javax.swing.JToggleButton jtbThursday1;
    private javax.swing.JToggleButton jtbThursday2;
    private javax.swing.JToggleButton jtbThursday3;
    private javax.swing.JToggleButton jtbThursday4;
    private javax.swing.JToggleButton jtbThursday5;
    private javax.swing.JToggleButton jtbThursday6;
    private javax.swing.JToggleButton jtbThursday7;
    private javax.swing.JToggleButton jtbThursday8;
    private javax.swing.JToggleButton jtbTuesday1;
    private javax.swing.JToggleButton jtbTuesday2;
    private javax.swing.JToggleButton jtbTuesday3;
    private javax.swing.JToggleButton jtbTuesday4;
    private javax.swing.JToggleButton jtbTuesday5;
    private javax.swing.JToggleButton jtbTuesday6;
    private javax.swing.JToggleButton jtbTuesday7;
    private javax.swing.JToggleButton jtbTuesday8;
    private javax.swing.JToggleButton jtbWednesday1;
    private javax.swing.JToggleButton jtbWednesday2;
    private javax.swing.JToggleButton jtbWednesday3;
    private javax.swing.JToggleButton jtbWednesday4;
    private javax.swing.JToggleButton jtbWednesday5;
    private javax.swing.JToggleButton jtbWednesday6;
    private javax.swing.JToggleButton jtbWednesday7;
    private javax.swing.JToggleButton jtbWednesday8;
    private javax.swing.JToggleButton jtbWednesday9;
    private javax.swing.JToggleButton jtbWednesdayCommonHour;
    // End of variables declaration//GEN-END:variables

}
