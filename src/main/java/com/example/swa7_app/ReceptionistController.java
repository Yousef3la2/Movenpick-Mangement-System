package com.example.swa7_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;

public class ReceptionistController {

    @FXML
    private Button Add_btn;

    @FXML
    private TextField CardNumberInp;

    @FXML
    private Button Delete_btn;

    @FXML
    private Button Update_btn;

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
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private TextField availableRooms_search;

    @FXML
    private TableView<?> availableRooms_tableView;

    @FXML
    private Button availableRooms_updateBtn;

    @FXML
    private Button checkin_btn;

    @FXML
    private Button checkout_btn;

    @FXML
    private Button close_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private Button guests_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button minimize_btn;

    @FXML
    private AnchorPane reservitionReport_form;

    @FXML
    private TableColumn<?, ?> reservition_checkedIn;

    @FXML
    private TableColumn<?, ?> reservition_checkedIn1;

    @FXML
    private TableColumn<?, ?> reservition_checkedOut;

    @FXML
    private TableColumn<?, ?> reservition_checkedOut1;

    @FXML
    private TableColumn<?, ?> reservition_customerNumber;

    @FXML
    private TableColumn<?, ?> reservition_customerNumber1;

    @FXML
    private TableColumn<?, ?> reservition_customerPhone;

    @FXML
    private TableColumn<?, ?> reservition_customerPhone1;

    @FXML
    private TableColumn<?, ?> reservition_firstName;

    @FXML
    private TableColumn<?, ?> reservition_firstName1;

    @FXML
    private TableColumn<?, ?> reservition_lastName;

    @FXML
    private TableColumn<?, ?> reservition_lastName1;

    @FXML
    private TextField reservition_search;

    @FXML
    private TextField reservition_search1;

    @FXML
    private TableView<?> reservition_tableView;

    @FXML
    private TableView<?> reservition_tableView1;

    @FXML
    private TableColumn<?, ?> reservition_totalPayment;

    @FXML
    private TableColumn<?, ?> reservition_totalPayment1;

    @FXML
    private Button rooms_btn;

    @FXML
    private AnchorPane rooms_form;

    @FXML
    private Button services_btn;

    @FXML
    private Label username;

    @FXML
    void availableRoomClear(ActionEvent event) {

    }

    @FXML
    void availableRoomDelete(ActionEvent event) {

    }

    @FXML
    void availableRoomsAdd(ActionEvent event) {

    }

    @FXML
    void availableRoomsSearch(MouseEvent event) {

    }

    @FXML
    void availableRoomsSelectionData(MouseEvent event) {

    }

    @FXML
    void availableRoomsUpdate(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void employeesAdd(ActionEvent event) {

    }

    @FXML
    private TextField creditCardField;

    public void initialize() {
        
    }


    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void switchForm(ActionEvent event) {

    }

}
