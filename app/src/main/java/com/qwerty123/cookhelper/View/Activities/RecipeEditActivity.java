package com.qwerty123.cookhelper.View.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.qwerty123.cookhelper.Model.RecipeBook.PreparationStep;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.Controller.RecipeBookController;
import com.qwerty123.cookhelper.R;
import com.qwerty123.cookhelper.View.StepEditListAdapter;

public class RecipeEditActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener
{
    Recipe recipe;
    NumberPicker stepsNumberPicker;

    PreparationStep[] steps;

    // TODO  BIG TODO ===========================================================================
    // TODO  Need to change this class and the layout to make use of a scroll view instead
    // TODO  of a list view. Need to do dynamic creation of entries for steps, based on the value
    // TODO  of the number picker.
    // TODO  BIG TODO ===========================================================================

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        stepsNumberPicker = (NumberPicker) findViewById(R.id.ER_steps_number_picker);
        stepsNumberPicker.setMinValue(1);
        stepsNumberPicker.setMaxValue(50);
        stepsNumberPicker.setOnValueChangedListener(this);

        recipe = getRecipeReference();

        updateMembers();
        updateDisplay();
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
            return  null;
        }
    }

    private void updateMembers()
    {
        if(recipe != null)
        {
            stepsNumberPicker.setValue(recipe.getNumberSteps());
            steps = recipe.getPreparationSteps();
        }
        else
        {
            stepsNumberPicker.setValue(stepsNumberPicker.getMinValue());
            steps = new PreparationStep[stepsNumberPicker.getValue()];
        }

        updateListView();
    }

    private void updateListView()
    {
        StepEditListAdapter adapter = new StepEditListAdapter(this, R.layout.step_edit_item, steps);
        ListView stepsEditList = (ListView)findViewById(R.id.ER_steps_list_view);
        stepsEditList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateDisplay()
    {
        if(recipe != null)
        {
            EditText name = (EditText) findViewById(R.id.ER_recipe_name_field);
            EditText category = (EditText) findViewById(R.id.ER_cultural_category_field);
            EditText type = (EditText) findViewById(R.id.ER_meal_type_field);
            EditText prepTime = (EditText) findViewById(R.id.ER_preparation_time_field);

            name.setText(recipe.getName());
            category.setText(recipe.getCulturalCategory().toString());
            type.setText(recipe.getMealType().toString());
            prepTime.setText(Integer.toString(recipe.getPreparationTime()));
        }

        ListView stepList = (ListView)findViewById(R.id.ER_steps_list_view);

    }

    public void onSaveButton(View view)
    {
        Toast.makeText(getApplicationContext(), "Save Button Clicked", Toast.LENGTH_SHORT).show();

        //TODO Implement recipe saving

        finish();
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue)
    {
        if(numberPicker == stepsNumberPicker)
        {
            resizeStepArray(newValue);
        }
    }

    private void resizeStepArray(int size)
    {
        if(size > 0)
        {
            PreparationStep[] newSteps = new PreparationStep[size];

            for(int i = 0; i < newSteps.length; ++i)
            {
                if(i < steps.length)
                {
                    newSteps[i] = steps[i];
                }
            }

            steps = newSteps;

            updateListView();
        }
    }
}
