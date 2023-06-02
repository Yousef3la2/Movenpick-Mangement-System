package com.example.swa7_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class OfferPopController {
    @FXML
    private Button closeButton;

    @FXML
    private Label labeltext1,labeltext2,labeltext3,vCode,copiedLabel;


    private final String[] voucherCode ={"","HONEYMOON25OFF","DOUB40OFF","SING10OFF" ,"QUAD25OFF"};
    private final String[] voucherCodeOFF ={"","25%","40%" ,"10%","25%"};
    private final String[] voucherCodeRooms ={"","honeymoon suite","double room" ,"single room","quadruple room"};


    String voucherCodeSentence = "Use this coupon code to show it to the receptionist : ";



    public void setText(int num) {
        switch (num){
            case 1:
                labeltext1.setText( voucherCodeSentence );
                vCode.setText(voucherCode[1]);
                labeltext2.setText(voucherCodeOFF[1] + " off for a "+ voucherCodeRooms[1] +" (of your choice, room number)");
                break;
            case 2:
                labeltext1.setText(voucherCodeSentence );
                vCode.setText(voucherCode[2]);
                labeltext2.setText("Book and stay until 15 June 2023");
                break;
            case 3:
                labeltext1.setText(voucherCodeSentence );
                vCode.setText(voucherCode[3]);
                labeltext2.setText(voucherCodeOFF[3] + " off for a "+ voucherCodeRooms[3] +" (of your choice, room number)");

                break;
            case 4 :
                labeltext1.setText(voucherCodeSentence);
                vCode.setText(voucherCode[4]);
                labeltext2.setText(voucherCodeOFF[4] + " off for a "+ voucherCodeRooms[4] +" (of your choice, room number)");

                break;
        }

    }

    public void labelCopy(MouseEvent event){

        String textToCopy = vCode.getText();

        // Copy the label's text to the clipboard
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(textToCopy);
        clipboard.setContent(content);

        copiedLabel.setText("Copied!");
    }
    @FXML
    public void CloseOnAction(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
