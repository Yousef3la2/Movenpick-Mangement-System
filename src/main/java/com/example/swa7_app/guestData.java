package com.example.swa7_app;

import java.sql.Date;

public class guestData {


        private Integer idcustomer,room ;
        private String firstName ;
        private String lastName;
        private String phoneNumber;
        private Date checkin;
        private Date checkout;

        public guestData(Integer idcustomer,Integer room, String firstName, String lastName, String phoneNumber , Date checkin, Date checkout){
            this.idcustomer=idcustomer;
            this.room=room;
            this.firstName=firstName;
            this.lastName=lastName;
            this.phoneNumber=phoneNumber;

            this.checkin=checkin;
            this.checkout=checkout;

        }
        public Integer getIdcustomer(){
        return idcustomer;
    }
        public Integer getRoom(){
            return room;
        }
        public String getFirstName(){
            return firstName;
        }
        public String getLastName(){
            return lastName;
        }
        public String getPhoneNumber(){return phoneNumber;}
        public Date getCheckin(){return checkin;}
        public Date getCheckout(){
            return checkout;
        }




}
