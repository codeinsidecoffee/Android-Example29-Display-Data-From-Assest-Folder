package com.mrlonewolfer.example62;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *  Create an application to read file from asset folder and copy it in memory card.
 *
 */

public class MainActivity extends AppCompatActivity {
    private static final int My_PERMISSION_REQUEST_FOR_WRITE_EXTERNAL =1 ;
    TextView tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                    }, My_PERMISSION_REQUEST_FOR_WRITE_EXTERNAL);

        }


        tv3 = (TextView) findViewById(R.id.textView3);
        tv4=(TextView) findViewById(R.id.textView4);

        try {
            InputStream inputStream=getResources().getAssets().open("redme.txt");

            String locationToStoreFile = Environment.getExternalStorageDirectory().getPath() + "/readme.txt";
            byte[] b= new byte[inputStream.available()];
            inputStream.read(b);
            String s = new String(b);
            tv4.setText(s);
            tv3.setText("Redme File Contains : \n "+locationToStoreFile);

            FileOutputStream fileOutputStream=new FileOutputStream(new File(locationToStoreFile));

            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
