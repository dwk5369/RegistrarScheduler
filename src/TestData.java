
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Donald
 */
public class TestData extends javax.swing.JFrame {

    DBConnect dbcConnector;
    DefaultTableModel dtmData;
    ClassScheduler cs;
    
    /**
     * Creates new form TestData
     */
    public TestData() {
        cs = new ClassScheduler(true);
        dbcConnector = new DBConnect();
        initComponents();
        getTableNames();
    }
/**
     * Grabs all the table names in th database and adds them to a combo box
     * A pet method of mine to look at the database structure without it being open
     */  
    private void getTableNames()
    {
        try 
        {
            ResultSet rsMeta = dbcConnector.connect().getMetaData().getTables(null, null, "%", null);
            while(rsMeta.next())
            {
                String strNextItem = rsMeta.getString(3);
                //if(!strNextItem.startsWith("sqlite"))
                    jcbTables.addItem(strNextItem);
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database: " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
        }
        dbcConnector.disconnect();
    }//getTableNames
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbTables = new javax.swing.JComboBox();
        jcbFields = new javax.swing.JComboBox();
        jbGetData = new javax.swing.JButton();
        jpbOne = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtData = new javax.swing.JTable();
        jbInsertTime = new javax.swing.JButton();
        jbTestRoomsTimeslots = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jcbTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTablesActionPerformed(evt);
            }
        });

        jbGetData.setText("Get Data From Table");
        jbGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGetDataActionPerformed(evt);
            }
        });

        jtData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtData);

        jbInsertTime.setText("Insert Timeslots");
        jbInsertTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertTimeActionPerformed(evt);
            }
        });

        jbTestRoomsTimeslots.setText("Get Rooms and Timeslots");
        jbTestRoomsTimeslots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTestRoomsTimeslotsActionPerformed(evt);
            }
        });

        jButton1.setText("Test Time");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(381, 381, 381)
                        .addComponent(jpbOne, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                        .addGap(391, 391, 391))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbGetData)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbInsertTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jcbTables, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbFields, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbTestRoomsTimeslots, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGetData)
                    .addComponent(jbInsertTime)
                    .addComponent(jbTestRoomsTimeslots))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(jpbOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTablesActionPerformed
        try 
        {   
            if(jcbTables.getComponents() != null)
            {
                jcbFields.removeAllItems();
                ResultSet rsMeta = dbcConnector.connect().getMetaData().getColumns(null, null, jcbTables.getSelectedItem().toString(), null);
                while(rsMeta.next())
                {
                    String strNextItem = rsMeta.getString(4);
                    jcbFields.addItem(strNextItem);
                }
            }
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database: " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
        }
        
        dbcConnector.disconnect();
    }//GEN-LAST:event_jcbTablesActionPerformed

    private void jbGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGetDataActionPerformed
        try {
            dbcConnector.connect();
            String table = jcbTables.getSelectedItem().toString();
            ResultSet rs = dbcConnector.getData(table);
            
            ResultSetMetaData metaData = rs.getMetaData();
            
            // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            
            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            dtmData = new DefaultTableModel(data, columnNames);
            jtData.setModel(dtmData);
            dbcConnector.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbGetDataActionPerformed

    private void jbInsertTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertTimeActionPerformed
        dbcConnector.connect();
        int evening;
        try {
            Statement st = dbcConnector.getConnDB().createStatement();
            st.execute("delete from timeslot");
        } catch (SQLException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Timeslot t : DataGet.timeslotData)
        {
            if(t.getStartTime().equals("6:30"))
                evening = 1;
            else
                evening = 0;
            dbcConnector.insertTimeslot(t.getDayOfWeek(), t.getStartTime(), t.getEndTime(), evening);
        }
        dbcConnector.disconnect();
    }//GEN-LAST:event_jbInsertTimeActionPerformed

    private void jbTestRoomsTimeslotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTestRoomsTimeslotsActionPerformed
        dbcConnector.connect();
        Timeslot[] time = dbcConnector.getTimeslots();
        Room[] room = dbcConnector.getRooms();
        Faculty[] fac = dbcConnector.getFaculty();
        for(Timeslot t : time)
            System.out.println(t);
        for(Room r : room)
            System.out.println(r);
        for(Faculty f: fac)
            System.out.println(f);
        try {
            dbcConnector.getConnDB().prepareStatement("delete from classschedule").execute();
        } catch (SQLException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        Schedule[] sched = dbcConnector.createSchedule(room, time);
        for(Schedule s: sched)
            System.out.println(s);
        Section[] sec = dbcConnector.getSections();
        for(Section section: sec)
            System.out.println(section.toString());
        System.out.println(sec[1].getCourse().getMajor().getName() + " Hello World");
        dbcConnector.disconnect();
    }//GEN-LAST:event_jbTestRoomsTimeslotsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println(cs.compareTimeslots("9:50P", "10:50A"));
        System.out.println(cs.compareTimeslots("9:50A", "10:50A"));
        System.out.println(cs.compareTimeslots("11:50A", "3:50P"));
        System.out.println(cs.compareTimeslots("8:30A", "8:50P"));
        System.out.println(cs.compareTimeslots("2:50P", "10:50P"));
        System.out.println(cs.compareTimeslots("9:50A", "8:50A"));
        System.out.println(cs.compareTimeslots("9:50A", "9:50A"));
        System.out.println(cs.compareTimeslots("11:50A", "8:50P"));
        System.out.println(cs.compareTimeslots("11:50A", "12:50P"));
        System.out.println(cs.compareTimeslots("11:50A", "12:50A"));
        System.out.println(cs.compareTimeslots("12:50P", "1:50P"));
        if("TWH".contains("H"))
        {
            System.out.println("yess");
        }
        if("TWH".contains("M"))
        {
            System.out.println("got it");
        }
        if("MTWF".contains("MWF"))
        {
            System.out.println("score");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbGetData;
    private javax.swing.JButton jbInsertTime;
    private javax.swing.JButton jbTestRoomsTimeslots;
    private javax.swing.JComboBox jcbFields;
    private javax.swing.JComboBox jcbTables;
    private javax.swing.JProgressBar jpbOne;
    private javax.swing.JTable jtData;
    // End of variables declaration//GEN-END:variables
}
