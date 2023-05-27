package com.example.swa7_app;

public class employeeData {

    private Integer account_Id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String nationality;
    private String username;
    private String emailaddress;
    private String password;


    public employeeData(int accountId, String firstName, String lastName, String phoneNumber, String gender, String nationality, String username, String emailaddress) {
        this.account_Id = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.nationality = nationality;
        this.username = username;
        this.emailaddress = emailaddress;

    }

    public Integer getAccount_id() {
        return account_Id;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getPassword() {
        return password;
    }


}