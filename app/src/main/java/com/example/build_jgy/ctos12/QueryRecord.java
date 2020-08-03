package com.example.build_jgy.ctos12;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

public class QueryRecord extends AppCompatActivity {
    ImageButton button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_record);
        Intent getData=getIntent();
        final  String teacher_id = (String)getData.getSerializableExtra("t_id2");
        button = findViewById(R.id.imageButton);
        editText = findViewById(R.id.aa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        NameValuePair pair2=new BasicNameValuePair("key",editText.getText().toString());
                        NameValuePair pair= new BasicNameValuePair("teacher_id",teacher_id);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair);
                        pairList.add(pair2);
                        try {
                            HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList,"UTF_8");
                            // URl是接口地址
                            HttpPost httpPost = new HttpPost("http://172.16.156.35:8080/Android/Query_st_Record");//服务器接口地址
                            // 将请求体内容加入请求中
                            httpPost.setEntity(requestHttpEntity);
                            // 需要客户端对象来发送请求
                            HttpClient httpClient = new DefaultHttpClient();
                            // 发送请求
                            HttpResponse response = httpClient.execute(httpPost);
                            // 显示响应
                            List<Student> list2 = getInfo(response);
                           if(list2.size()!=0){
//                               Intent intent = new Intent(QueryRecord.this, result.class);
//                               intent.putExtra("result",(Serializable) list2);
//                               QueryRecord.this.startActivityForResult(intent,16);
//                               QueryRecord.this.finish();
                               Bundle bundle= new Bundle();

                               bundle.putSerializable("data1",(Serializable) list2);
                               //1、在系统中原生的Fragment是通过getFragmentManager获得的。
                               FragmentManager fragmentManager=getFragmentManager();
                               //2.开启一个事务，通过调用beginTransaction方法开启。
                               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                               //3.用自己创建好的fragment1类创建一个对象
                               QueryFragment fragment=new QueryFragment();
                               fragment.setArguments(bundle);
                               //4.向容器内加入Fragment，一般使用add或者replace方法实现，需//要传入容器的id和Fragment的实例。
                               fragmentTransaction.replace(R.id.frag,fragment);
                               //5.提交事务，调用commit方法提交。
                               fragmentTransaction.commit();
                               //补充程序，需要补充5行代码

                           }else{
                               Looper.prepare();
                               Toast.makeText(QueryRecord.this,"没有此学生信息！",Toast.LENGTH_SHORT).show();
                               Looper.loop();

                           }
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
