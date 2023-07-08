package com.example.recipemobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApiService {

    @GET("recipes")
    Call<List<Recipe>> getAllRecipes();

}
