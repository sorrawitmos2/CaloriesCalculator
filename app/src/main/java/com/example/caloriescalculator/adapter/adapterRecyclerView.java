package com.example.caloriescalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriescalculator.R;
import com.example.caloriescalculator.db.food;

import java.util.List;

public class adapterRecyclerView extends RecyclerView.Adapter<adapterRecyclerView.MyViewHolder> {

    private Context mContext;
    private int mLayoutResId;
    private List<food> foodItemList;

    public adapterRecyclerView(Context mContext, int mLayoutResId, List<food> foodItemList) {
        this.mContext = mContext;
        this.mLayoutResId = mLayoutResId;
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutResId, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        food item  = foodItemList.get(position);

        holder.food=item;
        holder.nameTextView.setText(item.name);
        holder.calTextView.setText(String.valueOf(item.cal));

    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView calTextView;

        private food food;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.food_name_text_view);
            this.calTextView = itemView.findViewById(R.id.textView2);


        }
    }



}
