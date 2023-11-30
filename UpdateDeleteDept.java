/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalinfosystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Karee
 */
public class UpdateDeleteDept extends javax.swing.JFrame {

    /**
     * Creates new form AddDepartment
     */
    dbCon con; 
    ResultSet hdeptRs; 
    private void clearErrorLabels(){
        this.ErrorDeptName.setVisible(false);
        this.ErrorDeptNum.setVisible(false);
    }
    
    private void enableDisableButtons(){
        try{
            if(hdeptRs.isFirst()){
                this.btnPrev.setEnabled(false);
            }
            else{
                this.btnPrev.setEnabled(true);
            }
            
            if(hdeptRs.isLast()){
                this.btnNext.setEnabled(false);
            }
            else{
                this.btnNext.setEnabled(true);
            }
        }
        catch(SQLException e){}
    }
    
    private void moveNext(){
        try{
            hdeptRs.next();
            this.populateFields();
            this.enableDisableButtons();
        }catch(SQLException e){}
    }
    
    private void movePrev(){
        try{
            hdeptRs.previous(); 
            this.populateFields();
            this.enableDisableButtons();
        }catch(SQLException e){}
    }
    
    private void populateComboBox(){
        try{
            ResultSet docRs = con.executeStatement("SELECT ID FROM DOCTOR");
            while(docRs.next()){
                this.cmbDeptHead.addItem(docRs.getString(1));
            }
        } 
        catch(SQLException e){System.out.println(e.getMessage());}
        
    }
    
    private boolean validateDeptName(){
        if(this.txtDeptName.getText().isEmpty()){
            this.ErrorDeptName.setText("Should not be empty");
            this.ErrorDeptName.setVisible(true);
            return false; 
        }
        
        if(this.txtDeptName.getText().toString().length() > 15){
            this.ErrorDeptName.setText("must be less than 15 characters");
            this.ErrorDeptName.setVisible(true);
            return false; 
        }
        
        // check if the department name exists
        try{
            ResultSet deptRs = con.executeStatement("SELECT * FROM HDEPT"); 
            while(deptRs.next()){
                if(!deptRs.getString(2).equals(this.txtDeptNum.getText()) && deptRs.getString("DNAME").toLowerCase().equals(this.txtDeptName.getText().toString().toLowerCase())){
                    this.ErrorDeptName.setText("Department name exists");
                    this.ErrorDeptName.setVisible(true);
                    return false; 
                }
            }
        }catch(Exception e){}
        
        return true; 
    }
    
    private void populateFields(){
        try{
            this.txtDeptName.setText(hdeptRs.getString(1));
            this.txtDeptNum.setText(hdeptRs.getInt(2) +"");
            this.cmbDeptHead.setSelectedItem(hdeptRs.getString(3));
        }catch(Exception e){}
    }
    
    // function to check if the dept has doctors
    private boolean hasDoctors(){
        // check if it has doctors 
        int deptNum = Integer.parseInt(this.txtDeptNum.getText().toString()); 
        try{
            ResultSet docRs = con.executeStatement("Select * from doctor");
            while(docRs.next()){
                if(docRs.getInt(2) == deptNum){
                    JOptionPane.showMessageDialog(null, "Error: cannot delete department that has doctors");
                    return true; 
                }
            }
        }catch(Exception e){}
        return false; 
    }
    
    // function to check if the department has patients
    private boolean hasPatients(){
        try{
            int deptNum = Integer.parseInt(this.txtDeptNum.getText().toString()); 

            ResultSet patientRs = con.executeStatement("SELECT P.NAME, D.DEP_NUM" +
            " FROM PATIENT P JOIN DOCTOR D ON P.DOCID = D.ID;");
            
            while(patientRs.next()){
                if(patientRs.getInt(2) == deptNum){
                    JOptionPane.showMessageDialog(null, "Error: cannot delete department that has patients");
                    return true; 
                }
            }
        }catch(Exception e){}
        return false; 
    }
    
    private boolean hasNurses(){
        int deptNum = Integer.parseInt(this.txtDeptNum.getText().toString()); 
        try{
            ResultSet rsNurse = con.executeStatement("Select * from nurse");
            while(rsNurse.next()){
                if(rsNurse.getInt(2) == deptNum){
                    JOptionPane.showMessageDialog(null, "Error: cannot delete department that has Nurses");
                    return true; 
                }
            }
        }catch(Exception e){}
        return false; 
    }
    
    public UpdateDeleteDept() {
        initComponents();
        con = new dbCon(); 
        this.clearErrorLabels();
        this.populateComboBox();
        try{
            hdeptRs = con.executeStatement("SELECT * FROM HDEPT");
            hdeptRs.next(); 
        }catch(Exception e){}
        this.setLocationRelativeTo(null);
        this.populateFields();
        this.txtDeptNum.setEnabled(false);
        this.enableDisableButtons();
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDeptNum = new javax.swing.JTextField();
        txtDeptName = new javax.swing.JTextField();
        cmbDeptHead = new javax.swing.JComboBox<>();
        ErrorDeptNum = new javax.swing.JLabel();
        ErrorDeptName = new javax.swing.JLabel();
        BtnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Department Number: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Update/Delete Department");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Department Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 1, 20)); // NOI18N
        jLabel4.setText("Department head:");

        txtDeptNum.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        txtDeptNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeptNumActionPerformed(evt);
            }
        });

        txtDeptName.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        ErrorDeptNum.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        ErrorDeptNum.setForeground(new java.awt.Color(255, 51, 51));
        ErrorDeptNum.setText("Error Label");

        ErrorDeptName.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        ErrorDeptName.setForeground(new java.awt.Color(255, 51, 51));
        ErrorDeptName.setText("Error Label");

        BtnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BtnUpdate.setText("Update");
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrev.setText("<<<Prev");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNext.setText("Next>>>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDeptNum)
                    .addComponent(txtDeptName)
                    .addComponent(cmbDeptHead, 0, 155, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ErrorDeptNum, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(ErrorDeptName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeptNum, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ErrorDeptNum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ErrorDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDeptHead, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDeptNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeptNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeptNumActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.moveNext();
        this.clearErrorLabels();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.movePrev();
        this.clearErrorLabels();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String deptNum = this.txtDeptNum.getText().toString(); 
        String deptName = this.txtDeptName.getText().toString(); 
        Object HID = this.cmbDeptHead.getSelectedItem();
        
        // check if the dept has doctors or patients (it should be empty to be deleted)
        boolean update = true;
        if(this.hasDoctors())
            update = false; 
        if(this.hasPatients())
            update = false; 
        if(this.hasNurses())
            update = false; 
        if(update){
            try{
                int result = con.executePrepared("DELETE FROM HDEPT WHERE DNAME = '" + deptName + "' AND DNUM = " + deptNum + " AND H_ID = '" + HID + "'");

                if(result > 0){
                    JOptionPane.showMessageDialog(null, "Department deleted successfully");
                    // get the new data and populate fields 
                    hdeptRs = con.executeStatement("SELECT * FROM HDEPT");
                    hdeptRs.first(); 
                    this.populateFields();
                    this.enableDisableButtons();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error: No such department exists");
                }
             }catch(Exception e){JOptionPane.showMessageDialog(null, "SQL Error at deleting");}
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        boolean update = true; 
        if(!this.validateDeptName())
            update = false; 
        if(update){
            try{
               int result = con.executePrepared("Update hdept set DNAME = '" + this.txtDeptName.getText().toString() + "', H_ID = '" + this.cmbDeptHead.getSelectedItem()+ "' "
                       + "where dnum = " + this.txtDeptNum.getText().toString() );
               if(result > 0){
                   JOptionPane.showMessageDialog(null, "Department updated successfully");
                   hdeptRs = con.executeStatement("SELECT * FROM HDEPT");
                   hdeptRs.first(); 
                   this.populateFields();
                   this.enableDisableButtons();
               }
               else{
                   JOptionPane.showMessageDialog(null, "Department not updated :(");
               }
            }catch(Exception e){JOptionPane.showMessageDialog(null, "SQL Error at update");}
        }
    }//GEN-LAST:event_BtnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(AddDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDepartment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDepartment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JLabel ErrorDeptName;
    private javax.swing.JLabel ErrorDeptNum;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox<String> cmbDeptHead;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDeptName;
    private javax.swing.JTextField txtDeptNum;
    // End of variables declaration//GEN-END:variables
}
