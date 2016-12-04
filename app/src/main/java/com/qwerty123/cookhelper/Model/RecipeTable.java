package com.qwerty123.cookhelper.Model;

import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.R;

import java.util.ArrayList;
import java.util.HashSet;

public class RecipeTable
{
    public HashSet<Recipe> recipes;

    public RecipeTable()
    {
        recipes = new HashSet<>();
    }

    public void addRecipe(Recipe recipe)
    {
        recipes.add(recipe);
    }

}