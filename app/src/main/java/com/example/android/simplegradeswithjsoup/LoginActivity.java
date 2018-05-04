package com.example.android.simplegradeswithjsoup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.login_screen);
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v){
                EditText tUserName = (EditText) findViewById(R.id.username);
                EditText tPassword = (EditText) findViewById(R.id.password);
                String userName = tUserName.getText().toString();
                String password = tPassword.getText().toString();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("Username", userName);
                intent.putExtra("Password",  password);
                startActivity(intent);
        }
        });
    }

}
