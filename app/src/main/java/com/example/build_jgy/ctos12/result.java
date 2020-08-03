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

public class result extends AppCompatActivity {
    List<Student> students;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Intent getData2=getIntent();
        students = (List<Student>)getData2.getSerializableExtra("result");
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i = 0 ; i < students.size();i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("name",students.get(i).getStudent_name());
            listItem.put("exam",students.get(i).getFinal_grades());
            listItems.add(listItem);

        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(result.this,listItems,R.layout.record_items,
                new String[] {"name","exam"},
                new int[]{R.id.student_name,R.id.record});
        ListView listView=findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);
    }
}
