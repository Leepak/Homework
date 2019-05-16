package com.example.activitylifecycle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_count;
    Button btn_count;
    EditText txt_text;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_count = (TextView) findViewById(R.id.txt_count);
        btn_count = (Button) findViewById(R.id.btn_count);

        if(savedInstanceState != null){
            count = savedInstanceState.getInt("count");
//            Log.d(TAG, "recreated -- percentage=  "+percentage);
        }

        txt_count.setText(count+"");

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txt_count.setText(count + "");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("count", count);
//       Log.d(TAG, "onSaveInstanceState() -- percentage =  "+percentage);

    }
}
