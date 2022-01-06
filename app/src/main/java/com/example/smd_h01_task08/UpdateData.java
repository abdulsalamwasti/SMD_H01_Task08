package com.example.smd_h01_task08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    FirebaseLinks firebaseLinks;
    EditText nameTxt,cnicTxt,ageTxt,semesterTxt,cgpaTxt;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        firebaseLinks = new FirebaseLinks();

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("updateItem");

        nameTxt = findViewById(R.id.updatedata_PersonName2);
        cnicTxt = findViewById(R.id.updatedata_PersonName3);
        ageTxt = findViewById(R.id.updatedata_PersonName4);
        semesterTxt = findViewById(R.id.updatedata_PersonName5);
        cgpaTxt = findViewById(R.id.updatedata_PersonName6);


        nameTxt.setText(student.getName());
        cnicTxt.setText(student.getCnic());
        ageTxt.setText(student.getAge());
        semesterTxt.setText(student.getSemester());
        cgpaTxt.setText(student.getGpa());

    }

    public void updateRecord(View view) {

        student.setName(nameTxt.getText().toString());
        student.setCnic(cnicTxt.getText().toString());
        student.setAge(ageTxt.getText().toString());
        student.setSemester(semesterTxt.getText().toString());
        student.setGpa(cgpaTxt.getText().toString());
        //student.setObjectName("student" + String.valueOf(student.getId()));
        if (student.getName().length() > 0 && student.getCnic().length() > 0 &&
                student.getAge().length() > 0 && student.getSemester().length() > 0 &&
                student.getGpa().length() > 0)
        {
            //Log.d("wasti", student.getObjectName());
            firebaseLinks.insertData(student);
            finish();

        }
        else
        {
            Toast.makeText(this, "Please fill complete form" + String.valueOf(firebaseLinks.getLastId()), Toast.LENGTH_SHORT).show();
        }
    }
}