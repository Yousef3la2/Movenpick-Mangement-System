package com.example.swa7_app;

import java.util.Date;

public class customerData {

    private Integer idcustomer ;
    private String firstName ;
    private String lastName;
    private String phoneNumber;
    private String total;
    private Date checkin;
    private Date checkout;

public customerData(Integer idcustomer, String firstName, String lastName, String phoneNumber , String total, Date checkin, Date checkout){

    this.idcustomer=idcustomer;
    this.firstName=firstName;
    this.lastName=lastName;
    this.phoneNumber=phoneNumber;
    this.total=total;
    this.checkin=checkin;
    this.checkout=checkout;

}

public Integer getIdcustomer(){
    return idcustomer;
}
public String getFirstName(){
    return firstName;
}
    public String getLastName(){
        return lastName;
    }
public String getPhoneNumber(){

    return phoneNumber;
}
public String getTotal(){
    return total;
}

public Date getCheckin(){
    return checkin;

}

public Date getCheckout(){
    return checkout;
}




}
