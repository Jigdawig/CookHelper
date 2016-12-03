package com.qwerty123.cookhelper.Model.RecipeBook;

public class Ingredient
{
    private String name;

    public Ingredient(String name)
    {
        if(name.length() == 0)
        {
            throw new IllegalArgumentException("Empty string passed as ingredient name.");
        }

        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
