package com.example.user.neveralone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonUserLogin;
    Button buttonUserSignup;
    EditText user_email;
    EditText user_pass;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUserLogin = findViewById(R.id.button_login);
        buttonUserSignup = findViewById(R.id.button_signup);
        user_email = findViewById(R.id.main_email);
        user_pass = findViewById(R.id.sign_user_password);
        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(databaseHelper.checkUser(user_email.getText().toString(),user_pass.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), ViewFeed.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong user",Toast.LENGTH_LONG).show();
                }
            }

        });
        buttonUserSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
}
