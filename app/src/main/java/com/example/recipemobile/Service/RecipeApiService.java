package com.example.recipemobile.Service;

import com.example.recipemobile.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RecipeApiService {

    @GET("recipes")
    Call<List<Recipe>> getAllRecipes();

    @FormUrlEncoded
    @POST("recipes")
    Call<Recipe> addRecipe(@Field("name") String name);

}
