/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalinfosystem;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Karee
 */
public class MainMenu extends javax.swing.JFrame {

    int type; 
    
    /**
     * Creates new form MainMenu
     */
    
    public MainMenu(int uType){
        type = uType; 
        initComponents();
        this.setLocationRelativeTo(null);
        
        // set the users menu visible or not depending on the type 
        if(type == 0){
            this.usersMenu.setVisible(false);
            this.patientMenu.setVisible(false);
            this.docMenu.setVisible(false);
            this.deptMenu.setVisible(false);
            this.nurseMenu.setVisible(false);
        }
    }
    
    public MainMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        viewDepartmentMenuItem = new javax.swing.JMenuItem();
        viewDoctorMenuItem = new javax.swing.JMenuItem();
        viewPatientMenuItem = new javax.swing.JMenuItem();
        viewNurseMenuItem = new javax.swing.JMenuItem();
        patientAndNursesMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        deptMenu = new javax.swing.JMenu();
        AddDeptMenuItem = new javax.swing.JMenuItem();
        UpdateDeleteDeptMenuItem = new javax.swing.JMenuItem();
        docMenu = new javax.swing.JMenu();
        AddDoctorMenuItem = new javax.swing.JMenuItem();
        UpdateDeleteDoctorMenuItem = new javax.swing.JMenuItem();
        patientMenu = new javax.swing.JMenu();
        AddPatientMenuItem = new javax.swing.JMenuItem();
        UpdateDeletePatientMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        nurseMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        usersMenu = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HospitalDrawingResized2.png"))); // NOI18N
        jLabel2.setToolTipText("");
        jLabel2.setMaximumSize(new java.awt.Dimension(900, 500));

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        jMenuItem8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem8.setText("Log out");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        viewMenu.setText("View");
        viewMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        viewDepartmentMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        viewDepartmentMenuItem.setText("Department");
        viewDepartmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDepartmentMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewDepartmentMenuItem);

        viewDoctorMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        viewDoctorMenuItem.setText("Doctor");
        viewDoctorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDoctorMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewDoctorMenuItem);

        viewPatientMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        viewPatientMenuItem.setText("Patient");
        viewPatientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPatientMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewPatientMenuItem);

        viewNurseMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        viewNurseMenuItem.setText("Nurse");
        viewNurseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewNurseMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewNurseMenuItem);

        patientAndNursesMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        patientAndNursesMenuItem.setText("Patient and Nurses");
        patientAndNursesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientAndNursesMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(patientAndNursesMenuItem);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jMenuItem4.setText("Number of patients per department");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        viewMenu.add(jMenuItem4);

        jMenuBar1.add(viewMenu);

        deptMenu.setText("Department");
        deptMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        AddDeptMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        AddDeptMenuItem.setText("Add Department");
        AddDeptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDeptMenuItemActionPerformed(evt);
            }
        });
        deptMenu.add(AddDeptMenuItem);

        UpdateDeleteDeptMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        UpdateDeleteDeptMenuItem.setText("Update/Delete Department");
        UpdateDeleteDeptMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDeleteDeptMenuItemActionPerformed(evt);
            }
        });
        deptMenu.add(UpdateDeleteDeptMenuItem);

        jMenuBar1.add(deptMenu);

        docMenu.setText("Doctor");
        docMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        AddDoctorMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        AddDoctorMenuItem.setText("Add Doctor");
        AddDoctorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDoctorMenuItemActionPerformed(evt);
            }
        });
        docMenu.add(AddDoctorMenuItem);

        UpdateDeleteDoctorMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        UpdateDeleteDoctorMenuItem.setText("Update/Delete Doctor");
        UpdateDeleteDoctorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDeleteDoctorMenuItemActionPerformed(evt);
            }
        });
        docMenu.add(UpdateDeleteDoctorMenuItem);

        jMenuBar1.add(docMenu);

        patientMenu.setText("Patient");
        patientMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        AddPatientMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        AddPatientMenuItem.setText("Add Patient");
        AddPatientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPatientMenuItemActionPerformed(evt);
            }
        });
        patientMenu.add(AddPatientMenuItem);

        UpdateDeletePatientMenuItem.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        UpdateDeletePatientMenuItem.setText("Update/Delete Patient");
        UpdateDeletePatientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDeletePatientMenuItemActionPerformed(evt);
            }
        });
        patientMenu.add(UpdateDeletePatientMenuItem);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem3.setText("Assign Nurses");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        patientMenu.add(jMenuItem3);

        jMenuItem5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem5.setText("Remove Nurses");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        patientMenu.add(jMenuItem5);

        jMenuBar1.add(patientMenu);

        nurseMenu.setText("Nurse");
        nurseMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        nurseMenu.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                nurseMenuComponentAdded(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem1.setText("Add Nurse");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        nurseMenu.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem2.setText("Update/Delete Nurse");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        nurseMenu.add(jMenuItem2);

        jMenuBar1.add(nurseMenu);

        usersMenu.setText("Users");
        usersMenu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem6.setText("Add users");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        usersMenu.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jMenuItem7.setText("Update/Delete Users");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        usersMenu.add(jMenuItem7);

        jMenuBar1.add(usersMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddDeptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDeptMenuItemActionPerformed
        // TODO add your handling code here:
        new AddDepartment().setVisible(true);
    }//GEN-LAST:event_AddDeptMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new AddNurse().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void nurseMenuComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_nurseMenuComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_nurseMenuComponentAdded

    private void UpdateDeleteDeptMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDeleteDeptMenuItemActionPerformed
        new UpdateDeleteDept().setVisible(true);
    }//GEN-LAST:event_UpdateDeleteDeptMenuItemActionPerformed

    private void AddDoctorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDoctorMenuItemActionPerformed
        new AddDoctor().setVisible(true);
    }//GEN-LAST:event_AddDoctorMenuItemActionPerformed

    private void AddPatientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPatientMenuItemActionPerformed
        new AddPatient().setVisible(true);
    }//GEN-LAST:event_AddPatientMenuItemActionPerformed

    private void UpdateDeletePatientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDeletePatientMenuItemActionPerformed
        new UpdateDeletePatient().setVisible(true);
    }//GEN-LAST:event_UpdateDeletePatientMenuItemActionPerformed

    private void UpdateDeleteDoctorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDeleteDoctorMenuItemActionPerformed
        new UpdateDeleteDoctor().setVisible(true);
    }//GEN-LAST:event_UpdateDeleteDoctorMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
          new UpdateDeleteNurse().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // display a joptionpane to let the user choose the patient
        JPanel panel = new JPanel(new GridLayout(1, 1)); 
        JLabel label = new JLabel("Choose patient: ");
        JComboBox cmbBox = new JComboBox(); 
        
        // fill the combobox with data 
        // querey the patient
        dbCon con = new dbCon(); 
        try{
            ResultSet rs = con.executeStatement("SELECT * FROM PATIENT");
            while(rs.next()){
                cmbBox.addItem(rs.getString("ID"));
            }
        }catch(Exception e){}
        
        panel.add(label); 
        panel.add(cmbBox);
        
        int res = JOptionPane.showConfirmDialog(null, panel, "Patients", JOptionPane.OK_CANCEL_OPTION);
        if(res == JOptionPane.OK_OPTION){
            // add the form to assign nurses
            new AssignNurses(cmbBox.getSelectedItem().toString()).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void viewDepartmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDepartmentMenuItemActionPerformed
        // call view department
        new viewDepartment().setVisible(true);
    }//GEN-LAST:event_viewDepartmentMenuItemActionPerformed

    private void viewDoctorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDoctorMenuItemActionPerformed
        new viewDoctor().setVisible(true);
    }//GEN-LAST:event_viewDoctorMenuItemActionPerformed

    private void viewPatientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPatientMenuItemActionPerformed
        new viewPatient().setVisible(true);
    }//GEN-LAST:event_viewPatientMenuItemActionPerformed

    private void viewNurseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewNurseMenuItemActionPerformed
        new viewNurse().setVisible(true);
    }//GEN-LAST:event_viewNurseMenuItemActionPerformed

    private void patientAndNursesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientAndNursesMenuItemActionPerformed
        new viewPatientNurses().setVisible(true);
    }//GEN-LAST:event_patientAndNursesMenuItemActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new ViewPatientsPerDept().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JPanel panel = new JPanel(new GridLayout(1, 1)); 
        JLabel label = new JLabel("Choose patient: ");
        JComboBox cmbBox = new JComboBox(); 
        
        // fill the combobox with data 
        // querey the patient
        dbCon con = new dbCon(); 
        try{
            ResultSet rs = con.executeStatement("SELECT DISTINCT PATIENT_ID FROM NURSING");
            while(rs.next()){
                cmbBox.addItem(rs.getString(1));
            }
        }catch(Exception e){}
        
        panel.add(label); 
        panel.add(cmbBox);
        
        int res = JOptionPane.showConfirmDialog(null, panel, "Patients", JOptionPane.OK_CANCEL_OPTION);
        if(res == JOptionPane.OK_OPTION){
            // add the form to assign nurses
            new removeNurses(cmbBox.getSelectedItem().toString()).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new AddUser().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new UpdateDeleteUser().setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        this.dispose();
        new LoginFormHospital().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        // display login form first
        new LoginFormHospital().setVisible(true);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AddDeptMenuItem;
    private javax.swing.JMenuItem AddDoctorMenuItem;
    private javax.swing.JMenuItem AddPatientMenuItem;
    private javax.swing.JMenuItem UpdateDeleteDeptMenuItem;
    private javax.swing.JMenuItem UpdateDeleteDoctorMenuItem;
    private javax.swing.JMenuItem UpdateDeletePatientMenuItem;
    private javax.swing.JMenu deptMenu;
    private javax.swing.JMenu docMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu nurseMenu;
    private javax.swing.JMenuItem patientAndNursesMenuItem;
    private javax.swing.JMenu patientMenu;
    private javax.swing.JMenu usersMenu;
    private javax.swing.JMenuItem viewDepartmentMenuItem;
    private javax.swing.JMenuItem viewDoctorMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JMenuItem viewNurseMenuItem;
    private javax.swing.JMenuItem viewPatientMenuItem;
    // End of variables declaration//GEN-END:variables
}
