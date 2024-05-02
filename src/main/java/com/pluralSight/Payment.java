package com.pluralSight;


import java.time.LocalDate;
import java.time.LocalDateTime;

// Payment class is derived from the Transaction class
// Payment class a child class of the Transaction class
public class Payment extends Transaction {

    // additional attributes to specify a payment
    private LocalDateTime dateTime; // Represents the date and time of the payment
    private String vendor; // the entity receiving the payment
    private String description; // tells more about the payment
    private double amount; // cost of the payment


    // Constructor
    public Payment(LocalDateTime dateTime, String vendor, String description, double amount) {

        // Call the constructor of the superclass (Transaction). inherit desired attributes
        super(dateTime.toLocalDate(), dateTime.toLocalTime(), vendor, description);

        this.amount = -amount; // negate provided payment amount
        this.description = description; // assign a description
        this.vendor = vendor; // assign a vendor

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
