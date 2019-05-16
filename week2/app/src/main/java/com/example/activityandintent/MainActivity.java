package com.example.activityandintent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button btn_hello, btn_count;
    TextView txt_count;

    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_hello = (Button) findViewById(R.id.btn_hello);
        btn_count = (Button) findViewById(R.id.btn_count);
        txt_count = (TextView) findViewById(R.id.txt_count);

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txt_count.setText(count.toString());
            }
        });

        btn_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("count", count.toString());
                Intent intent  = new Intent(MainActivity.this, ActivityHello.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}