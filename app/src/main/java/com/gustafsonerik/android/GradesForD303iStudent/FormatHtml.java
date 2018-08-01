package com.gustafsonerik.android.GradesForD303iStudent;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class FormatHtml
{
    private FormatHtml()
    {
    }
    public static ArrayList<ClassDetails> formatHtml(Document d)
    {
        Elements elements = d.getElementsByAttributeValue("class","AssignmentClass");
        ArrayList<ClassDetails> classDetailsArrayList = new ArrayList<>();
        for(int i = 0; i<elements.size(); i++)
        {
            ClassDetails temp = new ClassDetails(elements.get(i), i );
            if(temp.getAssignmentNames().size()==0)
            {

            }
            else
            {
                temp.setPicture();
                classDetailsArrayList.add(temp);

            }
        }
        return classDetailsArrayList;
    }
}

