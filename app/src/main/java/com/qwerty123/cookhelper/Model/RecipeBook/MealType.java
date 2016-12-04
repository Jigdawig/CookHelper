package com.qwerty123.cookhelper.Model.RecipeBook;

import com.qwerty123.cookhelper.Model.RecipeTable;
import com.qwerty123.cookhelper.Utils.Utils;

public class MealType extends RecipeTable
{
    private String name;
    private String displayName;

    public MealType(String name)
    {
        if (name.length() == 0)
        {
            throw new IllegalArgumentException("Empty string passed as type name.");
        }

        this.name = name;
        displayName = Utils.createDisplayName(name);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getDisplayName()
    {
        return displayName;
    }
}
