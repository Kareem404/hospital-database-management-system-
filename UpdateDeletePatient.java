/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalinfosystem;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Karee
 */
public class UpdateDeletePatient extends javax.swing.JFrame {

   
    dbCon con; 
    ResultSet patRs;
    void clearErrorLabels(){
        this.errorAge.setVisible(false);
        this.errorBloodType.setVisible(false);
        this.errorHeight.setVisible(false);
        this.errorId.setVisible(false);
        this.errorName.setVisible(false);
        this.errorPhoneNumber.setVisible(false);
        this.errorWeight.setVisible(false);
    }
    
    private void populateDoctorCmb(){
        try{
            ResultSet docRs = con.executeStatement("SELECT ID FROM DOCTOR");
            while(docRs.next()){
                this.cmbDoctor.addItem(docRs.getString(1));
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
            ResultSet patRs2 = con.executeStatement("SELECT * FROM PATIENT");
            while(patRs2.next()){
                if(patRs2.getString("ID").equals(this.txtId.getText())){
                    this.errorId.setText("ID already exists");
                    this.errorId.setVisible(true);
                    return false;
                }
            }
        }catch(Exception e){}
        return true; 
    }
    
    private boolean validateAge(){
        if(this.txtAge.getText().toString().isEmpty()){
            this.errorAge.setText("Cannot be empty");
            this.errorAge.setVisible(true);
            return false; 
        }
        for(int i = 0; i < this.txtAge.getText().toString().length(); i++){
            if(!Character.isDigit(this.txtAge.getText().charAt(i))){
                this.errorAge.setText("Should not contain a letter or a special character");
                this.errorAge.setVisible(true);
                return false; 
            }
        }
        int age = Integer.parseInt(this.txtAge.getText().toString());
        if(age > 100){
            this.errorAge.setText("should be less than 100 years");
            this.errorAge.setVisible(true);
            return false; 
        }
        if(age < 0){
            this.errorAge.setText("should be a positive integer");
            this.errorAge.setVisible(true);
            return false; 
        }
        
        return true; 
    }
    
    private boolean validateHeight(){
        if(this.txtHeight.getText().toString().isEmpty()){
            this.errorHeight.setText("Should not be empty");
            this.errorHeight.setVisible(true);
            return false; 
        }
        
        for(int i = 0; i < this.txtHeight.getText().toString().length(); i++){
            if(!Character.isDigit(this.txtHeight.getText().charAt(i))){
                this.errorHeight.setText("Should not contain a letter or a special character");
                this.errorHeight.setVisible(true);
                return false; 
            }
        }
        
        double height = Double.parseDouble(this.txtHeight.getText().toString());
        if(height < 0){
            this.errorHeight.setText("Should be a positive number");
            this.errorHeight.setVisible(true);
            return false; 
        }
        
        return true; 
    }
    
    private boolean validateWeight(){
        if(this.txtWeight.getText().toString().isEmpty()){
            this.errorWeight.setText("Should not be empty");
            this.errorWeight.setVisible(true);
            return false; 
        }
        for(int i = 0; i < this.txtWeight.getText().toString().length(); i++){
            if(!Character.isDigit(this.txtWeight.getText().charAt(i))){
                this.errorWeight.setText("Should not contain a letter or a special character");
                this.errorWeight.setVisible(true);
                return false; 
            }
        }
        double weight = Double.parseDouble(this.txtWeight.getText().toString());
        if(weight < 0){
            this.errorWeight.setText("Should be a positive number");
            this.errorWeight.setVisible(true);
            return false; 
        }
        
        return true; 
    }
    
    private boolean validateBloodType(){
        if(this.txtBloodType.getText().toString().isEmpty()){
            this.errorBloodType.setText("Should not be empty");
            this.errorBloodType.setVisible(true);
            return false; 
        }
        String bloodType = this.txtBloodType.getText().toString().trim(); 
        if(bloodType.length() > 3){
            this.errorBloodType.setText("Should be at maximum 3 charcters");
            this.errorBloodType.setVisible(true);
            return false; 
        }
        // should not contain a number 
        for(int i = 0; i < bloodType.length(); i++){
            if(Character.isDigit(bloodType.charAt(i))){
                this.errorBloodType.setText("Should not contain a digit");
                this.errorBloodType.setVisible(true);
                return false; 
            }
        }
        
        // check if it is a valid blood type 
        String[] arrBloodType = {"a+", "a-", "b-", "b+", "ab-", "ab+", "o-", "o+"}; 
        boolean validBloodType = false; 
        for(int i = 0; i < arrBloodType.length &&!validBloodType; i++){
            if(arrBloodType[i].equals(bloodType)){
                validBloodType = true; 
            }
        }
        
        if(!validBloodType){
            this.errorBloodType.setText("Not a valid blood type");
            this.errorBloodType.setVisible(true);
            return false; 
        }
        
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
        
        return true; 
    }
    
    private void populateFields(){
        try{
            this.txtName.setText(patRs.getString(1));
            this.txtId.setText(patRs.getString(2));
            if(patRs.getString(3).equals("f"))
                this.cmbSex.setSelectedItem("Female");
            else{
                this.cmbSex.setSelectedItem("Male");
            }
            this.txtAge.setText(patRs.getInt(4) + "");
            this.txtHeight.setText(patRs.getInt(5) + "");
            this.txtWeight.setText(patRs.getInt(6) + "");
            this.txtBloodType.setText(patRs.getString(7));
            this.txtPhoneNumber.setText(patRs.getString(8));
            String docId = patRs.getString(9);
            this.cmbDoctor.setSelectedItem(docId);
        }catch(Exception e){}
    }
    private void enableDisableButtons(){
        try{
            if(patRs.isFirst()){
                this.btnPrev.setEnabled(false);
            }
            else{
                this.btnPrev.setEnabled(true);
            }
            
            if(patRs.isLast()){
                this.btnNext.setEnabled(false);
            }
            else{
                this.btnNext.setEnabled(true);
            }
        }catch(Exception e){}
    }
    
    private void moveNext(){
        try{
            patRs.next(); 
            this.populateFields();
            this.enableDisableButtons();
        }catch(Exception e){}
    }
    
    private void movePrev(){
        try{
            patRs.previous(); 
            this.populateFields();
            this.enableDisableButtons();
        }catch(Exception e){}
    }
    public UpdateDeletePatient() {
        con = new dbCon(); 
        try{
            patRs = con.executeStatement("SELECT * FROM PATIENT");
            patRs.first();
        }catch(Exception e){}
        initComponents();
        this.setLocationRelativeTo(null);
        this.txtId.setEnabled(false);
        this.setResizable(false);
        this.populateDoctorCmb();
        this.populateFields();
        this.enableDisableButtons();
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
        jLabel5 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHeight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBloodType = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbDoctor = new javax.swing.JComboBox<>();
        errorName = new javax.swing.JLabel();
        errorId = new javax.swing.JLabel();
        errorAge = new javax.swing.JLabel();
        errorHeight = new javax.swing.JLabel();
        errorWeight = new javax.swing.JLabel();
        errorBloodType = new javax.swing.JLabel();
        errorPhoneNumber = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Update/Delete Patient");

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Age:");

        txtAge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Height:");

        txtHeight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHeightActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Weight:");

        txtWeight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Blood Type:");

        txtBloodType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Phone Number:");

        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Assigned Doctor:");

        cmbDoctor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        errorName.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorName.setForeground(new java.awt.Color(255, 51, 51));
        errorName.setText("Error Label");

        errorId.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorId.setForeground(new java.awt.Color(255, 51, 51));
        errorId.setText("Error Label");

        errorAge.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorAge.setForeground(new java.awt.Color(255, 51, 51));
        errorAge.setText("Error Label");

        errorHeight.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorHeight.setForeground(new java.awt.Color(255, 51, 51));
        errorHeight.setText("Error Label ");

        errorWeight.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorWeight.setForeground(new java.awt.Color(255, 0, 0));
        errorWeight.setText("Error Label");

        errorBloodType.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorBloodType.setForeground(new java.awt.Color(255, 0, 51));
        errorBloodType.setText("Error Label");

        errorPhoneNumber.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        errorPhoneNumber.setForeground(new java.awt.Color(255, 51, 0));
        errorPhoneNumber.setText("Error Label");

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNext.setText("Next>>>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPrev.setText("<<<Prev");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhoneNumber))
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
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBloodType))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtWeight))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHeight))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorName)
                            .addComponent(errorId)
                            .addComponent(errorAge)
                            .addComponent(errorHeight)
                            .addComponent(errorWeight)
                            .addComponent(errorBloodType)
                            .addComponent(errorPhoneNumber))
                        .addGap(0, 348, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorAge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorHeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorWeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorBloodType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorPhoneNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHeightActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        this.movePrev();
        this.clearErrorLabels();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.moveNext();
        this.clearErrorLabels();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // delete the patient normally 
        try{
            String sqlDelete = "Delete from patient where id = '" + this.txtId.getText() + "'";
            int result = con.executePrepared(sqlDelete);
            if(result > 0){
                JOptionPane.showMessageDialog(null, "Patient deleted successfully");
                patRs = con.executeStatement("SELECT * FROM PATIENT");
                patRs.first(); 
                this.populateFields();
                this.enableDisableButtons();
            }
            else{
                JOptionPane.showMessageDialog(null, "Patient not deleted :(");
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null, "SQL ERROR AT DELETE");}
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            boolean add = true; 
             if(!this.validateName())
                add = false; 
        
            if(!this.validateAge())
                add = false; 
        
            if(!this.validateHeight())
                add = false; 
        
            if(!this.validateWeight())
                add = false; 
        
            if(!this.validatePhone())
                add = false; 
        
            if(!this.validateBloodType())
                add = false; 
            
            if(add){
                // update
                String name = this.txtName.getText();
                String id = this.txtId.getText().toString(); 
                char sex = 'm';
                if(this.cmbSex.getSelectedItem().equals("Female")){
                    sex = 'f';
                }
    
                int age = Integer.parseInt(this.txtAge.getText().toString());
                double height = Double.parseDouble(this.txtHeight.getText().toString());
                double weight = Double.parseDouble(this.txtWeight.getText().toString());
                String bloodType = this.txtBloodType.getText().toString();
                String phone = this.txtPhoneNumber.getText().toString(); 
                String docId = this.cmbDoctor.getSelectedItem().toString();
                
                String SqlUpdate = "Update patient set name = '" + name + "', sex = '" +sex +"', age = " + age + ""
                        + ", height = " + height +", "+ "weight = " + weight + ", blood_type = '" + bloodType + "', "
                        + "phone = '" + phone +"', docId = '" +docId +"' WHERE ID = '" + id +"'"; 
                int result = con.executePrepared(SqlUpdate);
                if(result > 0)
                {
                    JOptionPane.showMessageDialog(null, "Patient updated successfully!");
                    this.clearErrorLabels();
                    patRs = con.executeStatement("SELECT * FROM PATIENT");
                    patRs.first();
                    this.populateFields();
                    this.enableDisableButtons();
                }
            }
            else{
               JOptionPane.showMessageDialog(null, "Please fix validation errors");
            }
        
        }catch(Exception e){JOptionPane.showMessageDialog(null, "SQL ERROR AT UPDATE");}
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateDeletePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateDeletePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateDeletePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateDeletePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateDeletePatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbDoctor;
    private javax.swing.JComboBox<String> cmbSex;
    private javax.swing.JLabel errorAge;
    private javax.swing.JLabel errorBloodType;
    private javax.swing.JLabel errorHeight;
    private javax.swing.JLabel errorId;
    private javax.swing.JLabel errorName;
    private javax.swing.JLabel errorPhoneNumber;
    private javax.swing.JLabel errorWeight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBloodType;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
