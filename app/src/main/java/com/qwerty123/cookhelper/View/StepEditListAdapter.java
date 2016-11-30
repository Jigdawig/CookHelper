package com.qwerty123.cookhelper.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.qwerty123.cookhelper.Model.RecipeBook.PreparationStep;
import com.qwerty123.cookhelper.Model.RecipeBook.Recipe;
import com.qwerty123.cookhelper.R;

public class StepEditListAdapter extends ArrayAdapter<PreparationStep>
{
    public StepEditListAdapter(Context context, int resource, PreparationStep[] objects)
    {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.step_edit_item, parent, false);
        EditText instructions = (EditText) rowView.findViewById(R.id.SEI_instructions_field);

        PreparationStep step = getItem(position);

        if (step != null)
        {
            instructions.setText(step.toString());
        }

        return rowView;
    }
}
