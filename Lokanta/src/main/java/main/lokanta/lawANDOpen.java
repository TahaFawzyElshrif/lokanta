package main.lokanta;

import interfaces.gates;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objects.restaurant;

public class lawANDOpen extends gates implements java.io.Serializable, Cloneable {
    /**
     * @param args the command line arguments
     */
    protected TextField restCode, restName,manager;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private void center() {
        Label law = new Label("WELCOME \n for one PC only one restaurant would be make at below C:\\Lokanta\\restaurant\\"
                + "\n and you can add as many branchs as you want \\ and you can add files\\ and manage it here"
                + "\n good afternoon ");

        law.setTextFill(Color.WHITE);
        restName = new TextField("Enter Restaurant name (only will affect if first time)");
        restCode = new TextField("Enter Restaurant code (10 digit for logging in) (only will affect if first time)");
        manager = new TextField("Manager");
        Button ok = new Button("GO");
        ok.setOnAction(new oklawANDOpen(this));
        Button cancel = new Button("exit");
        HBox Buttons = new HBox();
        Buttons.getChildren().addAll(ok, cancel /*,folder*/);
        Buttons.setAlignment(Pos.CENTER);
        VBox pane = new VBox();
        pane.getChildren().addAll(law, restName, restCode,manager, Buttons);
        pane.setAlignment(Pos.CENTER);
        back.setCenter(pane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //  folder.setOnAction(new chooseFolder(stage));
        body();
        center();
        stage = super.stage;
        stage.setTitle("READ CAREFULLY ");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

}

/*class chooseFolder implements EventHandler<ActionEvent> {

Stage s;

public chooseFolder(Stage s) {
this.s = s;
}

@Override
public void handle(ActionEvent t) {
try {
DirectoryChooser dir_chooser = new DirectoryChooser();
File file = dir_chooser.showDialog(s);
} catch (Exception ex) {
}
}



}*/
class oklawANDOpen implements EventHandler<ActionEvent> {

    lawANDOpen from;
    String restName, restCode;
    private restaurant newRest;

    public oklawANDOpen() {
    }

    public oklawANDOpen(lawANDOpen from) {
        this.from = from;

    }

    @Override
    public void handle(ActionEvent t) {
        try {
            this.restName = from.restName.getText();
            this.restCode = from.restCode.getText();
            if (!restCode.matches("[\\w]{10}")) {
                from.restCode.setText("Code must be 10 numbers");
            } else {

                newRest = new restaurant(restName, Long.parseLong(restCode));
                newRest.managerName=from.manager.getText();
                createMainFiles();
                newBranche1 go = new newBranche1(newRest);
                go.start(new Stage());
            }
            Stage stage = (Stage) ((Button)t.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    void createMainFiles() throws Exception {

        File branches = new File("C:\\Lokanta\\restaurant");
        if (!branches.exists()) {
            File branches_compPath = new File("C:\\Lokanta\\restaurant\\" + restName + "\\branches\\");
            branches_compPath.mkdirs();
            File files_compPath = new File("C:\\Lokanta\\restaurant\\" + restName + "\\Files\\");
            files_compPath.mkdir();
            File files_generalPath = new File("C:\\Lokanta\\Files\\");
            files_generalPath.mkdir();
            ObjectOutputStream restWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\Files\\RestInfo.dat"));
            restWrt.writeObject(newRest);
        }

    }
}
