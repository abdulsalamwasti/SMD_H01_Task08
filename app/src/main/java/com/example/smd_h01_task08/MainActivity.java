package com.example.smd_h01_task08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button createBtn,searchBtn,updateBtn,deleteBtn,allrecBtn;
    TextView heading;

    FirebaseLinks firebaseLinks = new FirebaseLinks();
    int objectCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBtn = findViewById(R.id.button1);
        searchBtn = findViewById(R.id.button2);
        updateBtn = findViewById(R.id.button3);
        deleteBtn = findViewById(R.id.button4);
        allrecBtn = findViewById(R.id.button5);



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
                //objectCount = firebaseLinks.getObjectCount();
                //Toast.makeText(getApplicationContext(), String.valueOf(objectCount) + firebaseLinks.getLastStudent() + firebaseLinks.getLastId(), Toast.LENGTH_LONG).show();

            }
        });
        allrecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllRecordsActivity.class);
                startActivity(intent);
            }
        });

    }
}