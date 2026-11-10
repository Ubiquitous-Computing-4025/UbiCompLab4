package com.example.ubicomp4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class IngredientsFragment extends Fragment {

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        TextView recipeTitle = view.findViewById(R.id.recipeTitle);
        TextView ingredientsText = view.findViewById(R.id.ingredientsText);
        TextView methodText = view.findViewById(R.id.methodText);

        Bundle bundle = getArguments(); //gets the bundle from the previous fragment with the details

        if (bundle != null) {
            //retrieves the details
            recipeTitle.setText(bundle.getString("name"));
            methodText.setText(bundle.getString("method"));
            try {
                //get teh string ingredients and parses the ingredients into json array
                JSONArray ingredients = new JSONArray(bundle.getString("ingredients"));

                String ingredientsList = "Ingredients:\n";

                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList += "~   " + ingredients.getString(i) + "\n";
                }

                ingredientsText.setText(ingredientsList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;


    }
}