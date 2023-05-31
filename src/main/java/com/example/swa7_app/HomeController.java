package com.example.swa7_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomeController {
    @FXML
    private ImageView offer1Img,offer2Img,offer3Img,offer4Img;
    @FXML
    private Label offer1label,offer2label,offer3label,offer4label;

    @FXML
    private Button loginButton;

    @FXML
    private Button OffersButton,HomeButton  ;

    @FXML
    private AnchorPane home_page,offers_page;

    @FXML
    private String labeltext1;


    public void loginonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle("login page");
        sstage.initStyle(StageStyle.UNDECORATED);

        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);
        loginButton.getScene().getWindow().hide();
        sstage.show();


    }

    public void switchforms(ActionEvent event){
        if(event.getSource()==OffersButton){
            home_page.setVisible(false);
            offers_page.setVisible(true);
        }else if(event.getSource()==HomeButton){
            offers_page.setVisible(false);
            home_page.setVisible(true);
        }
    }

    public void Offer(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("offerpop.fxml"));
        Parent root = loader.load();

        OfferPopController changeOfferDetaails = loader.getController();

        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle(" Offer Voucher ");
        sstage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);
        sstage.show();

        if(event.getSource()==offer1Img) {
            changeOfferDetaails.setText(1);
        }
        if(event.getSource()==offer1label) {
            changeOfferDetaails.setText(1);
        }
        if(event.getSource()==offer2Img) {
            changeOfferDetaails.setText(2);
        }
        if(event.getSource()==offer2label) {
            changeOfferDetaails.setText(2);
        }
        if(event.getSource()==offer3Img) {
            changeOfferDetaails.setText(3);
        }
        if(event.getSource()==offer3label) {
            changeOfferDetaails.setText(3);
        }
        if(event.getSource()==offer4Img) {
            changeOfferDetaails.setText(4);
        }
        if(event.getSource()==offer4label) {
            changeOfferDetaails.setText(4);
        }


        }
    }

