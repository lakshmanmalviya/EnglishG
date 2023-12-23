package com.example.englishg.Modals;

public class VocabModal {
   String vocabimage;
   String vocabName,vocabMeaning,vocabId;
    public VocabModal() {
    }

    public String getVocabimage() {
        return vocabimage;
    }

    public void setVocabimage(String vocabimage) {
        this.vocabimage = vocabimage;
    }

    public String getVocabName() {
        return vocabName;
    }

    public void setVocabName(String vocabName) {
        this.vocabName = vocabName;
    }

    public String getVocabMeaning() {
        return vocabMeaning;
    }

    public void setVocabMeaning(String vocabMeaning) {
        this.vocabMeaning = vocabMeaning;
    }

    public String getVocabId() {
        return vocabId;
    }

    public void setVocabId(String vocabId) {
        this.vocabId = vocabId;
    }

    public VocabModal(String vocabimage, String vocabName, String vocabMeaning) {
        this.vocabimage = vocabimage;
        this.vocabName = vocabName;
        this.vocabMeaning = vocabMeaning;
    }

    public VocabModal(String vocabimage, String vocabName, String vocabMeaning, String vocabId) {
        this.vocabimage = vocabimage;
        this.vocabName = vocabName;
        this.vocabMeaning = vocabMeaning;
        this.vocabId = vocabId;
    }
}
