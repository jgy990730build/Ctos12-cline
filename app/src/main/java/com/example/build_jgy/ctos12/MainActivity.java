package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button login;
    EditText user, pwd;
    String username, password;
    User u;

    private String url = "http://172.16.156.35:8080/Android/LoginServlet";//服务器接口地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);

        user = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);


        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
            /**
             * 开辟一个子线程访问网络，否则会抛出异常
             */
            new Thread() {
                @Override
                public void run() {
                    username = user.getText().toString().trim();
                    password = pwd.getText().toString().trim();

                    u = new User(username, password);

                    NameValuePair pair1 = new BasicNameValuePair("username", username);
                    NameValuePair pair2 = new BasicNameValuePair("password", password);
                    List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                    pairList.add(pair1);
                    pairList.add(pair2);
                    try {
                        HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                        // URl是接口地址
                        HttpPost httpPost = new HttpPost(url);
                        // 将请求体内容加入请求中
                        httpPost.setEntity(requestHttpEntity);
                        // 需要客户端对象来发送请求
                        HttpClient httpClient = new DefaultHttpClient();
                        // 发送请求
                        HttpResponse response = httpClient.execute(httpPost);
                        // 显示响应
                        List<User> list2 = getInfo(response);
                        if (list2.size() != 0) {
                            Intent intent = new Intent(MainActivity.this, infoActivity.class);
                            User user2 = new User();
                            for (int i = 0; i < list2.size(); i++) {
                                user2.setUsername(list2.get(i).getUsername());
                                user2.setPassword(list2.get(i).getPassword());
                                user2.setRole(list2.get(i).getRole());
                            }
                            intent.putExtra("user", user2);
                            MainActivity.this.startActivityForResult(intent,10);
                            MainActivity.this.finish();
                        } else {
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }.start();
            break;
        }
    }

    // 收取数据
    private static List<User> getInfo(HttpResponse response) throws Exception {

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Type listType=new TypeToken<List<User>>(){}.getType();
        Gson gson=new Gson();
//        System.out.println(reader.readLine());
        List<User> list=gson.fromJson(reader.readLine(), listType);
        return list;
    }

}
