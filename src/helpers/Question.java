package helpers;

public class Question {
    String question;
    String[] choices;
    int correctChoice;

    public Question(String question, String[] choices, int correctChoice) {
        this.question = question;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }
}
