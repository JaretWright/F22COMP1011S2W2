package com.example.f22comp1011s2w2;

import java.util.*;

public class Pizza {
    private String size;
    private ArrayList<String> toppings;
    private String dough, crustStyle, sauce;
    private boolean delivery;
    private double price;

    public Pizza(String size, ArrayList<String> toppings, String dough, String crustStyle, String sauce, boolean delivery, double price) {
        //here is a comment
        setSize(size);
        setToppings(toppings);
        setDough(dough);
        setCrustStyle(crustStyle);
        setSauce(sauce);
        setDelivery(delivery);
        setPrice(price);
    }

    public String getSize() {
        return size;
    }

    public static List<String> validSizes()
    {
        return Arrays.asList("small", "medium", "large","x-large","Scorpio");
    }

    public static TreeSet<String> getMeatToppingOptions()
    {
        TreeSet<String> toppingsSet = new TreeSet<>();
        toppingsSet.addAll(Arrays.asList("Bacon","Sausage","Beef Crumble","Ham","Salami","Bacon"));
        return toppingsSet;
    }

    /**
     * Validate that the argument is "small", "medium" or "large", "x-large"
     * @param size
     */
    public void setSize(String size) {
        size = size.toLowerCase().trim();  //removes any leading or trailing white spaces and makes it all lower case
        List<String> validSizes = validSizes();

        //check if the argument is one of the valid sizes
        if (validSizes.contains(size))
            this.size = size;
        else
            throw new IllegalArgumentException("valid sizes are: " + validSizes);
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getCrustStyle() {
        return crustStyle;
    }

    public void setCrustStyle(String crustStyle) {
        this.crustStyle = crustStyle;
    }

    public static List<String> getAvailableSauces()
    {
        return Arrays.asList("tomato","pesto","bar bq","alfredo","habanero","artichoke");
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        sauce = sauce.trim().toLowerCase();
        if (getAvailableSauces().contains(sauce))
            this.sauce = sauce;
        else
            throw new IllegalArgumentException( sauce + " must be from the list "+getAvailableSauces());
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
