package com.example.homefoodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.homefoodapp.Domain.Foods;
import com.example.homefoodapp.FoodDetail_Screen;
import com.example.homefoodapp.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;

    public FoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FoodAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context =parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText(items.get(position).getPrice() + " руб");
        holder.category.setText(items.get(position).getCategory());
        holder.timeTxt.setText(items.get(position).getTimeValue() + " минут");
        holder.starTxt.setText(""+items.get(position).getStar());
        holder.person.setText(items.get(position).getPerson());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, FoodDetail_Screen.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt,timeTxt,starTxt,category,person;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            timeTxt=itemView.findViewById(R.id.time);
            titleTxt=itemView.findViewById(R.id.Ttitle);
            priceTxt=itemView.findViewById(R.id.total);
            starTxt=itemView.findViewById(R.id.stars);
            category=itemView.findViewById(R.id.category);
            pic=itemView.findViewById(R.id.pic);
            person=itemView.findViewById(R.id.person);


        }
    }
}
