package com.example.smd_h01_task08;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button createBtn,searchBtn,updateBtn,deleteBtn;
    TextView heading;

    AssignId assignId = new AssignId();
    int objectCount = assignId.getObjectCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBtn = findViewById(R.id.button1);
        searchBtn = findViewById(R.id.button2);
        updateBtn = findViewById(R.id.button3);
        deleteBtn = findViewById(R.id.button4);



        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateRecordActivity.class);
                startActivity(intent);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);
                //objectCount = assignId.getObjectCount();
                //Toast.makeText(getApplicationContext(), String.valueOf(objectCount) + assignId.getLastStudent() + assignId.lastId, Toast.LENGTH_LONG).show();

            }
        });

    }
}