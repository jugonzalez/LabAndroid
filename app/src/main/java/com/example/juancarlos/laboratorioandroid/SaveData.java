package com.example.juancarlos.laboratorioandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.parse.ParseObject;
import com.parse.Parse;

public class SaveData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        Intent intent = getIntent();
    }

    public void savedata(View view) {
        // Do something in response to button

        Parse.initialize(this, "tZoFQTdRpC3YplsyJxJxWOIsRUGaZV4NYYRUTiVD", "U6tiTlBS0BiWJqpwuv5r3eMf3MMRHskEecfTvjUT");

        EditText nombre = (EditText) findViewById(R.id.editText2);
        String nombrep = nombre.getText().toString();

        EditText descripcion = (EditText) findViewById(R.id.edit_message);
        String descripcionp = descripcion.getText().toString();

        EditText pais = (EditText) findViewById(R.id.editText);
        String paisp = pais.getText().toString();

        ParseObject food = new ParseObject("Food");
        food.put("Name", nombrep);
        food.put("Descripcion", descripcionp);
        food.put("Type", paisp);
        food.saveInBackground();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_data, menu);
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
