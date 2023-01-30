package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class App 
{
    public static void main( String[] args )
    {
        String host = "";
        String password = "";
        String user = "";
        String database = "";
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://"
                                            + host
                                            + ":5432/"
                                            + database, user, password);
            System.out.println("Opened database successfully");
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("database closed successfully");
    }
}
