package com.example.mypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.mypractice.db.DbHelper;
import com.example.mypractice.db.DbManager;

public class StudentsListActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{

            DbManager. _ID,
            DbManager. NAME,
            DbManager. AGE,
            DbManager. GENDER,
            DbManager. SUBJECT,
    };

    final int[] to = new int[]{R.id.id,R.id.name,R.id.age,R.id.gender,R.id.subject};
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        listView = findViewById(R.id.list_students);
        textView = findViewById(R.id.empty);

        dbHelper = new DbHelper(this);
        dbHelper.open();

        Cursor cursor = dbHelper.fetch();

        adapter = new SimpleCursorAdapter(this,R.layout.student_view_record,cursor,from,to,0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView t1 = view.findViewById(R.id.id);
                TextView t2 = view.findViewById(R.id.name);
                TextView t3 = view.findViewById(R.id.age);
                TextView t4 = view.findViewById(R.id.gender);
                TextView t5 = view.findViewById(R.id.subject);

                String txt_id = t1.getText().toString();
                String txt_name = t2.getText().toString();
                String txt_age = t3.getText().toString();
                String txt_gender = t4.getText().toString();
                String txt_subject = t5.getText().toString();

                Intent intent = new Intent(StudentsListActivity.this, ModifyStudentActivity.class);

                intent.putExtra("id",txt_id);
                intent.putExtra("name",txt_name);
                intent.putExtra("age",txt_age);
                intent.putExtra("gender",txt_gender);
                intent.putExtra("subject",txt_subject);

                startActivity(intent);

            }
        });
    }

}
