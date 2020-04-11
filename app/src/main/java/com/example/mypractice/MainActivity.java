package com.example.mypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.mypractice.db.DbHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name,et_age,et_gender,et_subject;
    Button btn_add, btn_list;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();

        dbHelper = new DbHelper(this);
        dbHelper.open();

        btn_add.setOnClickListener(this);
        btn_list.setOnClickListener(this);

    }

    private void initviews() {

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        et_subject = findViewById(R.id.et_subject);
        btn_add = findViewById(R.id.btn_add);
        btn_list = findViewById(R.id.btn_list);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_add:
                String name = et_name.getText().toString();
                Integer age = Integer.valueOf(et_age.getText().toString());
                String gender = et_gender.getText().toString();
                String subject = et_subject.getText().toString();

                dbHelper.insert(name,age,gender,subject);

                Intent intent = new Intent(MainActivity.this,StudentsListActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_list:
                Intent intent2 = new Intent(MainActivity.this,StudentsListActivity.class);
                startActivity(intent2);
                break;
        }


    }
}
