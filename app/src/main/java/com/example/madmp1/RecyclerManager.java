package com.example.madmp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerManager extends RecyclerView.Adapter<RecyclerManager.MyViewHolder> {

    private final RecyclerViewClick recyclerViewClick;
    Context context;
    ArrayList<CarAccModels> carAccModels;

    public RecyclerManager(RecyclerViewClick recyclerViewClick, Context context, ArrayList<CarAccModels> carAccModels){
        this.recyclerViewClick = recyclerViewClick;
        this.context = context;
        this.carAccModels = carAccModels;
    }

    @NonNull
    @Override
    public RecyclerManager.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new RecyclerManager.MyViewHolder(view, recyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerManager.MyViewHolder holder, int position) {
        holder.tv_desc.setText(carAccModels.get(position).getCar_desc());
        holder.tv_price.setText(carAccModels.get(position).getCar_price());
        holder.tv_oprice.setText(carAccModels.get(position).getCar_oprice());
        holder.tv_rate.setText(carAccModels.get(position).getCar_rate());
        holder.imageView.setImageResource(carAccModels.get(position).getCar_image());


    }

    @Override
    public int getItemCount() {
        return carAccModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tv_desc, tv_oprice, tv_price, tv_rate;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClick recyclerViewClick) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_desc = itemView.findViewById(R.id.textView);
            tv_price = itemView.findViewById(R.id.textView2);
            tv_oprice = itemView.findViewById(R.id.textView3);
            tv_rate = itemView.findViewById(R.id.rate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewClick != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewClick.OnItemClick(pos);
                        }
                    }
                }
            });

        }
    }

}

