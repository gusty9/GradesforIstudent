package com.gustafsonerik.android.GradesForD303iStudent;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public final class QueryUtils {
    private QueryUtils()
    {
    }

    public static Document getGrades(String[] login) {
        String loginFormUrl = "https://istudent.d303.org/HomeAccess/Account/LogOn?ReturnUrl=%2fHomeAccess%2f";
        String loginActionUrl = "https://istudent.d303.org/HomeAccess/Account/LogOn?ReturnUrl=%2fHomeAccess%2fHome%2fWeekView";
        String classWorkViewUrl = "https://istudent.d303.org/HomeAccess/Content/Student/Assignments.aspx";
        String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
        HashMap<String, String> formData = new HashMap<>();
        HashMap<String, String> cookies = new HashMap<>();


        try {
            Connection.Response loginForm = Jsoup
                    .connect(loginFormUrl)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .execute();
            cookies.putAll(loginForm.cookies());
            formData.put("Database", "10");
            formData.put("LogOnDetails.UserName", login[0]);
            formData.put("LogOnDetails.Password", login[1]);
            Connection.Response weekView = Jsoup
                    .connect(loginActionUrl)
                    .cookies(loginForm.cookies())
                    .data(formData)
                    .method(Connection.Method.POST)
                    .userAgent(USER_AGENT)
                    .execute();
            if(weekView.parse().title().equals(""))
            {
                return null;
            }
            //todo: make a toast to show a successful login
            cookies.putAll(weekView.cookies());
            Connection.Response a = Jsoup
                    .connect(classWorkViewUrl)
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .execute();
            cookies.putAll(a.cookies());
            Document assignments = a.parse();
            Connection.Response assignmentsFull = Jsoup
                    .connect(classWorkViewUrl)
                    .referrer(classWorkViewUrl)
                    .cookies(cookies)
                    .data(getAssignmentParams(assignments))
                    .method(Connection.Method.POST)
                    .userAgent(USER_AGENT)
                    .execute();
            return assignmentsFull.parse();

        } catch (IOException e)
        {
            Log.e("", "it is going to the catch statement");
            return null;
        }

    }

    private static HashMap<String, String> getAssignmentParams(Document d)
    {
        HashMap<String, String> formData = new HashMap<>();
        Element eventValidation = d.selectFirst("input[name=__EVENTVALIDATION]");
        String sEventValidation = eventValidation.attr("value");
        Element viewStateValidation = d.selectFirst("input[name=__VIEWSTATE]");
        String sViewStateValidation = viewStateValidation.attr("value");
        formData.put("__EVENTVALIDATION", sEventValidation);
        formData.put("__VIEWSTATE", sViewStateValidation);
        formData.put("__EVENTTARGET", "ctl00$plnMain$btnRefreshView");
        return formData;
    }
}

