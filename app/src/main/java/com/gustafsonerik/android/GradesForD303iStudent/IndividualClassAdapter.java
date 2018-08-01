package com.gustafsonerik.android.GradesForD303iStudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class IndividualClassAdapter extends ArrayAdapter<Assignment>
{
    public IndividualClassAdapter(Context context, ArrayList<Assignment> assignmentDetails)
    {
        super(context, 0, assignmentDetails);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.individual_class_item, parent, false);
        }
        Assignment assignment = getItem(position);
        TextView textView = (TextView) listItemView.findViewById(R.id.assignment);
        textView.setText(assignment.getAssignmentName());
        TextView percent = (TextView) listItemView.findViewById(R.id.percentage);
        percent.setText(assignment.getAssignmentPercent());
        TextView totalPoints = (TextView) listItemView.findViewById(R.id.total_points);
        totalPoints.setText(assignment.getTotalPoints());
        TextView pointsEarned = (TextView) listItemView.findViewById(R.id.points_earned);
        pointsEarned.setText(assignment.getPointsEarned());
        TextView averageScore = (TextView) listItemView.findViewById(R.id.average_score);
        averageScore.setText(assignment.getAverageScore());
        return listItemView;
    }
}

