package main.lokanta;

import interfaces.gates;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.*;

public class logInToBranch extends gates implements java.io.Serializable, Cloneable {

    private restaurant rest;
    public TextField code;

    logInToBranch() {

    }

    logInToBranch(restaurant rest) {
        this.rest = rest;
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public void center() {
        code = new TextField("enter Code");
        code.setBackground(new Background(new BackgroundFill(Color.GOLD,
                CornerRadii.EMPTY, Insets.EMPTY)));
        HBox Buttons = new HBox();
        Button login = new Button("LOGIN");
        Button newB = new Button("NEW Branch");
        newB.setOnAction(new newB(this, rest));
        login.setOnAction(new LogInToBr(this, rest));
        Buttons.getChildren().addAll(login, newB);
        Buttons.setAlignment(Pos.CENTER);
        VBox pane = new VBox();
        pane.getChildren().addAll(code, Buttons);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);
    }

    public void start(Stage stage) throws Exception {

        center();
        body();
        stage = super.stage;
        stage.setTitle(rest.name + "Good Night Chielf");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

}

class newB implements EventHandler<ActionEvent> {

    logInToBranch from;
    restaurant rest;

    public newB(logInToBranch from, restaurant rest) {
        this.from = from;
        this.rest = rest;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            newBranche1 go = new newBranche1(rest);
            go.start(new Stage());
            Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}

class LogInToBr implements EventHandler<ActionEvent> {

    restaurant rest;
    logInToBranch from;
    branch branch, specificbranch;

    //specific branch has the data of meals ,offer ...rather than data tokenfrom restaurant object
    public LogInToBr(logInToBranch from, restaurant rest) {
        this.from = from;
        this.rest = rest;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            String code = from.code.getText();
            branch = get(code);
            if (branch != null) {
                System.out.print("found");
                ManagerFirst go = new ManagerFirst(rest, getSpecificBranch());
                go.start(new Stage());
            } else {
                from.code.setText("retype branch code");
            }
            Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    private branch getSpecificBranch() {

        try {
            ObjectInputStream  open = new ObjectInputStream(new FileInputStream("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\Files\\RestInfo.dat"));
            specificbranch=(branch)open.readObject();
        } catch (Exception ex) {

        }
        return specificbranch;
    }

    private branch get(String code) {
        node tmp = rest.getBranches().getTail().getPrevious();
        while (tmp != null) {
            if (code.equals("" + ((branch) tmp.getData()).code)) {
                break;
            }
            tmp = tmp.getPrevious();
        }

        return (branch) tmp.getData();
    }
}
