package com.example.android.simplegradeswithjsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class FormatHtml
{
    public FormatHtml()
    {
    }
    public static String formatHtml(Document d)
    {
        Element testClass = d.selectFirst("Title: Assignment Course");
        return testClass.toString();
    }
}
