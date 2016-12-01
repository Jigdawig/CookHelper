package com.qwerty123.cookhelper.Controller.Searching;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.qwerty123.cookhelper.Utils;

import java.util.List;
import java.util.Scanner;

public class QueryBuilder
{
    String nameCriteria;
    String categoryCriteria;
    String typeCriteria;
    int prepTimeCriteria;
    IngredientQueryGroup ingredientQueryGroup;

    //Constants for parsing
    final char notToken = '-';
    final char andToken = '&';
    final char orToken = '+';

    public QueryBuilder(String name, String category, String type, int prepTime)
    {
        nameCriteria = name;
        categoryCriteria = category;
        typeCriteria = type;
        prepTimeCriteria = prepTime;
        ingredientQueryGroup = null;
    }

    public Query buildQuery()
    {
        return new Query(nameCriteria, categoryCriteria, typeCriteria, prepTimeCriteria, ingredientQueryGroup);
    }


    public boolean parseIngredientCriteria(String ingredientCriteria)
    {
        if(ingredientCriteria != null && !ingredientCriteria.isEmpty())
        {
            Scanner scanner = new Scanner(ingredientCriteria);

            IngredientQueryGroup ingredientQueryGroup = new IngredientQueryGroup();

            while (scanner.hasNext())
            {
                String token = scanner.next();
                char operator = 0;
                String ingredientName = null;

                if (!token.isEmpty())
                {
                    boolean parseOperator = checkParseOperator(token);

                    if (parseOperator)
                    {
                        operator = getOperator(token);

                        if (operator == 0)
                        {
                            return false;
                        }
                        else
                        {
                            token = removeOperatorFromToken(token);
                        }
                    }

                    boolean parseWord = checkParseIngredient(token);

                    if (parseWord)
                    {
                        if (token.length() > 1 && Utils.isAlpha(token))
                        {
                            ingredientName = token;
                        }
                        else
                        {
                            return false;
                        }

                        if (operator == 0)
                        {
                            operator = andToken;
                        }
                    }

                    if (operator != 0 && (token == null || token.isEmpty()))
                    {
                        if (scanner.hasNext())
                        {
                            String ingredient = scanner.next();
                            if (Utils.isAlpha(ingredient))
                            {
                                ingredientName = ingredient;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                }

                if (operator != 0 && ingredientName != null && !ingredientName.isEmpty())
                {
                    switch (operator)
                    {
                        case notToken:
                            ingredientQueryGroup.addIngredientExclude(ingredientName);
                            break;
                        case andToken:
                            ingredientQueryGroup.addIngredientRequired(ingredientName);
                            break;
                        case orToken:
                            ingredientQueryGroup.addIngredientOptional(ingredientName);
                            break;
                    }
                }
            }

            this.ingredientQueryGroup = ingredientQueryGroup;
            return true;
        }
        else
        {
            return true;
        }
    }

    @NonNull
    private String removeOperatorFromToken(String token)
    {
        token = token.length() > 1 ? token.substring(1) : "";
        return token;
    }

    private char getOperator(String token)
    {
        char operator;
        switch (token.charAt(0))
        {
            case notToken:
                operator = notToken;
                break;
            case andToken:
                operator = andToken;
                break;
            case orToken:
                operator = orToken;
                break;
            default:
                operator = 0;
                break;
        }
        return operator;
    }

    private boolean checkParseIngredient(String token)
    {
        return token.length() > 1;
    }

    private boolean checkParseOperator(String token)
    {
        char c;

        if (token.length() >= 1)
        {
            c = token.charAt(0);

            return (c == notToken || c == andToken || c == orToken);
        }
        else
        {
            return false;
        }
    }
}
