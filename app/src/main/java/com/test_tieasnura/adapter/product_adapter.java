package com.test_tieasnura.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.test_tieasnura.R;
import com.test_tieasnura.model.Mproducts;

import java.util.List;

public class product_adapter extends RecyclerView.Adapter<product_adapter.MyViewHolder> {

    private List<Mproducts> mproductsList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, desc, rate;
        ImageView image;

        public MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.prod_title);
            desc = view.findViewById(R.id.prod_desc);
            rate = view.findViewById(R.id.prod_rate);
            image = view.findViewById(R.id.prod_image);
        }
    }

    public product_adapter(List<Mproducts> product_list){
        this.mproductsList = product_list;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mproducts product = mproductsList.get(position);
        holder.name.setText(product.getProduct_name());
        holder.desc.setText(product.getProduct_desc());
        holder.rate.setText(product.getProduct_name());

        Picasso.get().load(product.getProduct_image()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
