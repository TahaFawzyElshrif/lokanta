package interfaces;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
import main.lokanta.showMeals;
import main.lokanta.showOrders;
import objects.branch;
import objects.restaurant;

public  abstract class servicesINT extends Application implements java.io.Serializable, Cloneable {
    public restaurant rest=new restaurant("restaurant",1000000000);
    protected Button menu = new Button();
    protected BorderPane back = new BorderPane();
    protected Stage stage = new Stage();
    protected Label photoPlaceHolder = new Label();
    protected menuClicked event=new menuClicked(back);
    public branch branch=new branch("branch",1000000000);
    private void menuButton() throws Exception {

        FileInputStream file = new FileInputStream("C:\\Lokanta\\App_Files\\photo\\menu.png");
        Image image = new Image(file);
        BackgroundImage BackgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background Background = new Background(BackgroundImage);
        menu.setBackground(Background);
        menu.setOnAction(event);
        back.setRight(menu);
    }

    private void backPane() throws Exception {

        FileInputStream file2 = new FileInputStream("C:\\Lokanta\\App_Files\\photo\\back.gif");

        Image image2 = new Image(file2);

        BackgroundImage backgroundimage2 = new BackgroundImage(image2,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundimage2);

        back.setBackground(background2);
    }

    public void body() throws Exception {

        //invoking methods 
        try {
            mainMenuThings();
            menuButton();
            backPane();
        } catch (Exception exception) {
        }

        //photo centered
        FileInputStream file3 = new FileInputStream("C:\\Lokanta\\App_Files\\photo\\1.png");
        Image image3 = new Image(file3);
        BackgroundImage BackgroundImage3 = new BackgroundImage(image3,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background Background3 = new Background(BackgroundImage3);
        photoPlaceHolder.setBackground(Background3);
        back.setBottom(photoPlaceHolder);

        stage.setMaxHeight(600);
        stage.setMaxWidth(700);
      
    }
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
        add(new Label("\n"+rest.name+"\n"+branch.name));
        add(new Label("Services :--------------------"));
        Label meals=new Label("Meals");
        meals.setOnMouseClicked(new crrntMeals(rest,branch));
        add(meals);
        add(new Label("new Services :----------------"));
        Label newMeal=new Label("Meals");
        newMeal.setOnMouseClicked(new meal(rest,branch));
        add(newMeal);
        Label offer=new Label("Offers");
        offer.setOnMouseClicked(new offer(rest,branch));
        add(offer);
        Label Files=new Label("Files(soon)");
        add(Files);
        add(new Label("Orders :--------------------"));
        Label orders=new Label("Orders ");
        orders.setOnMouseClicked(new orders(rest,branch));
        add(orders);
        add(new Label("Data Anlysics :-------------"));
        Label mostOrdered=new Label("most Ordered ");
        mostOrdered.setOnMouseClicked(new Mining2(rest,branch));
        add(mostOrdered);
        Label relation=new Label("relation ");
        relation.setOnMouseClicked(new Mining1(rest,branch));
        add(relation);
        add(new Label("exit :--------------------"));
        Label exit=new Label("Exit ");
        exit.setOnMouseClicked(new exitINT());
        add(exit);

    }
public void add(Node l){
    event.add(l);
}
    public boolean show() {
        try {
            String []args=new String[]{"a"};
start(stage);
        return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public abstract void center();
    public static void refreshObject(Object object,String path){
         try {
            ObjectOutputStream ReWrt= new ObjectOutputStream(new FileOutputStream(path));
            ReWrt.writeObject(object);
        } catch (Exception ex) {
            System.out.print(ex);

        }
    }
}
//menu clicking

class menuClicked implements EventHandler<ActionEvent> {

    private BorderPane back;
    private VBox menu = new VBox();

    public menuClicked(BorderPane back) {
        this.back = back;
    }

    @Override
    public void handle(ActionEvent t) {
        BackgroundFill background_fill = new BackgroundFill(Color.PINK,
                CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background0 = new Background(background_fill);

        // set background
        menu.setBackground(background0);
    
        if (back.getLeft()==menu) {
            Line m = new Line();
            m.setStartX(100);
            m.setStartY(277);
            m.setEndX(-250);
            m.setEndY(-10);
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(1000));
            pathTransition.setNode(menu);
            pathTransition.setPath(m);
            pathTransition.play();
            back.setLeft(null);


        } else {
            Line m = new Line();
            m.setStartX(-250);
            m.setStartY(-10);
            m.setEndX(100);
            m.setEndY(277);
            back.setLeft(menu);
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(1000));
            pathTransition.setNode(menu);
            pathTransition.setPath(m);
            pathTransition.play();

        }

    }
    void add(Node l){
    menu.getChildren().add(l);
    }
    
}
class meal implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public meal(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        System.out.print("new meal");
        newMeal go=new newMeal(rest,branch);
        go.start(new Stage());

    } catch (Exception ex) {
    }
    }
    
}
class offer implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public offer(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        System.out.print("new offer");
        newOffer go=new newOffer(rest,branch);
        go.start(new Stage());

    } catch (Exception ex) {
    }
    }
    
}
class crrntMeals implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public crrntMeals(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        showMeals go=new showMeals(rest,branch);
        go.start(new Stage());

    } catch (Exception ex) {
    }
    }
    
}
class orders implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public orders(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        showOrders go=new showOrders(rest,branch);
        go.start(new Stage());

    } catch (Exception ex) {
    }
    }
    
}
class Mining1 implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public Mining1(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        main.lokanta.Mining1 go=new main.lokanta.Mining1(rest,branch);
        go.start(new Stage());

    } catch (Exception ex) {
                System.out.print(ex);

    }
    }
    
}
class Mining2 implements EventHandler<MouseEvent>{
restaurant rest;
branch branch;

    public Mining2(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    @Override
    public void handle(MouseEvent t) {
    try {
        main.lokanta.MostUsed go=new main.lokanta.MostUsed(rest,branch);
        go.start(new Stage());
 
    } catch (Exception ex) {
        System.out.print(ex);
    }
    }
    
}
class exitINT implements EventHandler<MouseEvent>{

    @Override
    public void handle(MouseEvent t) {
    try {
        System.exit(0);
    } catch (Exception ex) {
        System.out.println(ex);
    }
    }
    
}