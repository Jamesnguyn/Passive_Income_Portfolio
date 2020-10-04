package com.company;

import java.sql.*;

public class InvestmentDataSource {

    private static final String file = "portfolio.db";
    public static final String path = "jdbc:sqlite:C:\\Users\\jamez\\Passive Income Portfolio\\" + file;

    public static final String table = "Investment";
    private static final String id = "id";
    private static final String date = "date";
    private static final String amount = "amount";

    private static void dataSource(String[] args){
        try{
            Connection connection = DriverManager.getConnection(path);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + table);
            statement.execute("CREATE TABLE IF NOT EXISTS " + table +
                    " (" +  id + " int, " +
                    date + " date, " +
                    amount + " double " +
                    ")");
            statement.close();
            connection.close();

        }catch(SQLException e){
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void insertInvestmentAmount(Statement statement, String date, double amount) throws SQLException {
        statement.execute("INSERT INTO " + table +
                " (" +  InvestmentDataSource.date + ", " +
                InvestmentDataSource.amount +
                " ) " +
                "VALUES('" + date + "', " + amount + ")");
    }

    public static void deleteInvestmentAmount(Statement statement, int integer) throws SQLException{
        statement.execute("DELETE FROM " + InvestmentDataSource.table + " WHERE " + InvestmentDataSource.id + " = "  + integer );
    }

    public static void editInvestmentAmount(Statement statement, String date, double amount, int integer) throws  SQLException{
        statement.execute("UPDATE " +   InvestmentDataSource.table + " SET " +
                InvestmentDataSource.date + " =' " + date + "', " +
                InvestmentDataSource.amount + " = " + amount +
                " WHERE " + InvestmentDataSource.id + " = " + integer);
    }

    public static void printInvestments() {
        try {
            Connection conn = DriverManager.getConnection(InvestmentDataSource.path);

            Statement statement = conn.createStatement();
            ResultSet table = statement.executeQuery("SELECT * FROM " + InvestmentDataSource.table);

            while (table.next()) {
                System.out.println(
                        table.getInt(InvestmentDataSource.id) + " " +
                                table.getString(InvestmentDataSource.date) + " " +
                                table.getDouble(InvestmentDataSource.amount));
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
