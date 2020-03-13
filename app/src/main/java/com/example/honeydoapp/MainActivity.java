package com.example.honeydoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.view.ScrollingView;

import android.content.Intent;
import android.net.Uri;
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
    private static Context mContext;
    private Button button;
    private static LinearLayout cardV;
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
        Toast.makeText(mContext, Integer.toString(view.getId()),Toast.LENGTH_LONG).show();

        switch (view.getId()) {
            case R.id.button:
                Intent viewAddInfo = new Intent(MainActivity.this,AddInfo.class);
                startActivity(viewAddInfo);
                Intent intent=new Intent(MainActivity.this,AddInfo.class);
                startActivityForResult(intent,2);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String returnedResult = new String();
            returnedResult = data.getData().toString();
            Toast.makeText(mContext, returnedResult, Toast.LENGTH_LONG);
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
                newCardView.setContentPadding(10, 0, 10, 4);
                newCardView.setPadding(6,3,6,3);
                // Set a background color for CardView
                newCardView.setCardBackgroundColor(Color.parseColor("#005F5B5B"));
                newCardView.setUseCompatPadding(false);
                newCardView.setMinimumHeight(30);
                newCardView.setClickable(true);
                newCardView.setOnClickListener(this);

                // Initialize a new TextView to put in CardView
                TextView tv = new TextView(mContext);
                tv.setLayoutParams(params);
                tv.setText("Name: "+Name+"\n"+"Date: "+Date+"\n"+"Notes: "+Notes);
                tv.setMinHeight(30);

                newCardView.addView(tv);
                cardV.addView(newCardView,cardcount);
                cardcount += 1;
    }

}
