package com.qwerty123.cookhelper.Model.RecipeBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        ingredients= new HashMap<>();
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

    public CulturalCategory getCategory(String categoryName)
    {
        categoryName = categoryName.toLowerCase();
        CulturalCategory category;

        if(categories.containsKey(categoryName))
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

        if(categories.containsKey(typeName))
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

        if(categories.containsKey(ingredientName))
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

    public void updateLookupTables(Recipe recipe)
    {
        CulturalCategory category = recipe.getCulturalCategory();
        MealType mealType = recipe.getMealType();
        Ingredient[] ingredients = recipe.getIngredients();

        // TODO Update the reference table.

    }

    public void addNewRecipe(Recipe recipe)
    {
        if(recipe != null)
        {
            recipes.add(recipe);
        }
    }

    public void deleteRecipe(int index)
    {
        if( index >= 0 && index < recipes.size())
        {
            recipes.remove(index);
        }
    }

    public void overwriteRecipe(int index, Recipe recipe)
    {
        if(index >= 0 && index < recipes.size())
        {
            recipes.set(index, recipe);
        }
    }

    public void setRecipeList(Recipe[] recipeArray)
    {
        this.recipes =new ArrayList<Recipe>(Arrays.asList(recipeArray));
    }


//    // SAMPLE RECIPE BOOK -- DEBUG ========================================================
//
//    public static int SAMPLE_LIST_SIZE = 20;
//
//    public static final String[] sampleCategories = {
//            "Canadian",
//            "Italian",
//            "Mexican",
//            "Japanese",
//            "Chinese",
//            "German"
//    };
//
//    public static final String[] sampleTypes = {
//            "Supper",
//            "Lunch",
//            "Breakfast",
//            "Dessert",
//            "Snack",
//            "Appetizer"
//    };
//
//    public static final String[] sampleIngredients = {
//            "potato",
//            "ground_beef",
//            "ground_pork",
//            "onion",
//            "rice",
//            "cabbage",
//            "tomato",
//            "celery",
//            "chicken_breasts",
//            "apple",
//            "pineapple",
//            "sugar"
//    };
//
//    public static RecipeBook createSampleRecipeBook()
//    {
//        RecipeBook recipeBook = RecipeBook.getInstance();
//
//        recipeBook.recipes = new ArrayList<Recipe>(SAMPLE_LIST_SIZE);
//        Random random = new Random();
//
//        for (int i = 0; i < SAMPLE_LIST_SIZE; ++i)
//        {
//            String name = String.format("Recipe%1$d", i);
//            String category = sampleCategories[random.nextInt(sampleCategories.length)];
//            String type = sampleTypes[random.nextInt(sampleTypes.length)];
//            int prepTime = 15 + random.nextInt(30);
//
//            String[] ingredients = createSampleIngredients(random);
//            String[] steps = createSampleSteps(ingredients);
//
//            recipeBook.recipes.add(i, RecipeBuilder.buildRecipe(recipeBook, name, category, type, prepTime, steps));
//        }
//
//        return recipeBook;
//    }
//
//    private static String[] createSampleSteps(String[] ingredients)
//    {
//        int numPairs = ingredients.length / 2;
//        boolean addSingle = ingredients.length % 2 == 1;
//
//        int numSteps = numPairs + (addSingle ? 1 : 0);
//
//        String[] steps = new String[numSteps];
//
//        int stepIndex = 0;
//        for (int i = 0; i <= numPairs; i += 2)
//        {
//            String[] stepIngredients = new String[2];
//
//            stepIngredients[0] = ingredients[i];
//            stepIngredients[1] = ingredients[i + 1];
//
//            steps[stepIndex] = String.format("Combine %1$s and %2$s", "#" + stepIngredients[0], "#" + stepIngredients[1]);
//            ++stepIndex;
//        }
//
//        if (addSingle)
//        {
//            steps[steps.length - 1] = String.format("Add %1$s", "#" + ingredients[ingredients.length - 1]);
//        }
//
//        return steps;
//    }
//
//    private static String[] createSampleIngredients(Random random)
//    {
//        int numIngredients = 2 + random.nextInt(4);
//
//        String[] ingredients = new String[numIngredients];
//
//        for (int i = 0; i < numIngredients; ++i)
//        {
//            int index = random.nextInt(sampleIngredients.length);
//
//            boolean different = true;
//
//            for (int j = 0; j < i; ++j)
//            {
//                different = different && ingredients[j] != sampleIngredients[index];
//            }
//
//            if (different)
//            {
//                ingredients[i] = sampleIngredients[index];
//            }
//            else
//            {
//                --i;
//            }
//        }
//
//        return ingredients;
//    }
}
