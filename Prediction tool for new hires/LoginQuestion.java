package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewActivity extends AppCompatActivity {

    private Button startbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_new);
        startbtn = findViewById (R.id.start_btn);startbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent categoryIntent = new Intent (NewActivity.this,CategoriesActivity.class);
                startActivity (categoryIntent);
            }
        });
    }
}
