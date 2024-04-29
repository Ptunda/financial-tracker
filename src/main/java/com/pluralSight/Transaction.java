package com.pluralSight;

import java.time.LocalDateTime;

public class Transaction {

    // attributes
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // constructor
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // second constructor to provide attributes for the Deposit class
    public Transaction(LocalDateTime dateTime, String vendor) {

        this.date = dateTime.toLocalDate().toString();
        this.time = dateTime.toLocalTime().toString();
        this.vendor = vendor;
        this.description = ""; // Setting description to an empty string as default
        this.amount = 0.0; // Setting amount to 0.0 as default


    }

    // getters for the private attributes
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

}
