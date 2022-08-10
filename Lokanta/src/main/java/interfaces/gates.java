package interfaces;

import java.io.FileInputStream;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public abstract class gates  extends Application implements java.io.Serializable, Cloneable{
protected BorderPane back = new BorderPane();
    protected Stage stage = new Stage();
    protected Rectangle photoPlaceHolder = new Rectangle(600,90);
    public gates() {
    }
     private void backPane() throws Exception {
        FileInputStream file2 = new FileInputStream("C:\\Lokanta\\App_Files\\photo\\gate.png");
        Image image2 = new Image(file2);
        BackgroundImage backgroundimage2 = new BackgroundImage(image2,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundimage2);
        back.setBackground(background2);
    }
     private void photo() throws Exception{
         FileInputStream file2 = new FileInputStream("C:\\Lokanta\\App_Files\\photo\\1.png");
        Image image2 = new Image(file2);
        BackgroundImage backgroundimage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundimage2);
        photoPlaceHolder.setFill(new ImagePattern(image2));  
        back.setTop(photoPlaceHolder);
        FadeTransition fade = new FadeTransition();  
        fade.setDuration(Duration.millis(6000));  
        fade.setFromValue(10);  
        fade.setToValue(0.1);  
        fade.setCycleCount(Integer.MAX_VALUE);  
        fade.setAutoReverse(true);  
        fade.setNode(photoPlaceHolder);  
        fade.play();  
     }  
    public void body() throws Exception {

        //invoking methods 
        try {
            backPane();
            photo();
            
        } catch (Exception exception) {
        }
        stage.setMaxHeight(600);
        stage.setMaxWidth(700);
      
    }
}
