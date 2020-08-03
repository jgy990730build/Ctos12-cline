package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.build_jgy.ctos12.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking extends AppCompatActivity {
    List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_student);
        Intent getData=getIntent();
        students = (List<Student>)getData.getSerializableExtra("students1");

        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i = 0 ; i < students.size();i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("id",students.get(i).getId());
            listItem.put("name",students.get(i).getStudent_name());
            listItem.put("exam",students.get(i).getComprehensive_assessment());
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.listviewitems,
                new String[] {"id","name","exam"},
                new int[]{R.id.textView,R.id.textView12,R.id.textView13});
        ListView listView=findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);
    }
}
