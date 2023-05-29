package com.example.swa7_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OfferPopController {
    @FXML
    private Button closeButton;

    @FXML
    private Label labeltext1;

    @FXML
    private Label labeltext2;

    @FXML
    private Label labeltext3;

    @FXML
    public void CloseOnAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
