package com.ezetap.rborawar2.ezetap;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int result;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null) {
            calculateAsPerTheAction();
            resendResultToApp1();
        }
    }

    public void calculateAsPerTheAction(){


            Bundle bundle = getIntent().getExtras();
            int number1 = bundle.getInt("number1");
            int number2 = bundle.getInt("number2");
            String action = bundle.getString("action");
            count++;
            if (action != null) {
                if (action.equals("add")) {
                    result = number1 + number2;
                } if (action.equals("subtract")) {
                    if (number1 > number2) {
                        result = number1 - number2;
                    } else if (number2 > number1) {
                        result = number2 - number1;
                    } else {
                        result = 0;
                    }
                }
            }

    }

    public void resendResultToApp1() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ezetap.rborawar.ezetaptest");
        if (launchIntent != null) {
            launchIntent.putExtra("result", result);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(launchIntent);
        }
    }
}
