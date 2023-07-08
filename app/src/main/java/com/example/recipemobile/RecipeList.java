package com.example.recipemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListItemAdapter recipeListItemAdapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        //TODO-fetch from api
        recipeList = new ArrayList<>();
        recipeList.add(new Recipe(1, "Recipe example 1"));
        recipeList.add(new Recipe(2, "Recipe example 2"));
        recipeList.add(new Recipe(3, "Recipe example 3"));
        recipeList.add(new Recipe(4, "Recipe example 4"));
        recipeList.add(new Recipe(5, "Recipe example 5"));

        // Inicializar o RecyclerView e o RecipeAdapter
        recyclerView = findViewById(R.id.listViewRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeListItemAdapter = new RecipeListItemAdapter(recipeList);
        recyclerView.setAdapter(recipeListItemAdapter);
    }
}