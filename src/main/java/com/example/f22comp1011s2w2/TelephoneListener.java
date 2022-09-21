package com.example.f22comp1011s2w2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TelephoneListener implements ChangeListener<String> {

    @Override
    public void changed(ObservableValue<? extends String> observableValue,
                        String oldValue, String newValue) {
        try{
            Integer.parseInt(oldValue);
        }catch (Exception e)
        {
            System.out.println("old value: " + oldValue + " new Value: "+ newValue);
        }
    }
}
