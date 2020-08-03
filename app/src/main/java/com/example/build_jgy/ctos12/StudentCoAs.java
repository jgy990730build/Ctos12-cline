package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.build_jgy.ctos12.bean.Student;

import java.util.List;

public class StudentCoAs extends AppCompatActivity {
    TextView textView;
    List<Student> my;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_final_grades);
        textView = findViewById(R.id.final_grades);
        Intent getData=getIntent();
        my = (List<Student>)getData.getSerializableExtra("my");
        textView.setText(String.valueOf(my.get(0).getComprehensive_assessment()));
    }
}
