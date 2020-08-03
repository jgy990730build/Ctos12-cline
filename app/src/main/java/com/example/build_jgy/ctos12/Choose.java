package com.example.build_jgy.ctos12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        Button button = findViewById(R.id.one);
        Button button1 = findViewById(R.id.two);

        Intent getData=getIntent();
        final  String teacher_id = (String)getData.getSerializableExtra("t_id");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, QueryRecord.class);
                intent.putExtra("t_id2",teacher_id);
                Choose.this.startActivityForResult(intent,18);
                Choose.this.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, QueryRecord2.class);
                intent.putExtra("t_id2",teacher_id);
                Choose.this.startActivityForResult(intent,19);
                Choose.this.finish();
            }
        });
    }
}
