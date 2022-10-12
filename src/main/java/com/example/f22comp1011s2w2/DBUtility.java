package com.example.f22comp1011s2w2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

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

    /**
     * This method will query the DB and return an ArrayList of Pizza
     * objects
     * @return
     */
    public static Collection<Pizza> getPizzasFromDB()
    {
        //the pizzaID will be the key, the pizza will be the value
        TreeMap<Integer,Pizza> pizzas = new TreeMap<>();

        ArrayList<Topping> allToppings = DBUtility.getToppingsFromDB();

        //create the sql string we want to run on the database
        String sql = "SELECT pizzas.pizzaID, size, dough, crustStyle,sauce,delivery,price,toppings.toppingID,toppingName " +
                "FROM pizzas INNER JOIN toppingsonpizza " +
                "INNER JOIN toppings " +
                "WHERE toppings.toppingID = toppingsonpizza.toppingID AND pizzas.pizzaID = toppingsonpizza.pizzaID " +
                "ORDER BY pizzas.pizzaID,toppingsonpizza.toppingonpizzaID;";

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
                int pizzaID = resultSet.getInt("pizzaID");
                String size = resultSet.getString("size");
                String dough = resultSet.getString("dough");
                String crust = resultSet.getString("crustStyle");
                String sauce = resultSet.getString("sauce");
                Boolean delivery = resultSet.getBoolean("delivery");
                Double price = resultSet.getDouble("price");
                int toppingID = resultSet.getInt("toppingID");


                //check if the pizzaID is already in the TreeMap
                if (pizzas.keySet().contains(pizzaID))
                {
                    //the pizza already exists, just add more toppings
                    pizzas.get(pizzaID).getToppings().add(allToppings.get(toppingID-1));
                }
                else
                {
                    //the pizza does not exist, create it and add it to the TreeMap
                    ArrayList<Topping> toppings = new ArrayList<>();
                    toppings.add(allToppings.get(toppingID-1));  //The toppingID in the database
                                                                //starts at 1.  In the arraylist
                                                                //it starts at 0.
                    Pizza newPizza = new Pizza(pizzaID,size,toppings,dough,crust,sauce,delivery);
                    pizzas.put(pizzaID,newPizza);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return pizzas.values();
    }

    /**
     * This method will return PieChart.Data that shows how many pizzas were
     * ordered for each category of toppings
     */
    public static ObservableList<PieChart.Data> getCategorySummary()
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        //create the sql string we want to run on the database
        String sql = "SELECT category, COUNT(pizzaID) AS numOfOrders " +
                "FROM toppings LEFT JOIN toppingsOnPizza ON toppings.toppingID = toppingsOnPizza.toppingID " +
                "GROUP BY category;";

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
            //4.  loop over the resultSet and data points for our PieChart.Data
            while (resultSet.next())
            {
                String category = resultSet.getString("category");
                int numOfOrders = resultSet.getInt("numOfOrders");
                pieChartData.add(new PieChart.Data(category,numOfOrders));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return pieChartData;
    }


public static XYChart.Series<String,Integer> getToppingsSummary()
{
    XYChart.Series<String, Integer> toppings = new XYChart.Series<>();

    //create the sql string we want to run on the database
    String sql = "SELECT toppingName, COUNT(pizzaID) as numOfOrders " +
            "FROM toppings LEFT JOIN toppingsOnPizza ON toppings.toppingID = toppingsOnPizza.toppingID " +
            "GROUP BY toppings.toppingID " +
            "ORDER BY numOfOrders DESC;";

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
        //4.  loop over the resultSet and data points for our PieChart.Data
        while (resultSet.next())
        {
            String topping = resultSet.getString("toppingName");
            int numOfOrders = resultSet.getInt("numOfOrders");
            toppings.getData().add(new XYChart.Data<>(topping,numOfOrders));
        }
    } catch (Exception e)
    {
        e.printStackTrace();
    }
    return toppings;
}
}
