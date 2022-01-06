package com.example.smd_h01_task08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    //FirebaseLinks firebaseLinks;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Student> testList = new ArrayList<Student>();
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
        myRef = database.getReference();

        textView = findViewById(R.id.editTextsearch2);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(testList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void searchRecord(View view) {

        String nameSearched = textView.getText().toString();
        if (nameSearched.length() > 0)
        {
            myRef.orderByChild("name").equalTo(nameSearched).addValueEventListener(new ValueEventListener() {
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
                        testList.add(student);
                    }
                    adapter.notifyDataSetChanged();

                    if (testList.size() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "No record found for " + nameSearched, Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter name to search", Toast.LENGTH_SHORT).show();
        }

    }

}