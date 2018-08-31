package com.example.user.neveralone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText sign_name;
    EditText sign_email;
    EditText sign_pass;
    Button buttonSign;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = new User();
        sign_name = findViewById(R.id.sign_user_name);
        sign_email = findViewById(R.id.sign_user_email);
        sign_pass = findViewById(R.id.sign_user_password);
        buttonSign = findViewById(R.id.button_sign_up);
        databaseHelper = new DatabaseHelper(this);
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUser_name(sign_name.getText().toString());
                user.set_user_email(sign_email.getText().toString());
                user.set_user_password(sign_pass.getText().toString());
                databaseHelper.addUser(user);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
