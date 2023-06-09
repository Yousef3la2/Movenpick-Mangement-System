package com.example.swa7_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegisterController {
    @FXML
    private TextField FirstnameInput,LastnameInput,Nationalityinput,PhoneNumberInput;
    @FXML
    private RadioButton male , female;
    @FXML
    private TextField usernameInput,EmailAddressInput;
    @FXML
    private PasswordField passwordInput,passwordconfirmCheck;
    @FXML
    private Label comfirmPasswordLabel,PasswordNullLabel;
    @FXML
    private Button SignUnButton,CancelButton;

    private AdminController parentController; // Reference to the AdminController instance

    public void setParentController(AdminController parentController) {
        this.parentController = parentController;
    }

    int checkPassword = 0;
    public void SignUpButtonOnAction(ActionEvent event) {
        validate();
        if (checkPassword == 3 && checkAll == 8 && validate() == 2){
            registerUser();
        }
    }


    private employeeData employee;

    public void setEmployeeData (employeeData Data){
        this.employee = Data;
        // Set the selected employee data in the components of RegisterController
        FirstnameInput.setText(employee.getFirstname());
        LastnameInput.setText(employee.getLastname());
        Nationalityinput.setText(employee.getNationality());
        PhoneNumberInput.setText(employee.getPhonenumber());
        usernameInput.setText(employee.getUsername());
        EmailAddressInput.setText(employee.getEmailaddress());

        // Set the gender radio button based on the selected employee's gender

        if (employee.getGender().equals("male")) {
            male.setSelected(true);
        } else if (employee.getGender().equals("female")) {
            female.setSelected(true);
        }



    }



    public void cancelButtonOnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        parentController.availableemployeeShowData(); // Call the method on AdminController to update data
    }
    int checkAll = 8;
    public int validate(){
        checkAll = 8;
        checkPassword();

        int checkGender = 0 ;
        if(male.isSelected()){
            checkGender = 1;
        } else if (female.isSelected()) {
            checkGender = 1;
        }

        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();
        String username = usernameInput.getText();

        int checkavailbalityUser = 0 ;

        try {
            String sql = "SELECT * FROM user_account WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Username exists in the database
                checkavailbalityUser = 0 ;
            } else {
                // Username does not exist in the database
                checkavailbalityUser = 1 ;
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        if (checkavailbalityUser == 0 ){
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("Username exists in the database");
            fail.showAndWait();

        }

        else if(FirstnameInput.getText().trim().isEmpty()){
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your firstname");
            fail.showAndWait();
            checkAll -=1;

        }

        else if (LastnameInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your lastname");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (Nationalityinput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your nationality");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (checkGender == 0) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("You didn't choose your gender");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (usernameInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("You didn't enter your username");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (EmailAddressInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your emailaddress");
            fail.showAndWait();
            checkAll -=1;
        }
        else if (PhoneNumberInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your phone number");
            fail.showAndWait();
            checkAll -=1;
        }

        if(checkAll == 8){
            return 2;
        }

        checkPassword = 1;
        return 1;

    }
    public void checkPassword(){
        boolean checkPasswordsEqual = passwordInput.getText().equals(passwordconfirmCheck.getText()) ;
        boolean checkPasswordEmpty = passwordInput.getText().trim().isEmpty() ;
        boolean checkConfirmEmpty = passwordconfirmCheck.getText().trim().isEmpty() ;
        if (checkPasswordEmpty){
            PasswordNullLabel.setTextFill(Color.RED);
            PasswordNullLabel.setText("You don't put password");

            PasswordNullLabel.setAlignment(Pos.CENTER);

        } else if (checkConfirmEmpty) {
            comfirmPasswordLabel.setTextFill(Color.RED);
            comfirmPasswordLabel.setText("You don't put the confirm");
            PasswordNullLabel.setText(null);
            comfirmPasswordLabel.setAlignment(Pos.CENTER);

        } else if (checkPasswordsEqual){
            comfirmPasswordLabel.setTextFill(Color.GREEN);
            comfirmPasswordLabel.setText("Password match");

            comfirmPasswordLabel.setAlignment(Pos.CENTER);
            PasswordNullLabel.setText(null);
            checkPassword = 3;

        }else{
            comfirmPasswordLabel.setTextFill(Color.RED);
            comfirmPasswordLabel.setText("Password doesn't match");
            PasswordNullLabel.setText(null);
            comfirmPasswordLabel.setAlignment(Pos.CENTER);
        }
    }
    public void comfirmSignUpMessage() throws IOException {

        Alert registerAlert = new Alert(Alert.AlertType.INFORMATION);
        //registerAlert.setTitle("Test Connection");
        registerAlert.initStyle(StageStyle.UNDECORATED);
        // Header Text: null
        registerAlert.setHeaderText(null);
        registerAlert.setContentText("User registered successfully!");
        //registerAlert.getButtonTypes();
        registerAlert.showAndWait();
        setAlltoEmpty();
        parentController.availableemployeeShowData(); // Call the method on AdminController to update data



    }
    public void registerUser(){
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();

        String  firstname = FirstnameInput.getText();
        String lastname = LastnameInput.getText();


        String gender = "";

        if(male.isSelected()){
            gender = "male";
            //System.out.println("m");
        }else if (female.isSelected()){
            gender = "female";
            //System.out.println("f");
        }


        String nationality = Nationalityinput.getText();
        String username = usernameInput.getText();
        String emailaddress = EmailAddressInput.getText();
        String password = passwordInput.getText();
        String phonenumber = PhoneNumberInput.getText();


        String insertFields = "INSERT INTO user_account (firstname,lastname,phonenumber,gender,nationality,username,emailaddress,password) VALUES ('";
        String insertValues = firstname + "','" +lastname + "','" + phonenumber + "','" + gender + "','" + nationality + "','" + username + "','" + emailaddress + "','" + password  + "')";
        String insertToRegister = insertFields + insertValues ;

        System.out.println(insertToRegister);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            comfirmSignUpMessage();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }


    public void setAlltoEmpty(){
        FirstnameInput.setText(null);
        LastnameInput.setText(null);
        Nationalityinput.setText(null);
        usernameInput.setText(null);
        EmailAddressInput.setText(null);
        passwordInput.setText(null);
        passwordconfirmCheck.setText(null);
        comfirmPasswordLabel.setText(null);
        male.setSelected(false);
        female.setSelected(false);
        PhoneNumberInput.setText(null);

    }

}

