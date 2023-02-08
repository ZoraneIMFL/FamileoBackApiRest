package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDDSingleton {
    private static Connection conn;

    private BDDSingleton() {}

    public static Connection getInstance() {
        if(conn == null) {
            try
            {
                String url = "jdbc:mysql://localhost:9000/db";
                Class.forName ("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection (url,"root","password");
                System.out.println ("Database connection established");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
