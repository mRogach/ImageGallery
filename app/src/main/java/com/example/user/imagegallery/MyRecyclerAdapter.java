package com.example.user.imagegallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 03.02.2015.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{


    private ArrayList<Image> items;
    private int itemLayout;


    public MyRecyclerAdapter(ArrayList<Image> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;

    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Image item = items.get(position);

        Picasso.with(holder.image.getContext())
                .load(item.getmUrl())
                .into(holder.image);
        //new LoadImageFromUrlTask(holder.image.getContext(), holder.image).execute(item.getmUrl());
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}