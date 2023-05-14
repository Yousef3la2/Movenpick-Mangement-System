package com.example.swa7_app;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {
    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private Button availableRooms_addBtn;

    @FXML
    private Button availableRooms_clearBtn;

    @FXML
    private TableColumn<?, ?> availableRooms_col_roomNumbr;

    @FXML
    private TableColumn<?, ?> availableRooms_col_roomPrice;

    @FXML
    private TableColumn<?, ?> availableRooms_col_roomStatus;

    @FXML
    private TableColumn<?, ?> availableRooms_col_roomType;

    @FXML
    private Button availableRooms_deleteBtn;

    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private TextField availableRooms_roomPrice;

    @FXML
    private ComboBox<?> availableRooms_roomStatus;

    @FXML
    private TextField availableRooms_search;

    @FXML
    private TableView<?> availableRooms_tableView;

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
    private TableColumn<?, ?> reservition_checkedIn;

    @FXML
    private TableColumn<?, ?> reservition_checkedOut;

    @FXML
    private TableColumn<?, ?> reservition_customerNumber;

    @FXML
    private TableColumn<?, ?> reservition_customerPhone;

    @FXML
    private TableColumn<?, ?> reservition_firstName;

    @FXML
    private TableColumn<?, ?> reservition_lastName;

    @FXML
    private Button reservition_report_btn;

    @FXML
    private TextField reservition_search;

    @FXML
    private TableView<?> reservition_tableView;

    @FXML
    private TableColumn<?, ?> reservition_totalPayment;

    @FXML
    private Button rooms_btn;

    @FXML
    private AnchorPane rooms_form;

    @FXML
    private Button service_report_btn;

    @FXML
    private Label username;

    public void availableRoomsAdd(){
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();

        String roomNumber = availableRooms_roomNumber.getText();
        String type = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status = (String)availableRooms_roomStatus.getSelectionModel().getSelectedItem();
        String price = availableRooms_roomPrice.getText();

        String insertFields = "INSERT INTO rooms(roomNumber,type,status,price) VALUES ('";
        String insertValues = roomNumber + "','" + type + "','" + status + "','" + price+ "')";
        String insertToRegister = insertFields + insertValues ;

        try{

            if (roomNumber.isEmpty() || type.isEmpty() || status.isEmpty() || price.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            }else{
                Statement statement = connection.createStatement();
                statement.executeUpdate(insertToRegister);
                Alert registerAlert = new Alert(Alert.AlertType.INFORMATION);
                registerAlert.initStyle(StageStyle.UNDECORATED);
                registerAlert.setHeaderText(null);
                registerAlert.setContentText("Successfully added !!");
                registerAlert.showAndWait();
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
