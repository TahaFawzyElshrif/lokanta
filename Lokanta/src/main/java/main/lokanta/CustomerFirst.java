package main.lokanta;

import interfaces.servicesINT;
import interfaces.servicesINTCUST;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import objects.branch;
import objects.restaurant;

public class CustomerFirst extends servicesINTCUST implements java.io.Serializable, Cloneable {


    CustomerFirst() {
    }

    CustomerFirst(restaurant rest, branch branch, String email) {
        this.rest = rest;
        this.branch = branch;
        this.email = email;
    }

    public static void main(String[] args) {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }

    }


    public void start(Stage stage) throws Exception {
        center();
        body();
        stage = super.stage;
        stage.setTitle(branch.branchManagerName + " Good Night Chielf");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void center() {

    }

}
