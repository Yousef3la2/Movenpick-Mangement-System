package com.example.swa7_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ReceptionistController  implements Initializable {

    @FXML
    private AnchorPane paycash_form;
    @FXML
    private Button checkValidate;
    @FXML
    private TextField VoucherCode_Input;
    @FXML
    private CheckBox CreditPay;
    @FXML
    private ComboBox<?> CheukOut;
    @FXML
    private AnchorPane checkout_form,services_form,guests_form;

    @FXML
    private Button close_btn;


    @FXML
    private Button minimize_btn;

    @FXML
    private Button checkin_btn;

    @FXML
    private Label username ,totalDay,totalpayment,totalDay1,totalpayment1;

    @FXML
    private Button rooms_btn;

    @FXML
    private Button guests_btn;

    @FXML
    private Button services_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button checkout_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TextField firestnameinput;

    @FXML
    private TextField lastnameinput,availableguest_search1;

    @FXML
    private TextField passinput;

    @FXML
    private TextField phoneinput;

    @FXML
    private TextField emailinput;

    @FXML
    private TextField nationalityinput;

    @FXML
    private TextField addressinput;

    @FXML
    private ComboBox<?> roomtype,roomtype1;

    @FXML
    private ComboBox<?> roomnumber,roomnumber1;

    @FXML
    private DatePicker checkindata,checkindata1;

    @FXML
    private DatePicker chekoutdata,chekoutdata1;

    @FXML
    private TextField creditCardField;

    @FXML
    private TextField cardHolderField;

    @FXML
    private TextField exp_Mth_Field;

    @FXML
    private TextField exp_Year_Field;

    @FXML
    private TextField CVV_Field;

    @FXML
    private Button Reset;

    @FXML
    private Button confirm;

    @FXML
    private AnchorPane rooms_form;
    @FXML
    private AnchorPane checkin_form;

    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private ComboBox<?> availableRooms_roomStatus;

    @FXML
    private TextField availableRooms_roomPrice;

    @FXML
    private Button availableRooms_addBtn;

    @FXML
    private Button availableRooms_updateBtn;

    @FXML
    private Button availableRooms_clearBtn;

    @FXML
    private Button availableRooms_deleteBtn;

    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private TableView<roomData> availableRooms_tableView;

    @FXML
    private TableColumn<roomData,String> availableRooms_col_roomNumbr;

    @FXML
    private TableColumn<roomData,String> availableRooms_col_roomType;

    @FXML
    private TableColumn<roomData,String> availableRooms_col_roomStatus;

    @FXML
    private TableColumn<roomData,String> availableRooms_col_roomPrice;

    @FXML
    private TextField availableRooms_search;



    @FXML
    private Button Add_btn;

    @FXML
    private Button Update_btn;

    @FXML
    private Button Delete_btn;


    @FXML
    private TableView<guestData> guest_tableView;

    @FXML
    private TableColumn<guestData,String> guest_Room;

    @FXML
    private TableColumn<guestData,String> guest_firstName;

    @FXML
    private TableColumn<guestData,String> guest_lastName;

    @FXML
    private TableColumn<guestData,String> guest_customerPhone;

    @FXML
    private TableColumn<guestData,String> guest_checkedIn;

    @FXML
    private TableColumn<guestData,String> guest_checkedOut;


    public void setText(String text) {
        username.setText(text);
    }
    @FXML
    void creditPayOnAction(ActionEvent event){
        if (CreditPay.isSelected()){
            paycash_form.setVisible(false);
        }else{
            paycash_form.setVisible(true);
        }

    }

/*if(event.getSource()==checkin_btn){
            checkin_form.setVisible(true);
            checkout_form.setVisible(false);
            rooms_form.setVisible(false);
            guests_form.setVisible(false);
            services_form.setVisible(false);

        }else if(event.getSource()==checkout_btn){
            checkin_form.setVisible(false);
            checkout_form.setVisible(true);
            rooms_form.setVisible(false);
            guests_form.setVisible(false);
            services_form.setVisible(false);

        }*/

    @FXML
    void closepage(ActionEvent event) {
        System.exit(0);
    }



    @FXML
    void logout(ActionEvent event) {
        try {

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("are you sure you want to logout ?");

            Optional<ButtonType> option =alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                Parent root = FXMLLoader.load(this.getClass().getResource("Login.fxml"));
                Stage stage=new Stage();
                Scene scene = new Scene(root);
                Image image = new Image("file:icon.png");
                stage.getIcons().add(image);

                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        x=mouseEvent.getScreenX();
                        y=mouseEvent.getScreenY();

                    }
                });

                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        stage.setX(mouseEvent.getScreenX()-x);
                        stage.setY(mouseEvent.getScreenY()-y);

                    }
                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                logout_btn.getScene().getWindow().hide();

            }else
            {return;}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage=(Stage)rooms_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void switchForm(ActionEvent event) {
        if(event.getSource()==checkin_btn){
            checkin_form.setVisible(true);
            checkout_form.setVisible(false);
            rooms_form.setVisible(false);
            guests_form.setVisible(false);
            services_form.setVisible(false);

            reset();

        }else if(event.getSource()==checkout_btn){
            checkin_form.setVisible(false);
            checkout_form.setVisible(true);
            rooms_form.setVisible(false);
            guests_form.setVisible(false);
            services_form.setVisible(false);

        }else if(event.getSource()==rooms_btn){
            checkin_form.setVisible(false);
            checkout_form.setVisible(false);
            rooms_form.setVisible(true);
            guests_form.setVisible(false);
            services_form.setVisible(false);
            availableRoomsShowData();
        }else if(event.getSource()==guests_btn){
            checkin_form.setVisible(false);
            checkout_form.setVisible(false);
            rooms_form.setVisible(false);
            guests_form.setVisible(true);
            services_form.setVisible(false);
            guestShowData();
            guestSearchData();
        }else if(event.getSource()==services_btn){
            checkin_form.setVisible(false);
            checkout_form.setVisible(false);
            rooms_form.setVisible(false);
            guests_form.setVisible(false);
            services_form.setVisible(true);

        }
    }
    ////    guestSearchData   ////
    public void availableRoomsSearch(){
        FilteredList<roomData> filter = new FilteredList<>(roomDataList, e->true);
        availableRooms_search.textProperty().addListener((Observable,oldValue,newValue )->{
            filter.setPredicate(predicateRoomData->{
                if(newValue==null&&newValue.isEmpty()){
                    return  true;
                }
                String searchkey=newValue.toLowerCase();

                if (predicateRoomData.getRoomNumber().toString().contains(searchkey)){
                    return true;
                } else if (predicateRoomData.getRoomType().toLowerCase().contains(searchkey)) {
                    return true;

                }else if (predicateRoomData.getStatus().toLowerCase().contains(searchkey)) {
                    return true;

                }else if (predicateRoomData.getPrice().toString().contains(searchkey)) {
                    return true;

                }else return false;

            });  });
        SortedList<roomData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(availableRooms_tableView.comparatorProperty());
        availableRooms_tableView.setItems(sortList);
    }




    public void guestSearchData(){
        FilteredList<guestData> filter = new FilteredList<>(listguestData,e->true);
        availableguest_search1.textProperty().addListener((Observable,oldValue,newValue )->{
            filter.setPredicate(predicateguestData->{
                if(newValue==null&&newValue.isEmpty()){
                    return  true;
                }
                String searchkey=newValue.toLowerCase();

                if (predicateguestData.getIdcustomer().toString().contains(searchkey)){
                    return true;

                }else if (predicateguestData.getRoom().toString().contains(searchkey)) {
                    return true;
                }
                else if (predicateguestData.getFirstName().toString().contains(searchkey)){
                    return true;

                }else if (predicateguestData.getLastName().toString().contains(searchkey)) {
                    return true;

                }else if (predicateguestData.getPhoneNumber().toString().contains(searchkey)) {
                    return true;
                }
                else if (predicateguestData.getCheckin().toString().contains(searchkey)){
                    return true;

                }else if (predicateguestData.getCheckout().toString().contains(searchkey)) {
                    return true;

                }else return false;

            });  });
        SortedList<guestData>sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(guest_tableView.comparatorProperty());
        guest_tableView.setItems(sortList);
    }


    /////////////
    ///       room number combo box //////

    public void roomNumberList() {
        String room_TYPE;
        if (CreditPay.isSelected()) {
            room_TYPE = (String) roomtype.getSelectionModel().getSelectedItem();
        }else{
            room_TYPE = (String) roomtype1.getSelectionModel().getSelectedItem();
        }
        String listNumber = "SELECT distinct roomNumber FROM rooms WHERE status = 'Available' AND type = ?";
        connect = DatabaseConnection.getConnection();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listNumber);
            prepare.setString(1, room_TYPE);
            result = prepare.executeQuery();
            while (result.next()) {
                listData.add(result.getString("roomNumber"));
            }
            if (CreditPay.isSelected()){
            roomnumber.setItems(listData);
                roomnumber.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                    displayAmount();
                });


                //displayAmount();
            }
            else{
            roomnumber1.setItems(listData);
                roomnumber1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                    displayAmount();
                });
            //displayAmount();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
/////////////////////////////////////////
    ////////////display pay and days////////////////
    void displayAmount (){
        displaytotalpay();
        totalDays();
    }
public int code1=0,code2=0,code3=0, code4=0,i=0 ;
    @FXML
    void checkValidateclick(ActionEvent event) {
        code1=0;code2=0;code3=0;code4=0;i=0;
        int t= roomtype1.getItems().size();
        if(VoucherCode_Input.getText().equals("123")||VoucherCode_Input.getText().equals("105")){
            i=1;
            if(roomtype1.getItems().contains("Double Room")||roomtype.getItems().contains("Double Room")){
                if(VoucherCode_Input.getText().equals("123"))
                code1=1;
                else if (VoucherCode_Input.getText().equals("105"))
                    code4=1;

                if(CreditPay.isSelected()){
               roomtype.getSelectionModel().select(0);
                checkindata.setValue(LocalDate.of(2023,6,12));
                chekoutdata.setValue(LocalDate.of(2023,6,13));}
            else{
                roomtype1.getSelectionModel().select(0);
                checkindata1.setValue(LocalDate.of(2023,6,12));
                chekoutdata1.setValue(LocalDate.of(2023,6,13));
            }}}



        else  if (VoucherCode_Input.getText().equals("456")){
            i=1;
            if(roomtype1.getItems().contains("Single Room")||roomtype.getItems().contains("Single Room")) {
                code2 = 1;
                if (CreditPay.isSelected()) {

                        roomtype.getSelectionModel().select((t-1));
                    checkindata.setValue(LocalDate.of(2023, 6, 1));
                    chekoutdata.setValue(LocalDate.of(2023, 6, 3));
                } else {

                        roomtype1.getSelectionModel().select((t-1));
                    checkindata1.setValue(LocalDate.of(2023, 6, 1));
                    chekoutdata1.setValue(LocalDate.of(2023, 6, 3));
                } } }


        else  if (VoucherCode_Input.getText().equals("789")) {
            i=1;
            if(roomtype1.getItems().contains("Quad Room")||roomtype.getItems().contains("Quad Room")){
                code3=1;
                if(CreditPay.isSelected()){
                if((t==3) ||(t==2&&roomtype.getItems().contains("Double Room")))
                    roomtype.getSelectionModel().select(1);
                else if((t==1)||(t==2&&roomtype.getItems().contains("Single Room")))
                    roomtype.getSelectionModel().select(0);
                checkindata.setValue(LocalDate.of(2023,5,15));
                chekoutdata.setValue(LocalDate.of(2023,5,17));
            }
            else {
                    if ((t == 3) || (t == 2 && roomtype1.getItems().contains("Double Room")))
                        roomtype1.getSelectionModel().select(1);
                    else if ((t == 1) || (t == 2 && roomtype1.getItems().contains("Single Room")))
                        roomtype1.getSelectionModel().select(0);
                    checkindata1.setValue(LocalDate.of(2023, 5, 15));
                    chekoutdata1.setValue(LocalDate.of(2023, 5, 17));
                }}}


        if(i==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Not Found This Offer");
            alert.showAndWait();
        }


        else if (code1==0&&code2==0&&code3==0&&code4==0){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Sorry this offer not available at this time");
                alert.showAndWait();
            }


    }



    ///////////////////////////
    ////////////////////////////
    ///       room type combo box //////

    public void roomTypeList() {
        String listType = "SELECT distinct type FROM rooms WHERE status = 'Available' ORDER BY type ";
        connect = DatabaseConnection.getConnection();
        try{
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while (result.next()){
                listData.add(result.getString("type"));
            }
            roomtype.setItems(listData);
           // String s=new String("single");

            roomtype1.setItems(listData);

                roomtype.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    roomNumberList();
                    displayAmount();
                });

                roomtype1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    roomNumberList();
                    displayAmount();
                });

        }catch(Exception e){
            e.printStackTrace();
        }
        //roomNumberList();
    }
/////////////////////////////////////////
    ///       room table //////
    public ObservableList<roomData>availableRoomListData() {
        ObservableList<roomData> listdata = FXCollections.observableArrayList();
        String sql = "SELECT * FROM rooms";

        connect = DatabaseConnection.getConnection();
        try {
            roomData roomD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                roomD = new roomData(result.getInt("roomNumber"),
                        result.getString("type"),
                        result.getString("status"),
                        result.getDouble("price"));
                listdata.add(roomD);
            }
        } catch (SQLException e) {
            e.printStackTrace();}
        return listdata;}
//////////////////////////////////
    private ObservableList<roomData>roomDataList;


    public void availableRoomsShowData(){
        roomDataList=availableRoomListData();
        availableRooms_col_roomNumbr.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        availableRooms_col_roomType.setCellValueFactory(new  PropertyValueFactory<>("roomType") );
        availableRooms_col_roomStatus.setCellValueFactory(new  PropertyValueFactory<>("status"));
        availableRooms_col_roomPrice.setCellValueFactory(new  PropertyValueFactory<>("price"));

        availableRooms_tableView.setItems(roomDataList);
    }

        //////////////////////////////////////////////
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;



    @FXML
    void confirmbtn(ActionEvent event) {

        String insertcustomerdata = "insert into customer (firstName,lastName,total,phoneNumber,email,checkin,checkout) " +
                "values(?,?,?,?,?,?,?)" ;
        String insertguestdata = "insert into guest (room,firstName,lastName,total,phoneNumber,email,checkin,checkout) " +
                "values(?,?,?,?,?,?,?,?)" ;
        String updateroomdata = "update rooms set status='Not Available' where roomNumber= ?";
        String addpayment ="insert INTO payment (currentpayment,datee)"+ "VALUES (?,?)";


        connect = DatabaseConnection.getConnection();

        try {

            String firstName = firestnameinput.getText();
            String lastName = lastnameinput.getText();
            String phoneNumber = phoneinput.getText();
            String email = emailinput.getText();
            String Checkin = String.valueOf(checkindata.getValue());
            String Checkout = String.valueOf(chekoutdata.getValue());
            String room_num = (String) roomnumber.getSelectionModel().getSelectedItem();
            String total = String.valueOf(getData.totalpays);
            if (!CreditPay.isSelected()) {
                Checkin = String.valueOf(checkindata1.getValue());
                Checkout = String.valueOf(chekoutdata1.getValue());
            }
            //String roomNum = roomnumber.getSelectionModel().getSelectedItem().toString();
            int  check = 1;
            String date;
            Alert alter;
            // || roomtype1.getSelectionModel().isEmpty() || roomnumber1.getSelectionModel().isEmpty() ||  checkindata1.getValue() == null || chekoutdata1.getValue() == null
            if (firestnameinput.getText().isEmpty()|| lastnameinput.getText().isEmpty() || phoneinput.getText().isEmpty() || emailinput.getText().isEmpty()
                    || nationalityinput.getText().isEmpty()|| addressinput.getText().isEmpty() || passinput.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please complete information.");
                alert.showAndWait();
                check = 0;
            } else if (CreditPay.isSelected()) {
                if (roomtype.getSelectionModel().isEmpty() || roomnumber.getSelectionModel().isEmpty() ||  checkindata.getValue() == null || chekoutdata.getValue() == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please complete room data.");
                    alert.showAndWait();
                    check = 0;
                }
                else if (cardHolderField.getText().isEmpty() || CVV_Field.getText().isEmpty() || creditCardField.getText().isEmpty() || exp_Mth_Field.getText().isEmpty() || exp_Year_Field.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please complete your card data.");
                    alert.showAndWait();
                    check = 0;
                }

            }
            else if (!CreditPay.isSelected()) {
                if (roomtype1.getSelectionModel().isEmpty() || roomnumber1.getSelectionModel().isEmpty() || checkindata1.getValue() == null || chekoutdata1.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please complete room data.");
                    alert.showAndWait();
                    check = 0;
                }
            }

            if (check == 1){
                    alter = new Alert(Alert.AlertType.CONFIRMATION);
                    alter.setTitle("CONFIRMATION message");
                    alter.setHeaderText(null);
                    alter.setContentText("Are you sure ?");

                    Optional<ButtonType> option = alter.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        prepare = connect.prepareStatement(insertcustomerdata);
                        prepare.setString(1, firstName);
                        prepare.setString(2, lastName);
                        prepare.setString(3, total);
                        prepare.setString(4, phoneNumber);
                        prepare.setString(5, email);
                        prepare.setString(6, Checkin);
                        prepare.setString(7, Checkout);
                        prepare.executeUpdate();
                        room_num = (String) roomnumber.getSelectionModel().getSelectedItem();
                        date= String.valueOf(checkindata.getValue());

                        if (!CreditPay.isSelected()) {
                            room_num = (String) roomnumber1.getSelectionModel().getSelectedItem();
                            date= String.valueOf(checkindata1.getValue());

                        }
                        prepare = connect.prepareStatement(insertguestdata);
                        prepare.setString(1, room_num);
                        prepare.setString(2, firstName);
                        prepare.setString(3, lastName);
                        prepare.setString(4, total);
                        prepare.setString(5, phoneNumber);
                        prepare.setString(6, email);
                        prepare.setString(7, Checkin);
                        prepare.setString(8, Checkout);
                        prepare.executeUpdate();
                        prepare = connect.prepareStatement(updateroomdata);
                        prepare.setString(1, room_num);
                        prepare.executeUpdate();

                        prepare = connect.prepareStatement(addpayment);
                        prepare.setString(1, String.valueOf(getData.totalpays));
                        prepare.setString(2, date);

                        prepare.executeUpdate();

                        alter.setTitle("information message");
                        alter.setHeaderText(null);
                        alter.setContentText("successfully checked-in");
                        alter.showAndWait();
                        reset();
                        roomTypeList();
                        roomNumberList();

                    } else {
                        return;
                    }
                }

//else {
//                alter = new Alert(Alert.AlertType.ERROR);
//                alter.setTitle("Error message");
//                alter.setHeaderText(null);
//                alter.setContentText("please complete information.");
//                alter.showAndWait();
//        }

            checkout();
        }catch(Exception e){
            e.printStackTrace();
        };
    }
    /////////////////////////
    public void checkout() {
        String listNumber = "SELECT room FROM guest ";
        connect = DatabaseConnection.getConnection();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listNumber);
            result = prepare.executeQuery();
            while (result.next()) {
                listData.add(result.getString("room"));
            }
            CheukOut.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void totalDays() {
    if (CreditPay.isSelected()) {
        chekoutdata.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && checkindata.getValue() != null) {
                Alert alert;
                if (chekoutdata.getValue() != null && checkindata.getValue() != null && !chekoutdata.getValue().isAfter(checkindata.getValue())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid check_out date");
                    alert.showAndWait();

                } else {
                    getData.totalDays = newValue.compareTo(checkindata.getValue());
                    totalDay1.setText(String.valueOf(getData.totalDays));
                    displaytotalpay();
                }
            }
        });
    } else {

        chekoutdata1.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && checkindata1.getValue() != null) {
                Alert alert;
                if (chekoutdata1.getValue() != null && checkindata1.getValue() != null && !chekoutdata1.getValue().isAfter(checkindata1.getValue())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid check_out date");
                    alert.showAndWait();


                } else {

                    getData.totalDays = newValue.compareTo(checkindata1.getValue());
                    totalDay.setText(String.valueOf(getData.totalDays));
                    displaytotalpay();

                }
            }
        });}}


    public void displaytotalpay() {
        String selectItems;
        if (CreditPay.isSelected()){
        selectItems = (String)roomnumber.getSelectionModel().getSelectedItem();}
        else {
            selectItems = (String)roomnumber1.getSelectionModel().getSelectedItem();
        }

        String sql ="SELECT price FROM rooms WHERE roomNumber = '"+selectItems+"'";


    double pricedata =0;

        connect = DatabaseConnection.getConnection();


        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()){
                pricedata=result.getDouble("price");
            }
            float totalpay=(float)((pricedata)*getData.totalDays);
            if(code1==1) {
                totalpay = (totalpay * 80) / 100;
                if (CreditPay.isSelected())
                    totalpayment1.setText("$" + String.valueOf(totalpay));
                else
                    totalpayment.setText("$" + String.valueOf(totalpay));
            }
            else if(code2==1) {
                totalpay = (totalpay * 90) / 100;
                if (CreditPay.isSelected())
                    totalpayment1.setText("$" + String.valueOf(totalpay));
                else
                    totalpayment.setText("$" + String.valueOf(totalpay));
            }
            else if(code3==1) {
                totalpay = (totalpay * 75) / 100;
                if (CreditPay.isSelected())
                    totalpayment1.setText("$" + String.valueOf(totalpay));
                else
                    totalpayment.setText("$" + String.valueOf(totalpay));
            }
            else if(code4==1) {
                totalpay = (totalpay * 60) / 100;
                if (CreditPay.isSelected())
                    totalpayment1.setText("$" + String.valueOf(totalpay));
                else
                    totalpayment.setText("$" + String.valueOf(totalpay));
            }



            else {
                if (CreditPay.isSelected())
                    totalpayment1.setText("$" + String.valueOf(totalpay));
                else
                    totalpayment.setText("$" + String.valueOf(totalpay));

            }

            getData.totalpays=totalpay;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void CheckOut_btn(ActionEvent event) {
        Alert alert;

        if (CheukOut.getSelectionModel().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("You Don't have guest.");
            alert.showAndWait();
        }else {
            String roomNUM = (String) CheukOut.getSelectionModel().getSelectedItem();
            String deleteData = "DELETE FROM guest WHERE room = ?";
            String updateRoom = "UPDATE rooms SET status = 'Available' WHERE roomNumber = ?";
            connect = DatabaseConnection.getConnection();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Checkout Room #" + roomNUM + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, roomNUM);
                    prepare.executeUpdate();

                    prepare = connect.prepareStatement(updateRoom);
                    prepare.setString(1, roomNUM);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Checked Out!");
                    alert.showAndWait();
                    checkout();
                    roomTypeList();
                    roomNumberList();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//////////////////////////////
    private double x=0;
    private double y=0;

    /////////////////////////////////
    public ObservableList<guestData> guestListData() {
        ObservableList<guestData> listDatacust = FXCollections.observableArrayList();
        String sql = "SELECT idcustomer,room,firstName,lastName,phoneNumber,checkin,checkout FROM guest";
        connect = DatabaseConnection.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            guestData custD;

            while (result.next()) {
                custD = new guestData(result.getInt("idcustomer"),
                        result.getInt("room"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                        result.getDate("checkin"),
                        result.getDate("checkout"));
                listDatacust.add(custD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return listDatacust;
    }

    private ObservableList<guestData>listguestData;
    public void guestShowData(){
        listguestData=guestListData();
        guest_Room.setCellValueFactory(new PropertyValueFactory<>("room"));
        guest_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        guest_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        guest_customerPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        guest_checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        guest_checkedOut.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        guest_tableView.setItems(listguestData);

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            guestShowData();
            roomTypeList();
            roomNumberList();
            displaytotalpay();
            totalDays();
            checkout();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @FXML
    void resett(ActionEvent event) {

        reset();
    }


    void reset(){
        roomtype.setValue(null);
        roomnumber.setValue(null);
        checkindata.setValue(null);
        chekoutdata.setValue(null);
        ///\
        roomtype1.setValue(null);
        roomnumber1.setValue(null);
        checkindata1.setValue(null);
        chekoutdata1.setValue(null);
        CVV_Field.setText(null);
        cardHolderField.setText(null);
        exp_Mth_Field.setText(null);
        exp_Year_Field.setText(null);
        creditCardField.setText(null);
        //
        firestnameinput.setText(null);
        lastnameinput.setText(null);
        passinput.setText(null);
        phoneinput.setText(null);
        addressinput.setText(null);
        nationalityinput.setText(null);
        emailinput.setText(null);
        getData.totalDays=0;
        getData.totalpays=0;
        totalpayment.setText("$0.0");
        totalpayment1.setText("$0.0");
       totalDay1.setText("00");
       totalDay.setText("00");

        displaytotalpay();
        totalDays();


    }



}
