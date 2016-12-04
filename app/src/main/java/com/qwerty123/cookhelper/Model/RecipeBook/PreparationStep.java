package com.qwerty123.cookhelper.Model.RecipeBook;

import java.util.ArrayList;
import java.util.Scanner;

public class PreparationStep
{

    private RecipeBook recipeBook;

    //String that contains the #ingredientName type of tokens.
    private String specificationString;

    //String intended for viewing. Removed the # in the specification string.
    private String displayString;

    private Ingredient[] ingredients;

    public PreparationStep(String specificationString, String displayString, Ingredient[] ingredients)
    {
        this.specificationString = specificationString;
        this.displayString = displayString;
        this.ingredients = ingredients;
    }

    public String getDisplayString()
    {
        return displayString;
    }

    public String getSpecificationString()
    {
        return specificationString;
    }
}
