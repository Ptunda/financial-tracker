package com.pluralSight;

import java.time.LocalDateTime;

// the `Deposit` class is derived from the `Transaction` class
// Deposit class is a child-class of the Transaction class
public class Deposit extends Transaction {

    // Additional fields specific to deposits
    private LocalDateTime dateTime; // Represents the date and time of the payment
    private String vendor; // the entity providing the deposit
    private String description; // tells more about the payment
    private double amount; // cost of the deposit


    // Constructor
    public Deposit(LocalDateTime dateTime, String vendor, String description, double amount) {

        // Call the constructor of the superclass (Transaction). inherit desired attributes
        super(dateTime, vendor, description);

        this.vendor = vendor; // assign the vendor
        this.description = description; // assign the description
        this.amount = amount; // change default amount to provided amount

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
