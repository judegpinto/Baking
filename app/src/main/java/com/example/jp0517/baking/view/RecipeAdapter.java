package com.example.jp0517.baking.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jp0517.baking.R;
import com.example.jp0517.baking.recipe.Ingredient;
import com.example.jp0517.baking.recipe.Recipe;

import org.w3c.dom.Text;

/**
 * Created by jp0517 on 1/17/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private Recipe[] mRecipes;

    public RecipeAdapter(Context context) {
        mContext = context;
    }

    public void setRecipes(Recipe[] recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_card,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {
        String name = mRecipes[position].getName();
        holder.recipeName.setText(name);
    }

    @Override
    public int getItemCount() {
        if(mRecipes == null) {return 0;}
        return mRecipes.length;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        CardView recipeCard;

        RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.name);
            recipeCard = (CardView) itemView.findViewById(R.id.card_view);
            recipeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRecipe(getAdapterPosition());
                }
            });
        }
    }

    private void showRecipe(int pos) {
        Toast.makeText(mContext, "Showing recipe: " + pos, Toast.LENGTH_LONG).show();
    }

}
