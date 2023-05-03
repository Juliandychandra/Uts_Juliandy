package com.si61.uts_juliandysqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvPasar;
    private com.si61.uts_juliandysqlite.MyDatabaseHelper myDB;
    private ArrayList<String>arrID, arrNama, arrKota, arrAlamat;
    private com.si61.uts_juliandysqlite.MyDatabaseHelper adPasar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabTambah = findViewById(R.id.fab_Tambah);
        rvPasar = findViewById(R.id.rv_Pasar);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.si61.uts_juliandysqlite.MyDatabaseHelper.class));
            }
        });
        myDB = new com.si61.uts_juliandysqlite.MyDatabaseHelper(MainActivity.this);
    }

    private void SQLiteToArrayList() {
        Cursor varCursor = myDB.bacaDataPasar();
        if (varCursor.getCount() == 0) {
            Toast.makeText(this, "Data Pasar Tidak Tersedia!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (varCursor.moveToNext()){
                arrID.add(varCursor.getString(0));
                arrNama.add(varCursor.getString(1));
                arrKota.add(varCursor.getString(2));
                arrAlamat.add(varCursor.getString(3));
            }
        }
    }

    private void tampilPasar() {
        arrID = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrKota = new ArrayList<>();
        arrAlamat = new ArrayList<>();

        SQLiteToArrayList();

        adPasar = new com.si61.uts_juliandysqlite.MyDatabaseHelper(MainActivity.this,
                arrID, arrNama, arrKota, arrAlamat);
        rvPasar.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPasar.setAdapter(adPasar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilPasar();
    }
}