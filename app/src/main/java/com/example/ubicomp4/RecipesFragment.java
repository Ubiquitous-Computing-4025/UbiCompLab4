package com.example.ubicomp4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class RecipesFragment extends Fragment {

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        ListView recipeListView = view.findViewById(R.id.recipeListView);

        String[] recipeNames = {
                "Spaghetti Carbonara",
                "Spaghetti Bolognese",
                "Penne Arrabbiata",
                "Fettuccine Alfredo",
                "Spaghetti Aglio e Olio"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                recipeNames
        );
        recipeListView.setAdapter(adapter);

        return view;
    }
}