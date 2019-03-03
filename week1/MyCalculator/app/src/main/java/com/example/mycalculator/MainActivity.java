package com.example.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView txt_screen;
    Button btn_sum, btn_diff, btn_mul, btn_div, btn_equals, btn_reset, btn_quit;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private double valueOne = Double.NaN;
    private double valueTwo;
    private char CURRENT_ACTION;

    DecimalFormat decimalFormat = new DecimalFormat("#.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_screen = (TextView) findViewById(R.id.txt_screen);
        btn_sum = (Button) findViewById(R.id.btn_sum);
        btn_diff = (Button) findViewById(R.id.btn_difference);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_equals = (Button) findViewById(R.id.btn_equals);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_quit = (Button) findViewById(R.id.btn_quit);

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.exit(0);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Quit App")
                        .setMessage("Are you sure you want to quit application?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                CURRENT_ACTION = ADDITION;
                txt_screen.setText(decimalFormat.format(valueOne) + ADDITION);
                postCheck();
            }
        });

        btn_diff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                CURRENT_ACTION = SUBTRACTION;
                txt_screen.setText(decimalFormat.format(valueOne) + SUBTRACTION);
                postCheck();

            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                txt_screen.setText(decimalFormat.format(valueOne) + "x");
                postCheck();

            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                CURRENT_ACTION = DIVISION;
                txt_screen.setText(decimalFormat.format(valueOne) + DIVISION);
                postCheck();
            }
        });

        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                txt_screen.setText(decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                if (txt_screen.getText().toString().contains("∞")) {
                    Toast.makeText(getApplicationContext(), "Infinity Value Detected. Resetting all values", Toast.LENGTH_LONG).show();
                    txt_screen.setText("0");
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resetting Calculator", Toast.LENGTH_SHORT).show();
                valueOne = Double.NaN;
                txt_screen.setText("0");
            }
        });
    }

    public void postCheck() {

        if (txt_screen.getText().toString().contains("∞")) {
            Toast.makeText(getApplicationContext(), "Infinity Value Detected. Resetting value to 0", Toast.LENGTH_LONG).show();
            valueOne = 0;
            txt_screen.setText(decimalFormat.format(valueOne) + CURRENT_ACTION);
        }
    }

    public String checkPreviousCalcs() {
        if (Double.isNaN(valueOne)) {
            return "proceed";
        } else {

        }
        return "";
    }

    public void performCalculation() {
        if (!Double.isNaN(valueOne)) {
            switch (CURRENT_ACTION) {
                case ADDITION:
                    try {
                        String arr[] = txt_screen.getText().toString().split("\\+");
                        if (arr.length == 2) {
                            valueTwo = Double.parseDouble(arr[1]);
                            valueOne = this.valueOne + valueTwo;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case SUBTRACTION:
                    try {
                        String arr[] = txt_screen.getText().toString().split("-");
                        if (arr.length == 2) {
                            valueTwo = Double.parseDouble(arr[1]);
                            valueOne = this.valueOne - valueTwo;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case MULTIPLICATION:
                    try {
                        String arr[] = txt_screen.getText().toString().split("x");
                        if (arr.length == 2) {
                            valueTwo = Double.parseDouble(arr[1]);
                            valueOne = this.valueOne * valueTwo;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case DIVISION:
                    try {
                        String arr[] = txt_screen.getText().toString().split("/");
                        if (arr.length == 2) {
                            valueTwo = Double.parseDouble(arr[1]);
                            valueOne = this.valueOne / valueTwo;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            valueOne = Double.parseDouble(txt_screen.getText().toString());
        }
    }

    public void numberClicked(View view) {
        Button button = (Button) view;
        String current_text = txt_screen.getText().toString();
        if (current_text.equals("0")) {
            txt_screen.setText(button.getText().toString());
        } else {
            txt_screen.setText(current_text + "" + button.getText().toString());
        }
    }
}

