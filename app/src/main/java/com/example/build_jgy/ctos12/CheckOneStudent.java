package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.List;


public class CheckOneStudent extends AppCompatActivity {
   Student student;
   TextView student_id,student_name,add1,add2,add3;
   Button button;
   WebView webView;
   private String url = "http://172.16.156.35:8080/Android/UpdateStudent";//服务器接口地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_student);
        Intent getData=getIntent();
        student = (Student)getData.getSerializableExtra("student");

        student_id = findViewById(R.id.student_id);
        student_name = findViewById(R.id.student_name);
        add1 = findViewById(R.id.record1);
        add2 = findViewById(R.id.record2);
        add3 = findViewById(R.id.record3);

        student_id.setText(String.valueOf(student.getId()));
        student_name.setText(student.getStudent_name());
        add1.setText(student.getCompetition_add());
        add2.setText(student.getActivity_add());
        add3.setText(student.getCadres_add());

        webView=findViewById(R.id.show);
        webView.loadUrl("http://172.16.156.35:8080/Android/show.jsp?img_id="+String.valueOf(student.getId()));
        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        NameValuePair pair= new BasicNameValuePair("student_id_update", String.valueOf(student.getId()));
                        NameValuePair pair2= new BasicNameValuePair("student_class_update", student.getClass1());
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair);
                        pairList.add(pair2);
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
                            Intent intent = new Intent(CheckOneStudent.this, CheckStudent.class);
                            intent.putExtra("students_check",(Serializable) list2);
                            CheckOneStudent.this.startActivityForResult(intent,16);
                            CheckOneStudent.this.finish();
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
