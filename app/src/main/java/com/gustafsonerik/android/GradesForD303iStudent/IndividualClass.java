package com.gustafsonerik.android.GradesForD303iStudent;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class IndividualClass extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.individual_class_listview);
        ClassDetails classDetail = (ClassDetails) getIntent().getSerializableExtra("classDetails");
        ListView listView = findViewById(R.id.individual_class_listView);
        ImageView imageView = (ImageView) findViewById(R.id.image_view_individual_class);
        imageView.setImageResource(classDetail.getClassIcon());
        TextView classTextView = (TextView) findViewById(R.id.class_name_individual_class);
        classTextView.setText(classDetail.getClassName());
        TextView percentTextView = (TextView) findViewById(R.id.class_percent_individual_class);
        percentTextView.setText(classDetail.getClassPercent());
        IndividualClassAdapter adapter = new IndividualClassAdapter(getApplicationContext(), classDetail.getAssignments());
        listView.setAdapter(adapter);
        /*
        //shows the grade needed for a certain letter grade on a final when clicked. Currently works but crashes if final is entered
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(classDetail.calculateFinal())
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        final AlertDialog alert = builder.create();

        LinearLayout classBanner = (LinearLayout) findViewById(R.id.class_name_banner);
        classBanner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                alert.show();
            }
        });
        */
    }
}
