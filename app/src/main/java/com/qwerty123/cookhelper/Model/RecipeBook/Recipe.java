package com.qwerty123.cookhelper.Model.RecipeBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JSONSerialization.JSONSerializable;

public class Recipe implements JSONSerializable
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

    //Constructor initialize recipe from a json Object
    public Recipe(JSONObject recipe)
    {
        initializeFromJSON(recipe);
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
            buffer.append(preparationSteps[i].getDisplayString());

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

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray ingredientArray = new JSONArray();
        JSONArray prepArray = new JSONArray();

        try
        {
            jsonObject.put("Name", name);
            jsonObject.put("CulturalCategory", culturalCategory.toString());
            jsonObject.put("MealType", mealType.toString());
            jsonObject.put("PreparationTime", preparationTime);

            for (Ingredient ingredient: ingredients) {
                String ingredientName = ingredient.toString();
                ingredientArray.put(ingredientName);
            }
            jsonObject.put("Ingredients", ingredientArray);

            for (PreparationStep prepStep: preparationSteps) {
                String instruction = prepStep.toString();
                prepArray.put(instruction);
            }
            jsonObject.put("PreparationSteps", prepArray);
        }
        catch (JSONException e)
        {
            System.err.println("Error while converting Recipe details to JsonObject");
            e.printStackTrace();
        }

        return jsonObject;
    }

    @Override
    public void initializeFromJSON(JSONObject jsonObject) {
        if (jsonObject != null)
        {
            try
            {
                name = jsonObject.getString("Name");
                culturalCategory = new CulturalCategory(jsonObject.getString("CulturalCategory"));
                mealType = new MealType(jsonObject.getString("MealType"));
                preparationTime = jsonObject.getInt("PreparationTime");

                JSONArray ingredientArray = jsonObject.getJSONArray("Ingredients");
                ingredients = new Ingredient[ingredientArray.length()];
                for (int i = 0; i < ingredientArray.length(); i++) {
                    ingredients[i] = new Ingredient((String)ingredientArray.get(i));
                }

                JSONArray prepSteps = jsonObject.getJSONArray("PreparationSteps");
                preparationSteps = new PreparationStep[prepSteps.length()];
                for (int i = 0; i < prepSteps.length(); i++) {
                    preparationSteps[i] = new PreparationStep((String)prepSteps.get(i));
                }


            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new NullPointerException("JSON Object is null. Cannot Initialize");
        }

    }
}
