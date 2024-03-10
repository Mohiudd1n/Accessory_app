package com.example.madmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class your_orders extends AppCompatActivity {
    String email = MainActivity.userEmail;
    ListView cartList,cartList2,cartList3;

    ArrayAdapter<String> adp ;
    Button orderplaced;
    ArrayAdapter<Integer> adp2 ;
    String str;
    ArrayAdapter<Integer> adp3 ;
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> short_desc = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        cartList = findViewById(R.id.list1);
        cartList2 = findViewById(R.id.list2);
        cartList3 = findViewById(R.id.list3);
        DBManager db = new DBManager(getApplicationContext());
        String email = db.get_email();
        Cursor cursor = db.fetch_orders(email);
        adp = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,desc);

        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex("description");
                desc.add(cursor.getString(index));

                int index2 = cursor.getColumnIndex("quantity");
                quantity.add(cursor.getInt(index2));

                int index3 = cursor.getColumnIndex("amount");
                amount.add(cursor.getInt(index3));

            }
            for (int i = 0; i < quantity.toArray().length; i++) {
                str = desc.get(i);
                short_desc.add(str.substring(0, 20) + "...");
            }
            adp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, short_desc);
            adp2 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, quantity);
            adp3 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, amount);
            cartList.setAdapter(adp);
            cartList2.setAdapter(adp2);
            cartList3.setAdapter(adp3);
        }
    }
}