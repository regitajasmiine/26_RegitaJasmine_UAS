package com.example.a26_regitajasmine_uas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateData extends AppCompatActivity {
    EditText text1, text2;
    String Etext1, Etext2;
    Button btnSave;
    DatabaseHelper databaseHelper;
    Notes notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);


        text1 = (EditText) findViewById(R.id.edit1);
        text2 = (EditText) findViewById(R.id.Edit2);
        btnSave   = (Button) findViewById(R.id.Update);
        text1.setText(getIntent().getStringExtra("Judul"));
        text2.setText(getIntent().getStringExtra("Deskripsi"));

        databaseHelper = new DatabaseHelper(UpdateData.this);
        notes = new Notes();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

    }

    public void updateData() {
        notes.setJudul(text1.getText().toString());
        notes.setDeskripsi(text2.getText().toString());
        databaseHelper.update(notes);
        startActivity(new Intent(UpdateData.this, ListData.class));
        finish();
    }
}