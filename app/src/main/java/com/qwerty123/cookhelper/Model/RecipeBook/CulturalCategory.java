package com.qwerty123.cookhelper.Model.RecipeBook;

import com.qwerty123.cookhelper.Model.RecipeTable;
import com.qwerty123.cookhelper.Utils.Utils;

public class CulturalCategory extends RecipeTable
{
    private String name;
    private String displayName;

    public CulturalCategory(String name)
    {
        if(name.length() == 0)
        {
            throw new IllegalArgumentException("Empty string passed as category name.");
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
