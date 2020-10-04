package com.company;

import java.sql.*;
import java.util.Scanner;

public class InvestmentActions {
    public static Scanner scanner = new Scanner(System.in);

    public static void actions() {

    }

    public static void printMenu() {
        System.out.println("ACTIONS:\n");
        System.out.println(
                "0 - QUIT\n" +
                        "1 - ADD INVESTMENT\n" +
                        "2 - REMOVE INVESTMENT\n" +
                        "3 - EDIT INVESTMENT\n" +
                        "4 - TOTAL INVESTMENTS\n" +
                        "5 - PRINT INVESTMENTS\n" +
                        "6 - PRINT MENU");
    }

    public static void addInvestment(){
        try {
            Connection conn = DriverManager.getConnection(InvestmentDataSource.path);
            Statement state = conn.createStatement();

            System.out.println("What is the payment date: (YYYY-MM-DD)\r");
            String dateInput = scanner.nextLine();
            scanner.nextLine();

            System.out.println("What is the investment amount?\r");
            double amountInput = scanner.nextDouble();

            InvestmentDataSource.insertInvestmentAmount(state, dateInput, amountInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + InvestmentDataSource.table);

            while (tableSum.next()) {

                double c = tableSum.getInt(1);
                TotalInvestments.sum = TotalInvestments.sum + c;
            }

            double addInvestmentTotal = TotalInvestments.sum + TotalInvestments.totalBalance;
            System.out.println("Investment Amount added.");
            System.out.println("Balance: " + addInvestmentTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalInvestments.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void removeInvestment(){

        try {

            Connection conn = DriverManager.getConnection(InvestmentDataSource.path);
            Statement state = conn.createStatement();

            System.out.println("What is the transaction id you want to remove?\r");
            int idInput = scanner.nextInt();

            InvestmentDataSource.deleteInvestmentAmount(state, idInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + InvestmentDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalInvestments.sum = TotalInvestments.sum + c;
            }

            double removeInvestmentTotal = TotalInvestments.sum + TotalInvestments.totalBalance;

            System.out.println("Investment removed.");
            System.out.println("Balance: " + removeInvestmentTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalInvestments.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static  void editInvestment(){
        try {
            Connection conn = DriverManager.getConnection(InvestmentDataSource.path);
            Statement state = conn.createStatement();

            Scanner scanner = new Scanner(System.in);
            System.out.println("What is the dividend id you want to edit?\r");
            int idInput = scanner.nextInt();

            System.out.println("What is the new date: \r");
            String newDateInput = scanner.nextLine();
            scanner.nextLine();

            System.out.println("What is the new amount: \r");
            double newAmountInput = scanner.nextDouble();

            InvestmentDataSource.editInvestmentAmount(state, newDateInput, newAmountInput, idInput);

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + InvestmentDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalInvestments.sum = TotalInvestments.sum + c;
            }

            double editInvestmentTotal = TotalInvestments.sum + TotalInvestments.totalBalance;

            System.out.println("Transaction added.");
            System.out.println("Balance: " + editInvestmentTotal);

            tableSum.close();
            state.close();
            conn.close();

            TotalInvestments.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void currentInvestments(){
        try {
            Connection conn = DriverManager.getConnection(InvestmentDataSource.path);
            Statement state = conn.createStatement();

            ResultSet tableSum = state.executeQuery("SELECT SUM(amount) FROM " + InvestmentDataSource.table);
            while (tableSum.next()) {
                int c = tableSum.getInt(1);
                TotalInvestments.sum = TotalInvestments.sum + c;
            }
            double currentBalance = TotalInvestments.sum + TotalInvestments.totalBalance;

            System.out.println("INVESTMENTS TO DATE: $" + currentBalance);
            tableSum.close();
            state.close();
            conn.close();

            TotalInvestments.sum = 0;
        }

        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printAllInvestments() {
        InvestmentDataSource.printInvestments();
    }
}
