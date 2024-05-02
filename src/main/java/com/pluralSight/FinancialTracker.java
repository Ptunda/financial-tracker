package com.pluralSight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class FinancialTracker {

    // provide static attributes
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);


    public static void main(String[] args) {


        loadTransactions(FILE_NAME); // invoke the loadTransactions() method


        System.out.println("See all transactions: \n");

        int i = 1;

        // print the current transaction list to the user to view transaction history
        for (Transaction transaction : transactions) {

            System.out.println(i + ". Date: " + transaction.getDate() + " - " + transaction.getTime() + " - " + transaction.getDescription() + " - " + transaction.getVendor() + " - $" + transaction.getAmount());
            i++;

        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // create an infinite loop to give the user control of the program
        while (running) {

            // prompt the user to select an option: add deposit, make a payment, open their ledger, or exit the app
            System.out.println("\nWelcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            // check whether the user's input matches any of the case values and invoke the subsequent method
            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();

    }

    // read from a file to create a transactions list or create it if it doesn't exist
    public static void loadTransactions(String fileName) {

        File file = new File(fileName); // create a new file

        // if file doesn't exist
        if (!file.exists()) {

            try {

                // create the file and print to the user that it has been created
                file.createNewFile();

                System.out.println("File was created: " + fileName);

            } catch (IOException e) {

                // if creating the file fails, tell the user
                System.out.println("Error creating the file. " + e.getMessage());

            }
        }

        // and if the file exists
        try {

            // read the file
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line; // create a String to read the file

            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split("\\|"); // split each line of the file, to create arrays of strings

                // check if the size of the array is 5 and extract values to populate the ArrayList
                if (parts.length == 5) {

                    LocalDate date = LocalDate.parse(parts[0], DATE_FORMATTER); // Retrieve a String date input and convert it to an instance of the LocalDate
                    LocalTime time = LocalTime.parse(parts[1], TIME_FORMATTER); // Retrieve a String time and convert it to an instance of the LocalDate
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]); // Retrieve a String amount input and convert it into a double

                    // create an instance of the Transaction class
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);

                    transactions.add(transaction); // add the transactions to the ArrayList

                }

            }

            bufferedReader.close(); // close the BufferedReader

        } catch (FileNotFoundException e) {

            System.out.println("FileNotFoundException occurred! " + e.getMessage());

        } catch (IOException e) {

            System.out.println("IOException occurred! " + e.getMessage());

        }

    }

    // create a Deposit object to be used to add new deposits transactions to the transactions file and list
    private static void addDeposit(Scanner scanner) {

        try {
            // prompt the user to enter required date
            System.out.println("Enter the date and time (yyyy-MM-dd HH:mm:ss):");
            String dateAndTimeInput = scanner.nextLine().trim();

            // Validate date and time format
            LocalDateTime dateTime;

            // convert the String input to a LocalDAteTime instance in the desired format
            dateTime = LocalDateTime.parse(dateAndTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // otherwise prompt the user for more details about the deposit
            System.out.println("Enter the vendor:");
            String vendor = scanner.nextLine().trim();

            System.out.println("Enter the description of this deposit:");
            String description = scanner.nextLine().trim();

            System.out.println("Enter the amount:");
            double amount = Double.parseDouble(scanner.nextLine());


            // check if the amount is a positive number
            if (amount <= 0) {
                System.out.println("Amount must be a positive number.");
                return;
            }

            // Create new Deposit object
            Deposit deposit = new Deposit(dateTime, vendor, description, amount);

            // Add deposit to transactions ArrayList
            transactions.add(deposit);

            // write to the transactions file
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // create a functionality that adds new deposit transactions to the original transactions file
            bufferedWriter.write(dateTime.toLocalDate() + "|" + dateTime.toLocalTime() + "|" + description + "|" + vendor + "|" + amount + "\n");


            bufferedWriter.flush(); // ensure that every buffered character is written to the underlying file
            bufferedWriter.close(); // close the BufferedReader


        } catch (DateTimeParseException e) {

            // when a user enters a wrong format, display an error message and return to Home Screen
            System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm:ss." + e.getMessage());
            return;

        } catch (IOException e) {

            // Display to the user when we can't write the payment to the file and return to Home Screen
            System.out.println("IOException occurred! " + e.getMessage());
            return;
        }

        // display confirmation message
        System.out.println("Deposit added successfully!");

    }

    // create a Payment object to be used to add new deposits to the transactions file and list
    private static void addPayment(Scanner scanner) {


        try {
            System.out.println("Enter the date and time (yyyy-MM-dd HH:mm:ss):");
            String dateAndTimeInput = scanner.nextLine().trim();

            // Validate date and time format
            LocalDateTime dateTime;

            // convert the String input to a LocalDAteTime instance in the desired format
            dateTime = LocalDateTime.parse(dateAndTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // otherwise prompt the user for more details about the deposit
            System.out.println("Enter the vendor:");
            String vendor = scanner.nextLine().trim();

            System.out.println("Enter the description of this payment:");
            String description = scanner.nextLine().trim();


            System.out.println("Enter the amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            // check if the amount is a positive number
            if (amount <= 0) {

                System.out.println("Amount must be a positive number.");
                return;

            }


            // Create new Payment object
            Payment payment = new Payment(dateTime, vendor, description, amount);

            // Add payment to transactions ArrayList
            transactions.add(payment);

            // write the deposit transactions to the transactions file
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // create a functionality that adds new payment transactions to the original transactions file
            bufferedWriter.write(dateTime.toLocalDate() + "|" + dateTime.toLocalTime() + "|" + description + "|" + vendor + "|" + -amount + "\n");


            bufferedWriter.flush(); // ensure that every buffered character is written to the underlying file
            bufferedWriter.close(); // close the BufferedReader

        } catch (DateTimeParseException e) {

            // when a user enters wrong format, display an error message and return to Home Screen
            System.out.println("Invalid date and time format. Please use yyyy-MM-dd HH:mm:ss.");
            return;

        } catch (IOException e) {

            // Display to the user when we can't write the payment to the file and return to Home Screen
            System.out.println("IOException occurred! " + e.getMessage());
            return;

        }

        // display confirmation message
        System.out.println("Payment added successfully!");

    }

    // create a ledger menu that redirects the user to a desired destination / menu
    private static void ledgerMenu(Scanner scanner) {

        boolean running = true;

        // create an infinite loop to allow the user to have control over the flow of execution
        while (running) {

            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) A`ll");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger(); // if the user enters A, invoke the displayLedger() method, where they'll be able to see all transaction
                    break;
                case "D":
                    displayDeposits(); // if the user enters B, invoke the displayDeposits() method, where they'll be able to see deposit transactions only
                    break;
                case "P":
                    displayPayments(); // if the user enters C, invoke the displayPayment() method, where they'll be able to see payment transactions only
                    break;
                case "R":
                    reportsMenu(scanner); // if the user enters R, invoke the reportsMenu() method, where the user can filter reports
                    break;
                case "H":
                    running = false; // if the user enters H, post them to the Home Menu
                default:
                    System.out.println("Invalid option"); // if the user enters anything else
                    break;

            }

        }

    }

    // This method should display a table of all transactions in the `transactions` ArrayList.
    private static void displayLedger() {

        // create a table to display all transactions
        System.out.println("Ledger");
        System.out.println("=========================================================================================================");
        System.out.printf("%-15s %-10s %-30s %-20s %s\n", "Date", "Time", "Type", "Vendor", "Amount"); // align output to the left and specify the width for every item
        System.out.println("=========================================================================================================");
        for (Transaction transaction : transactions) {

            System.out.printf("%-15s %-10s %-30s %-20s $%.2f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

        }

    }

    // This method should display a table of only deposits in the `transactions` ArrayList.
    private static void displayDeposits() {

        // create a table to display deposit transactions only
        System.out.println("Deposits");
        System.out.println("=========================================================================================================");
        System.out.printf("%-15s %-10s %-30s %-20s %s\n", "Date", "Time", "Type", "Vendor", "Amount"); // align output to the left and specify the width for every item
        System.out.println("=========================================================================================================");
        for (Transaction transaction : transactions) {

            // check if the transaction object is of the `Deposit` type, and display only that, ignore payment transactions
            if(transaction instanceof Deposit){

                System.out.printf("%-15s %-10s %-30s %-20s $%.2f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

            }

        }

//        Deposit deposit = (Deposit) transaction;
//        System.out.printf("%-15s %-10s %-20s $%.2f\n", deposit.getDate(), deposit.getTime(), deposit.getVendor(), deposit.getAmount());
    }

    // This method should display a table of only payments in the `transactions` ArrayList.
    private static void displayPayments() {

        // create a table to display payment transactions only
        System.out.println("Payments");
        System.out.println("=========================================================================================================");
        System.out.printf("%-15s %-10s %-30s %-20s %s\n", "Date", "Time", "Type", "Vendor", "Amount"); // align output to the left and specify the width for every item
        System.out.println("=========================================================================================================");
        for (Transaction transaction : transactions) {

            // check if the transaction object is of the `Payment` type, and display only that, ignore deposit transactions
            if (transaction instanceof Payment) {

                System.out.printf("%-15s %-10s %-30s %-20s $%.2f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

            }
        }

//        Payment payment = (Payment) transaction;
//        System.out.printf("%-15s %-10s %-20s $%.2f\n", payment.getDate(), payment.getTime(), payment.getVendor(), payment.getAmount());

    }

    // Method to display the reports menu and allow users to choose different report options
    private static void reportsMenu(Scanner scanner) {

        boolean running = true; // Set a boolean flag to control the loop execution

        // Start an infinite loop to display the reports menu until the user decides to exit
        while (running) {
            System.out.println("Reports");
            // Display the options for the user to choose from
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Generate a report for all transactions within the current month
                    filterTransactionsByDate(LocalDate.now().withDayOfMonth(1), LocalDate.now().plusDays(1));
                    break;

                case "2":
                    // Generate a report for all transactions within the previous month
                    filterTransactionsByDate(LocalDate.now().minusMonths(1).withDayOfMonth(1), LocalDate.now().withDayOfMonth(1));
                    break;

                case "3":
                    // Generate a report for all transactions within the current year
                    filterTransactionsByDate(LocalDate.now().withDayOfYear(1), LocalDate.now().plusDays(1));
                    break;


                case "4":
                    // Generate a report for all transactions within the previous year,
                    filterTransactionsByDate(LocalDate.now().minusYears(1).withDayOfYear(1), LocalDate.now().withDayOfYear(1));
                    break;

                case "5":
                    // Prompt the user to enter a vendor name, then generate a report for all transactions with that vendor
                    System.out.println("Enter the vendor name:");
                    String vendorName = scanner.nextLine().trim();
                    filterTransactionsByVendor(vendorName);
                    break;

                case "0":
                    running = false; // Exit the loop and return to the previous menu

                default:
                    System.out.println("Invalid option"); // Display an error message for invalid options
                    break;
            }

        }

    }

    // Method to filter transactions by date
    private static void filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {

        boolean foundTransactions = false; // Set a flag to track if any transactions are found within the date range

        // Display a message indicating that the report is being generated
        System.out.println("Transactions within the specified date range:");

        // Iterate through the transactions list
        for (Transaction transaction : transactions) {

            // Get the date of the current transaction
            LocalDate transactionDate = transaction.getDate(); // Get the transaction date directly

            // Check if the transaction date falls within the specified date range
            if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {

                // If the transaction is within the date range, display its details
                System.out.printf("Date: %s, Vendor: %s, Amount: $%.2f\n", transaction.getDate(), transaction.getVendor(), transaction.getAmount());
                foundTransactions = true;// Update the flag to indicate that transactions were found

            }
        }

        // If no transactions were found within the date range, display a message
        if (!foundTransactions) {

            System.out.println("No transactions found within the specified date range.");

        }

    }

    // Method to filter transactions by vendor
    private static void filterTransactionsByVendor(String vendor) {

        boolean foundTransactions = false; // Set a flag to track if any transactions are found for the specified vendor

        // Display a message indicating that the report is being generated
        System.out.println("Transactions with the specified vendor:");

        // Iterate through the transactions list
        for (Transaction transaction : transactions) {

            // Check if the vendor of the current transaction matches the specified vendor
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {

                // If the transaction matches the vendor, display its details
                System.out.printf("Date: %s, Amount: $%.2f\n", transaction.getDate(), transaction.getAmount());
                foundTransactions = true; // Update the flag to indicate that transactions were found

            }
        }

        // If no transactions were found for the specified vendor, display a message
        if (!foundTransactions) {

            System.out.println("No transactions found with the specified vendor.");

        }

    }


}

