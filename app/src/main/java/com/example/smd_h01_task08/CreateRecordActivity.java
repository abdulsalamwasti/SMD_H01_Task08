package com.example.smd_h01_task08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRecordActivity extends AppCompatActivity {

    int count;
    FirebaseLinks firebaseLinks;
    EditText nameTxt,cnicTxt,ageTxt,semesterTxt,cgpaTxt;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);
        student = new Student();
        firebaseLinks = new FirebaseLinks();

        nameTxt = findViewById(R.id.editTextTextPersonName2);
        cnicTxt = findViewById(R.id.editTextTextPersonName3);
        ageTxt = findViewById(R.id.editTextTextPersonName4);
        semesterTxt = findViewById(R.id.editTextTextPersonName5);
        cgpaTxt = findViewById(R.id.editTextTextPersonName6);

        Toast.makeText(getApplicationContext(), String.valueOf(firebaseLinks.getLastId()), Toast.LENGTH_LONG).show();

    }


    public void submitRecord(View view) {

        count = firebaseLinks.getLastId() + 1;

        student.setId(count);
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
            //Log.d("wasti", String.valueOf(count));
            firebaseLinks.insertData(student);
            finish();
        }
        else
        {
            Toast.makeText(this, "Please fill complete form" + String.valueOf(firebaseLinks.getLastId()), Toast.LENGTH_SHORT).show();
        }

    }
}