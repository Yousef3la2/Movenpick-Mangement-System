package com.example.swa7_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class AdminController implements Initializable {




    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private Button availableRooms_addBtn,availableRooms_clearBtn;

    @FXML
    private Button changepassword_icon;

    @FXML
    private PasswordField Old_Pass_Label,New_Pass_Label,confirm_Pass_Label;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomNumbr;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomPrice;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomStatus;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomType;

    @FXML
    private TableColumn<employeeData, String> employee_firstName;
    @FXML
    private TableColumn<employeeData, String> employee_lastName;
    @FXML
    private TableColumn<employeeData, String> employee_Phone;
    @FXML
    private TableColumn<employeeData, String> employee_gender;
    @FXML
    private TableColumn<employeeData, String> employee_email;
    @FXML
    private TableColumn<employeeData, String> employee_username;
    @FXML
    private TableColumn<employeeData, String> employee_Number;


    @FXML
    private TableView<employeeData> employee_tableView;

    @FXML
    private Button availableRooms_deleteBtn,employees_btn;

    @FXML
    private TextField availableRooms_roomNumber,employee_search;

    @FXML
    private TextField availableRooms_roomPrice;

    @FXML
    private ComboBox<?> availableRooms_roomStatus;

    @FXML
    private TextField availableRooms_search;

    @FXML
    private TableView<roomData> availableRooms_tableView;

    @FXML
    private Button availableRooms_updateBtn;

    @FXML
    private Button close_btn;

    @FXML
    private AreaChart<?, ?> dashboard_areaChart;

    @FXML
    private Label dashboard_bookToday;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane changepassword_form,dashboard_form,employees_form;

    @FXML
    private Label dashboard_incomeToday;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button logout_btn;

    @FXML
    private Button minimize_btn;

    @FXML
    private AnchorPane reservitionReport_form;

    @FXML
    private TableColumn<customerData, String> reservition_checkedIn;

    @FXML
    private TableColumn<customerData, String> reservition_checkedOut;

    @FXML
    private TableColumn<customerData, String> reservition_customerNumber;

    @FXML
    private TableColumn<customerData,String> reservition_customerPhone;

    @FXML
    private TableColumn<customerData,String> reservition_firstName;

    @FXML
    private TableColumn<customerData,String> reservition_lastName;

    @FXML
    private Button reservition_report_btn;

    @FXML
    private TextField reservition_search;

    @FXML

    private TableView<customerData> reservition_tableView;
    @FXML
    private TableColumn<customerData,String> reservition_totalPayment;

    @FXML
    private Button rooms_btn;

    @FXML
    private AnchorPane rooms_form;

    @FXML
    private Button service_report_btn;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

                        //------------------------- change passwords -------------------------//



    public void ChangePassOnAction(ActionEvent e){
        Alert alert ;
        if (Old_Pass_Label.getText().isEmpty()||New_Pass_Label.getText().isEmpty()||confirm_Pass_Label.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else {

            String sql = "SELECT password FROM user_account WHERE account_id = 1 ";

            connect = DatabaseConnection.getConnection();
            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                String oldpasswordSQL = null;
                while (result.next()) {
                    oldpasswordSQL = result.getString("password");
                }
                if (oldpasswordSQL.equals(Old_Pass_Label.getText())) {
                    Old_Pass_Label.setStyle("-fx-background-radius: 15px");
                    Old_Pass_Label.setStyle("-fx-border-color: #05a40a");
                    if (New_Pass_Label.getText().equals(confirm_Pass_Label.getText())) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("are you sure you want to change your password ?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                            String changePass = "UPDATE user_account SET password = '" + New_Pass_Label.getText() + "' WHERE account_id = 1 ";
                            prepare = connect.prepareStatement(changePass);
                            prepare.executeUpdate();
                            Old_Pass_Label.setText(null);
                            New_Pass_Label.setText(null);
                            confirm_Pass_Label.setText(null);
                            returnPF(Old_Pass_Label);
                            returnPF(New_Pass_Label);
                            returnPF(confirm_Pass_Label);

                        }
                    } else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Confirm your new password correctly.");
                        alert.showAndWait();
                    }
                }else{
                    Old_Pass_Label.setStyle("-fx-background-radius: 15px");
                    Old_Pass_Label.setStyle("-fx-border-color: #a40518");

                    if (New_Pass_Label.getText().equals(confirm_Pass_Label.getText())) {
                        New_Pass_Label.setStyle("-fx-background-radius: 15px");
                        New_Pass_Label.setStyle("-fx-border-color: #05a40a");
                        confirm_Pass_Label.setStyle("-fx-background-radius: 15px");
                        confirm_Pass_Label.setStyle("-fx-border-color: #05a40a");
                    }else{
                        confirm_Pass_Label.setStyle("-fx-background-radius: 15px");
                        confirm_Pass_Label.setStyle("-fx-border-color: #a40518");
                    }
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Write your old password correctly.");
                    alert.showAndWait();
                }
            } catch (SQLException ee) {
                ee.printStackTrace();
            }

        }
    }

    public void returnPF(TextField t){
        t.setStyle("-fx-background-color: transparent;\n" +
                "    -fx-background-radius: 15px;\n" +
                "    -fx-border-color: #000000FF;\n" +
                "    -fx-border-radius: 5px 5px 5px 5px;\n" +
                "    -fx-border-width: 0.5px;");
    }

                          //------------------------- rooms -------------------------//

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

    private ObservableList<roomData>roomDataList;

    public void availableRoomsShowData(){
        roomDataList=availableRoomListData();
        availableRooms_col_roomNumbr.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        availableRooms_col_roomType.setCellValueFactory(new  PropertyValueFactory<>("roomType") );
        availableRooms_col_roomStatus.setCellValueFactory(new  PropertyValueFactory<>("status"));
        availableRooms_col_roomPrice.setCellValueFactory(new  PropertyValueFactory<>("price"));

        availableRooms_tableView.setItems(roomDataList);
    }


public void availableRoomsSelectionData(){
roomData roomD =availableRooms_tableView.getSelectionModel().getSelectedItem();
int num = availableRooms_tableView.getSelectionModel().getSelectedIndex();
if ((num-1)<-1){
    return;
}
availableRooms_roomNumber.setText(String.valueOf(roomD.getRoomNumber()));
availableRooms_roomPrice.setText(String.valueOf(roomD.getPrice()));
//availableRooms_roomType.setSelectionModel(String.valueOf(roomD.getRoomType()));
    }

public void availableRoomsSearch(){
        FilteredList<roomData> filter = new FilteredList<>(roomDataList,e->true);
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
        SortedList<roomData>sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(availableRooms_tableView.comparatorProperty());
        availableRooms_tableView.setItems(sortList);
    }


        public void availableRoomsAdd(){

                String sql="INSERT INTO rooms(roomNumber,type,status,price) VALUES (?,?,?,?)";

                connect=DatabaseConnection.getConnection();

        try{
            String roomNumber = availableRooms_roomNumber.getText();
            String type = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String)availableRooms_roomStatus.getSelectionModel().getSelectedItem();
            String price = availableRooms_roomPrice.getText();

            Alert alert;

            if (roomNumber.isEmpty() || type.isEmpty() || status.isEmpty() || price.isEmpty()){
             alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            }else{

                String check = "SELECT roomNumber FROM rooms WHERE roomNumber = '"+roomNumber+"'";
                prepare=connect.prepareStatement(check);
                result=prepare.executeQuery();

                if(result.next()){
              alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Room # "+roomNumber+" was already exist !");
                alert.showAndWait();


            }else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, roomNumber);
                    prepare.setString(2, type);
                    prepare.setString(3, status);
                    prepare.setString(4, price);
                    prepare.executeUpdate();
                    Alert registerAlert = new Alert(Alert.AlertType.INFORMATION);
                    registerAlert.initStyle(StageStyle.UNDECORATED);
                    registerAlert.setHeaderText(null);
                    registerAlert.setContentText("Successfully added !!");
                    registerAlert.showAndWait();

                    availableRoomsShowData();
                    availableRoomClear();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }}

    public void availableRoomsUpdate() throws SQLException {
        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_roomStatus.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_roomPrice.getText();
        String roomNum = availableRooms_roomNumber.getText();
        String check = "SELECT roomNumber FROM rooms WHERE roomNumber = '" + roomNum + "'";

        prepare = connect.prepareStatement(check);
        result = prepare.executeQuery();

        Alert alert;
        if (result.next()) {

            String sql = "UPDATE rooms SET type = '" + type1 + "', status = '" + status1 + "',price ='" + price1 + "'WHERE roomNumber ='" + roomNum + "'";
            connect = DatabaseConnection.getConnection();
            try {

                if (type1 == null || status1 == null || price1 == null || roomNum == null) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select the data first");
                    alert.showAndWait();


                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Update !");
                    alert.showAndWait();

                    availableRoomsShowData();
                    availableRoomClear();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Can not update this room " + roomNum + "");
            alert.showAndWait();

        }
    }
    public void availableRoomDelete() throws SQLException {
        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_roomStatus.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_roomPrice.getText();
        String roomNum = availableRooms_roomNumber.getText();
        String check = "SELECT roomNumber FROM rooms WHERE roomNumber = '" + roomNum + "'";

        prepare = connect.prepareStatement(check);
        result = prepare.executeQuery();

        Alert alert;
        if (result.next()) {
            String deleteData = "DELETE FROM rooms WHERE roomNumber = '" + roomNum + "'";
            connect = DatabaseConnection.getConnection();
            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("are you sure you want to delete Room #" + roomNum + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {


                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted !");
                    alert.showAndWait();

                    availableRoomsShowData();
                    availableRoomClear();


                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else

        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Can not delete room " + roomNum + "");
            alert.showAndWait();

        }
    }



    public void availableRoomClear(){
        availableRooms_roomNumber.setText("");
        availableRooms_roomStatus.getSelectionModel().clearSelection();
    availableRooms_roomType.getSelectionModel().clearSelection();
    availableRooms_roomPrice.setText("");
    }


 private String[] type ={"Single Room","Double Room" ,"Quad Room","Suite"};

     public void availableRoomsRoomType() {
            List<String> listdata =new ArrayList<>();

            for (String data :type){
                listdata.add(data);
            }
         ObservableList list = FXCollections.observableArrayList(listdata);
availableRooms_roomType.setItems(list);
        }

    private String[] status ={"Available","Not Available" ,"Occupied"};

    public void availableRoomsStatus() {
        List<String> listdata =new ArrayList<>();

        for (String data :status){
            listdata.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listdata);
        availableRooms_roomStatus.setItems(list);
    }
    //--------------------------------------------------------------------------------------//

                     //------------------------- reservition  -------------------------//

    public ObservableList<customerData>customerListData() {
        ObservableList<customerData> listDatacust = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer";
        connect = DatabaseConnection.getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            customerData custD;

            while (result.next()) {
                custD = new customerData(result.getInt("idcustomer"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                        result.getDouble("total"),
                        result.getDate("checkin"),
                        result.getDate("checkout"));
                listDatacust.add(custD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return listDatacust;
    }

    private ObservableList<customerData>listCustomerData;
    public void customerShowData(){
        listCustomerData=customerListData();
        reservition_customerNumber.setCellValueFactory(new PropertyValueFactory<>("idcustomer"));
        reservition_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        reservition_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        reservition_customerPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservition_totalPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
        reservition_checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        reservition_checkedOut.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        reservition_tableView.setItems(listCustomerData);

    }

    public void availablecustomerSearch(){
        FilteredList<customerData> filter = new FilteredList<>(listCustomerData,e->true);
        reservition_search.textProperty().addListener((Observable,oldValue,newValue )->{
            filter.setPredicate(predicatecustomerData->{
                if(newValue==null&&newValue.isEmpty()){
                    return  true;
                }
                String searchkey=newValue.toLowerCase();

                if (predicatecustomerData.getFirstName().toString().contains(searchkey)){
                    return true;

                }else if (predicatecustomerData.getCheckin().toString().contains(searchkey)) {
                    return true;
                }
                    else if (predicatecustomerData.getCheckout().toString().contains(searchkey)){
                        return true;

                    }else if (predicatecustomerData.getLastName().toString().contains(searchkey)) {
                        return true;

                }else if (predicatecustomerData.getPhoneNumber().toString().contains(searchkey)) {
                    return true;
                }
                else if (predicatecustomerData.getTotal().toString().contains(searchkey)){
                    return true;

                }else if (predicatecustomerData.getIdcustomer().toString().contains(searchkey)) {
                    return true;

                }else return false;

            });  });
        SortedList<customerData>sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(reservition_tableView.comparatorProperty());
        reservition_tableView.setItems(sortList);
    }

//---------------------------------------------------------------------------------------------------

    // -------------------------------------------employee--------------------------------------------
    employeeData roomD;

    public void employeesUpdate(ActionEvent event) throws IOException {
        roomD = employee_tableView.getSelectionModel().getSelectedItem();
        if (roomD == null) {
            // No row selected, return or show an error message
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();

        RegisterController registerController = loader.getController();
        registerController.setEmployeeData(roomD); // Pass the selected data to RegisterController
        registerController.setParentController(this);

        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle("Update Employee");
        sstage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);
        sstage.show();
        availableemployeeShowData();
    }

    public void availableemployeesSelectionData(){
         roomD =employee_tableView.getSelectionModel().getSelectedItem();
        int num = employee_tableView.getSelectionModel().getSelectedIndex();
        if ((num-1)<-1){
            return;
        }

//availableRooms_roomType.setSelectionModel(String.valueOf(roomD.getRoomType()));
    }
    public void employeesAdd(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();

        RegisterController registerController = loader.getController();
        registerController.setParentController(this);

        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle("Add Employee");
        sstage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);
        sstage.show();
        availableemployeeShowData();
    }
    public void  Delete_btnOnAcion (ActionEvent event) {

            availableemployeesSelectionData();
            String e_username = roomD.getUsername();
            //System.out.println(e_username);
            String deleteData = "DELETE FROM user_account WHERE username = '" + e_username + "'";
            connect = DatabaseConnection.getConnection();
            Alert alert;
            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("are you sure you want to delete this employee ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {


                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted !");
                    alert.showAndWait();

                    availableemployeeShowData();


                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public ObservableList<employeeData>employeeDataObservableList() {
        ObservableList<employeeData> listdata = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user_account WHERE account_id != 1";

        connect = DatabaseConnection.getConnection();
        try {
            employeeData employeeD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                employeeD = new employeeData(
                        result.getInt("account_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                        result.getString("gender"),
                        result.getString("nationality"),
                        result.getString("username"),
                        result.getString("emailaddress"));
                        listdata.add(employeeD);
            }
        } catch (SQLException e) {
            e.printStackTrace();}
        return listdata;}


    private ObservableList<employeeData>employeeDataList;

    public void availableemployeeShowData(){
        employeeDataList=employeeDataObservableList();
        employee_email.setCellValueFactory(new PropertyValueFactory<>("emailaddress"));
        employee_gender.setCellValueFactory(new  PropertyValueFactory<>("gender") );
        employee_firstName.setCellValueFactory(new  PropertyValueFactory<>("firstname"));
        employee_Number.setCellValueFactory(new  PropertyValueFactory<>("account_id"));
        employee_lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        employee_Phone.setCellValueFactory(new  PropertyValueFactory<>("phonenumber") );
        employee_username.setCellValueFactory(new  PropertyValueFactory<>("username"));
        employee_tableView.setItems(employeeDataList);


    }


public void availableemployeesSearch(){
        FilteredList<employeeData> filter = new FilteredList<>(employeeDataList,e->true);
    employee_search.textProperty().addListener((Observable,oldValue,newValue )->{
            filter.setPredicate(predicateemployeeData->{
                if(newValue==null&&newValue.isEmpty()){
                    return  true;
                }
                String searchkey=newValue.toLowerCase();

                if (predicateemployeeData.getAccount_id().toString().contains(searchkey)){
                    return true;

                }else if (predicateemployeeData.getEmailaddress().toString().contains(searchkey)) {
                    return true;
                }else if (predicateemployeeData.getFirstname().toString().contains(searchkey)) {
                    return true;
                }else if (predicateemployeeData.getGender().toString().contains(searchkey)) {
                    return true;
                }else if (predicateemployeeData.getLastname().toString().contains(searchkey)) {
                    return true;
                }else if (predicateemployeeData.getPhonenumber().toString().contains(searchkey)) {
                    return true;
                }else if (predicateemployeeData.getUsername().toString().contains(searchkey)) {
                    return true;

                }else return false;

            });  });
        SortedList<employeeData>sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(employee_tableView.comparatorProperty());
    employee_tableView.setItems(sortList);
    }


    //---------------------------------------------------------------------------------------------


//-----------------------------------dashboard-------------------------
private int count =0;
   public void dashboard() {
       Date date = new Date();
       java.sql.Date sqldate = new java.sql.Date(date.getTime());
       String sql = "SELECT COUNT(id) FROM payment WHERE datee = '" + sqldate + "'";
       connect = DatabaseConnection.getConnection();
     count =0 ;
       try {

           prepare = connect.prepareStatement(sql);
           result = prepare.executeQuery();

           while (result.next()) {
            count=result.getInt("COUNT(id)");

           }

       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

   public void displaydashboard(){
       dashboard();
       dashboard_bookToday.setText(String.valueOf(count));
   }

   private double sumtoday =0;
    public void dashboardincometoday() {
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(currentpayment) FROM payment WHERE datee = '" + sqldate + "'";
        connect = DatabaseConnection.getConnection();
        sumtoday=0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                sumtoday=result.getDouble("SUM(currentpayment)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaydashboardincometoday(){
        dashboardincometoday();
        dashboard_incomeToday.setText("EGP"+String.valueOf(sumtoday));
    }

    private double sumtotal =0;
    public void dashboardincometotal() {
        Date date = new Date();
      //  java.sql.Date sqldate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(currentpayment) FROM payment ";
        connect = DatabaseConnection.getConnection();
        sumtoday=0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                sumtotal=result.getDouble("SUM(currentpayment)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaydashboardincometotal(){
        dashboardincometotal();
        dashboard_totalIncome.setText("EGP"+String.valueOf(sumtotal));
    }

    public void Dashboard_areaChart(){

        dashboard_areaChart.getData().clear();;
        //String sql = "SELECT datee,currentpayment FROM payment GROUP BY datee ORDER BY TIMESTAMP (datee) ASC LIMIT 8 ";
        String sql = "SELECT datee,currentpayment FROM payment ORDER BY TIMESTAMP (datee) ASC LIMIT 8 ";
        connect = DatabaseConnection.getConnection();
        XYChart.Series CHART =new XYChart.Series();
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                CHART.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
            }
            dashboard_areaChart.getData().add(CHART);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }




       private double x=0;
    private double y=0;
    public void logout() {
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


public void switchForm(ActionEvent event){
    if(event.getSource()==dashboard_btn){
        changepassword_form.setVisible(false);
        dashboard_form.setVisible(true);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(false);
        displaydashboard();
        displaydashboardincometoday();
        displaydashboardincometotal();
        Dashboard_areaChart();

    }else if(event.getSource()==rooms_btn){
        changepassword_form.setVisible(false);
        dashboard_form.setVisible(false);
        rooms_form.setVisible(true);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(false);
        availableRoomsSearch();
        availableRoomsShowData();

    }else if(event.getSource()==reservition_report_btn){
        changepassword_form.setVisible(false);
        dashboard_form.setVisible(false);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(true);
        employees_form.setVisible(false);
        availablecustomerSearch();
        customerShowData();

    }else if(event.getSource()==employees_btn){
        changepassword_form.setVisible(false);
        dashboard_form.setVisible(false);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(true);
        availableemployeesSearch();
        availableemployeeShowData();


    }
}
public void changePass (MouseEvent event){
    if(event.getSource()==username){
        changepassword_form.setVisible(true);
        dashboard_form.setVisible(false);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(false);
    }
}


public  void close(){
        System.exit(0);
}

public void minimize(){
    Stage stage=(Stage)rooms_form.getScene().getWindow();
    stage.setIconified(true);
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaydashboard();
        availableRoomsRoomType();
        availableRoomsStatus();
        availableRoomsShowData();
        customerShowData();
        //availableemployeesSearch();
        availablecustomerSearch();
        availableemployeeShowData();
        displaydashboardincometoday();
        displaydashboardincometotal();
        Dashboard_areaChart();

    }
}