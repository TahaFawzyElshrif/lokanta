package main.lokanta;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCallerOptions;
import com.github.rcaller.rstuff.RCode;
import interfaces.servicesINT;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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

public class Mining1 extends servicesINT implements java.io.Serializable, Cloneable {

    public Mining1() {
    }

    public Mining1(restaurant rest, branch branch) {
        this.rest = rest;
        this.branch = branch;
    }

    public static void main(String[] args) {

        try {
            Application.launch(args);
        } catch (Exception ex) {
        }

    }

    public void start(Stage stage) throws Exception {
        center();
        body();
        stage = super.stage;
        stage.setTitle(branch.branchManagerName + " Analtyics of how things sold ");
        Scene scene = new Scene(super.back);
        stage.setScene(scene);
        stage.show();
    }

   
    public static String RScript() {
        StringBuilder buffered_RCode = new StringBuilder();
        try {
            File mealsCsv = new File("C:\\Lokanta\\App_Files\\txt\\RCode.txt");
            try ( Scanner RFile_Scanner = new Scanner(mealsCsv);) {
                while (RFile_Scanner.hasNext()) {
                    buffered_RCode.append(RFile_Scanner.nextLine() + "\n");
                }
                RFile_Scanner.close();
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
            System.out.print(ex);

        }
        return buffered_RCode.toString();
    }

     static String[] prepareR(String fuctionName, String function, String Path) {
        RCode code = RCode.create();
        code.addRCode(RScript());
        code.addRCode(fuctionName + " <-" + function + "(\"" + Path + "\")");
        RCaller caller = RCaller.create(code, RCallerOptions.create());
        caller.runAndReturnResult(fuctionName);
        String[] stringC = caller.getParser().getAsStringArray(fuctionName);
        return stringC;
    }
    VBox IteratingBoxes = new VBox();

    private boolean second(String[] analticResults, VBox IteratingBoxes) {
        VBox secondVBox = new VBox();
        for (int i = 0; i < analticResults.length / 3; i++) {
            HBox line = new HBox();
            Label Food1 = new Label(analticResults[i] + ":");
            Food1.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

            Label Food2 = new Label(analticResults[i + analticResults.length / 3] + "---->");
            Food2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            Label price = new Label(analticResults[i + (2 * analticResults.length) / 3]);
            price.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            line.getChildren().addAll(Food1, Food2, price);
            secondVBox.getChildren().add(line);
        }
        IteratingBoxes.getChildren().addAll(new Label("Mining\nby Two"), secondVBox);
        boolean isLastIterating = (analticResults.length > 3) ? true : false;
        return isLastIterating;
    }

    private void third(String[] analticResults, VBox IteratingBoxes) {

        VBox thirdVBox = new VBox();
        for (int i = 0; (i < analticResults.length / 4); i++) {
            HBox line = new HBox();
            Label Food1 = new Label(analticResults[i] + ":");
            Food1.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

            Label Food2 = new Label(analticResults[i + analticResults.length / 4] + "---->");
            Food2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            Label Food3 = new Label(analticResults[i + (2 * analticResults.length) / 4] + "---->");
            Food3.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

            Label price = new Label(analticResults[i + (3 * analticResults.length) / 4]);
            price.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            line.getChildren().addAll(Food1, Food2, Food3, price);
            thirdVBox.getChildren().add(line);
        }
        IteratingBoxes.getChildren().addAll(new Label("\nBy Three"), thirdVBox);
    }

    public void center() {
        menu.setVisible(false);
        VBox IteratingBoxes = new VBox();
        //meals
        String[] AnaltyicSecondIterationMeals = prepareR("secondCalling", "second", "C:/Lokanta/restaurant/" + rest.name + "/branches/" + branch.name + "/Meals/meals orders.csv");
        boolean DoSecondIterationMealsAndIsLast = second(AnaltyicSecondIterationMeals, IteratingBoxes);
        
        if (!DoSecondIterationMealsAndIsLast) {
        String[] AnaltyicThirdIterationMeals = prepareR("thirdCalling", "third", "C:/Lokanta/restaurant/" + rest.name + "/branches/" + branch.name + "/Meals/meals orders.csv");
        third(AnaltyicThirdIterationMeals, IteratingBoxes);
        }
        //offers
        IteratingBoxes.getChildren().addAll(new Label("\nOffers:---------"));

        String[] AnaltyicSecondIterationOffers = prepareR("secondCallingOffer", "second", "C:/Lokanta/restaurant/" + rest.name + "/branches/" + branch.name + "/Offers/offers orders.csv");
        boolean DoSecondIterationOffersAndIsLast = second(AnaltyicSecondIterationOffers, IteratingBoxes);
        
        if (!DoSecondIterationOffersAndIsLast) {
        String[] AnaltyicThirdIterationOffers = prepareR("thirdCallingOffer", "third", "C:/Lokanta/restaurant/" + rest.name + "/branches/" + branch.name + "/Offers/offers orders.csv");
        third(AnaltyicThirdIterationOffers, IteratingBoxes);
        }
        IteratingBoxes.getChildren().addAll(new Label("support:.125\n Confidence:,2"));
        //adding to scroll pane
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(595, 200);
        scroll.setContent(IteratingBoxes);
        back.setCenter(scroll);
    }

    
   

}
