package com.example.android.simplegradeswithjsoup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
     TextView t;
     //Document assignmentsHtml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.test);
        String username = getIntent().getStringExtra("Username");
        String password = getIntent().getStringExtra("Password");
        String[] loginCredentials = new String[]{username, password};
        NetworkAsyncTask task = new NetworkAsyncTask();
        task.execute(loginCredentials);
    }
    private class NetworkAsyncTask extends AsyncTask<String[], Void, Document>
    {
        @Override
        protected Document doInBackground(String[]... loginInfo)
        {
            return QueryUtils.getGrades(loginInfo[0]);
        }
        @Override
        protected void onPostExecute(Document s)
        {
            //assignmentsHtml = s;
            t.setText(s.toString());
        }
    }
}
