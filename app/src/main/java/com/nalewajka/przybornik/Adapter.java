package com.nalewajka.przybornik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Notatka> notes;
    private View.OnClickListener mOnItemClickListener;

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewList.setText(notes.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setData(ArrayList<Notatka> newNotes){
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewList;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewList = itemView.findViewById(R.id.textViewItemList);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}