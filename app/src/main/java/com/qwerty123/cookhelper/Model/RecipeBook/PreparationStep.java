package com.qwerty123.cookhelper.Model.RecipeBook;

/**
 * Created by David on 2016-11-26.
 */
public class PreparationStep
{
    String instructions;
    Ingredient[] ingredients;

    public PreparationStep(String instructions, Ingredient[] ingredients)
    {
        this.instructions = String.format(instructions, ingredients);
        this.ingredients = ingredients;
    }

    @Override
    public String toString()
    {
        return instructions;
    }
}
