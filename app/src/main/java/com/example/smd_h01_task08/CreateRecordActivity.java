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

    int count = 0;
    AssignId assignId = new AssignId();
    EditText nameTxt,cnicTxt,ageTxt,semesterTxt,cgpaTxt;
    Student student;
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
        student.setId(assignId.getLastId() + 1);
        student.setName(nameTxt.getText().toString());
        student.setCnic(cnicTxt.getText().toString());
        student.setAge(ageTxt.getText().toString());
        student.setSemester(semesterTxt.getText().toString());
        student.setGpa(cgpaTxt.getText().toString());
        student.setObjectName("student" + String.valueOf(student.getId()));

        if (student.getName().length() > 0 && student.getCnic().length() > 0 &&
                student.getAge().length() > 0 && student.getSemester().length() > 0 &&
                student.getGpa().length() > 0)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child(student.getObjectName()).child("id").setValue(student.getId());
            myRef.child(student.getObjectName()).child("name").setValue(student.getName());
            myRef.child(student.getObjectName()).child("cnic").setValue(student.getCnic());
            myRef.child(student.getObjectName()).child("age").setValue(student.getAge());
            myRef.child(student.getObjectName()).child("semester").setValue(student.getSemester());
            myRef.child(student.getObjectName()).child("cgpa").setValue(student.getGpa());
            finish();
        }
        else
        {
            Toast.makeText(this, "Please fill complete form", Toast.LENGTH_SHORT).show();
        }

    }
}