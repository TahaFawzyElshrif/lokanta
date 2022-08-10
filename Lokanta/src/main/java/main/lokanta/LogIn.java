
package main.lokanta;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import objects.restaurant;

class LogIn implements EventHandler<ActionEvent> {
private restaurant rest;
logInToRestManager from;
String code;
    public LogIn(logInToRestManager from) {
        this.from = from;
    }

    @Override
    public void handle(ActionEvent t) {
    try { 
        this.code=from.code.getText();
        handleObject();
        if(code.equals(rest.code+"")){
             logInToBranch go=new logInToBranch(rest);
             go.start(new Stage());
        }else{
        System.out.print("Check code , "+code+"isn't true");
        }
        
        Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
        stage.close();
    } catch (Exception ex) {
        System.out.print(ex);
    }
    }
     void handleObject() throws Exception{
        ObjectInputStream restOpen=new ObjectInputStream(new FileInputStream("C:\\Lokanta\\Files\\RestInfo.dat"));
        rest=(restaurant)restOpen.readObject();
    }
}