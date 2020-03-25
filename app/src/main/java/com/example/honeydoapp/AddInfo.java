package com.example.honeydoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static androidx.annotation.InspectableProperty.ValueType.GRAVITY;


public class AddInfo extends AppCompatActivity implements android.view.View.OnClickListener{

    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        //create a date string.
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        //get hold of textview.
        TextInputEditText date  = findViewById(R.id.DueDate);
        //set it as current date.
        date.setText(date_n);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button3:
                finish();
                break;
            case R.id.button2:

                TextInputEditText getHelper;
                String Name;
                String Date;
                String Notes;
                getHelper = findViewById(R.id.HoneyDoName);
                Name = getHelper.getText().toString();
                getHelper = findViewById(R.id.DueDate);
                Date = getHelper.getText().toString();
                getHelper = findViewById(R.id.Notes);
                Notes = getHelper.getText().toString();
                if ( ( Name.equals("")) )
                    {
                        Toast toast=Toast.makeText(this,"HoneyDo Name is empty", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP,0,3);
                        toast.show();
                    }else if ( ( Date.equals("")) )
                        {
                            Toast.makeText(getApplicationContext(), "Date is empty", Toast.LENGTH_LONG).show();
                        }else if ( ( Notes.equals("")) )
                            {
                                Toast.makeText(getApplicationContext(), "Notes is empty", Toast.LENGTH_LONG).show();
                            }else{
                                Intent NDN = new Intent();
                                NDN.setType("String");
                                NDN.putExtra("Name", Name);
                                NDN.putExtra("Date",Date);
                                NDN.putExtra("Notes",Notes);
                                setResult(3,NDN);
                                finish();
                            }
                break;
        }
    }

}
