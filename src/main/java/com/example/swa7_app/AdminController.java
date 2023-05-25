package com.example.swa7_app;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {


    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private Button availableRooms_addBtn;

    @FXML
    private Button availableRooms_clearBtn;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomNumbr;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomPrice;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomStatus;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomType;

    @FXML
    private Button availableRooms_deleteBtn,employees_btn;

    @FXML
    private TextField availableRooms_roomNumber;

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
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane employees_form;

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
            e.printStackTrace();
        }
        return listdata;

    }

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
//availableRooms_roomType.setSelectionModel();
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
       /* DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();*/

                String sql="INSERT INTO rooms(roomNumber,type,status,price) VALUES (?,?,?,?)";

                connect=DatabaseConnection.getConnection();

     /*   String insertFields = "INSERT INTO rooms(roomNumber,type,status,price) VALUES ('";
        String insertValues = roomNumber + "','" + type + "','" + status + "','" + price+ "')";
        String insertToRegister = insertFields + insertValues ;
*/
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

               /* Statement statement = connection.createStatement();
                statement.executeUpdate(insertToRegister);*/
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


 private String[] type ={"Single Room","Double Room" ,"Triple Room","Quad Room","Ring Room"};

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

/*

    public ObservableList<customerData>customerListData() {
        ObservableList<customerData> listData = FXCollections.observableArrayList();
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
                        result.getString("email"),
                        result.getDate("'checkin"),
                        result.getDate("'checkout"));
                listData.add(custD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }return listData;
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
*/


public void switchForm(ActionEvent event){
    if(event.getSource()==dashboard_btn){
        dashboard_form.setVisible(true);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(false);

    }else if(event.getSource()==rooms_btn){
        dashboard_form.setVisible(false);
        rooms_form.setVisible(true);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(false);
        availableRoomsSearch();
        availableRoomsShowData();

    }else if(event.getSource()==reservition_report_btn){
        dashboard_form.setVisible(false);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(true);
        employees_form.setVisible(false);

    }else if(event.getSource()==employees_btn){
        dashboard_form.setVisible(false);
        rooms_form.setVisible(false);
        reservitionReport_form.setVisible(false);
        employees_form.setVisible(true);

    }else if(event.getSource()==service_report_btn){

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

        availableRoomsRoomType();
        availableRoomsStatus();
        availableRoomsShowData();

//        customerShowData();
    }
}
