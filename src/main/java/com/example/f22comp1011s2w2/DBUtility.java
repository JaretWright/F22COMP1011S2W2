package com.example.f22comp1011s2w2;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student"; //MySQL server username
    private static String pw = "student";   //MySQL server password

    //jdbc:mysql = Java DataBase Connector to MySQL
    //127.0.0.1 = IP address where the MySQL server is
    //3306 = Port # that MySQL server is accessible on
    //F22 = the database name
    private static String connectUrl = "jdbc:mysql://127.0.0.1:3306/F22";

    /**
     * This method returns a list of topping objects from the Database
     * @return
     */
    public static ArrayList<Topping> getToppingsFromDB()
    {
        ArrayList<Topping> toppings = new ArrayList<>();

        //create the sql string we want to run on the database
        String sql = "SELECT * FROM toppings";

        //the try () is called "try with resources".  Anything opened in the () will
        //automatically close when the try block is done.
        try(
                //1.  connect to the database
                Connection conn = DriverManager.getConnection(connectUrl,user,pw);

                //2.  create a statement object
                Statement statement = conn.createStatement();

                //3.  use the statement object to run the sql and return a ResultSet object
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            //4.  loop over the resultSet and create Topping objects
            while (resultSet.next())
            {
                int toppingID = resultSet.getInt("toppingID");
                String name = resultSet.getString("toppingName");
                String category = resultSet.getString("category");
                Topping newTopping = new Topping(toppingID,name,category);
                toppings.add(newTopping);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return toppings;
    }

}