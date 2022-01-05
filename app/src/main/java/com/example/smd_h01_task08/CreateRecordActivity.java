package com.example.smd_h01_task08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRecordActivity extends AppCompatActivity {

    int count = 2;
    EditText nameTxt,cnicTxt,ageTxt,semesterTxt,cgpaTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);

        nameTxt = findViewById(R.id.editTextTextPersonName2);
        cnicTxt = findViewById(R.id.editTextTextPersonName3);
        ageTxt = findViewById(R.id.editTextTextPersonName4);
        semesterTxt = findViewById(R.id.editTextTextPersonName5);
        cgpaTxt = findViewById(R.id.editTextTextPersonName6);

    }


    public void submitRecord(View view) {
        String nameStr = nameTxt.getText().toString();
        String cnicStr = cnicTxt.getText().toString();
        String ageStr = ageTxt.getText().toString();
        String semesterStr = semesterTxt.getText().toString();
        String cgpaStr = cgpaTxt.getText().toString();
        String id = "student" + count;
        count++;

        if (nameStr.length() > 0 && cnicStr.length() > 0 &&
                ageStr.length() > 0 && semesterStr.length() > 0 &&
                cgpaStr.length() > 0)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("records");
            myRef.child(id).child("name").setValue(nameStr);
            myRef.child(id).child("cnic").setValue(cnicStr);
            myRef.child(id).child("age").setValue(ageStr);
            myRef.child(id).child("semester").setValue(semesterStr);
            myRef.child(id).child("cgpa").setValue(cgpaStr);
        }
        else
        {
            Toast.makeText(this, "Please fill complete form", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}