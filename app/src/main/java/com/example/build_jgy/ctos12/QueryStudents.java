package com.example.build_jgy.ctos12;


import android.content.Intent;
import android.os.Bundle;



import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

public class QueryStudents extends AppCompatActivity {
    Button button1,button2,button3;
    List<Student> students;
    String str1;
    private String url = "http://172.16.156.35:8080/Android/Ranking";//服务器接口地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_student);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button5);
        Intent getData=getIntent();
        students = (List<Student>)getData.getSerializableExtra("students");
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i = 0 ; i < students.size();i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("id",students.get(i).getId());
            listItem.put("name",students.get(i).getStudent_name());
            listItem.put("exam",students.get(i).getComprehensive_assessment());
            str1=students.get(i).getClass1();
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.listviewitems,
                new String[] {"id","name","exam"},
                new int[]{R.id.textView,R.id.textView12,R.id.textView13});
        ListView listView=findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);
        //传到班级信息
//        System.out.println(str1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String signal1="1";
                        NameValuePair pair1= new BasicNameValuePair("signal", signal1);
                        NameValuePair pair= new BasicNameValuePair("myclass", str1);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair1);
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
                            List<Student> list2 = getInfo(response);
                            Intent intent = new Intent(QueryStudents.this, Ranking.class);
                            intent.putExtra("students1",(Serializable) list2);
                            QueryStudents.this.startActivityForResult(intent,11);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String signal2="2";
                        NameValuePair pair1= new BasicNameValuePair("signal", signal2);
                        NameValuePair pair= new BasicNameValuePair("myclass", str1);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair1);
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
                            List<Student> list2 = getInfo(response);
                            Intent intent = new Intent(QueryStudents.this, Ranking.class);
                            intent.putExtra("students1",(Serializable) list2);
                            QueryStudents.this.startActivityForResult(intent,12);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String signal3="3";
                        NameValuePair pair1= new BasicNameValuePair("signal", signal3);
                        NameValuePair pair= new BasicNameValuePair("myclass", str1);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair1);
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
                            List<Student> list2 = getInfo(response);
                            Intent intent = new Intent(QueryStudents.this, Ranking.class);
                            intent.putExtra("students1",(Serializable) list2);
                            QueryStudents.this.startActivityForResult(intent,13);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });
    }
    // 收取数据
    private static List<Student> getInfo(HttpResponse response) throws Exception {

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Type listType=new TypeToken<List<Student>>(){}.getType();
        Gson gson=new Gson();
//        System.out.println(reader.readLine());
        List<Student> list=gson.fromJson(reader.readLine(), listType);
        return list;
    }

}
