package com.qwerty123.cookhelper.Model.RecipeBook;

public class MealType
{
    private String name;

    public MealType(String name)
    {
        if(name.length() == 0)
        {
            throw new IllegalArgumentException("Empty string passed as type name.");
        }

        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
