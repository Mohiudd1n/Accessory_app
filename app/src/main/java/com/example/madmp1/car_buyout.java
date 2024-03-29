package com.example.madmp1;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class car_buyout extends AppCompatActivity {
    int _quantity;
    String desc;
    EditText q;
    DBManager dbManager = new DBManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_buyout);

        q = findViewById(R.id.quantity_amnt);
        desc = getIntent().getStringExtra("desc");
        String rate = getIntent().getStringExtra("rate");
        String oprice = getIntent().getStringExtra("oprice");
        String price = getIntent().getStringExtra("price");
        int image = getIntent().getIntExtra("image",0);


        Button buyit = findViewById(R.id.buyout);
        TextView tv_desc = findViewById(R.id.desc);
        TextView tv_rate = findViewById(R.id.rate);
        TextView tv_oprice = findViewById(R.id.oprice);
        TextView tv_price = findViewById(R.id.price);
        ImageView imageView = findViewById(R.id.imageView);
        EditText quantity = findViewById(R.id.quantity_amnt);

        tv_desc.setText(desc);
        tv_rate.setText(rate);
        tv_oprice.setText(oprice);
        tv_price.setText(price);
        imageView.setImageResource(image);

        int dr_before = R.drawable.baseline_add_24;
        int dr_after = R.drawable.baseline_done_24;

        buyit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emal = dbManager.get_email();
                String des = desc;
                if(quantity.getText().toString().equals("")){
                    quantity.setError("Please enter quantity");
                    //                    Toast.makeText(getApplicationContext(), "Please Enter Valid Quantity!", Toast.LENGTH_LONG).show();
                } else if (dbManager.check_item(des,emal)) {
                    quantity.setError("Product Already In Cart. Please Edit Details in Cart");
                } else{

                String email = dbManager.get_email();
//                Toast.makeText(getApplicationContext(), MainActivity.userEmail, Toast.LENGTH_LONG).show();
                String inputString = tv_price.getText().toString();
                String description = tv_desc.getText().toString();

                String value = inputString.replaceAll("[^0-9]", "");
                int price_value = Integer.parseInt(value);

                if(!quantity.getText().toString().equals("")) {
                    _quantity = Integer.parseInt(quantity.getText().toString());
                    int quantity_price = _quantity*price_value;
                    boolean a = dbManager.addproduct(email,description,_quantity,quantity_price);
                    if(a) {
                        buyit.animate()
                                .scaleX(1.05f)
                                .scaleY(1.05f)
                                .setDuration(200) // duration
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        buyit.setText("Added To Cart");
                                        buyit.setEnabled(false);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                buyit.setText("Add To Cart");
                                                buyit.setEnabled(true);
                                                buyit.animate().scaleX(1f).scaleY(1f).setDuration(300); // Reset animation
                                            }
                                        }, 2000);
                                    }
                                })
                                .start();
//                        Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_LONG).show();
                        q.setText("");
                    }
                }}
            }
        });
    }
}