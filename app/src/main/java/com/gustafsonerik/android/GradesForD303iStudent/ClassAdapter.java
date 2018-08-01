package com.gustafsonerik.android.GradesForD303iStudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassAdapter extends ArrayAdapter<ClassDetails>
{
    public ClassAdapter(Context context, ArrayList<ClassDetails> classDetails)
    {
        super(context, 0, classDetails);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.class_item, parent, false);
        }
        ClassDetails classDetails = getItem(position);
        TextView className = (TextView) listItemView.findViewById(R.id.class_name);
        className.setText(classDetails.getClassName());
        TextView classPercent = (TextView) listItemView.findViewById(R.id.class_percent);
        classPercent.setText(classDetails.getClassPercent());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        imageView.setImageResource(classDetails.getClassIcon());
        return listItemView;
    }
}
