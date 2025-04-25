package models.enums;

public enum SecurityQuestion {
    Question1("1- what is your favourite food?"),
    Question2("2- how old are you?"),
    Question3("3- ..."),
    Question4("4- ..."),
    Question5("5- ...");
    private final String question;
    SecurityQuestion(String s) {
        question = s;
    }
    public String getQuestion() {
        return question;
    }
    public static SecurityQuestion getSecurityQuestion(int number) {
        return SecurityQuestion.values()[number-1];
    }
    public static String getQuestions(){
        StringBuilder questions = new StringBuilder();
        questions.append("\n").append("pick one of the following questions if you'd like!").append("\n");
        for(SecurityQuestion q : SecurityQuestion.values()){
            questions.append(q.getQuestion()).append("\n");
        }
        questions.deleteCharAt(questions.length()-1);
        return questions.toString();
    }
}
