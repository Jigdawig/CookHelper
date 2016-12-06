package com.qwerty123.cookhelper.Controller;

import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;
import com.qwerty123.cookhelper.R;
import com.qwerty123.cookhelper.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import com.qwerty123.cookhelper.Utils.JSONSerialization.JSONSerializer;

public class RecipeBookSaveController
{
   public static void loadRecipesFromFile(RecipeBook recipeBook)
    {
        loadAllRecipesToRecipeBook(recipeBook);

        for (Recipe recipe : recipeBook.getRecipeArray())
        {
            recipeBook.addToLookupTables(recipe);
        }
    }

    public static void loadAllRecipesToRecipeBook(RecipeBook recipeBook)
    {
        JSONArray recipeList = JSONSerializer.readArray(Utils.getApplicationContext(), getJsonRecipeListFilename());

        Recipe[] recipes = null;

        if (recipeList != null)
        {
            recipes = new Recipe[recipeList.length()];

            for (int i = 0; i < recipeList.length(); ++i)
            {
                try
                {
                    recipes[i] = new Recipe(recipeBook, recipeList.getJSONObject(i));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            recipes = new Recipe[0];
        }

        recipeBook.setRecipeList(recipes);
    }

    private static String getJsonRecipeListFilename()
    {
        return Utils.getApplicationContext().getResources().getString(R.string.recipe_book_filename);
    }

    public static void saveRecipes(RecipeBook recipeBook)
    {
        Recipe[] recipes = recipeBook.getRecipeArray();

        JSONArray jsonArray = new JSONArray();

        for(Recipe recipe : recipes)
        {
            jsonArray.put(recipe.toJSON());
        }

        JSONSerializer.writeArray(Utils.getApplicationContext(), jsonArray, getJsonRecipeListFilename());
    }
}
