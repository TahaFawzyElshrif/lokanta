package main.lokanta;

import interfaces.servicesINT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.branch;
import objects.meal;
import objects.offer;
import objects.restaurant;

public class newOffer extends servicesINT implements java.io.Serializable, Cloneable {

    restaurant rest;
    branch branch;
    protected TextField name, price,discount,numberofdays,otherInfo;
    protected String photoPath;
    newOffer() {
    }

    public newOffer(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    public static void main(String[] args) {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }
    }

    @Override
    public void center() {
        name = new TextField("name");
        price = new TextField("Price");
        discount = new TextField("discount");
        numberofdays = new TextField("numberOfDays");
        otherInfo = new TextField("OtherInfo");
        Button PhotoPathB = new Button("Insert photo (very preferred)");
        PhotoPathB.setOnAction( new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent e)
            {
 
                FileChooser fil_chooser = new FileChooser();
                fil_chooser.setTitle("choose a photo");
                File file = fil_chooser.showOpenDialog(stage);
                if (file != null) {
                    photoPath=(file.getAbsolutePath());
                    System.out.println(photoPath);
                }
            }});
        
        Button add = new Button("Add and login");
        add.setOnAction(new addOffer(this, rest, branch));
        VBox pane = new VBox();
        pane.getChildren().addAll(name, price,discount,numberofdays,otherInfo, PhotoPathB, add);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);

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

class addOffer implements EventHandler<ActionEvent> {

    newOffer from;
    restaurant rest;
    branch branch;
    offer offer;

    public addOffer(newOffer from, restaurant rest, branch branch) {
        this.from = from;
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            offer = new offer(from.name.getText(), Double.parseDouble(from.price.getText()),Double.parseDouble(from.discount.getText()),Integer.parseInt(from.numberofdays.getText()),from.otherInfo.getText(),from.photoPath);
            addCSV();
            addRestObject();
            addBranchObject();
            offerShow go=new offerShow(rest,branch,offer);
            go.start(new Stage());
            System.out.print("Successfully added");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    void addCSV() {
        try{
           File mealsCsv=new File("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\Offers\\offers.csv"); 
           StringBuffer offersCsvData=new StringBuffer();
           try(Scanner offersCsvScanner=new Scanner(mealsCsv);){
           while(offersCsvScanner.hasNext()){
           offersCsvData.append(offersCsvScanner.nextLine());
           }
           offersCsvScanner.close();
           } catch (Exception ex) {
           }
           PrintWriter offersCsvWrite=new PrintWriter(mealsCsv);
           offersCsvWrite.print(offersCsvData);
           offersCsvWrite.flush();
           offersCsvWrite.print("\n"+offer.name+","+offer.price+","+offer.discount+","+offer.numberOfDays);
           offersCsvWrite.flush();
        } catch (Exception ex) {
           System.out.print(ex);

        }
    }

    void addRestObject() {
       //restaurnt doesn't have the meeeeeeeeeels
    }

    void addBranchObject() {
        try {
            branch.offers.add(offer);
            ObjectOutputStream BranchReWrt;
            BranchReWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\restaurant\\"+rest.name+"\\branches\\"+branch.name+"\\Files\\RestInfo.dat"));
            BranchReWrt.writeObject(branch);
        } catch (Exception ex) {
            System.out.print(ex);

        }
    }

}
