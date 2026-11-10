
package com.example.ubicomp4;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RecipesFragment extends Fragment {

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        ListView recipeListView = view.findViewById(R.id.recipeListView);

        try {
            InputStream inputStream = requireContext().getAssets().open("recipes.json"); //open file

            byte[] data = new byte[inputStream.available()];

            inputStream.read(data); //reads the bytes of the file

            inputStream.close();

            String json = new String(data, StandardCharsets.UTF_8); //converts the bytes to a string

            // parsing the JSON data
            JSONObject jsonObject = new JSONObject(json); //parses the string to a JSON object

            JSONArray recipesArray = jsonObject.getJSONArray("recipes"); // loops through the JSON array

            String[] recipeNames = new String[recipesArray.length()];

            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipe = recipesArray.getJSONObject(i);
                recipeNames[i] = recipe.getString("name");
            }

            //create an ArrayAdapter to populate the ListView with recipe names
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    recipeNames
            );
            recipeListView.setAdapter(adapter);

            // click listener to handle recipe selection
            recipeListView.setOnItemClickListener((parent, view1, position, id) -> {

                try {
                    JSONObject selectedRecipe = recipesArray.getJSONObject(position); //gets the selected recipe

                    //container for passing details
                    Bundle bundle = new Bundle(); //
                    bundle.putString("name", selectedRecipe.getString("name"));
                    bundle.putString("method", selectedRecipe.getString("method"));
                    bundle.putString("ingredients",
                            selectedRecipe.getJSONArray("ingredients").toString());

                    IngredientsFragment detailFragment = new IngredientsFragment();
                    detailFragment.setArguments(bundle);

                    //check the phone orientation
                    if (getResources().getConfiguration().orientation ==
                            Configuration.ORIENTATION_LANDSCAPE) {

                        // Landscape: update the ingredients on the right
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.ingredients_container, detailFragment)
                                .commit();

                    } else {

                        // Portrait: start the IngredientsActivity
                        Intent intent = new Intent(requireContext(), IngredientsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
