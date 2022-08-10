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
import objects.offer;
import objects.restaurant;

public class offerShow extends servicesINT implements java.io.Serializable, Cloneable {

    restaurant rest;
    branch branch;
    offer offer;

    offerShow() {
    }

  
     public offerShow(restaurant rest, branch branch, offer offer) {
        this.rest = rest;
        this.branch = branch;
        this.offer = offer;
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
            BorderPane offerCenterPane = new BorderPane();
            //photo
            Rectangle photo = new Rectangle(500, 500);
            FileInputStream file = new FileInputStream(offer.photoPath);
            Image image = new Image(file);
            photo.setFill(new ImagePattern(image));
            offerCenterPane.setCenter(photo);

            //left
            Label atLeft = new Label(offer.name + "\nfrom" + offer.price + "\n TO " + offer.totalPrice + "\n discount" + offer.discount);
            atLeft.setTextFill(Color.WHITESMOKE);
            offerCenterPane.setLeft(atLeft);

            //bottom
            Label atBottom = new Label(offer.numberOfDays + "day\n" + offer.otherInfo);
            atBottom.setTextFill(Color.WHITESMOKE);
            offerCenterPane.setBottom(atBottom);

         
          
            VBox pane = new VBox();
            pane.getChildren().addAll(offerCenterPane);
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
        stage.setTitle(branch.name + "new Meal");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

}
