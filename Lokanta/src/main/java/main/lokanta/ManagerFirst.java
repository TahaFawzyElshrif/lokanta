package main.lokanta;

import interfaces.servicesINT;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.branch;
import objects.restaurant;

public class ManagerFirst extends servicesINT implements java.io.Serializable, Cloneable {
    ManagerFirst(){}
    ManagerFirst(restaurant rest,branch branch){this.rest=rest;this.branch=branch;}
    public static void main(String[] args)  {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }

    }

    public void start(Stage stage) throws Exception {
        center();
        body();
        stage = super.stage;
        stage.setTitle(branch.branchManagerName+" Good Night Chielf");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void center() {

    }

}
