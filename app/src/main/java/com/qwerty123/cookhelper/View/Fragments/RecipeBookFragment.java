package com.qwerty123.cookhelper.View.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qwerty123.cookhelper.View.Activities.RecipeDetailViewActivity;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Model.RecipeBookController;
import com.qwerty123.cookhelper.View.RecipeListAdapter;
import com.qwerty123.cookhelper.R;

public class RecipeBookFragment extends Fragment implements AdapterView.OnItemClickListener
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View recipeBookView = inflater.inflate(R.layout.fragment_recipe_book, container, false);
        Context context = recipeBookView.getContext();

        Recipe[] recipes = RecipeBookController.getRecipesFromSampleRecipeBook();

        if (recipeBookView instanceof ListView)
        {
            ListView recipeBookListView = (ListView) recipeBookView;

            RecipeListAdapter adapter = new RecipeListAdapter(context, R.id.recipe_list_item_view, recipes);
            recipeBookListView.setAdapter(adapter);
            recipeBookListView.setOnItemClickListener(this);
            adapter.notifyDataSetChanged();
        }

        return recipeBookView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(view.getContext(), RecipeDetailViewActivity.class);
        intent.putExtra(getResources().getString(R.string.recipe_index_extra), position);
        startActivity(intent);
    }
}
