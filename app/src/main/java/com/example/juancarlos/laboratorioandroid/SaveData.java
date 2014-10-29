package com.example.juancarlos.laboratorioandroid;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.parse.ParseObject;
import com.parse.Parse;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.*;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import  android.graphics.Bitmap.CompressFormat;

import static android.graphics.BitmapFactory.decodeFile;
import android.view.View.OnClickListener;
public class SaveData extends Activity implements OnClickListener {

    TextView txtSDK;
    Button btnSelectImage;
    TextView txtUriPath,txtRealPath;
    ImageView imageView;
    String ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        Intent intentt = getIntent();

        txtSDK = (TextView) findViewById(R.id.txtSDK);
        btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        txtUriPath = (TextView) findViewById(R.id.txtUriPath);
        txtRealPath = (TextView) findViewById(R.id.txtRealPath);
        imageView = (ImageView) findViewById(R.id.imgView);

        btnSelectImage.setOnClickListener(this);
        System.out.println("onCreate");
    }

    @Override
    public void onClick(View view) {

        // 1. on Upload click call ACTION_GET_CONTENT intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // 2. pick image only
        intent.setType("image/*");
        // 3. start activity
        startActivityForResult(intent, 0);
        // define onActivityResult to do something with picked image
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if(resCode == Activity.RESULT_OK && data != null){
            String realPath;
            // SDK < API11
            if (Build.VERSION.SDK_INT < 11)
                realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

                // SDK >= 11 && SDK < 19
            else if (Build.VERSION.SDK_INT < 19)
                realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());

                // SDK > 19 (Android 4.4)
            else
                realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());

            ruta=realPath;
            setTextViews(Build.VERSION.SDK_INT, data.getData().getPath(),realPath);
        }

    }

    private void setTextViews(int sdk, String uriPath,String realPath){

        this.txtSDK.setText("Build.VERSION.SDK_INT: "+sdk);
        this.txtUriPath.setText("URI Path: "+uriPath);
        this.txtRealPath.setText("Real Path: "+realPath);

        Uri uriFromPath = Uri.fromFile(new File(realPath));

        // you have two ways to display selected image

        // ( 1 ) imageView.setImageURI(uriFromPath);

        // ( 2 ) imageView.setImageBitmap(bitmap);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //imageView.setImageBitmap(bitmap);

        Log.d("HMKCODE", "Build.VERSION.SDK_INT:"+sdk);
        Log.d("HMKCODE", "URI Path:"+uriPath);
        Log.d("HMKCODE", "Real Path: "+realPath);
        System.out.println("Set Text View");
    }

    public void savedata(View view) throws IOException {
        // Do something in response to button

        Parse.initialize(this, "tZoFQTdRpC3YplsyJxJxWOIsRUGaZV4NYYRUTiVD", "U6tiTlBS0BiWJqpwuv5r3eMf3MMRHskEecfTvjUT");

        EditText nombre = (EditText) findViewById(R.id.editText2);
        String nombrep = nombre.getText().toString();

        EditText descripcion = (EditText) findViewById(R.id.edit_message);
        String descripcionp = descripcion.getText().toString();

        EditText pais = (EditText) findViewById(R.id.editText);
        String paisp = pais.getText().toString();

        // Locate the image in res > drawable-hdpi
        //File imgFile = new  File(String.valueOf(textFile.getText()));
        Bitmap bitmap = BitmapFactory.decodeFile(ruta);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("ic_launcher.png", image);
        // Upload the image into Parse Cloud
        file.saveInBackground();


        ParseObject food = new ParseObject("Food");
        food.put("Name", nombrep);
        food.put("Descripcion", descripcionp);
        food.put("Type", paisp);
        food.put("Image", file);
        //food.put("Image",file);
        food.saveInBackground();

        // Do something in response to button
        Intent intent = new Intent(this,mainactivity.class);
        startActivity(intent);
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
        System.out.println("On create option");
        return super.onOptionsItemSelected(item);
    }
}
