package com.qwerty123.cookhelper.Model;

import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;

public class RecipeBookController
{
    //NOTE: You can now load the stored recipes from RecipeBookSaveController
    //NOTE: We can store a set of default recipes there as a base data
    //NOTE: This way RecipeBook's constructor can just take in an array of arbitrary size
    //NOTE: Once we do that. I imagine this Sample Recipe functionality will not be needed

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
