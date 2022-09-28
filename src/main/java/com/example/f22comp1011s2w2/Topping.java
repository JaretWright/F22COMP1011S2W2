package com.example.f22comp1011s2w2;

import java.util.Arrays;
import java.util.List;

public class Topping {
    private int toppingID;
    private String name, category;

    public Topping(int toppingID, String name, String category) {
        setToppingID(toppingID);
        setName(name);
        setCategory(category);
    }

    public int getToppingID() {
        return toppingID;
    }

    public void setToppingID(int toppingID) {
        if (toppingID>0)
            this.toppingID = toppingID;
        else
            throw new IllegalArgumentException("toppingID must be greater than 0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        if (!name.isBlank())
            this.name = name;
        else
            throw new IllegalArgumentException("name cannot be blank");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        List<String> categories = Arrays.asList("veggie","meat","dairy");
        if (categories.contains(category))
            this.category = category;
        else
            throw new IllegalArgumentException("category must be veggie, meat or diary");
    }

    public String toString()
    {
        return name;
    }
}
