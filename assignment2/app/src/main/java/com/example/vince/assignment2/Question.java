package com.example.vince.assignment2;

class Question {
    private boolean answer;
    private boolean completed;
    private boolean usedHint;
    private boolean usedCheat;
    private int hintId;
    private int questionId;

    public Question(boolean answer, boolean completed, boolean usedHint, boolean usedCheat, int questionId, int hintId) {
        this.answer = answer;
        this.completed = completed;
        this.usedHint = usedHint;
        this.usedCheat = usedCheat;
        this.questionId = questionId;
        this.hintId = hintId;
    }


    public boolean getAnswer() {
        return answer;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean usedHint() {
        return usedHint;
    }

    public boolean usedCheat() {
        return usedCheat;
    }

    public int getHintId() {
        return hintId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setUsedHint(boolean usedHint) {
        this.usedHint = usedHint;
    }

    public void setUsedCheat(boolean usedCheat) {
        this.usedCheat = usedCheat;
    }
}
