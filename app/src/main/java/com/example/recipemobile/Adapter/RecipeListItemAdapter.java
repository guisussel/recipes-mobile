package com.example.recipemobile.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recipemobile.R;
import com.example.recipemobile.Model.Recipe;

import java.util.List;

public class RecipeListItemAdapter extends RecyclerView.Adapter<RecipeListItemAdapter.ViewHolder> {
    private List<Recipe> recipeList;

    public RecipeListItemAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextViewValueId().setText(String.valueOf(recipeList.get(position).getId()));
        viewHolder.getTextViewValueName().setText(recipeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewValueId;
        private final TextView textViewValueName;

        public ViewHolder(View view) {
            super(view);
            textViewValueId = view.findViewById(R.id.textViewValueId);
            textViewValueName = view.findViewById(R.id.textViewValueName);
        }

        public TextView getTextViewValueId() {
            return textViewValueId;
        }

        public TextView getTextViewValueName() {
            return textViewValueName;
        }
    }
}

