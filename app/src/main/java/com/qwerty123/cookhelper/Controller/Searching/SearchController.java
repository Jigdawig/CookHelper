package com.qwerty123.cookhelper.Controller.Searching;

import android.support.v4.util.Pair;

import com.qwerty123.cookhelper.Model.RecipeBook.CulturalCategory;
import com.qwerty123.cookhelper.Model.RecipeBook.Ingredient;
import com.qwerty123.cookhelper.Model.RecipeBook.MealType;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBook.RecipeBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SearchController
{
    static private ArrayList<Recipe> lastResult = null;

    public static ArrayList<Recipe> performSearch(Query query)
    {
        ArrayList<Recipe> results = new ArrayList<>();

        RecipeBook recipeBook = RecipeBook.getInstance();

        if (query.hasNameCriteria())
        {
            results.add(recipeBook.getRecipe(query.getName()));
            return results;
        }

        HashSet<Recipe> partialResults = new HashSet<>();

        if (query.hasCategoryCriteria())
        {
            if (recipeBook.hasCategory(query.getCategoryName()))
            {
                CulturalCategory category = recipeBook.getCategory(query.getCategoryName());

                if (partialResults.isEmpty())
                {
                    partialResults.addAll(category.recipes);
                }
                else
                {
                    partialResults.retainAll(category.recipes);
                }
            }
        }

        if (query.hasTypeCriteria())
        {
            if (recipeBook.hasType(query.getTypeName()))
            {
                MealType type = recipeBook.getMealType(query.getTypeName());

                if (partialResults.isEmpty())
                {
                    partialResults.addAll(type.recipes);
                }
                else
                {
                    partialResults.retainAll(type.recipes);
                }
            }
        }

        if (query.hasPrepTimeCriteria())
        {
            HashSet<Recipe> prepTimeRecipes = recipeBook.getRecipesPreparedBelow(query.getPrepTime());

            if (partialResults.isEmpty())
            {
                partialResults.addAll(prepTimeRecipes);
            }
            else
            {
                partialResults.retainAll(prepTimeRecipes);
            }
        }

        if (query.hasIngredientCriteria())
        {
            //Handle required ingredients first

            if (query.hasRequiredIngredients())
            {
                ArrayList<String> ingredientNames = query.getRequiredIngredients();

                for (String ingredientName : ingredientNames)
                {
                    if (recipeBook.hasIngredient(ingredientName))
                    {
                        if (partialResults.isEmpty())
                        {
                            partialResults.addAll(recipeBook.getIngredient(ingredientName).recipes);
                        }
                        else
                        {
                            partialResults.retainAll(recipeBook.getIngredient(ingredientName).recipes);
                        }
                    }
                }
            }

            //Exclusion of ingredients
            if (query.hasExcludedIngredients())
            {
                ArrayList<String> ingredientNames = query.getExcludedIngredients();

                for (String ingredientName : ingredientNames)
                {
                    if (recipeBook.hasIngredient(ingredientName))
                    {
                        if (!partialResults.isEmpty())
                        {
                            partialResults.removeAll(recipeBook.getIngredient(ingredientName).recipes);
                        }
                    }
                }
            }

            // At this point, we have grabbed all recipes with required ingredients and excluded as necessary.
            // Now we sort according to relevance, placing those with additional ingredients higher.
            PriorityQueue<Pair<Integer, Recipe>> recipeQueue = new PriorityQueue<>();

            if (query.hasOptionalIngredients())
            {
                ArrayList<String> ingredientNames = query.getExcludedIngredients();

                ArrayList<Ingredient> optionalIngredients = new ArrayList<>();

                for (String ingredientName : ingredientNames)
                {
                    if (recipeBook.hasIngredient(ingredientName))
                    {
                        optionalIngredients.add(recipeBook.getIngredient(ingredientName));
                    }
                }

                for (Recipe recipe : partialResults)
                {
                    int priority = 0;

                    for (Ingredient ingredient : optionalIngredients)
                    {
                        if (ingredient.recipes.contains(recipe))
                        {
                            ++priority;
                        }
                    }

                    recipeQueue.add(new Pair<Integer, Recipe>(priority, recipe));
                }
            }

            while(!recipeQueue.isEmpty())
            {
                results.add(0, recipeQueue.remove().second);
            }
        }

        lastResult = results;
        return results;
    }

    public static ArrayList<Recipe> getLastResults()
    {
        return lastResult;
    }
}
