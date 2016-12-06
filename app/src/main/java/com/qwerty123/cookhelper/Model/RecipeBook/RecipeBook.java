package com.qwerty123.cookhelper.Model.RecipeBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RecipeBook
{
    private static RecipeBook recipeBook;

    public Map<String, CulturalCategory> categories;
    public Map<String, MealType> types;
    public Map<String, Ingredient> ingredients;

    private ArrayList<Recipe> recipes;

    public static RecipeBook getInstance()
    {
        if (recipeBook == null)
        {
            recipeBook = new RecipeBook();
        }

        return recipeBook;
    }

    private RecipeBook()
    {
        recipes = new ArrayList<Recipe>(50);

        categories = new HashMap<>();
        types = new HashMap<>();
        ingredients = new HashMap<>();
    }

    public Recipe[] getRecipeArray()
    {
        Recipe[] recipesArray = new Recipe[recipes.size()];
        return recipes.toArray(recipesArray);
    }

    public Recipe getRecipe(int index)
    {
        if (index < recipes.size())
        {
            return recipes.get(index);
        }
        else
        {
            return null;
        }
    }

    public Recipe getRecipe(String name)
    {
        for (int i = 0; i < recipes.size(); i++)
        {
            Recipe recipe = recipes.get(i);
            String recipeName = recipe.getName();
            boolean nameEquals = recipeName.equals(name);

            if (nameEquals)
            {
                return recipe;
            }
        }

        return null;
    }

    public CulturalCategory getCategory(String categoryName)
    {
        categoryName = categoryName.toLowerCase();
        CulturalCategory category;

        if (categories.containsKey(categoryName))
        {
            category = categories.get(categoryName);
        }
        else
        {
            category = new CulturalCategory(categoryName);
            categories.put(categoryName, category);
        }

        return category;
    }

    public MealType getMealType(String typeName)
    {
        typeName = typeName.toLowerCase();
        MealType type;

        if (types.containsKey(typeName))
        {
            type = types.get(typeName);
        }
        else
        {
            type = new MealType(typeName);
            types.put(typeName, type);
        }

        return type;
    }

    public Ingredient getIngredient(String ingredientName)
    {
        ingredientName = ingredientName.toLowerCase();
        Ingredient ingredient;

        if (ingredients.containsKey(ingredientName))
        {
            ingredient = ingredients.get(ingredientName);
        }
        else
        {
            ingredient = new Ingredient(ingredientName);
            ingredients.put(ingredientName, ingredient);
        }

        return ingredient;
    }

    public void addToLookupTables(Recipe recipe)
    {
        CulturalCategory category = recipe.getCulturalCategory();
        MealType mealType = recipe.getMealType();
        Ingredient[] ingredients = recipe.getIngredients();

        category.addRecipe(recipe);
        mealType.addRecipe(recipe);

        for (Ingredient ingredient : ingredients)
        {
            ingredient.addRecipe(recipe);
        }
    }

    private void removeFromLookupTables(Recipe recipe)
    {
        CulturalCategory category = recipe.getCulturalCategory();
        MealType mealType = recipe.getMealType();
        Ingredient[] ingredients = recipe.getIngredients();

        category.removeRecipe(recipe);
        mealType.removeRecipe(recipe);

        for (Ingredient ingredient : ingredients)
        {
            ingredient.removeRecipe(recipe);
        }
    }

    public void addNewRecipe(Recipe recipe)
    {
        if (recipe != null)
        {
            recipes.add(recipe);
            addToLookupTables(recipe);
        }
    }

    public void deleteRecipeAtPosition(int index)
    {
        if (index >= 0 && index < recipes.size())
        {
            Recipe oldRecipe = recipes.get(index);
            removeFromLookupTables(oldRecipe);
            recipes.remove(index);
        }
    }

    public void overwriteRecipeAtPosition(int index, Recipe recipe)
    {
        if (index >= 0 && index < recipes.size())
        {
            Recipe oldRecipe = recipes.get(index);
            removeFromLookupTables(oldRecipe);
            recipes.set(index, recipe);
            addToLookupTables(recipe);
        }
    }


    public void setRecipeList(Recipe[] recipeArray)
    {
        this.recipes = new ArrayList<Recipe>(Arrays.asList(recipeArray));
    }

    public boolean hasCategory(String category)
    {
        return categories.containsKey(category);
    }

    public boolean hasType(String type)
    {
        return types.containsKey(type);
    }

    public boolean hasIngredient(String ingredient)
    {
        return ingredients.containsKey(ingredient);
    }

    public HashSet<Recipe> getRecipesPreparedBelow(int prepTime)
    {
        HashSet<Recipe> recipes = new HashSet<>();

        for (Recipe recipe : this.recipes)
        {
            if (recipe.getPreparationTime() <= prepTime)
            {
                recipes.add(recipe);
            }
        }

        return recipes;
    }

    public int getRecipeIndex(String recipeName)
    {
        for (int i = 0; i < recipes.size(); ++i)
        {
            if (recipes.get(i).getName().equals(recipeName))
            {
                return i;
            }
        }

        return -1;
    }
}