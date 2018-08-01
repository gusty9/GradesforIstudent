package com.gustafsonerik.android.GradesForD303iStudent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    SharedPreferences prefs = this.getSharedPreferences("com.gustafsonerik.android.GradsForD303iStudnet", Context.MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.login_screen);
        Button loginButton = (Button) findViewById(R.id.login);
        final CheckBox cbox = (CheckBox) findViewById(R.id.remember_password);
        final EditText tUserName = (EditText) findViewById(R.id.username);
        final EditText tPassword = (EditText) findViewById(R.id.password);
        tUserName.setText(prefs.getString("username", ""));
        tPassword.setText(prefs.getString("password", ""));
        cbox.setChecked(prefs.getBoolean("checked", false));
        loginButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v){
                String userName = tUserName.getText().toString();
                String password = tPassword.getText().toString();
                if(cbox.isChecked()){
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString("username" , userName);
                    prefsEditor.putString("password", password);
                    prefsEditor.putBoolean("checked" , true);
                    prefsEditor.apply();
                } else {
                    prefs.edit().clear().apply();
                }
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("Username", userName);
                intent.putExtra("Password",  password);
                startActivity(intent);
        }
        });
    }

}
