package com.pluralSight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {

    // attributes
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // constructor to provide attributes for the `Payment` class
    public Transaction(LocalDate date, LocalTime time, String vendor, String description) {

        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.description = description;
        this.amount = 0.0; // Setting amount to 0.0 as default

    }

    // constructor to provide attributes for the `Deposit` class
    public Transaction(LocalDateTime dateTime, String vendor, String description) {

        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
        this.vendor = vendor;
        this.description = description;
        this.amount = 0.0; // Setting amount to 0.0 as default

    }

    // constructor for creating `Transaction` object
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    // getters for the private attributes
    public LocalDate getDate() {

        return date;

    }

    public LocalTime getTime() {

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

    @Override
    public String toString() {

        return "Transaction{" + "date=" + date + ", time=" + time + ", description='" + description + '\'' + ", vendor='" + vendor + '\'' + ", amount=" + amount + '}';

    }
}
