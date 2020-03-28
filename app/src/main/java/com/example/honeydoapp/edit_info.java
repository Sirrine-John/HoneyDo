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
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        if(b != null)
        cardId = b.getInt("bId");
        Name = b.getString("bName");
        Date = b.getString("bDate");
        Notes = b.getString("bNotes");
        FillForm(Name,Date,Notes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                finish();
                break;
        }
    }

    private void FillForm(String Name, String Date, String Notes) {
        TextInputEditText tname = findViewById(R.id.EditHoneyDoName);
        TextInputEditText tdate = findViewById(R.id.EditDueDate);
        TextInputEditText tnotes = findViewById(R.id.EditNotes);

        tname.setText(Name);
        tdate.setText(Date);
        tnotes.setText(Notes);
    }
}
