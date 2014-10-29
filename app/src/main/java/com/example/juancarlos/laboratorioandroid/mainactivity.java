package com.example.juancarlos.laboratorioandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.content.Intent;
import com.parse.Parse;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;

public class mainactivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    Button btnSelectImage;
    TextView txtUriPath,txtRealPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        Intent intentt = getIntent();

        Parse.initialize(this, "tZoFQTdRpC3YplsyJxJxWOIsRUGaZV4NYYRUTiVD", "U6tiTlBS0BiWJqpwuv5r3eMf3MMRHskEecfTvjUT");

        FoodAdapter foodAdapter = new FoodAdapter(this);


        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(foodAdapter);
        // get reference to views

        foodAdapter.loadObjects();

    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,SaveData.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
