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
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Karee
 */
public class AddNurse extends javax.swing.JFrame {

    /**
     * Creates new form AddDoctor
     */
    
    dbCon con; 
    private void clearErrorLabels(){
        this.errorEmail.setVisible(false);
        this.errorId.setVisible(false);
        this.errorName.setVisible(false);
        this.errorPhoneNumber.setVisible(false);
        this.errorSalary.setVisible(false);
    }
    
    private void populateDept(){
        try{
            ResultSet deptRs = con.executeStatement("SELECT * FROM HDEPT");
            while(deptRs.next()){
                this.cmbDept.addItem(deptRs.getInt(2) +"");
            }
        }catch(Exception e){}
    }
    
    private boolean validateName(){
        if(this.txtName.getText().isEmpty()){
            this.errorName.setText("Cannot be empty");
            this.errorName.setVisible(true);
            return false; 
        }
        
        if(this.txtName.getText().toString().length() > 50){
            this.errorName.setText("Must be less than or equal to 50 characters");
            this.errorName.setVisible(true);
            return false; 
        }
        
        for(int i = 0; i < this.txtName.getText().toString().length(); i++){
            if(Character.isDigit(this.txtName.getText().toString().charAt(i))){
                this.errorName.setText("Should not contain a digit");
                this.errorName.setVisible(true);
                return false; 
            }
        }
        
        this.errorName.setVisible(false);
        return true; 
    }
    
    private boolean validateId(){
        String id = this.txtId.getText().toString(); 
        if(id.isEmpty()){
            this.errorId.setText("Cannot be empty");
            this.errorId.setVisible(true);
            return false; 
        }
        
        if(id.length() > 9){
            this.errorId.setText("Should be less than or equal to 9 charcters");
            this.errorId.setVisible(true);
            return false;
        }
        
        for(int i = 0; i < id.length(); i++){
            if(Character.isLetter(id.charAt(i))){
                this.errorId.setText("Should not contain a letter");
                this.errorId.setVisible(true);
                return false;
            }
        }
        
        // check if it does not exist
        try{
            ResultSet docRs = con.executeStatement("SELECT * FROM Nurse");
            while(docRs.next()){
                if(docRs.getString("ID").equals(this.txtId.getText())){
                    this.errorId.setText("ID already exists");
                    this.errorId.setVisible(true);
                    return false;
                }
            }
        }catch(Exception e){}
        
        this.errorId.setVisible(false);
        return true; 
    }
    
    private boolean validatePhone(){
        if(this.txtPhoneNumber.getText().toString().isEmpty()){
            this.errorPhoneNumber.setText("Should not be empty");
            this.errorPhoneNumber.setVisible(true);
            return false; 
        }                
        String phone = this.txtPhoneNumber.getText().toString(); 
        
        if(phone.length() > 10){
            this.errorPhoneNumber.setText("Must be less than 11 digits");
            this.errorPhoneNumber.setVisible(true);
            return false; 
        }
        for(int i = 0; i < phone.length(); i++){
            if(Character.isLetter(phone.charAt(i))){
                this.errorPhoneNumber.setText("Should not contain a letter");
                this.errorPhoneNumber.setVisible(true);
                return false; 
            }
        }
        
        this.errorPhoneNumber.setVisible(false);
        return true; 
    }
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );
    
    private boolean validateEmail(){
        if(this.txtEmail.getText().isEmpty()){
            this.errorEmail.setText("Cannot be empty");
            this.errorEmail.setVisible(true);
            return false; 
        }
        
        if(this.txtEmail.getText().length() > 30){
            this.errorEmail.setText("Must be 30 charcters or less");
            this.errorEmail.setVisible(true);
            return false;
        }
        
        // checks if it ends correctly:
        String email = this.txtEmail.getText(); 
        boolean validEnd = false; 
        if(email.charAt(email.length() - 1) == 'm' && email.charAt(email.length() - 2) == 'o' && email.charAt(email.length() - 3) == 'c')
            validEnd = true; 
        else if(email.charAt(email.length() - 1) == 'u' && email.charAt(email.length() - 2) == 'd' && email.charAt(email.length() - 3) == 'e')
            validEnd = true; 
        else if(email.charAt(email.length() - 1) == 'g' && email.charAt(email.length() - 2) == 'r' && email.charAt(email.length() - 3) == 'o')
            validEnd = true; 
        if(!validEnd){
            this.errorEmail.setText("Format should be example@domain.com/org/edu");
            this.errorEmail.setVisible(true);
            return false;
        }
        
        if(!EMAIL_PATTERN.matcher(this.txtEmail.getText()).matches()){
            this.errorEmail.setText("Format should be example@domain.com/org/edu");
            this.errorEmail.setVisible(true);
            return false;
        }
        
        this.errorEmail.setVisible(false);
        return true; 
    }
    
    
    
    private boolean validateSalary(){
        if(this.txtSalary.getText().isEmpty()){
            this.errorSalary.setText("Cannot be empty");
            this.errorSalary.setVisible(true);
            return false;
        }
        String salaryStr = this.txtSalary.getText(); 
        for(int i = 0; i < salaryStr.length(); i++){
            if(Character.isLetter(salaryStr.charAt(i))){
                this.errorSalary.setText("Should not contain a letter");
                this.errorSalary.setVisible(true);
                return false; 
            }
        }        
        double salary = Double.parseDouble(this.txtSalary.getText());
        if(salary < 0){
            this.errorSalary.setText("Should be a positive number");
            this.errorSalary.setVisible(true);
            return false;
        }
        
        this.errorSalary.setVisible(false);
        return true; 
    }
    
    public AddNurse() {
        con = new dbCon(); 
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.populateDept();
        this.clearErrorLabels(); 
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
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbSex = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbDept = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        errorName = new javax.swing.JLabel();
        errorId = new javax.swing.JLabel();
        errorEmail = new javax.swing.JLabel();
        errorPhoneNumber = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        errorSalary = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Nurse");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("ID:");

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("SEX:");

        cmbSex.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Phone Number:");

        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Departement ID:");

        cmbDept.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        errorName.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorName.setForeground(new java.awt.Color(255, 51, 51));
        errorName.setText("Error Label");

        errorId.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorId.setForeground(new java.awt.Color(255, 51, 51));
        errorId.setText("Error Label");

        errorEmail.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorEmail.setForeground(new java.awt.Color(255, 0, 0));
        errorEmail.setText("Error Label");

        errorPhoneNumber.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorPhoneNumber.setForeground(new java.awt.Color(255, 51, 0));
        errorPhoneNumber.setText("Error Label");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Role:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Salary:");

        txtSalary.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        errorSalary.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorSalary.setForeground(new java.awt.Color(255, 51, 51));
        errorSalary.setText("Error Label");

        cmbRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LABORATORY", "nursing patients" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSalary))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSex, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDept, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorName)
                            .addComponent(errorId)
                            .addComponent(errorEmail)
                            .addComponent(errorPhoneNumber)
                            .addComponent(errorSalary))
                        .addGap(0, 339, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorPhoneNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorSalary))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // add the doctor 
        boolean add = true; 
        if(!this.validateEmail())
            add = false; 
        
        if(!this.validateId())
            add = false; 
        
        if(!this.validateName())
            add = false; 
        
        if(!this.validatePhone())
            add = false; 
        
        if(!this.validateSalary())
            add = false; 
        
        if(add){
            // add 
            String email = this.txtEmail.getText(); 
            String id = this.txtId.getText(); 
            String name = this.txtName.getText(); 
            String phone = this.txtPhoneNumber.getText(); 
            double salary = Double.parseDouble(this.txtSalary.getText());
            Object role = this.cmbRole.getSelectedItem(); 
            Object deptId = this.cmbDept.getSelectedItem();
            char sex = 'm';
            if(this.cmbSex.getSelectedItem().equals("Female"))
                sex = 'f';
            
            try{
                int result = con.executePrepared("INSERT INTO nurse VALUES ('" + name + "', " + deptId + ", " +
                 "'" + id + "', '" + sex + "', '" + phone + "', " + salary + ", '" + role + "', '" + email + "')");

                if(result > 0){
                    JOptionPane.showMessageDialog(null, "Nurse inserted successfully!");
                    this.clearErrorLabels(); 
                    // clear the text 
                    this.cmbDept.setSelectedIndex(0);
                    this.cmbSex.setSelectedIndex(0);
                    this.cmbRole.setSelectedIndex(0);
                    this.txtName.setText("");
                    this.txtId.setText("");
                    this.txtEmail.setText("");
                    this.txtSalary.setText("");
                    this.txtPhoneNumber.setText("");
                }
            }
            catch(Exception e){JOptionPane.showMessageDialog(null, "SQL error at insert");}
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
            java.util.logging.Logger.getLogger(AddNurse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNurse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNurse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNurse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNurse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cmbDept;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JComboBox<String> cmbSex;
    private javax.swing.JLabel errorEmail;
    private javax.swing.JLabel errorId;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel errorPhoneNumber;
    private javax.swing.JLabel errorSalary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables
}
