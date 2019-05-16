package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_website, txt_location, txt_share;
    Button btn_website, btn_location, btn_share;
    ImageButton btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_website = (TextView) findViewById(R.id.txt_website);
        txt_location = (TextView) findViewById(R.id.txt_location);
        txt_share = (TextView) findViewById(R.id.txt_share);

        btn_website = (Button) findViewById(R.id.btn_website);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_camera = (ImageButton) findViewById(R.id.btn_camera);

        btn_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = txt_website.getText().toString();
                Uri parsed_url = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, parsed_url);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ERROR", "whoops something went wrong!!");
                }
            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = txt_location.getText().toString();
                Uri addressUrl = Uri.parse("geo:0,0?q=" + location);
                Intent intent = new Intent(Intent.ACTION_VIEW, addressUrl);
                Log.d("DEBUG", "HELLO");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.d("ERROR", "whoops something went wrong!!");

                }
            }
        });


        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareText = txt_share.getText().toString();
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Choose application to share")
                        .setText(shareText)
                        .startChooser();
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

    }
}
