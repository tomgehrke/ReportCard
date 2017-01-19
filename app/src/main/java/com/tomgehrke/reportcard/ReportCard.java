package com.tomgehrke.reportcard;

/* == Created by Tom Gehrke on 1/18/2017. == */

//import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReportCard {

    // Stores the reporting period
    private String mReportingPeriod;

    private HashMap<String, Integer> mSubjectGrades = new HashMap<>();

    // Constructor requires at least the reporting period
    //
    // Examples:
    //     Jan 2017 to June 2017
    //     2016 Fall Semester
    //     2016-2017

    public ReportCard(String period) {
        setReportingPeriod(period);
    }

    // Constructor may also include the addition of a subject/score.
    // This is the preferred method since all report cards should
    // have at least one class to report on.
    public ReportCard(String period, String subject, int score) {
        setReportingPeriod(period);
        addSubject(subject, score);
    }

    // Add a subject to the report card
    public void addSubject(String subject, int score) {
        mSubjectGrades.put(subject.toUpperCase(), score);
    }

    // Remove a subject from the report card by subject
    public void removeSubject(String subject) {
        mSubjectGrades.remove(subject.toUpperCase());
    }

    // Returns the total number of subjects on the report card
    public int getSubjectCount() {
        return mSubjectGrades.size();
    }

    // Returns reporting period
    public String getReportingPeriod() {
        return mReportingPeriod;
    }

    // Sets the reporting period
    public void setReportingPeriod(String period) {
        this.mReportingPeriod = period.toUpperCase();
    }

    // Returns the score of a particular subject by name
    public int getScore(String subject) {
        return mSubjectGrades.get(subject.toUpperCase());
    }

    // Set score for subject by subject
    public void setScore(String subject, int score) {
        mSubjectGrades.put(subject.toUpperCase(), score);
    }

    // Returns average of all scores
    public int getAverage() {
        int totalScore = 0;
        for (int currentScore : mSubjectGrades.values()) {
            totalScore += currentScore;
        }
        return totalScore / mSubjectGrades.size();
    }

    @Override
    public String toString() {

        String reportCard = "REPORT CARD FOR PERIOD " + mReportingPeriod + "\n\n";

        if (mSubjectGrades.size() > 0) {
            reportCard += "You are enrolled in the following " + getSubjectCount() + " classes:\n\n";

            Iterator subjectIterator = mSubjectGrades.entrySet().iterator();
            while (subjectIterator.hasNext()) {
                Map.Entry subjectGrade = (Map.Entry) subjectIterator.next();
                reportCard += subjectGrade.getKey() + ": " + subjectGrade.getValue() + "%\n";
            }

            reportCard += "\nAVERAGE GRADE: " + getAverage() + "%";
        } else {
            reportCard += "You have not enrolled in any classes yet. Try udacity.com!";
        }

        return reportCard;
    }
}
