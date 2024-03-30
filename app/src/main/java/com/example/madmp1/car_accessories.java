package com.example.madmp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class car_accessories extends AppCompatActivity implements RecyclerViewClick {

    ArrayList<CarAccModels> carAccModels = new ArrayList<>();
    int[] car_acc_images = {R.drawable.car_holder,R.drawable.item_2,R.drawable.item_3,R.drawable.item_4,R.drawable.item_5,
    R.drawable.item_6,R.drawable.item_7,R.drawable.car_item12,R.drawable.car_item9,R.drawable.car_item10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.statusBarColor));
        setContentView(R.layout.activity_car_accessories);

        RecyclerView recyclerView = findViewById(R.id.myrecyclerview);

        setCarAccModels();

        RecyclerManager adp = new RecyclerManager(this, this,carAccModels);

        recyclerView.setAdapter(adp);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setCarAccModels(){
        String[] car_desc = getResources().getStringArray(R.array.car_accessory_desc);
        String[] car_price = getResources().getStringArray(R.array.car_accessory_price);
        String[] car_oprice = getResources().getStringArray(R.array.car_accessory_old_price);
        String[] car_rate = getResources().getStringArray(R.array.car_accessory_rating);


        for(int i=0;i<car_oprice.length;i++){
            carAccModels.add(new CarAccModels(car_desc[i],
                    car_rate[i],
                    car_price[i],
                    car_oprice[i],car_acc_images[i]));
        }

    }

    @Override
    public void OnItemClick(int position) {
       Intent intent = new Intent(car_accessories.this, car_buyout.class);

       intent.putExtra("desc",carAccModels.get(position).getCar_desc());
       intent.putExtra("rate",carAccModels.get(position).getCar_rate());
       intent.putExtra("oprice",carAccModels.get(position).getCar_oprice());
       intent.putExtra("price",carAccModels.get(position).getCar_price());
       intent.putExtra("image",carAccModels.get(position).getCar_image());

       startActivity(intent);
    }
}