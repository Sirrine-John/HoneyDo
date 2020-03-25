package com.example.honeydoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.view.ScrollingView;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements android.view.View.OnClickListener {
    private Context mContext;
    private Button button;
    private LinearLayout cardV;
    private static int cardcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        cardV = findViewById(R.id.linearLayout);
        cardcount = 0;
    }

    @Override
    public void onClick(View view){
        Toast.makeText(mContext, Integer.toString(view.getId()),Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.button:
                AddInfo addHD = new AddInfo();
                Intent intent = new Intent(MainActivity.this,addHD.getClass());
                MainActivity.this.startActivityForResult(intent,2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2 && resultCode == 3)
        {
            Bundle dataBundle = data.getExtras();
            String Name;
            Name = dataBundle.getString("Name");
            String Date;
            Date = dataBundle.getString("Date");
            String Notes;
            Notes = dataBundle.getString("Notes");
//        Toast.makeText(this, Name+" - "+Date+" - "+Notes, Toast.LENGTH_SHORT).show();
            AddHoneyDo(Name,Date,Notes);
        }
    }

    public void AddHoneyDo(String Name,String Date,String Notes){
//                button.setText("Clicked");
                CardView newCardView;
                newCardView = new CardView(mContext);
                LayoutParams params = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
            );
                newCardView.setLayoutParams(params);
                // Set cardView content padding
                newCardView.setContentPadding(20, 10, 10, 10);
                newCardView.setPadding(6,15,6,15);
                newCardView.setUseCompatPadding(true);
                newCardView.setCardElevation(10);
                newCardView.setRadius(5);
                newCardView.setPreventCornerOverlap(true);
                newCardView.setPaddingRelative(0,5,0,5);
                // Set a background color for CardView
                newCardView.setCardBackgroundColor(Color.parseColor("#005F5B5B"));
                newCardView.setUseCompatPadding(false);
                newCardView.setMinimumHeight(30);
                newCardView.setClickable(true);
                newCardView.setOnClickListener(MainActivity.this);

                // Initialize a new TextView to put in CardView
                TextView tv = new TextView(mContext);
                tv.setPadding(5,10,5,10);
                tv.setTextSize(20);
                tv.setLayoutParams(params);
                String noteText = "Name: "+Name+"\n"+"Date: "+Date+"\n"+"Notes: "+Notes;
                tv.setText(noteText);
                tv.setMinHeight(30);
//                tv.isClickable();
//                tv.setOnClickListener(MainActivity.this);

                newCardView.addView(tv);
                cardV.addView(newCardView,cardcount);
                Integer cardId = cardcount+7000;
                newCardView. setId(cardId);
                cardcount += 1;
    }

}
