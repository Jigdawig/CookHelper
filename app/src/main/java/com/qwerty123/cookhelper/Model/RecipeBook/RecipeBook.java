package com.qwerty123.cookhelper.Model.RecipeBook;

import android.content.Context;

import com.qwerty123.cookhelper.Exceptions.DuplicateRecipeException;
import com.qwerty123.cookhelper.Model.RecipeBookSaveController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RecipeBook
{
    public Map<String,ArrayList> lookUpTable;
    public static int SAMPLE_LIST_SIZE = 20;

    public static final CulturalCategory[] sampleCategories = {
            new CulturalCategory("Canadian"),
            new CulturalCategory("Italian"),
            new CulturalCategory("Mexican"),
            new CulturalCategory("Japanese"),
            new CulturalCategory("Chinese"),
            new CulturalCategory("German")
    };

    public static final MealType[] sampleTypes = {
            new MealType("Supper"),
            new MealType("Lunch"),
            new MealType("Breakfast"),
            new MealType("Dessert"),
            new MealType("Snack"),
            new MealType("Appetizer")
    };

    public static final Ingredient[] sampleIngredients = {
            new Ingredient("potato"),
            new Ingredient("ground_beef"),
            new Ingredient("ground_pork"),
            new Ingredient("onion"),
            new Ingredient("rice"),
            new Ingredient("cabbage"),
            new Ingredient("tomato"),
            new Ingredient("celery"),
            new Ingredient("chicken_breasts"),
            new Ingredient("apple"),
            new Ingredient("pineapple"),
            new Ingredient("sugar")
    };

    private Recipe[] recipes;

    public RecipeBook()
    {
        recipes = new Recipe[10];
    }

    public static RecipeBook createSampleRecipeBook()
    {
        RecipeBook recipeBook = new RecipeBook();

        recipeBook.recipes = new Recipe[SAMPLE_LIST_SIZE];
        Random random = new Random();

        for (int i = 0; i < SAMPLE_LIST_SIZE; ++i)
        {
            String name = String.format("Recipe%1$d", i);
            CulturalCategory category = sampleCategories[random.nextInt(sampleCategories.length)];
            MealType type = sampleTypes[random.nextInt(sampleTypes.length)];
            int prepTime = 15 + random.nextInt(30);

            Ingredient[] ingredients = createSampleIngredients(random);
            PreparationStep[] steps = createSampleSteps(recipeBook, ingredients);

            recipeBook.recipes[i] = new Recipe(name, category, type, prepTime, ingredients, steps);
        }

        return recipeBook;
    }

    private static PreparationStep[] createSampleSteps(RecipeBook recipeBook, Ingredient[] ingredients)
    {
        int numPairs = ingredients.length/2;
        boolean addSingle = ingredients.length % 2 == 1;

        int numSteps = numPairs + (addSingle ? 1 : 0);

        PreparationStep[] steps = new PreparationStep[numSteps];

        int stepIndex = 0;
        for(int i = 0; i <= numPairs; i += 2)
        {
            Ingredient[] stepIngredients = new Ingredient[2];

            stepIngredients[0] = ingredients[i];
            stepIngredients[1] = ingredients[i + 1];

            steps[stepIndex] = new PreparationStep(recipeBook,
                    String.format("Combine %1$s and %2$s",
                            "#" + stepIngredients[0],
                            "#" + stepIngredients[1]));

            ++stepIndex;
        }

        if(addSingle)
        {
            Ingredient[] singleIngredient = {ingredients[ingredients.length-1]};
            steps[steps.length-1] = new PreparationStep(recipeBook,
                    String.format("Add %1$s", "#" + singleIngredient));
        }

        return steps;
    }

    private static Ingredient[] createSampleIngredients(Random random)
    {
        int numIngredients = 2 + random.nextInt(3);

        Ingredient[] ingredients = new Ingredient[numIngredients];

        for(int i = 0; i < numIngredients; ++i)
        {
            int index = random.nextInt(sampleIngredients.length);

            boolean different = true;

            for(int j = 0; j < i; ++j)
            {
                different = different && ingredients[j] != sampleIngredients[index];
            }

            if(different)
            {
                ingredients[i] = sampleIngredients[index];
            }
            else
            {
                --i;
            }
        }

        return ingredients;
    }

    //NOTE: For the dave and delete
    //NOTE: It might be better to have these methods
    //NOTE: In RecipeBookController and have an update
    //NOTE: method in RecipeBook that changes the
    //NOTE: the Recipe[] array.
    //NOTE: I don't really like that it calls RecipeBookSaveController


    /**
     * Add a recipe to the recipe book.
     * @param newRecipe The recipe to be added.
     * @throws DuplicateRecipeException
     * If the recipe was added alters Recipe[] recipes to match
     * new state, else the exception is thrown
     */
    public void addRecipe(Context context, Recipe newRecipe)
        throws DuplicateRecipeException {
    RecipeBookSaveController.saveNewRecipe(context, newRecipe);
        recipes = RecipeBookSaveController.loadAllRecipes(context);
    }

    /**
     * Removes a recipe from the recipe book.
     * @param recipe The recipe to be deleted.
     * Alters Recipe[] recipes to match new state
     */
    public void deleteRecipe(Context context, Recipe recipe){
        RecipeBookSaveController.deleteRecipe(context, recipe);
        recipes = RecipeBookSaveController.loadAllRecipes(context);
    }

    public Recipe getRecipe(String recipeName)
    {
        for (Recipe recipe : recipes)
        {
            if (recipe.getName().equals(recipeName))
            {
                return recipe;
            }
        }

        return null;
    }

    public Recipe getRecipe(int index)
    {
        if (index < recipes.length)
        {
            return recipes[index];
        }
        else
        {
            return null;
        }
    }

    private void createLookUpTable(){
        lookUpTable = new HashMap<String, ArrayList>();
        //List<Recipe> recipeList = new ArrayList<Recipe>();
        List<String> MTKeyList = new ArrayList<String>();
        List<String> CCKeyList = new ArrayList<String>();
        List<String> IngredientKeyList = new ArrayList<String>();

        for (Recipe recipe: recipes) {
            MealType mealtype = recipe.getMealType();
            if(!lookUpTable.containsKey(mealtype.toString())){
                MTKeyList.add(mealtype.toString());
            }
            CulturalCategory culturalCat = recipe.getCulturalCategory();
            if(!lookUpTable.containsKey(culturalCat.toString())){
                CCKeyList.add(culturalCat.toString());
            }

            String[] ingredients = recipe.getIngredientsEnumeration().split(", ");
            for (String ingredient: ingredients) {
                if(!lookUpTable.containsKey(ingredient)){
                    IngredientKeyList.add(ingredient);
                }
            }
        }

        for (String key: MTKeyList) {
            List<Recipe> recipeList = new ArrayList<Recipe>();
            for (Recipe recipe: recipes) {
                if (key.equals(recipe.getMealType().toString())) {
                    recipeList.add(recipe);
                }
                lookUpTable.put(key, (ArrayList) recipeList);
            }
        }

        for (String key: CCKeyList) {
            List<Recipe> recipeList = new ArrayList<Recipe>();
            for (Recipe recipe: recipes) {
                if (key.equals(recipe.getCulturalCategory().toString())) {
                    recipeList.add(recipe);
                }
                lookUpTable.put(key, (ArrayList) recipeList);
            }
        }

        for (String key: IngredientKeyList) {
            List<Recipe> recipeList = new ArrayList<Recipe>();
            for (Recipe recipe: recipes) {
                String[] ingredients = recipe.getIngredientsEnumeration().split(", ");
                if (Arrays.asList(ingredients).contains(key)) {
                    recipeList.add(recipe);
                }
                lookUpTable.put(key, (ArrayList) recipeList);
            }
        }
    }

    public Recipe[] getRecipeArray()
    {
        return recipes;
    }
}
