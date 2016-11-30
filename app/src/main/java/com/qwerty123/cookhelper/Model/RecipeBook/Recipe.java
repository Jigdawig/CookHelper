package com.qwerty123.cookhelper.Model.RecipeBook;

public class Recipe
{
    private String name;
    private CulturalCategory culturalCategory;
    private MealType mealType;
    private int preparationTime;

    private Ingredient[] ingredients;
    private PreparationStep[] preparationSteps;


    public Recipe(String name, CulturalCategory culturalCategory, MealType mealType, int preparationTime,
                  Ingredient[] ingredients, PreparationStep[] preparationSteps)
    {
        this.name = name;
        this.culturalCategory = culturalCategory;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.ingredients = ingredients;
        this.preparationSteps = preparationSteps;
    }

    public String getName()
    {
        return name;
    }

    public CulturalCategory getCulturalCategory()
    {
        return culturalCategory;
    }

    public MealType getMealType()
    {
        return mealType;
    }

    public int getPreparationTime()
    {
        return preparationTime;
    }

    public String getIngredientsEnumeration()
    {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < ingredients.length; ++i)
        {
            buffer.append(ingredients[i].toString());

            if(i != ingredients.length - 1)
            {
                buffer.append(", ");
            }
        }

        return buffer.toString();
    }

    public String getPreparationStepEnumeration()
    {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < preparationSteps.length; ++i)
        {
            buffer.append(preparationSteps[i].toString());

            if(i != preparationSteps.length - 1)
            {
                buffer.append("\n");
            }
        }

        return buffer.toString();
    }

    public int getNumberSteps()
    {
        return preparationSteps.length;
    }

    public PreparationStep[] getPreparationSteps()
    {
        return preparationSteps;
    }
}
