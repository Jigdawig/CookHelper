package com.qwerty123.cookhelper.Model;

import android.content.Context;

import com.qwerty123.cookhelper.Exceptions.DuplicateRecipeException;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JSONSerialization.JSONSerializer;


/**
 * Created by Ralph on 12/1/2016.
 */
//TODO do something with the duplicate recipe exception
public class RecipeBookSaveController {

    public static void saveNewRecipe(Context context, Recipe recipe)
            throws DuplicateRecipeException
    {
        if (duplicateRecipe(context, recipe))
            throw new DuplicateRecipeException();

        JSONArray recipeList = JSONSerializer.readArray(context, getJsonRecipeListFilename(context));

        if(recipeList == null)
            recipeList = new JSONArray();

        recipeList.put(recipe.toJSON());

        JSONSerializer.writeArray(context, recipeList, getJsonRecipeListFilename(context));
    }


    public static void deleteRecipe(Context context, Recipe recipe)
    {
        int index = getIndex(context, recipe);
        if(index >= 0)
        {
            Recipe[] recipes = loadAllRecipes(context);

            if(recipes != null && recipes.length > index)
            {
                //recipes[index] = null;
                JSONArray newRecipeList = new JSONArray();

                for (int i = 0; i < recipes.length; ++i)
                {
                    if(i != index)
                    {
                        newRecipeList.put(recipes[i].toJSON());
                    }
                }

                JSONSerializer.writeArray(context, newRecipeList, getJsonRecipeListFilename(context));
            }
        }
    }

//    public static Recipe loadRecipe(Context context, int index)
//    {
//        if(index >= 0)
//        {
//            Recipe[] recipes = loadAllRecipes(context);
//
//            if(recipes != null && recipes.length > index)
//            {
//                return recipes[index];
//            }
//        }
//
//        return null;
//    }

    public static Recipe[] loadAllRecipes(Context context)
    {
        JSONArray recipeList = JSONSerializer.readArray(context, getJsonRecipeListFilename(context));
        Recipe[] recipes = null;

        if(recipeList != null)
        {
            recipes = new Recipe[recipeList.length()];

            for (int i = 0; i < recipeList.length(); ++i)
            {
                try
                {
                    recipes[i] = new Recipe(recipeList.getJSONObject(i));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return recipes;
    }



    public static void saveChangedRecipe(Context context, Recipe recipe)
            throws DuplicateRecipeException
    {
        if (duplicateRecipe(context, recipe))
            throw new DuplicateRecipeException();

        int index = getIndex(context, recipe);
        Recipe[] recipes = loadAllRecipes(context);

        if(recipes != null && recipes.length > index && index >= 0)
        {
            recipes[index] = recipe;

            JSONArray newRecipeList = new JSONArray();

            for(Recipe currentRecipe : recipes)
            {
                newRecipeList.put(currentRecipe.toJSON());
            }

            JSONSerializer.writeArray(context, newRecipeList, getJsonRecipeListFilename(context));
        }
    }

    private static int getIndex(Context context, Recipe recipe){
        int index = -1;
        Recipe[] recipes = loadAllRecipes(context);
        for (int i = 0; i < recipes.length; i++) {
            if(recipes[i].equals(recipe)) {
                index = i;
                return i;
            }
        }
        
        return index;
    }

    private static Boolean duplicateRecipe(Context context, Recipe recipe){
        Boolean duplicate = false;

        Recipe[] recipeList = loadAllRecipes(context);

        for (Recipe currentRecipe: recipeList){
                if (currentRecipe.getName().equals(recipe.getName()))
                    return true;
            }

        for (Recipe currentRecipe: recipeList){
            //If the name is the same but all other details are equivalent
            if (currentRecipe.getCulturalCategory().equals(recipe.getCulturalCategory())
                    &&currentRecipe.getMealType().equals(recipe.getMealType())
                    &&currentRecipe.getPreparationTime() == recipe.getPreparationTime()
                    &&currentRecipe.getIngredientsEnumeration().equals(recipe.getIngredientsEnumeration())
                    &&currentRecipe.getPreparationStepEnumeration().equals(recipe.getIngredientsEnumeration()))
                return true;
        }

        return duplicate;
    }


    private static String getJsonRecipeListFilename(Context context)
    {
        return context.getResources().getString(R.string.json_recipe_savefile);
    }


}
