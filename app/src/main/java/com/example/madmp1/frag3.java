package com.example.madmp1;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class frag3 extends Fragment {

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

    View view ;
    DBManager db;
    public frag3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_frag3, container, false);
        initialize();
        return view;
    }

    private void initialize(){
        orderplaced = view.findViewById(R.id.orders);
        cartList = view.findViewById(R.id.list1);
        cartList2 = view.findViewById(R.id.list2);
        cartList3 = view.findViewById(R.id.list3);
        db = new DBManager(getContext());
        String email = db.get_email();
        Cursor cursor = db.fetch_cart(email);

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                int index = cursor.getColumnIndex("description");
                desc.add(cursor.getString(index));

                int index2 = cursor.getColumnIndex("quantity");
                quantity.add(cursor.getInt(index2));

                int index3 = cursor.getColumnIndex("amount");
                amount.add(cursor.getInt(index3));

            }
            for(int i=0; i < quantity.toArray().length; i++) {
                str = desc.get(i);
                short_desc.add(str.substring(0,20) + "...");
            }
            adp = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,short_desc);
            adp2 = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_list_item_1,quantity);
            adp3 = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_list_item_1,amount);
            cartList.setAdapter(adp);
            cartList2.setAdapter(adp2);
            cartList3.setAdapter(adp3);
            orderplaced.setEnabled(true);

            orderplaced.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = db.get_email();
                    if (email != null){
                        db.insert_order(email);
                        db.clear_cart(email);
                        initialize();
                        adp.clear();
                        adp2.clear();
                        adp3.clear();
                    }else{

                    }
                }
            });
        }
        else
        {
            Toast.makeText(getContext(),"Empty", Toast.LENGTH_SHORT).show();
            orderplaced.setEnabled(false);
        }

        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

//        db.close();

    }

}