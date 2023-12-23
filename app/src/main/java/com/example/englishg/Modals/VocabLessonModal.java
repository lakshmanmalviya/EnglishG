package com.example.englishg.Modals;

public class VocabLessonModal {
    String vocabImoji,vocabLessonText,vocabLessonId;

    public String getVocabImoji() {
        return vocabImoji;
    }

    public void setVocabImoji(String vocabImoji) {
        this.vocabImoji = vocabImoji;
    }

    public String getVocabLessonText() {
        return vocabLessonText;
    }

    public void setVocabLessonText(String vocabLessonText) {
        this.vocabLessonText = vocabLessonText;
    }

    public String getVocabLessonId() {
        return vocabLessonId;
    }

    public void setVocabLessonId(String vocabLessonId) {
        this.vocabLessonId = vocabLessonId;
    }

    public VocabLessonModal() {
    }

    public VocabLessonModal(String vocabImoji, String vocabLessonText) {
        this.vocabImoji = vocabImoji;
        this.vocabLessonText = vocabLessonText;
    }

    public VocabLessonModal(String vocabImoji, String vocabLessonText, String vocabLessonId) {
        this.vocabImoji = vocabImoji;
        this.vocabLessonText = vocabLessonText;
        this.vocabLessonId = vocabLessonId;
    }
}
