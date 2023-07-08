package com.example.recipemobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListItemAdapter recipeListItemAdapter;
    private List<Recipe> recipeList;
    private Retrofit retrofitClient;
    private RecipeApiService recipeApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        recipeList = new ArrayList<>();

        retrofitClient = RetrofitClient.getClient();

        recipeApiService = retrofitClient.create(RecipeApiService.class);

        recyclerView = findViewById(R.id.listViewRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeListItemAdapter = new RecipeListItemAdapter(recipeList);
        recyclerView.setAdapter(recipeListItemAdapter);

        fetchRecipesFromApi();
    }

    private void fetchRecipesFromApi() {
        recipeApiService.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                if(response.isSuccessful()) {
                    List<Recipe> recipesFromApi = response.body();
                    recipeList.clear();
                    recipeList.addAll(recipesFromApi);
                    recipeListItemAdapter.notifyDataSetChanged();
                } else {
                    //TODO-fetch from api
                    recipeList = new ArrayList<>();
                    recipeList.add(new Recipe(1, "Recipe example 1"));
                    recipeList.add(new Recipe(2, "Recipe example 2"));
                    recipeList.add(new Recipe(3, "Recipe example 3"));
                    recipeList.add(new Recipe(4, "Recipe example 4"));
                    recipeList.add(new Recipe(5, "Recipe example 5"));
                    recipeListItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERRO RETROFIT", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_list_activity_options, menu);
        return true;
    }
}