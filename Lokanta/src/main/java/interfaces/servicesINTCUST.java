package interfaces;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;
import main.lokanta.newMeal;
import main.lokanta.newOffer;
import main.lokanta.offerShowCustmer;
import main.lokanta.showMeals;
import main.lokanta.showMealsCust;
import objects.branch;
import objects.restaurant;

public  abstract class servicesINTCUST extends servicesINT implements java.io.Serializable, Cloneable {
    public String email;
    public double costs;
    
    public void mainMenuThings() throws Exception{
        //add logo 
        //<editor-fold defaultstate="collapsed" desc="comment">
        /* Label logo=new Label();
        FileInputStream file = new FileInputStream("E:\\Lokanta\\src\\main\\java\\sources\\photo\\logo.png");
        Image image= new Image(file);
        BackgroundImage backgroundimage = new BackgroundImage(image,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        logo.setBackground(background);
        add(logo);*/
//</editor-fold>
        
        //other         
         add(new Label("HELLO \u00343"+email.substring(0,email.indexOf('@'))+"\n"));
        add(new Label(rest.name+" : "+branch.name));
        add(new Label("Services :--------------------"));
        Label meals=new Label("Meals");
        meals.setOnMouseClicked(new crrntMealsCustomer(email,rest,branch));
        add(meals);
        Label offers=new Label("Offers");
        offers.setOnMouseClicked(new offerShowCustmerGO(email,rest,branch));
        add(offers);
            Label Files=new Label("Files(soon)");
        add(Files);
        Label exit=new Label("\n\n\n\nExit");
        exit.setOnMouseClicked(new exit());
        add(exit);
        
        

    }

}

class crrntMealsCustomer implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;
String email;
    public crrntMealsCustomer(String email,restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
        this.email=email;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        showMealsCust go=new showMealsCust(email,rest,branch);
        go.start(new Stage());
    } catch (Exception ex) {
        System.out.println(ex);
    }
    }
    
}

class offerShowCustmerGO implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;
String email;
    public offerShowCustmerGO(String email,restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
        this.email=email;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        offerShowCustmer go=new offerShowCustmer(email,rest,branch);
        go.start(new Stage());
    } catch (Exception ex) {
        System.out.println(ex);
    }
    }
    
}

class exit implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent t) {
    try {
        System.exit(0);

    } catch (Exception ex) {
        System.out.println(ex);
    }
    }
    
}