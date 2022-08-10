
package main.lokanta;

import interfaces.gates;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class logInToRestManager extends gates implements java.io.Serializable, Cloneable{
    public TextField code;
    public static void main(String[] args) throws Exception{
        launch();
    }
    private void center(){
         code=new TextField("enter Code");
        code.setBackground(new Background(new BackgroundFill(Color.GOLD,
                CornerRadii.EMPTY, Insets.EMPTY)));
        HBox Buttons=new HBox();
        Button login=new Button("LOGIN");
        Button newR=new Button("NEW RESTAURANT");
        newR.setOnAction(new newR(this));
        login.setOnAction(new LogIn(this));
        Buttons.getChildren().addAll(login,newR);
        Buttons.setAlignment(Pos.CENTER);
        VBox pane=new VBox();
        pane.getChildren().addAll(code,Buttons);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);
    }
    @Override
    public void start(Stage stage) throws Exception {
        body(); 
        center();
        stage=super.stage;
        stage.setTitle("good morning  Chielf  \u10463 ");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }
}
class newR implements EventHandler<ActionEvent> {

logInToRestManager from;
    public newR(logInToRestManager from) {
        this.from = from;
    }

    @Override
    public void handle(ActionEvent t) {
    try {
        from.stop();
        lawANDOpen go=new lawANDOpen();
        go.start(new Stage());
        Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
    } catch (Exception ex) {
        System.out.print(ex);
    }
    }
}
