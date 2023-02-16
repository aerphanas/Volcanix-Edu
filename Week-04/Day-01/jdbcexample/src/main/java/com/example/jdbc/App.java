package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class App {
    static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static Properties props = new Properties();

    public static void main(String[] args) {
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");

        doQuery("SELECT title, movie_status FROM movies.movie LIMIT 5");
    }

    public static void checkConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, props);

            System.out.println("Opened database successfully");

            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }

    public static void doQuery(String query) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, props);

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++)
                System.out.print(rsmd.getColumnName(i) + "\t\t");
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++)
                    System.out.print(rs.getString(i) + "\t");
                System.out.println();
            }

            rs.close();
            st.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }
}
