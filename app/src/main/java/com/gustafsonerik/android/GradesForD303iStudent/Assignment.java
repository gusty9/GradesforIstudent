package com.gustafsonerik.android.GradesForD303iStudent;
import java.io.Serializable;
public class Assignment implements Serializable
{
    private String mAssignmentName;
    private String mAssignmentPercent;
    private String mTotalPoints;
    private String mPointsEarned;
    private String mAverageScore;
    public Assignment(String assignmentName, String assignmentPercent, String pointsEarned, String totalPointsEarned, String averageScore)
    {
        mAssignmentName = assignmentName;
        mAssignmentPercent = assignmentPercent;
        mTotalPoints = totalPointsEarned;
        mPointsEarned = pointsEarned;
        mAverageScore = averageScore;
    }
    public String getAssignmentName()
    {
        return mAssignmentName;
    }
    public String getAssignmentPercent()
    {
        return mAssignmentPercent;
    }
    public String getTotalPoints()
    {
        return mTotalPoints;
    }
    public String getPointsEarned()
    {
        return mPointsEarned;
    }
    public String getAverageScore()
    {
        return mAverageScore;
    }
}
