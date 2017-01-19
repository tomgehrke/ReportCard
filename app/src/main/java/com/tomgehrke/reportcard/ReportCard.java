package com.tomgehrke.reportcard;

/* == Created by Tom Gehrke on 1/18/2017. == */

import java.util.ArrayList;

public class ReportCard {

    // Stores the reporting period
    private String mReportingPeriod;

    // Store subjects and scores in ArrayLists
    // (Preference would be a Class class, but that's out of scope for this assignment.
    private ArrayList<String> mSubjectList = new ArrayList<>();
    private ArrayList<Integer> mScoreList = new ArrayList<>();

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
    public boolean addSubject(String subject, int score) {

        // Always add subjects in upper case
        subject = subject.toUpperCase();

        if (getSubjectIndex(subject) >= 0) {
            // Subject already exists
            return false;
        } else {
            int indexLastAdded;

            // A subject is added to the list...
            mSubjectList.add(subject.toUpperCase().trim());

            indexLastAdded = mSubjectList.size() - 1;

            // ...and it's associate score is added at the same index.
            mScoreList.add(indexLastAdded, score);

            return true;
        }
    }

    // Remove a subject from the report card
    private boolean removeSubject(int i) {

        // Make sure a valid index was passed.
        if (i < 0) {
            return false;
        } else if (i <= mSubjectList.size() - 1) {
            mSubjectList.remove(i);
            mScoreList.remove(i);

            return true;
        } else {
            return false;
        }
    }

    // Remove a subject from the report card by subject
    public boolean removeSubject(String subject) {
        return removeSubject(getSubjectIndex(subject));
    }

    // Returns the total number of subjects on the report card
    public int getSubjectCount() {
        return mSubjectList.size();
    }

    // Returns the subject name by index
    private String getSubject(int i) {
        return mSubjectList.get(i);
    }

    // Utility function for the class to get a subject index
    private int getSubjectIndex(String subject) {
        return mSubjectList.indexOf(subject.toUpperCase().trim());
    }

    // Returns reporting period
    public String getReportingPeriod() {
        return mReportingPeriod;
    }

    // Sets the reporting period
    public void setReportingPeriod(String period) {
        this.mReportingPeriod = period.toUpperCase();
    }

    // Returns the score of a particular subject by index
    private int getScore(int i) {
        return mScoreList.get(i);
    }

    // Returns the score of a particular subject by name
    public int getScore(String subject) {

        // Ultimately still returning subject by index
        return mScoreList.get(getSubjectIndex(subject));
    }

    // Set score for subject by index
    private void setScore(int i, int score) {
        mScoreList.set(i, score);
    }

    // Set score for subject by subject
    public void setScore(String subject, int score) {
        setScore(getSubjectIndex(subject), score);
    }

    // Returns average of all scores
    public int getAverage() {
        int totalScore = 0;
        for (int score : mScoreList) {
            totalScore += score;
        }

        return totalScore / mScoreList.size();
    }

    @Override
    public String toString() {

        int subjectCount = getSubjectCount();
        String reportCard = "REPORT CARD FOR PERIOD " + mReportingPeriod + "\n\n";

        if (subjectCount > 0) {
            reportCard += "You are enrolled in the following " + getSubjectCount() + " classes:\n\n";

            for (int i = 0; i < mSubjectList.size(); i++) {
                reportCard += getSubject(i) + ": " + getScore(i) + "%\n";
            }

            reportCard += "\nAVERAGE GRADE: " + getAverage() + "%";
        } else {
            reportCard += "You have not enrolled in any classes yet. Try udacity.com!";
        }

        return reportCard;
    }
}
