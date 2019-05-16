package com.example.activityandintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityHello extends AppCompatActivity {
    TextView txt_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Bundle bundle = getIntent().getExtras();
        txt_count = (TextView) findViewById(R.id.txt_count1);
        txt_count.setText(bundle.getString("count"));
    }
}
