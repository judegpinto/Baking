package com.example.jp0517.baking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jp0517.baking.utilities.QueryTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new QueryTask().execute(getString(R.string.recipe_url));
    }
}
