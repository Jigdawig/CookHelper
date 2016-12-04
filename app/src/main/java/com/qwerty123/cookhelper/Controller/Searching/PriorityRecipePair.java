package com.qwerty123.cookhelper.Controller.Searching;

import android.support.v4.util.Pair;

import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;

public class PriorityRecipePair extends Pair<Integer, Recipe> implements Comparable<Pair<Integer, Recipe>>
{
    /**
     * Constructor for a Pair.
     *
     * @param first  the first object in the Pair
     * @param second the second object in the pair
     */
    public PriorityRecipePair(Integer first, Recipe second)
    {
        super(first, second);
    }

    @Override
    public int compareTo(Pair<Integer, Recipe>other )
    {
        return first.compareTo(other.first);
    }
}
