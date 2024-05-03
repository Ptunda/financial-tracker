package com.pluralSight;

import java.time.LocalDateTime;

public class Deposit extends Transaction {

    // Additional fields specific to deposits
    private LocalDateTime dateTime;
    private String vendor;
    private String description;
    private double amount;


    // Constructor
    public Deposit(LocalDateTime dateTime, String vendor, String description, double amount) {

        // Call the constructor of the superclass (Transaction). inherit desired attributes
        super(dateTime, vendor, description);

        this.vendor = vendor;
        this.description = description;
        this.amount = amount;

    }

    // getters
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // override the vendor, description, and amount attributes inherited from the super class and assign them new values
    @Override
    public String getVendor() {
        return vendor;
    }

    @Override
    public String getDescription() {
        return description;
    }

    // Getter for amount
    public double getAmount() {

        return amount;

    }


    // Override toString() method to provide custom string representation
    @Override
    public String toString() {

        return "Deposit{" + "amount=" + amount + ", dateTime=" + dateTime + ", vendor='" + vendor + '\'' + ", description='" + description + '\'' + '}';
    }

}
