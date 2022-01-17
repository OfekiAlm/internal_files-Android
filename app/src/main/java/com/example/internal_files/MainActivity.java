package com.example.internal_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button SaveBtn,LoadBtn;
TextView TvDisplay;
EditText etHobbie;
FileOutputStream out;
InputStream in;
String str = null;
Animation animFadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        animFadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        SaveBtn = findViewById(R.id.savebtn);
        LoadBtn = findViewById(R.id.loadbtn);
        SaveBtn.setOnClickListener(this);
        LoadBtn.setOnClickListener(this);
        TvDisplay = findViewById(R.id.loaddisplay);
        etHobbie = findViewById(R.id.hobbie);
    }

    @Override
    public void onClick(View view) {
        if(view == SaveBtn){
            str = etHobbie.getText().toString();
            try {
                out=openFileOutput("details-ofek",MODE_PRIVATE);
                if(str!=null){
                    out.write(str.getBytes(),0,str.length());
                    out.close();
                    Toast.makeText(this,"שמרתי את התחביב שלך! 😄",Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(LoadBtn==view)
        {
            try {
                in=openFileInput("details-ofek");
                byte[]buffer=new byte[4096];
                try {
                    in.read(buffer);
                    str=new String(buffer);
                    in.close();
                    if(str!=null) {
                        TvDisplay.setText(str);
                        TvDisplay.startAnimation(animFadeIn);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        }
    }

