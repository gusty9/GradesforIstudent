package com.gustafsonerik.android.GradesForD303iStudent;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.round;

public class ClassDetails implements Serializable
{
    //private variables
    //Name of classes
    private String mClassName = "";
    //ArrayList of assignments (strings)
    private ArrayList<Assignment> mAssignments = new ArrayList<Assignment>();
    //percent of the class
    private String mClassPercent;
    //image to follow with the class
    private int mClassIcon;
    //this double stores the current weight of all of the assignments, used for calculating what you need for your final grade
    private double mClassWeight;

    //constructor that takes an element and creates a new ClassDetails from it
    public ClassDetails(Element e, int index)
    {
        Elements aTag = e.select("a");
        mClassName = aTag.get(0).ownText().substring(11);
        if(aTag.size()>1)
        {
            Elements table = e.select("table");
            Elements fullClassInfo = table.get(0).getElementsByAttributeValue("class", "sg-asp-table-data-row");
            for(int i = 0; i<fullClassInfo.size(); i++)
            {
                Elements alphaTag = fullClassInfo.get(i).getElementsByTag("a");
                Elements classPercents = fullClassInfo.get(i).getElementsByAttributeValue("class","sg-view-quick");
                mAssignments.add(new Assignment(alphaTag.get(0).ownText(), classPercents.get(4).text(), classPercents.get(1).text(), classPercents.get(2).text(), classPercents.get(3).text()));
            }
            String temp = Integer.toString(index);
            String indexForSort = "plnMain_rptAssigmnetsByCourse_lblOverallAverage_" + temp;
            String indexForSort2 = "plnMain_rptAssigmnetsByCourse_lblMaxPoints_" + temp;
            mClassPercent = e.select("table").get(2).getElementsByAttributeValue("id", indexForSort).text();
            mClassWeight = Double.parseDouble(e.select("table").get(2).getElementsByAttributeValue("id", indexForSort2).text());
        }
    }



    public String getClassName()
    {
        return mClassName;
    }
    public ArrayList<Assignment> getAssignments()
    {
        return mAssignments;
    }
    public Assignment getAssignment(int i)
    {
        if(i> mAssignments.size())
        {
            return null;
        }
        else
        {
            return mAssignments.get(i);
        }
    }
    public String getClassPercent()
    {
        return mClassPercent + "%";
    }
    public void addAssignment(Assignment assignment)
    {
        mAssignments.add(assignment);
    }
    public void setClassName(String className)
    {
        mClassName = className;
    }
    public void reset()
    {
        mClassName = "";
        mAssignments.clear();
    }
    public ArrayList<String> getAssignmentNames()
    {
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i<mAssignments.size(); i++)
        {
            s.add(mAssignments.get(i).getAssignmentName());
        }
        return s;
    }
    public void setPicture()
    {
        if(mClassName.contains("Algebra") || mClassName.contains("Calc") || mClassName.contains("Geom") || mClassName.contains("Stats"))
        {
            mClassIcon = R.drawable.math;
        }
        else if(mClassName.contains("Eng") || mClassName.contains("Writing"))
        {
            mClassIcon = R.drawable.english;
        }
        else if(mClassName.contains("PE"))
        {
            mClassIcon = R.drawable.gym;
        }
        else if(mClassName.contains("Span") || mClassName.contains("Fren") || mClassName.contains("Latin"))
        {
            mClassIcon = R.drawable.language;
        }
        else if(mClassName.contains("Physics"))
        {
            mClassIcon = R.drawable.physics;
        }
        else if(mClassName.contains("Chem") || mClassName.contains("Bio") || mClassName.contains("Science"))
        {
            mClassIcon = R.drawable.science;
        }
        else if(mClassName.contains("US") || mClassName.contains("Euro") || mClassName.contains("Geog") || mClassName.contains("Gov") || mClassName.contains("Globe"))
        {
            mClassIcon = R.drawable.globe;
        }
        else if(mClassName.contains("Macro") || mClassName.contains("Micro")||mClassName.contains("Econ"))
        {
            mClassIcon = R.drawable.econ;
        }
        else
        {
            mClassIcon = R.drawable.defaultt;
        }
    }
    public int getClassIcon()
    {
        return mClassIcon;
    }
    /*
    //Code to calculate final, currently works but crashes if the final is already entered
    public String calculateFinal()
    {
        DecimalFormat df = new DecimalFormat("###.###");
        DecimalFormat dfw = new DecimalFormat("##.##");
        double classPercent = (Double.parseDouble(mClassPercent)) / 100;
        String getA = df.format(((.895 - (mClassWeight * classPercent)) / (1 - mClassWeight)) * 100);
        String getB = df.format(((.795 - (mClassWeight * classPercent)) / (1 - mClassWeight)) * 100);
        String getC = df.format(((.695 - (mClassWeight * classPercent)) / (1 - mClassWeight)) * 100);
        String getD = df.format(((.595 - (mClassWeight * classPercent)) / (1 - mClassWeight)) * 100);
        StringBuilder s = new StringBuilder();

        if(Double.parseDouble(getD) > 0)
        {
            s.append("You need a ");
            s.append(getA);
            s.append(" to get an A in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getB);
            s.append(" to get a B in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getC);
            s.append(" to get a C in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getD);
            s.append(" to get a D in the class");
            s.append(System.getProperty("line.separator"));
            s.append("with a current grade of ");
            s.append(mClassPercent);
            s.append("%");
            s.append(System.getProperty("line.separator"));
            s.append("and a final worth ");
            String temp = dfw.format((1-mClassWeight) * 100);
            s.append(temp);
            s.append("% of your grade");
        }
        else if(Double.parseDouble(getC) > 0)
        {
            s.append("You need a ");
            s.append(getA);
            s.append(" to get an A in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getB);
            s.append(" to get a B in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getC);
            s.append(" to get a C in the class");
            s.append(System.getProperty("line.separator"));
            s.append("with a current grade of ");
            s.append(mClassPercent);
            s.append("%");
            s.append(System.getProperty("line.separator"));
            s.append("and a final worth ");
            String temp = dfw.format((1-mClassWeight) * 100);
            s.append(temp);
            s.append("% of your grade");
        }
        else if(Double.parseDouble(getB) > 0)
        {
            s.append("You need a ");
            s.append(getA);
            s.append(" to get an A in the class");
            s.append(System.getProperty("line.separator"));
            s.append("You need a ");
            s.append(getB);
            s.append(" to get a B in the class");
            s.append(System.getProperty("line.separator"));
            s.append("with a current grade of ");
            s.append(mClassPercent);
            s.append("%");
            s.append(System.getProperty("line.separator"));
            s.append("and a final worth ");
            String temp = dfw.format((1-mClassWeight) * 100);
            s.append(temp);
            s.append("% of your grade");
        }
        else if(Double.parseDouble(getA)>0)
        {
            s.append("You need a ");
            s.append(getA);
            s.append(" to get an A in the class");
            s.append(System.getProperty("line.separator"));
            s.append("with a current grade of ");
            s.append(mClassPercent);
            s.append("%");
            s.append(System.getProperty("line.separator"));
            s.append("and a final worth ");
            String temp = dfw.format((1-mClassWeight) * 100);
            s.append(temp);
            s.append("% of your grade");
        }
        return s.toString();
    }
    */
}
