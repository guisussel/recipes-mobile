package com.example.recipemobile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recipemobile.Adapter.RecipeListItemAdapter;
import com.example.recipemobile.Model.Recipe;
import com.example.recipemobile.R;
import com.example.recipemobile.Retrofit.RetrofitClient;
import com.example.recipemobile.Service.RecipeApiService;
import com.example.recipemobile.Utils.DialogErrorUtil;

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
                if (response.isSuccessful()) {
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

    public void addNewRecipe(String recipeName) {
        if (!recipeName.isEmpty()) {

            Call<Recipe> call = recipeApiService.addRecipe(recipeName);
            call.enqueue(new Callback<Recipe>() {
                @Override
                public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                    if (response.isSuccessful()) {
                        Recipe addedRecipe = response.body();
                        Toast.makeText(getApplicationContext(), getString(R.string.the_recipe) + addedRecipe.getName() + getString(R.string.was_successfully_saved), Toast.LENGTH_LONG).show();
                        fetchRecipesFromApi();
                        recipeListItemAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.could_not_save_the_recipe_try_again_later), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Recipe> call, Throwable t) {
                    // Tratar falha na chamada à API
                }
            });
        }

    }

    public void openDialogNewRecipeNameInput(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.insert_new_recipe_name);
        final EditText recipeNameInput = new EditText(context);
        alertDialogBuilder.setView(recipeNameInput);
        alertDialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String recipeName = recipeNameInput.getText().toString().trim();
                if (!recipeName.isEmpty()) {
                    addNewRecipe(recipeName);
                } else {
                    DialogErrorUtil.dialogStandardError(RecipeList.this, R.string.recipe_name_cannot_be_null);
                }
            }
        });

        alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        // Exibe o diálogo de inserção de nome da receita
        final AlertDialog insertDialog = alertDialogBuilder.create();
        insertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_list_activity_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.recipeListMenuItemNew) {
            openDialogNewRecipeNameInput(RecipeList.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}