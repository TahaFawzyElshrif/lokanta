package main.lokanta;

import interfaces.servicesINT;
import interfaces.servicesINTCUST;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.*;

public class showMealsCust extends showMeals implements java.io.Serializable, Cloneable {
    public String email;//this is a main thing to have with customers ,as it used with the path of data
    private VBox paneInscroll=new VBox();
    public showMealsCust(){}
    public showMealsCust(String email,restaurant rest,branch branch){this.rest=rest;this.branch=branch;this.email=email;}
    public static void main(String[] args)  {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }

    }
     
     public void start(Stage stage) throws Exception {
        center();
        body();
        BuyButtons();
        stage = super.stage;
        stage.setTitle(branch.branchManagerName+" Good Night Chielf");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

    
private void BuyButtons(){
          menu.setVisible(false);//as it inherit class that inherits the interface of  managers
          for(int i=0;i<mealPane.size();i++){
          Button buy=new Button("BUY");
          buy.setOnAction(new BUY(this,branch.meals.get(i)));
          mealPane.get(i).getChildren().add(buy);
          }
}

}
