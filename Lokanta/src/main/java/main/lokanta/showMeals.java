package main.lokanta;

import interfaces.servicesINT;
import java.util.ArrayList;
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

public class showMeals extends servicesINT implements java.io.Serializable, Cloneable {
    protected ArrayList<HBox> mealPane=new ArrayList<>();
    public showMeals(){}
    public showMeals(restaurant rest,branch branch){this.rest=rest;this.branch=branch;}
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
 
      for(int i=0;i<branch.meals.size();i++){
          meal currentMeal=branch.meals.get(i);
          
          
          //content 
          mealPane.add(new HBox());
          Label oneMealInfo=new Label(Arrays.toString(currentMeal.getFoods())+"\t\t\t\tPrice: "+currentMeal.getTotalPrice()+"\t\t");
          mealPane.get(i).getChildren().add(oneMealInfo);
          
          
          //background:
          BackgroundFill background_fill = new BackgroundFill(Color.BLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
          Background background0 = new Background(background_fill);
          mealPane.get(i).setBackground(background0);
          paneInscroll.getChildren().add(mealPane.get(i));
      }

      
      
      
      
      //adding to scroll pane
      ScrollPane scroll = new ScrollPane();
      scroll.setPrefSize(595, 200);
      scroll.setContent(paneInscroll);
      back.setCenter(scroll);
    }

}
