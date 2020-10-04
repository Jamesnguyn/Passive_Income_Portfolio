package com.company;

import java.sql.*;
import java.util.Scanner;

public class PortfolioActions {
    public static Scanner scanner = new Scanner(System.in);

    public static void actions() {

    }

    public static void printMenu() {
        System.out.println("ACTIONS:\n");
        System.out.println(
                "0 - QUIT\n" +
                "1 - ADD DIVIDEND\n" +
                "2 - REMOVE DIVIDEND\n" +
                "3 - EDIT DIVIDEND\n" +
                "4 - TOTAL DIVIDEND EARNED\n" +
                "5 - PRINT DIVIDENDS EARNED\n" +
                "6 - PRINT MENU");
    }

    public static void addDividend(){
        try {
            Connection conn = DriverManager.getConnection(PortfolioDataSource.path);
            Statement state = conn.createStatement();

            System.out.println("What is the payment date: (YYYY-MM-DD)\r");
            String dateInput = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Who paid the dividend?\r");
            String descriptionInput = scanner.nextLine();

            System.out.println("What sector does it belong to? \n" +
                            "1) Financial\n" +
                            "2) Technology\n" +
                            "3) Consumer Goods\n" +
                            "4) Healthcare\n" +
                            "5) Services\n" +
                            "6) ETFs\n" +
                            "7) Funds");
            String categoryInput = scanner.nextLine();
            int action = Integer.valueOf(categoryInput);

                switch (action) {
                    case 1:
                         categoryInput = "Financial";
                        break;
                    case 2:
                        categoryInput = "Technology";
                        break;
                    case 3:
                        categoryInput = "Consumer Goods";
                        break;
                    case 4:
                        categoryInput = "Healthcare";
                        break;
                    case 5:
                        categoryInput = "Services";
                        break;
                    case 6:
                        categoryInput = "ETFs";
                        break;
                    case 7:
                        categoryInput = "Funds";
                        break;
                }

            System.out.println("What is the dividend amount?\r");
            double amountInput = scanner.nextDouble();

            PortfolioDataSource.insertDividend(state, dateInput, descriptionInput, categoryInput, amountInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + PortfolioDataSource.table);

            while (tableSum.next()) {

                double c = tableSum.getInt(1);
                TotalEarnedDividendBalance.sum = TotalEarnedDividendBalance.sum + c;
            }

            double addDividendTotal = TotalEarnedDividendBalance.sum + TotalEarnedDividendBalance.totalBalance;
            System.out.println("Dividend added.");
            System.out.println("Balance: " + addDividendTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalEarnedDividendBalance.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void removeDividend(){

        try {

            Connection conn = DriverManager.getConnection(PortfolioDataSource.path);
            Statement state = conn.createStatement();

            System.out.println("What is the transaction id you want to remove?\r");
            int idInput = scanner.nextInt();

            PortfolioDataSource.deleteDividend(state, idInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + PortfolioDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalEarnedDividendBalance.sum = TotalEarnedDividendBalance.sum + c;
            }

            double removeDividendTotal = TotalEarnedDividendBalance.sum + TotalEarnedDividendBalance.totalBalance;

            System.out.println("Dividend removed.");
            System.out.println("Balance: " + removeDividendTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalEarnedDividendBalance.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static  void editDividend(){
        try {
            Connection conn = DriverManager.getConnection(PortfolioDataSource.path);
            Statement state = conn.createStatement();

            Scanner scanner = new Scanner(System.in);
            System.out.println("What is the dividend id you want to edit?\r");
            int idInput = scanner.nextInt();

            System.out.println("What is the new date: \r");
            String newDateInput = scanner.nextLine();
            scanner.nextLine();

            System.out.println("What is the new description:\r");
            String newDescriptionInput = scanner.nextLine();

            System.out.println("What is the new category:\r");
            String newCategoryInput = scanner.nextLine();

            System.out.println("What is the new amount: \r");
            double newAmountInput = scanner.nextDouble();

            PortfolioDataSource.editDividend(state, newDateInput, newDescriptionInput, newCategoryInput, newAmountInput, idInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + PortfolioDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalEarnedDividendBalance.sum = TotalEarnedDividendBalance.sum + c;
            }

            double editDividendTotal = TotalEarnedDividendBalance.sum + TotalEarnedDividendBalance.totalBalance;

            System.out.println("Transaction added.");
            System.out.println("Balance: " + editDividendTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalEarnedDividendBalance.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void currentBalance(){
        try {
            Connection conn = DriverManager.getConnection(PortfolioDataSource.path);
            Statement state = conn.createStatement();

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + PortfolioDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalEarnedDividendBalance.sum = TotalEarnedDividendBalance.sum + c;
            }
            double currentBalance = TotalEarnedDividendBalance.sum + TotalEarnedDividendBalance.totalBalance;

            System.out.println("DIVIDENDS EARNED TO DATE: $" + currentBalance);
            tableSum.close();
            state.close();
            conn.close();

            TotalEarnedDividendBalance.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printAllDividends() {
        PortfolioDataSource.printDividends();
    }
}

/*
package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Actions {

   public static Scanner scanner = new Scanner(System.in);

   public static void actions() {

   }

   //ADD TRANSACTION
   public static void addTransactions(){

       try {

           Connection conn = DriverManager.getConnection(Datasource.getConnectionString());
           Statement state = conn.createStatement();

           System.out.println("What is the date of the transaction(MM/DD/YYYY)\r");
           String dateInput = scanner.nextLine();
           scanner.nextLine();

           System.out.println("What is the description of the transaction?\r");
           String descriptionInput = scanner.nextLine();

           System.out.println("What is the transaction amount?\r");
           double amountInput = scanner.nextDouble();

           Datasource.insertTransaction(state, dateInput, descriptionInput, amountInput);

           ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + Datasource.getTableTransactions());

           while (tableSum.next()) {

              int c = tableSum.getInt(1);
              TotalBalance.sum = TotalBalance.sum + c;

           }

           double addTransactionTotal = TotalBalance.sum + TotalBalance.totalBalance;

           System.out.println("Transaction added.");
           System.out.println("Balance: " + addTransactionTotal);

           tableSum.close();
           state.close();
           conn.close();

           TotalBalance.sum = 0;

       }

       catch (SQLException e) {
           System.out.println("Something went wrong: " + e.getMessage());
           e.printStackTrace();
       }
   }

   //DELETE TRANSACTION
   public static void removeTransaction(){

       try {

           Connection conn = DriverManager.getConnection(Datasource.getConnectionString());
           Statement state = conn.createStatement();

           System.out.println("What is the transaction id you want to remove?\r");
           int idInput = scanner.nextInt();

           Datasource.deleteTransaction(state, idInput);

           ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + Datasource.getTableTransactions());
           while (tableSum.next()) {
               int c = tableSum.getInt(1);
               TotalBalance.sum = TotalBalance.sum + c;
           }

           double removeTransactionTotal = TotalBalance.sum + TotalBalance.totalBalance;

           System.out.println("Transaction removed.");
           System.out.println("Balance: " + removeTransactionTotal);

           tableSum.close();
           state.close();
           conn.close();

           TotalBalance.sum = 0;
       }

       catch (SQLException e) {
           System.out.println("Something went wrong: " + e.getMessage());
           e.printStackTrace();
       }
   }

   //EDIT TRANSACTION
   public static  void editTransaction(){

       try {

           Connection conn = DriverManager.getConnection(Datasource.getConnectionString());
           Statement state = conn.createStatement();

           Scanner scanner = new Scanner(System.in);
           System.out.println("What is the transaction id you want to edit?\r");
           int idInput = scanner.nextInt();

           System.out.println("What is the new date: \r");
           String newDateInput = scanner.nextLine();

           System.out.println("What is the new description:\r");
           String newDescriptionInput = scanner.nextLine();

           System.out.println("What is the new amount: \r");
           double newAmountInput = scanner.nextDouble();

           Datasource.editTransaction(state, newDateInput, newDescriptionInput, newAmountInput, idInput);

           ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + Datasource.getTableTransactions());
           while (tableSum.next()) {
               int c = tableSum.getInt(1);
               TotalBalance.sum = TotalBalance.sum + c;
           }

           double editTransactionTotal = TotalBalance.sum + TotalBalance.totalBalance;

           System.out.println("Transaction added.");
           System.out.println("Balance: " + editTransactionTotal);

           tableSum.close();
           state.close();
           conn.close();

           TotalBalance.sum = 0;
       }

       catch (SQLException e) {
           System.out.println("Something went wrong: " + e.getMessage());
           e.printStackTrace();
       }
   }

   //CURRENT BALANCE
   public static void currentBalance(){
       try {

           Connection conn = DriverManager.getConnection(Datasource.getConnectionString());
           Statement state = conn.createStatement();

           ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + Datasource.getTableTransactions());
           while (tableSum.next()) {
               int c = tableSum.getInt(1);
               TotalBalance.sum = TotalBalance.sum + c;
           }
           double currentBalance = TotalBalance.sum + TotalBalance.totalBalance;

           System.out.println("CURRENT BALANCE: $" + currentBalance);
           tableSum.close();
           state.close();
           conn.close();

           TotalBalance.sum = 0;
       }

       catch (SQLException e) {
           System.out.println("Something went wrong: " + e.getMessage());
           e.printStackTrace();
       }
   }

   public static void transactionStatement() {
       System.out.println(" not available ");
   }

   //PRINT TRANSACTIONS
   public static void printAllTransactions() {

       Datasource.printTransactions();

   }

   //PRINT MENU
   public static void printMenu() {

       System.out.println("ACTIONS:");
       System.out.println("0 - QUIT\n" +
               "1 - ADD TRANSACTION\n" +
               "2 - REMOVE TRANSACTION\n" +
               "3 - EDIT TRANSACTION\n" +
               "4 - TOTAL BALANCE\n" +
               "5 - STATEMENT REPORT(WEEKLY/MONTHLY)\n" +
               "6 - PRINT TRANSACTIONS\n" +
               "7 - PRINT MENU");
   }
}
 */