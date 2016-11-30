package com.qwerty123.cookhelper.Model.RecipeBook;

import java.util.Random;

/**
 * Created by David on 2016-11-26.
 */
public class RecipeBook
{
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
            new Ingredient("Potato"),
            new Ingredient("Ground Beef"),
            new Ingredient("Ground Pork"),
            new Ingredient("Onion"),
            new Ingredient("Rice"),
            new Ingredient("Cabbage"),
            new Ingredient("Tomato"),
            new Ingredient("Celery"),
            new Ingredient("Chicken breasts"),
            new Ingredient("Apple"),
            new Ingredient("Pineapple"),
            new Ingredient("Sugar")
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
            PreparationStep[] steps = createSampleSteps(random, ingredients);

            recipeBook.recipes[i] = new Recipe(name, category, type, prepTime, ingredients, steps);
        }

        return recipeBook;
    }

    private static PreparationStep[] createSampleSteps(Random random, Ingredient[] ingredients)
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

            steps[stepIndex] = new PreparationStep("Combine %1$s and %2$s", stepIngredients);
            ++stepIndex;
        }

        if(addSingle)
        {
            Ingredient[] singleIngredient = {ingredients[ingredients.length-1]};
            steps[steps.length-1] = new PreparationStep("Add %1$s", singleIngredient);
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

//    /**
//     * Add a recipe to the recipe book. Ensures the recipe does not have the same name as another
//     * recipe in the book.
//     * @param newRecipe The recipe to be added.
//     * @return If the recipe was added, return true. Otherwise, false.
//     */
//    public boolean addRecipe(Recipe newRecipe)
//    {
//        //Ensure there are no two recipes with the same name as recipe to be added.
//        for(Recipe recipe : recipes)
//        {
//            if(recipe.name.equals(newRecipe.name))
//            {
//               return false;
//            }
//        }
//
//        recipes.add(newRecipe);
//        return true;
//    }

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

    public Recipe[] getRecipeArray()
    {
        return recipes;
    }
}