package com.example.android.simplegradeswithjsoup;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        formData.put("__EVENTARGUMENT", "");
        formData.put("__EVENTVALIDATION", sEventValidation);
        formData.put("__VIEWSTATE", sViewStateValidation);
        formData.put("__EVENTTARGET", "ctl00$plnMain$btnRefreshView");
        formData.put("ctl00$plnMain$hdnValidMHACLicense", "N");
        formData.put("ctl00$plnMain$hdnIsVisibleClsWrk", "N");
        formData.put("ctl00$plnMain$hdnIsVisibleCrsAvg", "N");
        formData.put("ctl00$plnMain$hdnJsAlert", "Averages cannot be displayed when  Report Card Run is set to (All Runs).");
        formData.put("ctl00$plnMain$hdnTitle" , "Classwork");
        formData.put("ctl00$plnMain$hdnLastUpdated" , "Last Updated");
        formData.put("ctl00$plnMain$hdnDroppedCourse" , "This course was dropped as of ");
        formData.put("ctl00$plnMain$hdnddlClasses" , "(All Classes)");
        formData.put("ctl00$plnMain$hdnddlCompetencies", "(All Classes)");
        formData.put("ctl00$plnMain$hdnCompDateDue", "Date Due");
        formData.put("ctl00$plnMain$hdnCompDateAssigned", "Date Assigned");
        formData.put("ctl00$plnMain$hdnCompCourse", "Course");
        formData.put("ctl00$plnMain$hdnCompAssignment", "Assignment");
        formData.put("ctl00$plnMain$hdnCompAssignmentLabel", "Assignments Not Related to Any Competency");
        formData.put("ctl00$plnMain$hdnCompNoAssignments", "No assignments found");
        formData.put("ctl00$plnMain$hdnCompNoClasswork", "Classwork could not be found for this competency for the selected report card run.");
        formData.put("ctl00$plnMain$hdnCompScore", "Score");
        formData.put("ctl00$plnMain$hdnCompPoints", "Points");
        formData.put("ctl00$plnMain$hdnddlReportCardRuns1", "(All Runs)");
        formData.put("ctl00$plnMain$hdnddlReportCardRuns2", "(All Terms)");
        formData.put("ctl00$plnMain$hdnbtnShowAverage", "Show All Averages");
        formData.put("ctl00$plnMain$hdnShowAveragesToolTip", "Show all student's averages");
        formData.put("ctl00$plnMain$hdnCollapseToolTip", "Collapse all courses");
        formData.put("ctl00$plnMain$hdnCollapse", "Collapse All");
        formData.put("ctl00$plnMain$hdnFullToolTip", "Switch courses to Full View");
        formData.put("ctl00$plnMain$hdnViewFull", "Full View");
        formData.put("ctl00$plnMain$hdnQuickToolTip", "Switch courses to Quick View");
        formData.put("ctl00$plnMain$hdnViewQuick", "Quick View");
        formData.put("ctl00$plnMain$hdnExpand", "Expand All");
        formData.put("ctl00$plnMain$hdnExpandToolTip", "Expand all courses");
        formData.put("ctl00$plnMain$hdnChildCompetencyMessage", "This competency is calculated as an average of the following competencies");
        formData.put("ctl00$plnMain$hdnCompetencyScoreLabel", "Grade");
        formData.put("ctl00$plnMain$hdnAverageDetailsDialogTitle", "Average Details");
        formData.put("ctl00$plnMain$hdnAssignmentCompetency", "Assignment Competency");
        formData.put("ctl00$plnMain$hdnAssignmentCourse", "Assignment Course");
        formData.put("ctl00$plnMain$hdnTooltipTitle", "Title");
        formData.put("ctl00$plnMain$hdnCategory", "Category");
        formData.put("ctl00$plnMain$hdnDueDate", "Due Date");
        formData.put("ctl00$plnMain$hdnMaxPoints", "Max Points");
        formData.put("ctl00$plnMain$hdnCanBeDropped", "Can Be Dropped");
        formData.put("ctl00$plnMain$hdnHasAttachments", "Has Attachments");
        formData.put("ctl00$plnMain$hdnExtraCredit", "Extra Credit");
        formData.put("ctl00$plnMain$hdnType", "Type");
        formData.put("ctl00$plnMain$hdnAssignmentDataInfo", "Information could not be found for the assignment");
        formData.put("ctl00$plnMain$SCSPI201401", "FALSE");
        formData.put("ctl00$plnMain$ddlReportCardRuns", "ALL");
        formData.put("ctl00$plnMain$ddlClasses", "ALL");
        formData.put("ctl00$plnMain$ddlCompetencies", "ALL");
        formData.put("ctl00$plnMain$ddlOrderBy", "Class");
        return formData;
    }
}

