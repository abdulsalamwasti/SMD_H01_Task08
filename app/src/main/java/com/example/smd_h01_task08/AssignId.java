package com.example.smd_h01_task08;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AssignId {

    static private FirebaseDatabase database = FirebaseDatabase.getInstance("https://hometask08-b2779-default-rtdb.firebaseio.com/");
    static private DatabaseReference myRef = database.getReference();
    int objectCount;

    public AssignId() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DataSnapshot snapshot1 = (DataSnapshot) snapshot.getChildren();
                objectCount = (int) snapshot1.getChildrenCount();
                Log.d("wasti", String.valueOf(objectCount));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public int getObjectCount() {
        return objectCount;
    }
}
