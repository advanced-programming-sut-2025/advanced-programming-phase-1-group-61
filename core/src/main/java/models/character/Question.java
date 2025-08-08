package models.character;

import models.enums.SecurityQuestion;

public class Question {
    private SecurityQuestion question;
    private String answer;
    public Question(SecurityQuestion question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question() {
    }

    public String getQuestion() {
        return question.getQuestion();
    }
    public String getAnswer() {
        return answer;
    }
}
