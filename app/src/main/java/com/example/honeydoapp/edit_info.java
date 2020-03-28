package com.example.honeydoapp;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class edit_info extends AppCompatActivity implements android.view.View.OnClickListener {

    private Button button2;
    private Button button3;
    private Integer cardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String Name;
        String Date;
        String Notes;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        //set up listeners
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        //create a bundle to hold the data passed into the intent
        Bundle b = getIntent().getExtras();
        if(b != null){
        cardId = b.getInt("bId");
        Name = b.getString("bName");
        Date = b.getString("bDate");
        Notes = b.getString("bNotes");
        FillForm(Name,Date,Notes);}
        else finish();
    }

//Handle any click event
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //exit when this button is pressed
            case R.id.button3:
                finish();
                break;
        }
    }

// Fill the fields with the passed data
    private void FillForm(String Name, String Date, String Notes) {
        TextInputEditText tname = findViewById(R.id.EditHoneyDoName);
        TextInputEditText tdate = findViewById(R.id.EditDueDate);
        TextInputEditText tnotes = findViewById(R.id.EditNotes);

        tname.setText(Name);
        tdate.setText(Date);
        tnotes.setText(Notes);
    }
}
