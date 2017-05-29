package com.cn.android.incomevsexpenses;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PromptActivity extends AppCompatActivity {

    private Button mPaycheckButton;
    private Button mUtilitiesButton;
    private Button mDiningButton;
    private Button mDepositsButton;
    private Button mFuelButton;
    private Button mMiscButton;
    private Button mRentButton;
    private Button mGroceriesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        //todo: create a factory for the button inits
        mPaycheckButton = (Button) findViewById(R.id.bPaycheck);
        mPaycheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                Log.d("STATE", "You clicked the paycheck btn!!!");
                ; //todo: something
            }
        });

        mUtilitiesButton = (Button) findViewById(R.id.bUtilities);
        mUtilitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mDiningButton = (Button) findViewById(R.id.bDining);
        mDiningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mDepositsButton = (Button) findViewById(R.id.bDeposits);
        mDepositsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mFuelButton = (Button) findViewById(R.id.bFuel);
        mFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mMiscButton = (Button) findViewById(R.id.bMisc);
        mMiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mRentButton = (Button) findViewById(R.id.bRent);
        mRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

        mGroceriesButton = (Button) findViewById(R.id.bGroceries);
        mGroceriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                ; //todo: something
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prompt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
