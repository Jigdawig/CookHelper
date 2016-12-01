package com.qwerty123.cookhelper.View.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Controller.RecipeBookController;
import com.qwerty123.cookhelper.R;

public class RecipeDetailViewActivity extends AppCompatActivity
{
    Recipe recipe;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail_view);

        recipe = getRecipeReference();

        if (recipe != null)
        {
            updateDisplay();
        }
    }

    private Recipe getRecipeReference()
    {
        int recipeIndex = getIntent().getIntExtra(getResources().getString(R.string.recipe_index_extra), -1);
        String recipeName = getIntent().getStringExtra(getResources().getString(R.string.recipe_name_extra));

        if (recipeIndex > -1)
        {
            return RecipeBookController.getRecipe(recipeIndex);
        }
        else if (recipeName != null && !recipeName.isEmpty())
        {
            return RecipeBookController.getRecipe(recipeName);
        }
        else
        {
            return null;
        }
    }

    private void updateDisplay()
    {
        if (recipe != null)
        {
            TextView name = (TextView) findViewById(R.id.RDV_recipe_name);
            TextView category = (TextView) findViewById(R.id.RDV_cultural_category);
            TextView type = (TextView) findViewById(R.id.RDV_meal_type);
            TextView prepTime = (TextView) findViewById(R.id.RDV_preparation_time);
            TextView ingredients = (TextView) findViewById(R.id.RDV_ingredients_enumeration);
            TextView steps = (TextView) findViewById(R.id.RDV_steps_enumeration);

            name.setText(recipe.getName());
            category.setText(recipe.getCulturalCategory().toString());
            type.setText(recipe.getMealType().toString());
            prepTime.setText(String.format("%1$s minutes", Integer.toString(recipe.getPreparationTime())));
            ingredients.setText(recipe.getIngredientsEnumeration());
            steps.setText(recipe.getPreparationStepEnumeration());
        }
    }

    public void onClickEditButton(View view)
    {
//        Intent intent = new Intent(getApplicationContext(), RecipeEditActivity.class);
//        startActivityForResult(intent, 0);
    }
}
