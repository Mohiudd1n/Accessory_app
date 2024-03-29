package com.example.madmp1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
    EditText quan;

    View view ;
    public DBManager db;
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
    private void dialog_show(int pos){
        quan = view.findViewById(R.id.nquantity);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Edit Quantity or Remove Item");
        builder.setMessage("Do you want to edit the quantity or remove this item from the cart?");

        builder.setPositiveButton("Edit Quantity", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogTheme);

                // Inflate custom layout for dialog
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                builder.setView(dialogView);

                ImageView dialogIcon = dialogView.findViewById(R.id.dialogIcon);
                TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
                EditText quan = dialogView.findViewById(R.id.nquantity);

//                dialogIcon.setImageResource(R.drawable.ic_alert);
                dialogTitle.setText("Add New Quantity");

                builder.setPositiveButton("Edit Quantity", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String quant = quan.getText().toString().trim();
                        int n = Integer.parseInt(quant);
                        int price = amount.get(pos)/quantity.get(pos);
                        int amo = price*n;
                        String em = db.get_email();
                        String d = desc.get(pos);
                        db.update_quantity(d,em, n,amo);
                        adp.clear();
                        adp2.clear();
                        adp3.clear();
                        initialize();
                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing, simply close the dialog
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        builder.setNegativeButton("Remove Item", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.remove_product(desc.get(pos));
                adp.clear();
                adp2.clear();
                adp3.clear();
                initialize();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing, simply close the dialog
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void initialize(){
        orderplaced = view.findViewById(R.id.orders);
        cartList = view.findViewById(R.id.list1);
        cartList2 = view.findViewById(R.id.list2);
        db = new DBManager(getContext());
        cartList3 = view.findViewById(R.id.list3);
        String email = db.get_email();
        Cursor cursor = db.fetch_cart(email);

        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_show(position);
            }
        });

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
            orderplaced.setEnabled(false);
        }
//        db.close();

    }
}