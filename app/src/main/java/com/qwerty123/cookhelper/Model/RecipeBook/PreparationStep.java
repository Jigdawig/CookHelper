package com.qwerty123.cookhelper.Model.RecipeBook;

import java.util.ArrayList;
import java.util.Scanner;

public class PreparationStep
{
    private static final char INGREDIENT_TOKEN = '#';

    private RecipeBook recipeBook;

    //String that contains the #ingredientName type of tokens.
    private String specificationString;

    //String intended for viewing. Removed the # in the specification string.
    private String displayString;

    //The name of all ingredients referenced in the specification string. Lower case.
    private String[] ingredientNames;

    private Ingredient[] ingredients;

    public PreparationStep(RecipeBook book, String specificationString)
    {
        recipeBook = book;

        this.specificationString = specificationString;

        interpretSpecificationString();
    }

    private void interpretSpecificationString()
    {
        ArrayList<String> ingredientNames = new ArrayList<String>();

        StringBuffer buffer = new StringBuffer();

        if(specificationString != null && !specificationString.isEmpty())
        {
            Scanner scanner = new Scanner(specificationString);

            while (scanner.hasNext())
            {
                String token = scanner.next();

                if(token.length() > 1 && token.charAt(0) == INGREDIENT_TOKEN)
                {
                    token = token.substring(1);
                    ingredientNames.add(token.toLowerCase());
                }

                buffer.append(" " + token);
            }
        }

        this.ingredientNames = new String[ingredientNames.size()];
        this.ingredientNames = ingredientNames.toArray(this.ingredientNames);
        displayString = buffer.toString();
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
