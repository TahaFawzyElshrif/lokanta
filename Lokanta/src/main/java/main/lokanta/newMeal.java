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
import javafx.stage.Stage;
import objects.branch;
import objects.meal;
import objects.restaurant;

public class newMeal extends servicesINT implements java.io.Serializable, Cloneable {

    restaurant rest;
    branch branch;
    protected TextField name, code;

    newMeal() {
    }

    public newMeal(restaurant rest, branch branch) {
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
        name = new TextField("Foods seperatedby comma");
        code = new TextField("Price");
        Button add = new Button("Add and login");
        add.setOnAction(new addMeal(this, rest, branch));
        VBox pane = new VBox();
        pane.getChildren().addAll(name, code, add);
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

class addMeal implements EventHandler<ActionEvent> {

    newMeal from;
    restaurant rest;
    branch branch;
    meal meal;

    public addMeal(newMeal from, restaurant rest, branch branch) {
        this.from = from;
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            meal = new meal(from.name.getText().split(","), Double.parseDouble(from.code.getText()));
            branch.meals.add(meal);
            addCSV();
            addRestObject();
            addMeal();
       //     servicesINT.refreshObject(branch, "C:\\Lokanta\\restaurant\\"+rest.name+"\\branches\\"+branch.name+"\\Files\\RestInfo.dat");
            System.out.print("Successfully added");
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    void addCSV() {
        try{
           File mealsCsv=new File("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\Meals\\meals.csv"); 
           StringBuffer mealsCsvData=new StringBuffer();
           try(Scanner mealsCsvScanner=new Scanner(mealsCsv);){
           while(mealsCsvScanner.hasNext()){
           mealsCsvData.append(mealsCsvScanner.nextLine()+"\n");
           }
           mealsCsvScanner.close();
           } catch (Exception ex) {
           }
           PrintWriter mealsCsvWrite=new PrintWriter(mealsCsv);
           mealsCsvWrite.print(mealsCsvData);
           mealsCsvWrite.flush();
           mealsCsvWrite.println(from.name.getText()+","+meal.getTotalPrice());
           mealsCsvWrite.flush();
        } catch (Exception ex) {
           System.out.print(ex);

        }
    }
    void addMeal() throws FileNotFoundException, IOException{
            ObjectOutputStream BranchReWrt;
            BranchReWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\Files\\RestInfo.dat"));
            BranchReWrt.reset();
            BranchReWrt.writeObject(branch);
            
    }
    void addRestObject() {
       //restaurnt doesn't have the meeeeeeeeeels
    }

   
}
