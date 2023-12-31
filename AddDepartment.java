/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalinfosystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Karee
 */
public class AddDepartment extends javax.swing.JFrame {

    /**
     * Creates new form AddDepartment
     */
    
    dbCon con; 
    
    private void clearErrorLabels(){
        this.ErrorDeptName.setVisible(false);
        this.ErrorDeptNum.setVisible(false);
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
                if(deptRs.getString("DNAME").toLowerCase().equals(this.txtDeptName.getText().toString().toLowerCase())){
                    this.ErrorDeptName.setText("Department name exists");
                    this.ErrorDeptName.setVisible(true);
                    return false; 
                }
            }
        }catch(Exception e){}
        
        return true; 
    }
    
    private boolean validateDeptNum(){
        // check if it has a letter 
        String deptNum = this.txtDeptNum.getText().toString(); 
        if(deptNum.length() == 0){
            this.ErrorDeptNum.setText("Should not be empty");
            this.ErrorDeptNum.setVisible(true);
            return false; 
        }
        for(int i = 0; i < deptNum.length(); i++){
            if(Character.isLetter(deptNum.charAt(i))){
                this.ErrorDeptNum.setText("Should be an integer value");
                this.ErrorDeptNum.setVisible(true);
                return false; 
            }
        }
        
        // check if the department number exists
        try{
            ResultSet deptRs = con.executeStatement("SELECT * FROM HDEPT"); 
            while(deptRs.next()){
                if(deptRs.getInt("DNUM") == Integer.parseInt(deptNum)){
                    this.ErrorDeptNum.setText("Department number exists");
                    this.ErrorDeptNum.setVisible(true);
                    return false; 
                }
            }
        }catch(Exception e){}
        
        return true; 
    }
    
    public AddDepartment() {
        con = new dbCon();
        initComponents();
        this.setLocationRelativeTo(null);
        this.clearErrorLabels();
        this.populateComboBox();  
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
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Department Number: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add Department");

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

        btnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDeptNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeptNumActionPerformed
       
    }//GEN-LAST:event_txtDeptNumActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        boolean add = true; 
        if(!this.validateDeptName())
            add = false; 
        if(!this.validateDeptNum())
            add = false; 
        
        if(add){
            // insert the data
            this.clearErrorLabels();
            
            String deptNum = this.txtDeptNum.getText().toString();
            String deptName = this.txtDeptName.getText().toString();
            Object headDept =  this.cmbDeptHead.getSelectedItem();
            try{
                int result = con.executePrepared("INSERT INTO HDEPT VALUES ('" + deptName +"', " + deptNum + ", '" + headDept + "')");
                if(result > 0){
                    JOptionPane.showMessageDialog(null,"Department inserted successfully");
                    this.txtDeptName.setText("");
                    this.txtDeptNum.setText("");
                }
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, "exception at inserting");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please fix validation errors");
        }
    }//GEN-LAST:event_btnAddActionPerformed

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
    private javax.swing.JLabel ErrorDeptName;
    private javax.swing.JLabel ErrorDeptNum;
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cmbDeptHead;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDeptName;
    private javax.swing.JTextField txtDeptNum;
    // End of variables declaration//GEN-END:variables
}
