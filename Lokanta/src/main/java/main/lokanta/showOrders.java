package main.lokanta;

import interfaces.servicesINT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class showOrders extends servicesINT implements java.io.Serializable, Cloneable {
    public showOrders(){}
    public showOrders(restaurant rest,branch branch){this.rest=rest;this.branch=branch;}
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
        //undisplaying menu button because it will affect later as the difference  of interfaces 
         menu.setVisible(false);

     VBox paneInscroll=new VBox();
 
      for(int i=0;i<branch.orders.size();i++){
          HBox orderPane=new HBox();
          order currentOrder=branch.orders.get(i);
          
          
          //content 
          Label oneOrderInfo=new Label(currentOrder.name+"\t\t\t\tPrice: "+currentOrder.price+"\t\tat"+currentOrder.date);
          orderPane.getChildren().add(oneOrderInfo);
          
          //remove button :
          Button finished=new Button("Finished");
          finished.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent t) {
                  branch.orders.remove(currentOrder);
                  ((Button)(t.getSource())).setText("finished");
                  ((Button)(t.getSource())).setDisable(true);
                  servicesINT.refreshObject(branch, "C:\\Lokanta\\restaurant\\"+rest.name+"\\branches\\"+branch.name+"\\Files\\RestInfo.dat");
              }
          
          });
          orderPane.getChildren().add(finished);
          
          
          
          //background:
          BackgroundFill background_fill = new BackgroundFill(Color.BLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
          Background background0 = new Background(background_fill);
          orderPane.setBackground(background0);
          paneInscroll.getChildren().add(orderPane);
      }

      
      
      
      //adding to scroll pane
      ScrollPane scroll = new ScrollPane();
      scroll.setPrefSize(595, 200);
      scroll.setContent(paneInscroll);
      back.setCenter(scroll);
    }

}
