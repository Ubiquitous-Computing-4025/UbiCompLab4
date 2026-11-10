package com.example.ubicomp4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingredients);
        //details passed from RecipesFragment
        Bundle bundle = getIntent().getExtras();

        //create IngredientsFragment
        IngredientsFragment detailFragment = new IngredientsFragment();
        detailFragment.setArguments(bundle);

        //display the Fragment inside IngredientsActivity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredients_fragment_container, detailFragment)
                .commit();


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
    }
}