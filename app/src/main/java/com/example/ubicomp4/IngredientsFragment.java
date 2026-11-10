package com.example.ubicomp4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            ingredientsText.setText(bundle.getString("ingredients"));
        }
        return view;


    }
}