package com.cn.android.incomevsexpenses;

import android.Manifest;
import android.app.VoiceInteractor;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;


public class PromptActivity extends AppCompatActivity {

    private double[] expenses = new double[8];
    private double amount = 0;

    private static final String fileName = "./data/account_example.csv"; //+ promptForFilename();

    private List<BankTransaction> transactions;
    private int index = 0;

    private TextView mTextView;

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

        try {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1); //todo figure out how to properly grant permissions
        } catch (Exception e) {
            Log.e("CN_STATE", e.toString());
        }


        try {
            transactions = readStatement();
        } catch (Exception e) {
            Log.e("CN_STATE", e.getStackTrace().toString());
        }

        Log.d("CN_STATE", Arrays.toString(transactions.toArray()));

        mTextView = (TextView) findViewById(R.id.textView);

        //todo: create a factory for the button inits
        mPaycheckButton = (Button) findViewById(R.id.bPaycheck);
        mPaycheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                Log.e("CN_STATE", "You clicked the paycheck btn!!!");
                expenses[0] += amount;
                goToNextTransaction();
            }
        });

        mUtilitiesButton = (Button) findViewById(R.id.bUtilities);
        mUtilitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[1] += amount;
                goToNextTransaction();
            }
        });

        mDiningButton = (Button) findViewById(R.id.bDining);
        mDiningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[2] += amount;
                goToNextTransaction();
            }
        });

        mDepositsButton = (Button) findViewById(R.id.bDeposits);
        mDepositsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[3] += amount;
                goToNextTransaction();
            }
        });

        mFuelButton = (Button) findViewById(R.id.bFuel);
        mFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[4] += amount;
                goToNextTransaction();
            }
        });

        mMiscButton = (Button) findViewById(R.id.bMisc);
        mMiscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[5] += amount;
                goToNextTransaction();
            }
        });

        mRentButton = (Button) findViewById(R.id.bRent);
        mRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[6] += amount;
                goToNextTransaction();
            }
        });

        mGroceriesButton = (Button) findViewById(R.id.bGroceries);
        mGroceriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update an array of "expense categories"
                expenses[7] += amount;
                goToNextTransaction();
            }
        });

        goToNextTransaction();

    }

    private void goToNextTransaction() {
        BankTransaction t = transactions.get(index);
        mTextView.setText("What type of transaction is this? \n " + t.description);
        amount = t.amount;
        index++;
    }

    public List<BankTransaction> readStatement() throws IOException {

        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.v("CN_STATE","Permission is granted");
            //File write logic here
        } else {
            Log.v("CN_STATE", "No permission!!!");
        }

        //List<BankTransaction> t = new ArrayList<BankTransaction>();
        List<BankTransaction> t = new ArrayList<BankTransaction>();
        File downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //getExternalStorageDirectory();
        File statement = new File(downloadsDirectory, "statement.txt.csv");

        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(statement));
            String line;

            //skip the first three lines of the file
            br.readLine();
            br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                double amount = Double.parseDouble(line.split(",")[1].replace("\"", "")); //get rid of double quotes
                String description = line.split(",")[2].replace("\"","");
                //String date = line.split(",")[0].replace("\"","");
                BankTransaction bt = new BankTransaction(description, amount);
                t.add(bt);
            }
        }
        catch (IOException e) {
            Log.e("CN_STATE", e.toString());
        }

        return t;
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
