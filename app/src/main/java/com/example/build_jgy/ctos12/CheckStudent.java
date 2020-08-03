package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.build_jgy.ctos12.bean.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckStudent extends AppCompatActivity {
    List<Student> students;
    private String url = "http://172.16.156.35:8080/Android/CheckOneStudnet";//服务器接口地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_student);
        Intent getData=getIntent();
        students = (List<Student>)getData.getSerializableExtra("students_check");
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i = 0 ; i < students.size();i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("id",students.get(i).getId());
            listItem.put("name",students.get(i).getStudent_name());
            listItem.put("record1",students.get(i).getActivity_add());
            listItem.put("record2",students.get(i).getCadres_add());
            listItem.put("record3",students.get(i).getCompetition_add());
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.check_items,
                new String[] {"id","name","record1","record2","record3"},
                new int[]{R.id.student_id,R.id.name,R.id.record1,R.id.record2,R.id.record3});
        ListView listView=findViewById(R.id.checklist);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView=(TextView) view.findViewById(R.id.student_id);
                final String item = textView.getText().toString();
                new Thread() {
                    @Override
                    public void run() {
                        NameValuePair pair= new BasicNameValuePair("student_id", item);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair);
                        try {
                            HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList,"UTF_8");
                            // URl是接口地址
                            HttpPost httpPost = new HttpPost(url);
                            // 将请求体内容加入请求中
                            httpPost.setEntity(requestHttpEntity);
                            // 需要客户端对象来发送请求
                            HttpClient httpClient = new DefaultHttpClient();
                            // 发送请求
                            HttpResponse response = httpClient.execute(httpPost);
                            // 显示响应
                            Student list2 = getInfo(response);
                            Intent intent = new Intent(CheckStudent.this, CheckOneStudent.class);
                            intent.putExtra("student",(Serializable) list2);
                            CheckStudent.this.startActivityForResult(intent,16);
                            CheckStudent.this.finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
//                Toast.makeText(CheckStudent.this,item,Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 收取数据
    private static Student getInfo(HttpResponse response) throws Exception {

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Type listType=new TypeToken<Student>(){}.getType();
        Gson gson=new Gson();
//        System.out.println(reader.readLine());
        Student list=gson.fromJson(reader.readLine(), listType);
        return list;
    }
}
