package com.example.smd_h01_task08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllRecordsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Student> studentsList;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_records);

        database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
        myRef = database.getReference();
        studentsList = new ArrayList<Student>();

        LoadData();

        recyclerView = findViewById(R.id.alldata_recyclerView);
        adapter = new RecyclerViewAdapter(studentsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        textView = findViewById(R.id.alldata_h2);

    }

    private void LoadData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren())
                {
                    //Log.d("wasti", snap.child("name").getValue().toString());
                    Student student = new Student();
                    student.setId(Integer.parseInt(snap.child("id").getValue().toString()));
                    student.setName(snap.child("name").getValue().toString());
                    student.setCnic(snap.child("cnic").getValue().toString());
                    student.setAge(snap.child("age").getValue().toString());
                    student.setSemester(snap.child("semester").getValue().toString());
                    student.setGpa(snap.child("cgpa").getValue().toString());
                    student.setObjectName(snap.getKey().toString());
                    studentsList.add(student);
                }
                adapter.notifyDataSetChanged();
                textView.setText( String.valueOf(studentsList.size()) + " Records found");
                if (studentsList.size() == 0)
                {
                    Toast.makeText(getApplicationContext(), "No record found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}