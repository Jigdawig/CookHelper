package com.qwerty123.cookhelper.Model.RecipeBook;

/**
 * Created by David on 2016-11-26.
 */
public class CulturalCategory
{
    private String name;

    public CulturalCategory(String name)
    {
        if(name.length() == 0)
        {
            throw new IllegalArgumentException("Empty string passed as category name.");
        }

        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
