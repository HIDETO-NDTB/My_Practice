package com.example.mypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mypractice.db.DbHelper;
import com.example.mypractice.db.DbManager;

public class ModifyStudentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name, et_age, et_gender, et_subject;
    Button btn_update, btn_delete;

    private long _id;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);

        dbHelper = new DbHelper(this);
        dbHelper.open();

        initView();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        String subject = intent.getStringExtra("subject");

        _id = Long.parseLong(id);

        et_name.setText(name);
        et_age.setText(age);
        et_gender.setText(gender);
        et_subject.setText(subject);

        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

    }

    private void initView() {

        et_name = findViewById(R.id.edit_name);
        et_age = findViewById(R.id.edit_age);
        et_gender = findViewById(R.id.edit_gender);
        et_subject = findViewById(R.id.edit_subject);
        btn_update = findViewById(R.id.button_update);
        btn_delete = findViewById(R.id.button_delete);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_update:
                String student_name = et_name.getText().toString();
                String student_age = et_age.getText().toString();
                String student_gender = et_gender.getText().toString();
                String student_subject = et_subject.getText().toString();
                dbHelper.update(_id, student_name, Integer.valueOf(student_age), student_gender, student_subject);
                this.returnHome();
                break;
            case R.id.button_delete:
                dbHelper.delete(_id);
                this.returnHome();
                break;
        }
    }

    private void returnHome() {
        Intent intent = new Intent(this, StudentsListActivity.class);
        startActivity(intent);
    }
}
