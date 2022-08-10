
package main.lokanta;

import interfaces.gates;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Lokanta extends gates implements java.io.Serializable, Cloneable{
     
    public static void main(String[] args) throws Exception{
        launch();
    }
    private void center(){
        HBox Buttons=new HBox();
        Button customer=new Button("CUSTOMER");
        customer.setOnAction(new customer());
        /* customer.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent t) {
        Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
        }
        
        });*/
        Button manager=new Button("MANAGER");
        manager.setOnAction(new manager());
        Buttons.getChildren().addAll(customer,manager);
        Buttons.setAlignment(Pos.CENTER);
        VBox pane=new VBox();
        Label hello=new Label("HELLO WORLD \u10463\n \n");
        hello.setTextFill(Color.GREEN);
        pane.getChildren().addAll(hello,Buttons);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);
    }
    @Override
    public void start(Stage stage) throws Exception {
        body(); 
        center();
        stage=super.stage;
        stage.setTitle("Hello world \u10463 ");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }
}
class manager implements EventHandler<ActionEvent> {

    public manager() {
    }

    @Override
    public void handle(ActionEvent t) {
    try {
        logInToRestManager go=new logInToRestManager();
        go.start(new Stage());
        Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
    } catch (Exception ex) {
    }
    }
}
class customer implements EventHandler<ActionEvent> {

    public customer() {
    }

    @Override
    public void handle(ActionEvent t) {
    try {
        logInCustomer go=new logInCustomer();
        go.start(new Stage());
        Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
    } catch (Exception ex) {
        System.out.print(ex);
    }
    }
}