package com.gustafsonerik.android.GradesForD303iStudent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("Username");
        String password = getIntent().getStringExtra("Password");
        String[] loginCredentials = new String[]{username, password};
        NetworkAsyncTask task = new NetworkAsyncTask();
        task.execute(loginCredentials);
    }
    private class NetworkAsyncTask extends AsyncTask<String[], Integer, Document>
    {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.header_progress);
        @Override
        protected void onPreExecute()
        {
            linearLayout.setVisibility(View.VISIBLE);

        }
        @Override
        protected Document doInBackground(String[]... loginInfo)
        {

            return QueryUtils.getGrades(loginInfo[0]);

        }

        @Override
        protected void onPostExecute(Document s)
        {
            linearLayout.setVisibility(View.GONE);
            if(s == null)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else
            {
                ListView listView = findViewById(R.id.list);
                final ArrayList<ClassDetails> classDetails = FormatHtml.formatHtml(s);
                ClassAdapter adapter = new ClassAdapter(getApplicationContext(), classDetails);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                    {

                        ClassDetails classDetail = classDetails.get(position);
                        Intent intent  = new Intent(getApplicationContext(), IndividualClass.class);
                        //view.startActionMode(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
                        intent.putExtra("classDetails" , classDetail);
                        startActivity(intent);

                    }
                });
            }

        }
    }
}