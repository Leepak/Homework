package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todolist.Helpers.DatabaseHelper;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTodo extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button btn_add;
    TextView txt_name, txt_date, txt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        final Calendar myCalendar = Calendar.getInstance();

        txt_date = (TextView) findViewById(R.id.txt_todo_date);
        txt_name = (TextView) findViewById(R.id.txt_todo_name);
        txt_time = (TextView) findViewById(R.id.txt_time);


        btn_add = (Button) findViewById(R.id.btn_save_todo);
        databaseHelper = new DatabaseHelper(this);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                txt_date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTodo.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddTodo.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo_name = txt_name.getText().toString();
                String todo_date = txt_date.getText().toString();


                if (todo_name.length() == 0 || todo_date.length() == 0) {
                    toastMessage("Please fill all fields");
                } else {
                    boolean result = databaseHelper.addData(todo_name, todo_date);
                    if (result) {
                        toastMessage("Toto successfully Added");
                        txt_name.setText("");
                        txt_date.setText("");
                        finish();
                    } else {
                        toastMessage("Something went wrong");
                    }
                }

            }
        });
    }

    public void toastMessage(String message) {
        Toast.makeText(AddTodo.this, message, Toast.LENGTH_SHORT).show();
    }


}
