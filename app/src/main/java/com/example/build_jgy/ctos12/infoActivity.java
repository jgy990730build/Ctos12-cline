package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.build_jgy.ctos12.bean.Student;
import com.example.build_jgy.ctos12.bean.User;
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

public class infoActivity extends AppCompatActivity {
    TextView tv1;
    ImageButton btn,btn1,btn2,btn3;
    User user;
    private String url = "http://172.16.156.35:8080/Android/Query_st_Servlet";//服务器接口地址
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getData=getIntent();
        user = (User)getData.getSerializableExtra("user");
        if(user.getRole().equals("student")) {
            setContentView(R.layout.student_side);
            btn1 = findViewById(R.id.btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(infoActivity.this, StudentAddRecord.class);
                    intent.putExtra("s_id",user.getUsername());
                    infoActivity.this.startActivityForResult(intent,10);
                }
            });
            btn2 = findViewById(R.id.btn2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            String signal2="2";
                            NameValuePair signal = new BasicNameValuePair("signal",signal2);
                            NameValuePair pair= new BasicNameValuePair("id", user.getUsername());
                            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                            pairList.add(pair);
                            pairList.add(signal);
                            try {
                                HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                                // URl是接口地址
                                HttpPost httpPost = new HttpPost("http://172.16.156.35:8080/Android/StudentAddFenServlet");//服务器接口地址
                                // 将请求体内容加入请求中
                                httpPost.setEntity(requestHttpEntity);
                                // 需要客户端对象来发送请求
                                HttpClient httpClient = new DefaultHttpClient();
                                // 发送请求
                                HttpResponse response = httpClient.execute(httpPost);
                                // 显示响应
                                List<Student> list2 = getInfo(response);
                                Intent intent = new Intent(infoActivity.this, StudentFinal_grades.class);
                                intent.putExtra("my",(Serializable) list2);
                                infoActivity.this.startActivityForResult(intent,10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();
                }
            });
            btn3 =findViewById(R.id.btn3);
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            String signal2="2";
                            NameValuePair signal = new BasicNameValuePair("signal",signal2);
                            NameValuePair pair= new BasicNameValuePair("id", user.getUsername());
                            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                            pairList.add(pair);
                            pairList.add(signal);
                            try {
                                HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                                // URl是接口地址
                                HttpPost httpPost = new HttpPost("http://172.16.156.35:8080/Android/StudentAddFenServlet");//服务器接口地址
                                // 将请求体内容加入请求中
                                httpPost.setEntity(requestHttpEntity);
                                // 需要客户端对象来发送请求
                                HttpClient httpClient = new DefaultHttpClient();
                                // 发送请求
                                HttpResponse response = httpClient.execute(httpPost);
                                // 显示响应
                                List<Student> list2 = getInfo(response);
                                Intent intent = new Intent(infoActivity.this, StudentCoAs.class);
                                intent.putExtra("my",(Serializable) list2);
                                infoActivity.this.startActivityForResult(intent,10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();
                }
            });
        }else{
            setContentView(R.layout.teacher_side);
            btn3 = findViewById(R.id.btn3);
            btn2 = findViewById(R.id.btn2);
            btn1 = findViewById(R.id.btn1);
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new Thread() {
                        @Override
                        public void run() {
                            NameValuePair pair= new BasicNameValuePair("id", user.getUsername());
                            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                            pairList.add(pair);
                            try {
                                HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                                // URl是接口地址
                                HttpPost httpPost = new HttpPost("http://172.16.156.35:8080/Android/Query_st_Servlet");//服务器接口地址
                                // 将请求体内容加入请求中
                                httpPost.setEntity(requestHttpEntity);
                                // 需要客户端对象来发送请求
                                HttpClient httpClient = new DefaultHttpClient();
                                // 发送请求
                                HttpResponse response = httpClient.execute(httpPost);
                                // 显示响应
                                List<Student> list2 = getInfo(response);
                                Intent intent = new Intent(infoActivity.this, QueryStudents.class);
                                intent.putExtra("students",(Serializable) list2);
                                infoActivity.this.startActivityForResult(intent,10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();
                }
            });
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread() {
                        @Override
                        public void run() {
                            NameValuePair pair= new BasicNameValuePair("id_check", user.getUsername());
                            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                            pairList.add(pair);
                            try {
                                HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                                // URl是接口地址
                                HttpPost httpPost = new HttpPost("http://172.16.156.35:8080/Android/CheckServlet");//服务器接口地址
                                // 将请求体内容加入请求中
                                httpPost.setEntity(requestHttpEntity);
                                // 需要客户端对象来发送请求
                                HttpClient httpClient = new DefaultHttpClient();
                                // 发送请求
                                HttpResponse response = httpClient.execute(httpPost);
                                // 显示响应
                                List<Student> list2 = getInfo(response);
                                Intent intent = new Intent(infoActivity.this, CheckStudent.class);
                                intent.putExtra("students_check",(Serializable) list2);
                                infoActivity.this.startActivityForResult(intent,15);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }.start();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(infoActivity.this, Choose.class);
                    intent.putExtra("t_id",user.getUsername());
                    infoActivity.this.startActivityForResult(intent,10);
                }
            });
        }
        tv1 = findViewById(R.id.title);
        btn = findViewById(R.id.login_out);


        tv1.setText("欢迎回来，"+user.getRole());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoActivity.this.finish();
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
