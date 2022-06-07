package com.example.giampiltd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList shoeId, shoeName, shoeNumberSize, shoeLengthSize,
            shoeUpperMaterial, shoeOutsoleMaterial, shoePrice;

    public CustomAdapter(Activity activity, Context context1,
                  ArrayList shoeId, ArrayList shoeName, ArrayList shoeNumberSize,
                  ArrayList shoeLengthSize,ArrayList shoeUpperMaterial,
                  ArrayList shoeOutsoleMaterial,ArrayList shoePrice) {
        this.activity = activity;
        this.context = context1;
        this.shoeId = shoeId;
        this.shoeName = shoeName;
        this.shoeNumberSize = shoeNumberSize;
        this.shoeLengthSize = shoeLengthSize;
        this.shoeUpperMaterial = shoeUpperMaterial;
        this.shoeOutsoleMaterial = shoeOutsoleMaterial;
        this.shoePrice = shoePrice;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.shoe_id_txt.setText(String.valueOf(shoeId.get(position)));
        holder.shoe_name_txt.setText(String.valueOf(shoeName.get(position)));
        holder.shoe_number_size_txt.setText("Н. " + String.valueOf(shoeNumberSize.get(position)));
        holder.shoe_length_size_txt.setText(String.valueOf(shoeLengthSize.get(position)) + " см");
        holder.shoe_upper_material_txt.setText(String.valueOf(shoeUpperMaterial.get(position)));
        holder.shoe_outsole_material_txt.setText(String.valueOf(shoeOutsoleMaterial.get(position)));
        holder.shoe_price_txt.setText(String.valueOf(shoePrice.get(position)) + " лв.");
        holder.main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(shoeId.get(position)));
                intent.putExtra("name", String.valueOf(shoeName.get(position)));
                intent.putExtra("number_size", String.valueOf(shoeNumberSize.get(position)));
                intent.putExtra("length_size", String.valueOf(shoeLengthSize.get(position)));
                intent.putExtra("upper_material", String.valueOf(shoeUpperMaterial.get(position)));
                intent.putExtra("outsole_material", String.valueOf(shoeOutsoleMaterial.get(position)));
                intent.putExtra("price", String.valueOf(shoePrice.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoeId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shoe_id_txt, shoe_name_txt, shoe_number_size_txt, shoe_length_size_txt,
                shoe_upper_material_txt, shoe_outsole_material_txt, shoe_price_txt;
        LinearLayout main_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shoe_id_txt = itemView.findViewById(R.id.shoe_id_text);
            shoe_name_txt = itemView.findViewById(R.id.shoe_name_text);
            shoe_number_size_txt = itemView.findViewById(R.id.shoe_numbersize_text);
            shoe_length_size_txt = itemView.findViewById(R.id.shoe_lengthsize_text);
            shoe_upper_material_txt = itemView.findViewById(R.id.shoe_upper_material_text);
            shoe_outsole_material_txt = itemView.findViewById(R.id.shoe_outsole_material_text);
            shoe_price_txt = itemView.findViewById(R.id.shoe_price_text);
            main_layout = itemView.findViewById(R.id.main_layout);
        }
    }
}
