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
    private String Name;
    private String Date;
    private String Notes;

    public edit_info(Integer id, String name, String date, String notes) {
        cardId = id;
        Name = name;
        Date = date;
        Notes = notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        FillForm();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                finish();
                break;
        }
    }

    private void FillForm() {
        TextInputEditText name = findViewById(R.id.EditHoneyDoName);
        TextInputEditText date = findViewById(R.id.EditDueDate);
        TextInputEditText notes = findViewById(R.id.EditNotes);

        name.setText(Name);
        date.setText(Date);
        notes.setText(Notes);
    }
}
