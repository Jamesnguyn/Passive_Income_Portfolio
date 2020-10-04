package com.company;

import java.sql.*;

public class PortfolioDataSource {

    private static final String file = "portfolio.db";
    public static final String path = "jdbc:sqlite:C:\\Users\\jamez\\Passive Income Portfolio\\" + file;

    public static final String table = "Portfolio";
    private static final String id = "id";
    private static final String date = "date";
    private static final String description = "description";
    private static final String category = "category";
    private static final String amount = "amount";

    private static void dataSource(String[] args){
        try{
            Connection connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + table);
            statement.execute("CREATE TABLE IF NOT EXISTS " + table +
                    " (" +  id + " int, " +
                            date + " date, " +
                            description + " text, " +
                            category + " text, " +
                            amount + " double " +
                    ")");
            statement.close();
            connection.close();

        }catch(SQLException e){
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void insertDividend(Statement statement, String date, String description, String category, double amount) throws SQLException {
        statement.execute("INSERT INTO " + table +
                " (" +  PortfolioDataSource.date + ", " +
                PortfolioDataSource.description + ", " +
                PortfolioDataSource.category + ", " +
                PortfolioDataSource.amount +
                " ) " +
                "VALUES('" + date + "', '" + description + "', '" + category + "',  " + amount + ")");
    }

    public static void deleteDividend(Statement statement, int integer) throws SQLException{
        statement.execute("DELETE FROM " + PortfolioDataSource.table + " WHERE " + PortfolioDataSource.id + " = "  + integer );
    }

    public static void editDividend(Statement statement, String date, String description, String category, double amount, int integer) throws  SQLException{
        statement.execute("UPDATE " +   PortfolioDataSource.table + " SET " +
                PortfolioDataSource.date + " =' " + date + "', " +
                PortfolioDataSource.description + " = '" + description + "', " +
                PortfolioDataSource.category + " = '" + category + "', " +
                PortfolioDataSource.amount + " = " + amount +
                " WHERE " + PortfolioDataSource.id + " = " + integer);
    }

    public static void printDividends() {
        try {
            Connection conn = DriverManager.getConnection(PortfolioDataSource.path);

            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery("SELECT * FROM " + PortfolioDataSource.table);

            while (table.next()) {
                System.out.println(
                        table.getInt(PortfolioDataSource.id) + " " +
                                table.getString(PortfolioDataSource.date) + " " +
                                table.getString(PortfolioDataSource.description) + " " +
                                table.getString(PortfolioDataSource.category) + " " +
                                table.getDouble(PortfolioDataSource.amount));
            }

            table.close();
            statement.close();
            conn.close();

        } catch (
                SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
package com.company;

import java.sql.*;

public class Datasource {
   public static final String DB_NAME = "transactions.db";
   public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/jamesnguyen/Desktop/Expense/" + DB_NAME;

   public static final String TABLE_TRANSACTIONS = "transactions";

   public static final String COLUMN_ID = "id";
   public static final String COLUMN_DATE = "date";
   public static final String COLUMN_DESCRIPTION = "description";
   public static final String COLUMN_AMOUNT = "amount";

   //ADD TRANSACTIONS TO DATABASE
   public static void insertTransaction(Statement statement, String date, String description, double amount) throws SQLException {
       statement.execute("INSERT INTO " + Datasource.getTableTransactions() +
               " (" +  Datasource.getColumnDate() + ", " +
                       Datasource.getColumnDescription() + ", " +
                       Datasource.getColumnAmount() +
               " ) " +
               //"VALUES(" + id + ", " + date + ", '" + description + "', " + amount + ")");
               "VALUES('" + date + "', '" + description + "', " + amount + ")");
   }

   //REMOVE TRANSACTIONS FROM DATABASE
   public static void deleteTransaction(Statement statement, int integer) throws SQLException{
       statement.execute("DELETE FROM " + Datasource.getTableTransactions() + " WHERE " + Datasource.getColumnId() + " = "  + integer );
       //statement.execute("DELETE FROM " + Datasource.getTableTransactions() + " WHERE id = "  + integer );

   }

   //EDIT TRANSACTIONS
   public static void editTransaction(Statement statement, String date, String description, double amount, int integer) throws  SQLException{
       statement.execute("UPDATE " +   Datasource.getTableTransactions() + " SET " +
                                       Datasource.getColumnDate() + " =' " + date + "', " +
                                       Datasource.getColumnDescription() + " = '" + description + "', " +
                                       Datasource.getColumnAmount() + " = " + amount +

                       " WHERE " + Datasource.getColumnId() + " = " + integer);
   }

   //PRINT ALL TRANSACTIONS
   public static void printTransactions() {
       try {
           Connection conn = DriverManager.getConnection(Datasource.getConnectionString());

           Statement statement = conn.createStatement();
           ResultSet table = statement.executeQuery("SELECT * FROM " + Datasource.getTableTransactions());

           while (table.next()) {
               System.out.println(
                       table.getInt(Datasource.getColumnId()) + " " +
                       table.getString(Datasource.getColumnDate()) + " " +
                       table.getString(Datasource.getColumnDescription()) + " " +
                       table.getDouble(Datasource.getColumnAmount()));
           }

           table.close();
           statement.close();
           conn.close();

       } catch (
               SQLException e) {
           System.out.println("Something went wrong: " + e.getMessage());
           e.printStackTrace();
       }
   }
}

 */