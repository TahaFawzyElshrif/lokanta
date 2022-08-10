package main.lokanta;

import interfaces.servicesINT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.branch;
import objects.linkedList;
import objects.meal;
import objects.node;
import objects.offer;
import objects.restaurant;

public class offerShowCustmer extends offerShow implements java.io.Serializable, Cloneable {

    restaurant rest;
    branch branch;
    node offer;//current offer
    public Button buy;
    String email;

    offerShowCustmer() {
    }

    public offerShowCustmer(String email, restaurant rest, branch branch) {
        this.email = email;
        this.rest = rest;
        this.branch = branch;
        this.offer = branch.offers.getHead().getNext();
    }

    public static void main(String[] args) {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }
    }

    @Override
    public void center() {
        try {
            menu.setVisible(false);
            BorderPane offerCenterPane = new BorderPane();
            //photo
            Rectangle photo = new Rectangle(500, 500);
            FileInputStream file = new FileInputStream(((offer) offer.getData()).photoPath);
            Image image = new Image(file);
            photo.setFill(new ImagePattern(image));
            offerCenterPane.setCenter(photo);

            //left
            Label atLeft = new Label(((offer) offer.getData()).name + "\nfrom" + ((offer) offer.getData()).price + "\n TO " + ((offer) offer.getData()).totalPrice + "\n discount" + ((offer) offer.getData()).discount);
            atLeft.setTextFill(Color.WHITESMOKE);
            offerCenterPane.setLeft(atLeft);

            //bottom
            Label atBottom = new Label(((offer) offer.getData()).numberOfDays + "day\n" + ((offer) offer.getData()).otherInfo);
            atBottom.setTextFill(Color.WHITESMOKE);
            offerCenterPane.setBottom(atBottom);

            //right
            Button prev = new Button("<");
            prev.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    try {
                        offer = offer.getPrevious();
                        atBottom.setText(((offer) offer.getData()).numberOfDays + "day\n" + ((offer) offer.getData()).otherInfo);
                        atLeft.setText(((offer) offer.getData()).name + "\nfrom" + ((offer) offer.getData()).price + "\n TO " + ((offer) offer.getData()).totalPrice + "\n discount" + ((offer) offer.getData()).discount);
                        FileInputStream fileChngd = new FileInputStream(((offer) offer.getData()).photoPath);
                        Image imageChngd = new Image(fileChngd);
                        photo.setFill(new ImagePattern(imageChngd));
                    } catch (NullPointerException ex) {
                        ((Button) t.getSource()).setDisable(true);
                    } catch (Exception ex) {
                        System.out.print(ex);
                    }
                }
            });
            Button next = new Button(">");
            next.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    try {
                        offer = offer.getNext();
                        atBottom.setText(((offer) offer.getData()).numberOfDays + "day\n" + ((offer) offer.getData()).otherInfo);
                        atLeft.setText(((offer) offer.getData()).name + "\nfrom" + ((offer) offer.getData()).price + "\n TO " + ((offer) offer.getData()).totalPrice + "\n discount" + ((offer) offer.getData()).discount);
                        FileInputStream fileChngd = new FileInputStream(((offer) offer.getData()).photoPath);
                        Image imageChngd = new Image(fileChngd);
                        photo.setFill(new ImagePattern(imageChngd));
                    } catch (NullPointerException ex) {
                        ((Button) t.getSource()).setDisable(true);
                    } catch (Exception ex) {
                        System.out.print(ex);
                    }
                }
            });
            VBox atRight = new VBox();
            atRight.getChildren().addAll(prev, next);
            offerCenterPane.setRight(atRight);

            //other
            buy = new Button("BUY");
            buy.setOnAction(new BUY(this, (offer) offer.getData()));
         

            VBox pane = new VBox();
            pane.getChildren().addAll(offerCenterPane, buy);
            pane.setAlignment(Pos.CENTER);
            back.setCenter(pane);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        center();
        body();
        stage = super.stage;
        stage.setTitle("OFFFERRRSSS !!!!");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

}
