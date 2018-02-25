package com.example.jp0517.baking.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.jp0517.baking.MainActivity;
import com.example.jp0517.baking.R;
import com.example.jp0517.baking.RecipeDetailActivity;
import com.example.jp0517.baking.recipe.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                String name, String ingredients, int appWidgetId) {
        Log.d("debug widget", "widget update");
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.widget_recipe_name, name);
        views.setTextViewText(R.id.widget_recipe_ingredients, ingredients);
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(Recipe.NAME, name);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.baking_widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);
        Log.d("debug widget", "enabling widget");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.widget_recipe_name, context.getString(R.string.appwidget_initial_text));
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.baking_widget, pendingIntent);

        AppWidgetManager appManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, BakingWidgetProvider.class);
        appManager.updateAppWidget(thisWidget, views);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateWidgetText(Context context, AppWidgetManager manager,
                                 String name, String ingredients, int[] appWidgetIds) {
        for (int id:appWidgetIds) {
            updateAppWidget(context, manager, name, ingredients, id);
        }
    }
}

