package main.lokanta;

import interfaces.gates;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import objects.branch;
import objects.node;
import objects.restaurant;

public class logInCustomer extends gates implements java.io.Serializable, Cloneable {

    protected TextField email;
    protected ComboBox avaibleRests, theBranchesOfRest;
    protected restaurant restaurant;

    public static void main(String[] args) throws Exception {
        launch();
    }

    private Object[] avaibleRestsStrings() {
        java.util.ArrayList<String> avaibleRestsStringsArray = new java.util.ArrayList();
        try {//as it's first verison it only read current restaurant at local device
            ObjectInputStream currentRest = new ObjectInputStream(new FileInputStream("C:\\Lokanta\\Files\\RestInfo.dat"));
            restaurant = ((restaurant) (currentRest.readObject()));
            avaibleRestsStringsArray.add(restaurant.name);
        } catch (Exception ex) {
            System.out.print(ex);

        }
        return avaibleRestsStringsArray.toArray();
    }

    private void center() {
        email = new TextField("enter email");
        avaibleRests = new ComboBox(FXCollections.observableArrayList(avaibleRestsStrings()));
        theBranchesOfRest = new ComboBox();
        avaibleRests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ArrayList BranchseNames = new ArrayList();
                node tmp = restaurant.getBranches().getTail().getPrevious();
                while (tmp != null) {
                    BranchseNames.add(((branch) tmp.getData()).name);
                    tmp = tmp.getPrevious();
                }
                theBranchesOfRest.setItems(FXCollections.observableArrayList(BranchseNames.toArray()));
            }
        });
        Button login = new Button("LOGIN");
        login.setOnAction(new logInUser(this));
        VBox pane = new VBox();
        pane.getChildren().addAll(email, avaibleRests, theBranchesOfRest, login);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        body();
        center();
        stage = super.stage;
        stage.setTitle("good morning  mr  \u10463 ");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }
}

class logInUser implements EventHandler<ActionEvent> {

    logInCustomer from;
    private branch branch;
    private restaurant restaurant;
    public logInUser(logInCustomer from) {
        this.from = from;
    }

    @Override
    public void handle(ActionEvent t) {
        try {
            if (from.email.getText().matches("[\\w]*[@][\\w]{1,6}[.][c][o][m]")) {
                System.out.println("done");
                restaurant=from.restaurant;
                selectBranch();         
                CustomerFirst go = new CustomerFirst(restaurant,branch,from.email.getText());
                go.start(new Stage());
            } else {
                System.out.println("check EMAIL");
            }
            Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
            stage.close();

        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    private void selectBranch(){
        try{
        ObjectInputStream currentRBranch = new ObjectInputStream(new FileInputStream("C:\\Lokanta\\restaurant\\"+restaurant.name+"\\branches\\"+from.theBranchesOfRest.getValue().toString()+"\\Files\\RestInfo.dat"));
            branch = ((branch) (currentRBranch.readObject()));
    
        }catch(Exception ex){
            
        }
        }
}
