package com.mishal;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String jdbcURL = "jdbc:sqlite:usersdb.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);

            String sql = "create table movies (movie varchar(30), actor varchar(30), actress varchar(30), date_of_release varchar(15), director varchar(30));";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table created");

            String sql_insert = "insert into movies values ('The Wolf of Wall Street','Leonardo DiCaprio','Margot Robbie','03-JAN-2013','Martin Scorsese');" +
                    "insert into movies values ('Interstellar','Matthew McConaughey','Anne Hathaway','05-NOV-2014','Christopher Nolan');" +
                    "insert into movies values ('Fight Club','Edward Norton','Helena Bonham Carter','15-OCT-1999','David Fincher');" +
                    "insert into movies values ('The Pursuit of Happyness','Will Smith','Thandiwe Newton','15-DEC-2006','Gabriele Muccino');" +
                    "insert into movies values ('Into the Wild','Emile Hirsch','Kristen Stewart','21-SEP-2007','Sean Penn');" +
                    "insert into movies values ('The Departed','Leonardo DiCaprio','Vera Ann Farmiga','26-SEP-2006','Martin Scorsese');";
            int rows = statement.executeUpdate(sql_insert);
            if (rows > 0) {
                System.out.println("Rows are created.");
            }
            System.out.println("Entire table\n");
            String row_id = "select rowid, * from movies";
            ResultSet result  = statement.executeQuery(row_id);
            while (result.next()) {
                int row = result.getInt("rowid");
                String movie = result.getString("movie");
                String actor = result.getString("actor");
                String actress = result.getString("actress");
                String date = result.getString("date_of_release");
                String director = result.getString("director");
                System.out.println(row+" | "+movie+" | "+" | "+ actor+" | "+actress+" | "+date+" | "+director);
            }
            System.out.println("\n");
            System.out.println("Movies in which Leonardo DiCaprio did act");
            String query = "select rowid, movie, actor from movies where actor='Leonardo DiCaprio'";
            result  = statement.executeQuery(query);
            while (result.next()) {
                int row = result.getInt("rowid");
                String movie = result.getString("movie");
                String actor = result.getString("actor");
                System.out.println(row+" | "+movie+" | "+" | "+ actor);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to SQLite database");
            e.printStackTrace();
        }
    }
}
