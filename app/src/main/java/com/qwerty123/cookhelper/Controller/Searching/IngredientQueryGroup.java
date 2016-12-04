package com.qwerty123.cookhelper.Controller.Searching;

import java.util.ArrayList;
import java.util.List;

public class IngredientQueryGroup
{
    ArrayList<String> required;
    ArrayList<String> optional;
    ArrayList<String> exclude;

    public IngredientQueryGroup()
    {
        required = new ArrayList<>();
        optional = new ArrayList<>();
        exclude = new ArrayList<>();
    }

    public void addIngredientRequired(String ingredientName)
    {
        required.add(ingredientName);
    }

    public void addIngredientOptional(String ingredientName)
    {
        optional.add(ingredientName);
    }

    public void addIngredientExclude(String ingredientName)
    {
        exclude.add(ingredientName);
    }

//    public boolean isRequired(String name)
//    {
//        return required.contains(name);
//    }
//
//    public boolean isOptional(String name)
//    {
//        return optional.contains(name);
//    }
//
//    public boolean isExcluded(String name)
//    {
//        return exclude.contains(name);
//    }

    public boolean isEmpty()
    {
        return required.isEmpty() && optional.isEmpty() && exclude.isEmpty();
    }
}
