package com.pluralSight;

import java.time.LocalDateTime;

// Deposit class is a child-class of the Transaction class
public class Deposit extends Transaction {

    // Additional fields specific to deposits
    private final double amount;
    private LocalDateTime dateTime;
    private String vendor;

    // Constructor
    public Deposit(LocalDateTime dateTime, String vendor, double amount) {

        super(dateTime, vendor);
        this.amount = amount;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }


    // Override toString() method to provide custom string representation
    @Override
    public String toString() {

        return "Deposit{" + "dateTime=" + dateTime + ", vendor='" + vendor + '\'' + ", amount=" + amount + '}';

    }


}
