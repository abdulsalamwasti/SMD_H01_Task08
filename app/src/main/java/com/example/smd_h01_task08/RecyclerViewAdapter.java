package com.example.smd_h01_task08;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myHolder> {


    ArrayList<Student> studentArrayList;
    public RecyclerViewAdapter(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recordview_card,parent, false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        //Student student = studentArrayList.get(position);
        holder.name.setText(studentArrayList.get(position).getName());
        holder.cnic.setText(studentArrayList.get(position).getCnic());
        holder.age.setText(studentArrayList.get(position).getAge());
        holder.semester.setText(studentArrayList.get(position).getSemester());
        holder.cgpa.setText(studentArrayList.get(position).getGpa());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,cnic,age,semester,cgpa;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtStdName);
            cnic = itemView.findViewById(R.id.txtStdcnic);
            age = itemView.findViewById(R.id.txtStdAge);
            semester = itemView.findViewById(R.id.txtStdSemester);
            cgpa = itemView.findViewById(R.id.txtStdCgpa);


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            // Triggers click upwards to the adapter on click
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position);
                }
            }
        }

    }



    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define listener member variable
    private OnItemClickListener listener;

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
