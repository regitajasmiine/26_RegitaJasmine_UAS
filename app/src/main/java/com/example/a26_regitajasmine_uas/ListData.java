package com.example.a26_regitajasmine_uas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListData extends AppCompatActivity implements RecyclerViewAdapter.OnUserClickListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Notes> notesList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button btn1= findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListData.this,InputData.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);

        setupRecyclerView();
    }

    private void setupRecyclerView(){
        DatabaseHelper db = new DatabaseHelper(ListData.this);
        notesList = db.selectUserData();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(ListData.this , notesList,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onUserClick(Notes currentPerson, String action) {
        if (action == "Delete"){
            DatabaseHelper db = new DatabaseHelper(ListData.this);
            db.delete(currentPerson.getJudul());
            setupRecyclerView();
        } else if(action == "Update"){
            DatabaseHelper db = new DatabaseHelper(ListData.this);
            Intent intent = new Intent(ListData.this, UpdateData.class);
            intent.putExtra("Judul",currentPerson.getJudul());
            intent.putExtra("Deskripsi",currentPerson.getDeskripsi());
            startActivity(intent );
        }

    }
}
