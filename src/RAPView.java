

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dwk5369
 */
public class RAPView extends javax.swing.JPanel {

    DefaultTableModel inputTable;
    DefaultListModel dlmCourse;
    ClassScheduler cs;
    String[] columnHeaders = {"Course","Section #","Instructor","Time","Days","Room","Credits"};
    DefaultComboBoxModel dcbmMajor;
    
    /**
     * Creates new form RAPView
     */
    public RAPView() {
        initComponents();
        inputTable = new DefaultTableModel(columnHeaders,0);
        dlmCourse = new DefaultListModel();
        jList1.setModel(dlmCourse);
        jtSchedule.setModel(inputTable);
        cs = new ClassScheduler();
        dcbmMajor = new DefaultComboBoxModel();
        Major[] maj = cs.getMajors();
        for(Major m: maj)
            dcbmMajor.addElement(m);
        jcbMajor.setModel(dcbmMajor);
    }

    public void fillTable()
    {
        String strMajor = jcbMajor.getSelectedItem().toString();
        int num = jcbSemester.getSelectedItem().toString().length();
        int semester = Integer.parseInt(jcbSemester.getSelectedItem().toString().substring(num-1, num));
        
        inputTable.getDataVector().removeAllElements();
        dlmCourse.removeAllElements();
        Course[] course = cs.getRAPCourses(strMajor, semester);
        Schedule[] sched = cs.getSchedule();
        Section[] sec = cs.getUnscheduled();
        for(Course c: course)
        {
            for(Schedule s: sched)
            {
                if(c != null && s.getSection().getCourse() != null)
                {
                    if(c.equals(s.getSection().getCourse()))
                    {
                        String[] row = {s.getSection().getCourse().toString(),s.getSection().getNumber(),s.getSection().getFaculty().toString(),
                            s.getTimeslot().toString(),s.getTimeslot().getDayOfWeek(),s.getCourseRoom().toString(),s.getSection().getCourse().getCredits()+""};
                        inputTable.addRow(row);
                    }
                }
            }
            for(Section s: sec)
            {
                if(c != null && s.getCourse() != null)
                {
                    if(c.equals(s.getCourse()))
                    {
                        dlmCourse.addElement(s);
                    }
                }                
            }
        }
        jList1.setModel(dlmCourse);
        jtSchedule.setModel(inputTable);
        repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpScheduleRAP = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtSchedule = new javax.swing.JTable();
        jcbMajor = new javax.swing.JComboBox();
        jcbSemester = new javax.swing.JComboBox();

        jScrollPane2.setViewportView(jList1);

        jtSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtSchedule.setColumnSelectionAllowed(true);
        jtSchedule.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtSchedule);
        jtSchedule.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        javax.swing.GroupLayout jpScheduleRAPLayout = new javax.swing.GroupLayout(jpScheduleRAP);
        jpScheduleRAP.setLayout(jpScheduleRAPLayout);
        jpScheduleRAPLayout.setHorizontalGroup(
            jpScheduleRAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScheduleRAPLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpScheduleRAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpScheduleRAPLayout.createSequentialGroup()
                        .addComponent(jcbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(548, Short.MAX_VALUE))
                    .addGroup(jpScheduleRAPLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))))
        );
        jpScheduleRAPLayout.setVerticalGroup(
            jpScheduleRAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScheduleRAPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpScheduleRAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpScheduleRAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpScheduleRAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpScheduleRAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setCS(ClassScheduler cs)
    {
        this.cs = cs;
    }
    
    public ClassScheduler getCS()
    {
        return cs;
    }    
    
    private void jcbMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMajorActionPerformed
        fillTable();
    }//GEN-LAST:event_jcbMajorActionPerformed

    private void jcbSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSemesterActionPerformed
        fillTable();
    }//GEN-LAST:event_jcbSemesterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcbMajor;
    private javax.swing.JComboBox jcbSemester;
    private javax.swing.JPanel jpScheduleRAP;
    private javax.swing.JTable jtSchedule;
    // End of variables declaration//GEN-END:variables
}