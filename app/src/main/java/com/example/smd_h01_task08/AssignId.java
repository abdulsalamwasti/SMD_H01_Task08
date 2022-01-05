package com.example.smd_h01_task08;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AssignId {

    static private FirebaseDatabase database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
    static private DatabaseReference myRef = database.getReference();
    int objectCount;
    int lastId;
    String lastStudent;


    public AssignId() {
        updateCount();
    }

    private void updateCount()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                objectCount = (int) snapshot.getChildrenCount();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    lastId = Integer.parseInt(String.valueOf(snap.child("id").getValue()));
                    //Log.d("wasti", String.valueOf(snap.child("id").getValue()));
                    lastStudent = String.valueOf(snap.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public int getObjectCount() {
        updateCount();
        //Log.d("wasti", String.valueOf(objectCount));
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
