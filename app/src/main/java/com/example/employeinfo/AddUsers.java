package com.example.employeinfo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddUsers extends AppCompatActivity {
    EditText mName,mInfo;
    Button mImage,mAdd;
    RatingBar mRatting;
    DatabaseHelper DB;
    byte[] byteArray;
    private static int RESULT_LOAD_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);
        DB = new DatabaseHelper(this);
        mName = (EditText) findViewById(R.id.userName);
        mInfo = (EditText) findViewById(R.id.userInfo);
        mImage = (Button) findViewById(R.id.btnSelect);
        mAdd = (Button) findViewById(R.id.AddUserData);
        mRatting = (RatingBar) findViewById(R.id.UserRatingBar);


        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent with action as ACTION_PICK
                Intent intent=new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                // Launching the Intent
                startActivityForResult(intent,RESULT_LOAD_IMG);
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isInserted = DB.insertData(mName.getText().toString(),mInfo.getText().toString(),mRatting.getRating(),byteArray);
                    Intent intent = new Intent(AddUsers.this,MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(AddUsers.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode,resultCode,data);

        if (resultCode == Activity.RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                InputStream iStream =   getContentResolver().openInputStream(selectedImage);
                byteArray = getBytes(iStream);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        inputStream.close();
        return byteBuffer.toByteArray();
    }
}
