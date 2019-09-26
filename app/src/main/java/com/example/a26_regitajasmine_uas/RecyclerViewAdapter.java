package com.example.a26_regitajasmine_uas;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    java.util.List<Notes> listPersonInfo;
    public RecyclerViewAdapter(Context context, List<Notes>
            listPersonInfo, OnUserClickListener listener) {
        this.context=context;
        this.listPersonInfo=listPersonInfo;
        this.listener=listener;
    }
    public interface OnUserClickListener{
        void onUserClick(Notes currentPerson, String action);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_data,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Notes currentPerson=listPersonInfo.get(position);
        holder.ctxtJudul.setText(currentPerson.getJudul());
        holder.ctxtDeskripsi.setText(currentPerson.getDeskripsi());
        holder.ctxtdate.setText(currentPerson.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onUserClick(currentPerson,"Menu");
                final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                final View view = LayoutInflater.from(context).inflate(R.layout.activity_dialog, null);
                dialog.setView(view);


                final TextView updateData= view.findViewById(R.id.updateDataa);
                final TextView deleteData= view.findViewById(R.id.hapusDataa);

                final AlertDialog alertDialog = dialog.create();
                alertDialog.show();
                deleteData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(currentPerson,"Delete");

                    }
                });
                updateData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(currentPerson, "Update");
                    }
                });

            }
        });


    }
    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView ctxtJudul, ctxtDeskripsi,ctxtdate;
        ImageView imgMenu;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtJudul=itemView.findViewById(R.id.ctxtJudul);
            cardView=itemView.findViewById(R.id.card);
            ctxtdate = itemView.findViewById(R.id.ctxtdate);
            ctxtDeskripsi=itemView.findViewById(R.id.ctxtdeskripsi);
        }
    }
}
