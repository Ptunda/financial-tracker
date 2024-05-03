package com.pluralSight;


import java.time.LocalDateTime;


public class Payment extends Transaction {

    // additional attributes to specify a payment
    private LocalDateTime dateTime;
    private String vendor;
    private String description;
    private double amount;


    // Constructor
    public Payment(LocalDateTime dateTime, String vendor, String description, double amount) {

        // Call the constructor of the superclass (Transaction). inherit desired attributes
        super(dateTime.toLocalDate(), dateTime.toLocalTime(), vendor, description);

        this.amount = -amount; // negate provided payment amount
        this.description = description;
        this.vendor = vendor;

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

    @Override
    public double getAmount() {
        return amount;
    }

}
