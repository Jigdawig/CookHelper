package com.qwerty123.cookhelper.Controller.Searching;


import java.util.ArrayList;

public class Query
{
    private String name;
    private String category;
    private String type;
    private int prepTime;
    private IngredientQueryGroup ingredients;

    public Query(String name, String category, String type, int prepTime, IngredientQueryGroup ingredients)
    {
        this.name = name;
        this.category = category;
        this.type = type;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasNameCriteria()
    {
        return name != null && !name.isEmpty();
    }

    public boolean hasCategoryCriteria()
    {
        return category != null && !category.isEmpty();
    }

    public boolean hasTypeCriteria()
    {
        return type != null && !type.isEmpty();
    }

    public boolean hasPrepTimeCriteria()
    {
        return prepTime != 0;
    }

    public boolean hasIngredientCriteria()
    {
        return ingredients != null && !ingredients.isEmpty();
    }

    public boolean hasRequiredIngredients()
    {
        return !ingredients.required.isEmpty();
    }

    public ArrayList<String> getRequiredIngredients()
    {
        return ingredients.required;
    }

    public boolean hasOptionalIngredients()
    {
        return !ingredients.optional.isEmpty();
    }

    public ArrayList<String> getOptionalIngredients()
    {
        return ingredients.optional;
    }

    public boolean hasExcludedIngredients()
    {
        return !ingredients.exclude.isEmpty();
    }

    public ArrayList<String> getExcludedIngredients()
    {
        return ingredients.exclude;
    }

    public String getCategoryName()
    {
        return category;
    }

    public String getTypeName()
    {
        return type;
    }

    public int getPrepTime()
    {
        return prepTime;
    }
}


