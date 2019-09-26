package com.example.a26_regitajasmine_uas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputData extends AppCompatActivity {
    EditText text1, text2;
    String Etext1, Etext2;
    Button btnSave;
    DatabaseHelper databaseHelper;
    Notes notes;

    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        text1 = (EditText) findViewById(R.id.edit1);
        text2 = (EditText) findViewById(R.id.edit2);
        save =(Button) findViewById(R.id.btnSimpan);
        databaseHelper = new DatabaseHelper(InputData.this);
        notes = new Notes();

        btnSave = (Button) findViewById(R.id.btnSimpan);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ' ' HH:mm:ss");
                String date = sdf.format(new Date());
                Etext1 = text1.getText().toString();
                Etext2 = text2.getText().toString();
                notes.setJudul(Etext1);
                notes.setDeskripsi(Etext2);
                notes.setDate(date);
                databaseHelper.insert(notes);

                text1.setText("");
                text2.setText("");
            }
        });

    }
}
