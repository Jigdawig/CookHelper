package com.qwerty123.cookhelper.Controller.Searching;

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
        return !ingredients.isEmpty();
    }


    public String getCategory()
    {
        return category;
    }

    public String getType()
    {
        return type;
    }

    public int getPrepTime()
    {
        return prepTime;
    }

    public boolean isRequired(String name)
    {
        return ingredients.isRequired(name);
    }

    public boolean isOptional(String name)
    {
        return ingredients.isOptional(name);
    }

    public boolean isExcluded(String name)
    {
        return ingredients.isExcluded(name);
    }
}


