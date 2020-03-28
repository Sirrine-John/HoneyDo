package com.example.honeydoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
    implements android.view.View.OnClickListener {
    private Context mContext;
    private Button button;
    private LinearLayout cardV;
    private static int cardcountInitial;
    private static int cardcount;
    public databaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        cardV = findViewById(R.id.linearLayout);
        cardcountInitial = 7000;
        cardcount = 0;
        myDb = new databaseHelper(mContext);
        //TESTING
        DeleteData(0);
        DeleteData(1);
        DeleteData(2);
        DeleteData(3);
        DeleteData(4);
//        DeleteData(5);
//        DeleteData(6);
//        DeleteData(7);
//        DeleteData(8);
//        DeleteData(9);
//        DeleteData(10);
//        DeleteData(11);
//        DeleteData(12);
//        DeleteData(13);
//        DeleteData(14);
//        DeleteData(15);
//        DeleteData(16);
//        DeleteData(17);
//        DeleteData(18);
//        DeleteData(19);
        InitializeList();
        //TESTING
        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardcount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
    }
    @Override
    public void onClick(View view){
//        Toast.makeText(mContext, Integer.toString(view.getId()),Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.button:
                AddInfo addHD = new AddInfo();
                Intent intent = new Intent(MainActivity.this,addHD.getClass());
                MainActivity.this.startActivityForResult(intent,2);
                break;
        }
        if (view instanceof CardView){
            Cursor singleRow = myDb.getSingleRow(view.getId()-cardcountInitial);
            if (singleRow != null)
                singleRow.moveToFirst();
            edit_info editHD = new edit_info();
            Intent intent = new Intent(MainActivity.this,editHD.getClass());
            Bundle b = new Bundle();
            b.putInt("bId",singleRow.getInt(0));
            b.putString("bName",singleRow.getString(1));
            b.putString("bDate",singleRow.getString(2));
            b.putString("bNotes",singleRow.getString(3));
            intent.putExtras(b);
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
            AddData(cardcount,Name,Date,Notes);
        }
    }

    private void InitializeList(){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
//            Toast.makeText(mContext,"Error \n Nothing found",Toast.LENGTH_SHORT);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            AddHoneyDo(res.getInt(0),res.getString(1),res.getString(2),res.getString(3));
//            Toast.makeText(mContext,"DB Index: "+res.getInt(0),Toast.LENGTH_SHORT);
        }
    }

    public  void AddData(Integer id,String name,String date, String notes) {
        //TESTING
        boolean isInserted = myDb.insertData(id,name,date,notes);
        if(isInserted == true){
            AddHoneyDo(id,name,date,notes);
//            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
            }
        else
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
    }

    public void DeleteData(Integer id) {
        Integer deletedRows = myDb.deleteData(id.toString());
//        if(deletedRows > 0)
//            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_SHORT).show();
    }

    public void AddHoneyDo(Integer id,String Name,String Date,String Notes){
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
                String noteText = "ID:"+id+"\nName: "+Name+"\n"+"Date: "+Date+"\n"+"Notes: "+Notes;
                tv.setText(noteText);
                tv.setMinHeight(30);
//                tv.isClickable();
//                tv.setOnClickListener(MainActivity.this);

                newCardView.addView(tv);
                cardV.addView(newCardView,cardcount);
                newCardView. setId(id+cardcountInitial);
                cardcount += 1;
    }

    public void EditHoneyDo(Integer id,String Name,String Date,String Notes){

    }

}
