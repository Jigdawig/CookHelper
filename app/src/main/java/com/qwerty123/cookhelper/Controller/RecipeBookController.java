package com.qwerty123.cookhelper.Controller;

import android.support.annotation.NonNull;

import com.qwerty123.cookhelper.Model.RecipeBook.CulturalCategory;
import com.qwerty123.cookhelper.Model.RecipeBook.MealType;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by David on 2016-11-26.
 */
public class RecipeBookController
{
    private static RecipeBook sampleRecipeBook = RecipeBook.createSampleRecipeBook();

    public static Recipe[] getRecipesFromSampleRecipeBook()
    {
        return sampleRecipeBook.getRecipeArray();
    }

    public static Recipe getRecipe(String recipeName)
    {
        return sampleRecipeBook.getRecipe(recipeName);
    }

    public static Recipe getRecipe(int index)
    {
        return sampleRecipeBook.getRecipe(index);
    }
}
