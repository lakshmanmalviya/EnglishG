package com.example.englishg.Modals;

public class PhraseModal {
    String imoji,phrase,lessonid;
    public String getLessonId() {
        return lessonid;
    }

    public PhraseModal(String imoji, String phrase, String lessonId) {
        this.imoji = imoji;
        this.phrase = phrase;
        this.lessonid = lessonId;
    }

    public void setLessonId(String lessonId){
        this.lessonid = lessonId;
    }

    public PhraseModal() {
    }

    public String getImoji() {
        return imoji;
    }

    public void setImoji(String imoji) {
        this.imoji = imoji;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public PhraseModal(String imoji, String phrase) {
        this.imoji = imoji;
        this.phrase = phrase;
    }
}
