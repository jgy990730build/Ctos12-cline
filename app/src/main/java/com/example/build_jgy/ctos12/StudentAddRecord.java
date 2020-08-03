package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

public class StudentAddRecord extends AppCompatActivity {
    EditText editText,editText1,editText2;
    String str1,str2,str3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_addrecord);
        Intent getData=getIntent();
        final String student_id=(String)getData.getSerializableExtra("s_id");
        editText=findViewById(R.id.sadd1);
        editText1=findViewById(R.id.sadd2);
        editText2=findViewById(R.id.sadd3);
        Button button=findViewById(R.id.button);
        Button button1=findViewById(R.id.button2);
        Button button2=findViewById(R.id.upload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        String signal1="1";
                        str1=editText.getText().toString().trim();
                        str2=editText1.getText().toString().trim();
                        str3=editText2.getText().toString().trim();
                        NameValuePair signal = new BasicNameValuePair("signal",signal1);
                        NameValuePair pair= new BasicNameValuePair("id", student_id);
                        NameValuePair add1= new BasicNameValuePair("sadd1", str1);
                        NameValuePair add2= new BasicNameValuePair("sadd2", str2);
                        NameValuePair add3= new BasicNameValuePair("sadd3", str3);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair);
                        pairList.add(signal);
                        pairList.add(add1);
                        pairList.add(add2);
                        pairList.add(add3);
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
                            int result = getInfo(response);
                            if(result!=0){
                                Looper.prepare();
                                Toast.makeText(StudentAddRecord.this,"操作成功",Toast.LENGTH_LONG).show();
                                Looper.loop();
                                StudentAddRecord.this.finish();
                            }else{
                                System.out.println("发生错误");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentAddRecord.this.finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到上传图片页面
                Intent intent = new Intent(StudentAddRecord.this, UpLoadImg.class);

                StudentAddRecord.this.startActivityForResult(intent,16);
            }
        });
    }
    // 收取数据
    private static int  getInfo(HttpResponse response) throws Exception {

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        System.out.println(reader.readLine());
        int result=Integer.valueOf(reader.readLine());
        return result;
    }
}
