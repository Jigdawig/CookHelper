package com.qwerty123.cookhelper.Controller;


import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;

public class RecipeBookController
{
    private static RecipeBook sampleRecipeBook = RecipeBook.createSampleRecipeBook();

    public static Recipe[] getRecipesFromSampleRecipeBook()
    {
        return sampleRecipeBook.getRecipeArray();
    }

    public static Recipe getRecipe(int index)
    {
        return sampleRecipeBook.getRecipe(index);
    }

    public static void deleteRecipe(int position)
    {

    }

    public static void addNewRecipe(int index, String name, String category, String type, int prepTime, String[] steps)
    {

    }

    public static void overwriteRecipe(int index, String name, String category, String type, int prepTime, String[] steps)
    {

    }
}
