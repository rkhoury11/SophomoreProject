package com.example.beacontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username, email, password, repassword;
    private TextView signuptitle, txtLoginInfo;
    private Button signupbtn;

    private boolean isSignUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        signuptitle = findViewById(R.id.signuptitle);
        txtLoginInfo = findViewById(R.id.txtLoginInfo);

        signupbtn = findViewById(R.id.signupbtn);

        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSignUp) {
                    isSignUp = false;
                    username.setVisibility(View.GONE);
                    repassword.setVisibility(View.GONE);
                    signupbtn.setText("Log in");
                    txtLoginInfo.setText("Dont have an account? Sign up");
                }else {
                    isSignUp = true;
                    username.setVisibility(View.VISIBLE);
                    repassword.setVisibility(View.VISIBLE);
                    signupbtn.setText("Sign up");
                    txtLoginInfo.setText("Already have an account? Log in");

                }
            }
        });
    }
}