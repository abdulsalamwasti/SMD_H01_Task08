package com.example.smd_h01_task08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    FirebaseLinks firebaseLinks;
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Student> studentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
        myRef = database.getReference();
        firebaseLinks = new FirebaseLinks();
        studentsList = new ArrayList<Student>();

        LoadData();

        recyclerView = findViewById(R.id.delete_recyclerView);
        adapter = new RecyclerViewAdapter(studentsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Student student = studentsList.get(position);
                firebaseLinks.deleteData(student);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Record of " + student.getName() + "deleted sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
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
}