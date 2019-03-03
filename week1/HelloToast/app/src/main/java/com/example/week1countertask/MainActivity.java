package com.example.week1countertask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn_toast, btn_reset, btn_count;
    public TextView txt_count;
    public Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_count = (Button) findViewById(R.id.btn_count);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_toast = (Button) findViewById(R.id.btn_toast);

        txt_count = (TextView) findViewById(R.id.txt_count);

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              increaseCount();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCount();
            }
        });



        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This is toast Example", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void increaseCount(){
        count++;
        txt_count.setText(count.toString());
    }

    public void resetCount(){
        count = 0;
        txt_count.setText(count.toString());
    }
}
