package main.lokanta;

import interfaces.gates;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.branch;
import objects.restaurant;

public class newBranche1 extends gates implements java.io.Serializable, Cloneable {

    restaurant restaurant;
    private String restName;
    protected TextField name, code,manager;

    newBranche1(restaurant restaurant) {
        this.restaurant = restaurant;
        restName = restaurant.name;
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
        }
    }

    void center() {
        name = new TextField("branch name");
        code = new TextField("Branch code , code to be able to login later , consist of 10 number");
        manager = new TextField("Manager");
        Button add = new Button("Add and login");
        add.setOnAction(new done(this, restaurant));
        VBox pane = new VBox();
        pane.getChildren().addAll(name, code, manager,add);
        back.setCenter(pane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //  folder.setOnAction(new chooseFolder(stage));
        body();
        center();
        stage = super.stage;
        stage.setTitle("new Branche , " + restaurant.name);
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

}

class done implements EventHandler<ActionEvent> {

    String restName;
    newBranche1 from;
    String Name;
    String Code;
    restaurant restaurant;
    private branch newBranche;

    public done(newBranche1 from, restaurant restaurant) {
        this.from = from;

        this.restName = restaurant.name;
        this.restaurant = from.restaurant;
    }

    @Override
    public void handle(ActionEvent t) {
        this.Name = from.name.getText();
        this.Code = from.code.getText();
        try {
            if (!Code.matches("[\\w]{10}")) {
                from.code.setText("Code must be 10 numbers");
            } else {

                newBranche = new branch(Name, Long.parseLong(Code));
                newBranche.branchManagerName=from.manager.getText();
                from.stop();
                createMainFiles();
                handleObject();
                ManagerFirst go = new ManagerFirst(restaurant,newBranche);
                go.start(new Stage());

            }
            Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    void handleObject() throws Exception {
        restaurant.addBranch(newBranche);
        ObjectOutputStream restWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\Files\\RestInfo.dat"));
        restWrt.writeObject(restaurant);
    }

    void createMainFiles() throws Exception {

        File filesFolder = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Files");
        filesFolder.mkdirs();
        File offersFolder = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Offers");
        offersFolder.mkdir();
        File mealsFolder = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Meals");
        mealsFolder.mkdir();
        File mealsCSV = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Meals\\meals.csv");
        mealsCSV.createNewFile();
        File ORDmealsCSV = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Meals\\meals orders.csv");
        ORDmealsCSV.createNewFile();
        PrintWriter ORDmealsCSVWrite=new PrintWriter(ORDmealsCSV);
        ORDmealsCSVWrite.print("email,foods");
        ORDmealsCSVWrite.flush();
        File offersCsv=new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Offers\\offers.csv"); 
        offersCsv.createNewFile();
        File ORDoffersCsv=new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Offers\\offers orders.csv"); 
        ORDoffersCsv.createNewFile();
        PrintWriter ORDoffersCsvWrite=new PrintWriter(ORDoffersCsv);
        ORDoffersCsvWrite.print("email,foods");
        ORDoffersCsvWrite.flush();
        ObjectOutputStream branchWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\" + Name + "\\Files\\RestInfo.dat"));
        branchWrt.writeObject(newBranche);
    }

}
