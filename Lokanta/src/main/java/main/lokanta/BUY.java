package main.lokanta;

import interfaces.servicesINTCUST;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import objects.branch;
import objects.meal;
import objects.offer;
import objects.order;
import objects.restaurant;
import objects.service;

class BUY implements EventHandler<ActionEvent> {

    private restaurant rest;
    private branch branch;
    private servicesINTCUST from;
    private service required;
    private String email;
    private String mealOrOfferPath = "";

    public BUY(servicesINTCUST from, offer required) {
        this.from = from;
        this.rest = from.rest;
        this.branch = from.branch;
        this.required = new service();
        this.required.name = required.name;
        this.required.totalPrice = required.totalPrice;
        mealOrOfferPath = "Offers\\offers orders.csv";
        email = from.email;

    }
    

    public BUY(offerShowCustmer from, meal required) {
        this.rest = from.rest;
        this.branch = from.branch;
        this.required = new service();
        this.required.name = required.name;
        this.required.totalPrice = required.totalPrice;
        mealOrOfferPath = "Meals\\meals orders.csv";
        email = from.email;

    }
    public BUY(offerShowCustmer from, offer required) {
        this.rest = from.rest;
        this.branch = from.branch;
        this.required = new service();
        this.required.name = required.name;
        this.required.totalPrice = required.totalPrice;
        mealOrOfferPath = "Offers\\offers orders.csv";
        email = from.email;

    }

    public BUY(showMealsCust from, offer required) {
        this.rest = from.rest;
        this.branch = from.branch;
        this.required = new service();
        this.required.name = required.name;
        this.required.totalPrice = required.totalPrice;
        mealOrOfferPath = "Offers\\offers orders.csv";
        email = from.email;

    }

    public BUY(showMealsCust from, meal required) {
        this.rest = from.rest;
        this.branch = from.branch;
        this.required = new service();
        this.required.name = required.name;
        this.required.totalPrice = required.totalPrice;
        mealOrOfferPath = "Meals\\meals orders.csv";
        email = from.email;

    }

    @Override
    public void handle(ActionEvent t) {
        try {
            order order = new order(required.name, new java.util.Date(), required.totalPrice);

//add order
            branch.orders.add(order);
            handleFile();

//vsv
            handleCSV();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    protected void handleCSV() {
        try {
            File Csv = new File("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\" + mealOrOfferPath);
            StringBuffer CsvData = new StringBuffer();
            try ( Scanner CsvScanner = new Scanner(Csv);) {
                while (CsvScanner.hasNext()) {
                    CsvData.append(CsvScanner.nextLine() + "\n");
                }
                CsvScanner.close();
            } catch (Exception ex) {
            }
            PrintWriter mealsCsvWrite = new PrintWriter(Csv);
            mealsCsvWrite.print(CsvData);
            mealsCsvWrite.flush();
            mealsCsvWrite.println(email + ",\"" + required.name.substring(1,required.name.length()-1) + "\"");
            mealsCsvWrite.flush();
        } catch (Exception ex) {
            System.out.print(ex);

        }
    }

    protected void handleFile() {
        try {
            ObjectOutputStream branchReWrt = new ObjectOutputStream(new FileOutputStream("C:\\Lokanta\\restaurant\\" + rest.name + "\\branches\\" + branch.name + "\\Files\\RestInfo.dat"));
            branchReWrt.writeObject(branch);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
