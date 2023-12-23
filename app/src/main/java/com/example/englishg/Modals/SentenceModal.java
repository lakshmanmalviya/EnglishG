package com.example.englishg.Modals;

public class SentenceModal {
    String english,hindi,sentenceid;

    public SentenceModal() {
    }

    public SentenceModal(String english, String hindi) {
        this.english = english;
        this.hindi = hindi;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getHindi() {
        return hindi;
    }

    public void setHindi(String hindi) {
        this.hindi = hindi;
    }

    public String getSentenceid() {
        return sentenceid;
    }

    public void setSentenceid(String sentenceid) {
        this.sentenceid = sentenceid;
    }

    public SentenceModal(String english, String hindi, String sentenceid) {
        this.english = english;
        this.hindi = hindi;
        this.sentenceid = sentenceid;
    }
}
