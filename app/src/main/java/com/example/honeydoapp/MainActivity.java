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
    private Button addButton;
    private LinearLayout cardV;
    private static int cardCountInitial;
    private static int cardCount;
    public databaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        //Add new card button
        addButton = findViewById(R.id.button);
        addButton.setOnClickListener(this);
        //Card View that holds the cards.
        cardV = findViewById(R.id.linearLayout);
        //Id offset for the card Views
        cardCountInitial = 7000;
        //Card count incrementer for the Main screen
        cardCount = 0;
        //Database connection
        myDb = new databaseHelper(mContext);
        //TESTING
        //Clearing all cards to start fresh when the app launches.
        //Otherwise it retains the DB.
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
        //Adding cards for testing during build time.
        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
//        AddData(cardCount,"Filler","Mar 26, 2020","Filler Notes that are not that important, but are here to take up space.");
    }

//click listener to determine what to do when items on the screen are selected
    @Override
    public void onClick(View view){
    //React to Button Click
    // create a new intent and start the AddInfo activity which will return a result and a Bundle of data for the new Card
        if (view.getId() == R.id.button){
                AddInfo addHD = new AddInfo();
                Intent intent = new Intent(MainActivity.this,addHD.getClass());
                MainActivity.this.startActivityForResult(intent,2);
        }
        //If the click event happens on a card do the following code
        else if (view instanceof CardView){
            //grab the data from the database from the row that is the same that populated the card
            Cursor singleRow = myDb.getSingleRow(view.getId()- cardCountInitial);
            if (singleRow != null)
                singleRow.moveToFirst();
            //Create the intent to launch the edit view
            edit_info editHD = new edit_info();
            Intent intent = new Intent(MainActivity.this,editHD.getClass());
            //Create a bundle to pass with the Intent into the new activity
            Bundle b = new Bundle();
            b.putInt("bId",singleRow.getInt(0));
            b.putString("bName",singleRow.getString(1));
            b.putString("bDate",singleRow.getString(2));
            b.putString("bNotes",singleRow.getString(3));
            intent.putExtras(b);
            //Launch new Activity for editing
            MainActivity.this.startActivityForResult(intent,2);
        }
    }

//Catch the result from an activity and execute code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        // the result code from that activity is 3 and then it adds data to the database as well as adding a new card
        if(requestCode==2 && resultCode == 3)
        {
            Bundle dataBundle = data.getExtras();
            String Name;
            Name = dataBundle.getString("Name");
            String Date;
            Date = dataBundle.getString("Date");
            String Notes;
            Notes = dataBundle.getString("Notes");
            AddData(cardCount,Name,Date,Notes);
        }
    }

// fill Card List with all of the data sitting in the database
    private void InitializeList(){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        // Loop to step through all items returned from query
        while (res.moveToNext()) {
            // add a card to the view
            AddHoneyDo(res.getInt(0),res.getString(1),res.getString(2),res.getString(3));
        }
    }

    public  void AddData(Integer id,String name,String date, String notes) {
        boolean isInserted = myDb.insertData(id,name,date,notes);
        if(isInserted == true){
            //add data from manual create
            AddHoneyDo(id,name,date,notes);
            }
        else
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
    }

//Delete the item referenced by id
    public void DeleteData(Integer id) {
        Integer deletedRows = myDb.deleteData(id.toString());
    }

// Create card and format it and then add the data passed in to the Card text view
    public void AddHoneyDo(Integer id,String Name,String Date,String Notes){
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
                String noteText = //"ID:"+id+"\n"+
                    "Name: "+Name+"\n"+
                    "Date: "+Date+"\n"+
                    "Notes: "+Notes;
                tv.setText(noteText);
                tv.setMinHeight(30);

                //add card to the view
                newCardView.addView(tv);
                cardV.addView(newCardView, cardCount);
                newCardView. setId(id+ cardCountInitial);
                //increment counter for the view.
                cardCount += 1;
    }
}
