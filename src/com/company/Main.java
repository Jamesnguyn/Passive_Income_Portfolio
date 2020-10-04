package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\nWELCOME JAMES\n");

        boolean quit = false;
        int action;

        System.out.println("ACTIONS:\n");
        System.out.println(
                        "0 - QUIT\n" +
                        "1 - ACCESS DIVIDENDS\n" +
                        "2 - ACCESS INVESTMENTS\n");

        while (!quit) {

            System.out.println("SELECT AN OPTION:");
            action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {

                case 0:
                            //EXIT
                            System.out.println("Goodbye.");
                            quit = true;
                            break;
                case 1:
                    switch (action) {
                        case 0:
                            //EXIT
                            System.out.println("Goodbye.");
                            quit = true;
                            break;
                        case 1:
                            //ADD
                            PortfolioActions.addDividend();
                            break;
                        case 2:
                            //DELETE
                            PortfolioActions.removeDividend();
                            break;
                        case 3:
                            PortfolioActions.editDividend();
                            break;
                        case 4:
                            PortfolioActions.currentBalance();
                            break;
                        case 5:
                            PortfolioActions.printAllDividends();
                            break;
                        case 6:
                            //PRINT MENU OPTIONS
                            PortfolioActions.printMenu();
                            break;
                    }
                case 2:
                    switch (action) {
                        case 0:
                            //EXIT
                            System.out.println("Goodbye.");
                            quit = true;
                            break;
                        case 1:
                            //ADD
                            InvestmentActions.addInvestment();
                            break;
                        case 2:
                            //DELETE
                            InvestmentActions.removeInvestment();
                            break;
                        case 3:
                            InvestmentActions.editInvestment();
                            break;
                        case 4:
                            InvestmentActions.currentInvestments();
                            break;
                        case 5:
                            InvestmentActions.printAllInvestments();
                            break;
                        case 6:
                            //PRINT MENU OPTIONS
                            InvestmentActions.printMenu();
                            break;
                    }

            }
        }
    }
}

/*
public class Main {
   public static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
       System.out.println("\nWELCOME JAMES\n");

       boolean quit = false;
       int action;
       Actions.printMenu();

       while (!quit) {

           System.out.println("SELECT AN OPTION:");
           action = scanner.nextInt();
           scanner.nextLine();

           switch (action) {

               case 0:
                   //EXIT
                   System.out.println("Goodbye.");
                   quit = true;
                   break;

               case 1:
                   //ADD
                   Actions.addTransactions();
                   break;

               case 2:
                   //DELETE
                   Actions.removeTransaction();
                   break;

               case 3:
                   //EDIT
                   Actions.editTransaction();
                   break;
               case 4:
                   //PRINT CURRENT BALANCE
                   Actions.currentBalance();
                   break;

               case 5:
                   //PRINT TRANSACTIONS WITH DATE PARAMETERS
                   Actions.transactionStatement();
                   break;

               case 6:
                   //PRINT ALL TRANSACTIONS
                   Actions.printAllTransactions();
                   break;

               case 7:
                   //PRINT MENU OPTIONS
                   Actions.printMenu();
                   break;
           }
       }
   }
}
 */

