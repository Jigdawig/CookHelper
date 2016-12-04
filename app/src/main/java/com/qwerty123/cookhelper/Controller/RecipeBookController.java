package com.qwerty123.cookhelper.Controller;

import com.qwerty123.cookhelper.Exceptions.DuplicateRecipeException;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;

public class RecipeBookController
{
    private static RecipeBook recipeBook = null;

    public static RecipeBook getRecipeBook()
    {
        if(recipeBook == null)
        {
            recipeBook = RecipeBook.getInstance();

            RecipeBookSaveController.loadRecipesFromFile(recipeBook);
        }

        return recipeBook;
    }

    public static Recipe getRecipe(int index)
    {
        return recipeBook.getRecipe(index);
    }

    public static void deleteRecipe(int position)
    {
        recipeBook.deleteRecipe(position);
        RecipeBookSaveController.saveRecipes(recipeBook);
    }

    public static void addNewRecipe(String name, String category, String type, int prepTime, String[] steps)
    {
        Recipe recipe = RecipeBuilder.buildRecipe(recipeBook, name, category, type, prepTime, steps);
        recipeBook.addNewRecipe(recipe);
        RecipeBookSaveController.saveRecipes(recipeBook);
    }

    public static void overwriteRecipe(int index, String name, String category, String type, int prepTime, String[] steps)
    {
        Recipe recipe = RecipeBuilder.buildRecipe(recipeBook, name, category, type, prepTime, steps);
        recipeBook.overwriteRecipe(index, recipe);
        RecipeBookSaveController.saveRecipes(recipeBook);
    }



    // Sample recipe book code ===============================================================
//    private static RecipeBook sampleRecipeBook = RecipeBook.createSampleRecipeBook();

//    public static Recipe[] getRecipesFromSampleRecipeBook()
//    {
//        return sampleRecipeBook.getRecipeArray();
//    }
}
