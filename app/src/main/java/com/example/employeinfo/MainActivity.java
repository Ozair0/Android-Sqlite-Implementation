package com.example.employeinfo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Employee> KUEmployees;
    FloatingActionButton flt;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DatabaseHelper(this);

        flt = (FloatingActionButton) findViewById(R.id.AddUser);
        final Intent addUser = new Intent(this, AddUsers.class);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor res =DB.getAllData();
        KUEmployees = new ArrayList<>();
        while (res.moveToNext()){
            Employee KUEmp = new Employee(res.getString(1), res.getString(2), getPhoto(res.getBlob(3)),res.getFloat(4));
            KUEmployees.add(KUEmp);
        }


        MyAdapter myAdapter = new MyAdapter(KUEmployees, getApplicationContext());
        recyclerView.setAdapter(myAdapter);
        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addUser);
            }
        });
    }
    public static Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
