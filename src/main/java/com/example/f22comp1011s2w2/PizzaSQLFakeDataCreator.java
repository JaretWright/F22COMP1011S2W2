package com.example.f22comp1011s2w2;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class PizzaSQLFakeDataCreator {
    public static void main(String[] args) throws FileNotFoundException {
        Formatter formatter = new Formatter("pizza.sql");

        //INSERT INTO pizzas (size, dough,crustStyle,sauce,delivery) VALUES
        //('large','regular','thin','tomato',0);

        //to create a random number generator, we will use the SecureRandom class
        SecureRandom rng = new SecureRandom();
        List<String> pizzaSizeOptions = Pizza.validSizes();
        List<String> doughOptions = Pizza.getDoughOptions();
        List<String> crustStyles = Pizza.getCrustOptions();
        List<String> sauceOptions = Pizza.getAvailableSauces();

        for (int i=1; i<=75; i++)
        {
            //figure out the size, dough, crust and sauce of the pizza
            String size = pizzaSizeOptions.get(rng.nextInt(pizzaSizeOptions.size()));
            String dough = doughOptions.get(rng.nextInt(doughOptions.size()));
            String crust = crustStyles.get(rng.nextInt(crustStyles.size()));
            String sauce = sauceOptions.get(rng.nextInt(sauceOptions.size()));

            //randomly generate the toppings
            HashSet<Topping> toppingSet = new HashSet<>();  //A set prevents duplicates
            ArrayList<Topping> availableToppings = DBUtility.getToppingsFromDB();
            int numOfToppings = rng.nextInt(6);
            for (int t=0; t<numOfToppings; t++)
            {
                toppingSet.add(availableToppings.get(rng.nextInt(availableToppings.size())));
            }
            ArrayList<Topping> toppings = new ArrayList<>();
            toppings.addAll(toppingSet);

            //at this point we can create a Pizza object to figure out the cost
            Pizza newPizza = new Pizza(size,toppings,dough,crust,sauce,rng.nextBoolean());

            formatter.format("INSERT INTO pizzas (pizzaID, size, dough,crustStyle,sauce,delivery,price)" +
                    " VALUES (%d,'%s','%s','%s','%s',%d,%.2f);\n",i,size,dough,crust,sauce,
                                                            rng.nextInt(2),newPizza.getPrice());

            //loop over the toppings for each pizza and insert them into the table
            for (Topping topping : toppings)
            {
                formatter.format("INSERT INTO toppingsonpizza (pizzaID, toppingID) VALUES (%d,%d);\n"
                        ,i,topping.getToppingID());
            }
        }
        formatter.close();
    }
}
