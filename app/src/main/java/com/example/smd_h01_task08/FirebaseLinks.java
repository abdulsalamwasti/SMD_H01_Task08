package com.example.smd_h01_task08;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class FirebaseLinks {

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
    private DatabaseReference myRef = database.getReference();
    int objectCount;
    int lastId;
    String lastStudent;


    public FirebaseLinks() {
        updateCount();
    }

    private void updateCount()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                objectCount = (int) snapshot.getChildrenCount();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    int nlastId = Integer.parseInt(String.valueOf(snap.child("id").getValue()));
                    if (nlastId > lastId)
                    {
                        lastId = nlastId;
                        lastStudent = String.valueOf(snap.getKey());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertData(Student student)
    {
        myRef.child(student.getObjectName()).child("id").setValue(student.getId());
        myRef.child(student.getObjectName()).child("name").setValue(student.getName());
        myRef.child(student.getObjectName()).child("cnic").setValue(student.getCnic());
        myRef.child(student.getObjectName()).child("age").setValue(student.getAge());
        myRef.child(student.getObjectName()).child("semester").setValue(student.getSemester());
        myRef.child(student.getObjectName()).child("cgpa").setValue(student.getGpa());
    }

    public void deleteData(Student student)
    {
        myRef.orderByChild("id").equalTo(student.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    snap.getRef().removeValue();
                    Log.d("wasti", String.valueOf(snap));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public int getObjectCount() {
        updateCount();
        return objectCount;
    }
    public int getLastId() {
        updateCount();
        return lastId;
    }
    public String getLastStudent() {
        updateCount();
        return lastStudent;
    }
}
