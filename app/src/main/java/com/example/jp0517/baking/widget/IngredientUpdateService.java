package com.example.jp0517.baking.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.jp0517.baking.recipe.Recipe;

/**
 * Created by jp0517 on 2/25/18.
 */

public class IngredientUpdateService extends IntentService {
    public static final String ACTION_UPDATE_INGREDIENTS =
            "com.example.jp0517.baking.update_ingredients";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public IngredientUpdateService() {
        super("IngredientUpdateService");
    }

    public static void startActionUpdateIngredients
            (Context context,
             String name,
             String ingredients) {
        Intent intent = new Intent(context, IngredientUpdateService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS);
        intent.putExtra(Recipe.NAME, name);
        intent.putExtra(Recipe.INGREDIENTS, ingredients);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent == null) {
            return;
        }
        if(intent.getAction().equals(ACTION_UPDATE_INGREDIENTS)) {
            String name = intent.getStringExtra(Recipe.NAME);
            String ingredients = intent.getStringExtra(Recipe.INGREDIENTS);
            handleUpdateIngredients(name, ingredients);
        }
    }

    private void handleUpdateIngredients(String name, String ingredients) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));
        BakingWidgetProvider.updateWidgetText(this, appWidgetManager, name, ingredients, appWidgetIds);
    }
}
