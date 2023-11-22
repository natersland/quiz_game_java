package helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    int score = 0;

    // Stored questions in an array for easy to use and can maintainable
    private final Question[] questions = {
            new Question("What is the language spoken in Brazil?", new String[]{"Spanish", "Portuguese", "English", "French"}, 1),
            new Question("What is the capital of Australia?", new String[]{"Sydney", "Melbourne", "Canberra", "Perth"}, 2),
            new Question("What year did World War 2 end?", new String[]{"1945", "1939", "1944", "1942"}, 0),
            new Question("Venezuela is located in which continent?", new String[]{"South America", "North America", "Europe", "Asia"}, 0),
            new Question("How many fingers does a human have?", new String[]{"12", "8", "10", "6"}, 2),
    };

    // Map choice to question index for accessing the correct choice
    private final Map<String, Integer> choiceMapToIndex = new HashMap<String, Integer>() {{
        put("A", 0);
        put("B", 1);
        put("C", 2);
        put("D", 3);
    }};

    // Map question index to choice for displaying the correct choice
    private final Map<Integer, String> indexMapToChoice = new HashMap<Integer, String>() {{
        put(0, "A");
        put(1, "B");
        put(2, "C");
        put(3, "D");
    }};

    // Convert user score to percentage
    private int convertToPercentage(int score, int totalQuestion) {
        return Math.round((float) score / totalQuestion * 100);
    }

    // Show user score
    private void showScore() {
        int totalQuestion = questions.length;
        System.out.println("Your score is " + convertToPercentage(score,  totalQuestion) + "%"+ " out of " + "100%" + ".");
    }

    // Show question and choices from the questions object above
    private void showQuestionAndChoice(Question question, int questionNo) {
        System.out.println("Question " + (questionNo + 1) + ": " + question.question);
        for (Map.Entry<String, Integer> entry : choiceMapToIndex.entrySet()) {
            System.out.println(entry.getKey() + " -> " + question.choices[entry.getValue()]);
        }
    }

    // Check if the answer is correct format (A, B, C, or D)
    private boolean isCorrectAnswerFormat(String answer) {
        return choiceMapToIndex.containsKey(answer);
    }

    // Check if the answer is correct
    private boolean isCorrectAnswer(String answer, int questionNo) {
        int userAnswer = choiceMapToIndex.get(answer);
        return Objects.equals(userAnswer, questions[questionNo].correctChoice);
    }

    // Show the correct choice and answer
    private void showCorrectChoiceAndAnswer(int questionNo, String answer) {
        String correctChoice = indexMapToChoice.get(questions[questionNo].correctChoice);
        String correctChoiceAnswer = questions[questionNo].choices[questions[questionNo].correctChoice];
        System.out.println("Incorrect!\nThe correct answer is: " + correctChoice + ": "+ correctChoiceAnswer);
        System.out.println("Your answer:" + answer);
    }

    public void start() {
        int questionNo = 0; // always start from question 0 because array index start from 0
        System.out.println("Welcome to the quiz game!");
        System.out.println("For each question, you will be given 4 choices. Enter the A,B,C or D of the correct choice.");
        System.out.println("Let's begin!\n");

        while (questionNo < questions.length) {
            Question question = questions[questionNo];
            showQuestionAndChoice(question, questionNo);

            // Get user input from the console
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer (A, B, C, or D: ");
            String answer = scanner.next().toUpperCase();

            // If the answer is not A, B, C, or D, then ask the user to enter again.
            if (!isCorrectAnswerFormat(answer)) {
                System.out.println("Invalid answer format. Please try again.\n");
                continue;
            }

            if (isCorrectAnswer(answer, questionNo)){
                System.out.println("Correct!");
                score++;
                showScore();
            } else {
                showCorrectChoiceAndAnswer(questionNo, answer);
                showScore();
            }
            System.out.println();
            questionNo++;
        }

        System.out.println("Finished game!");
        showScore();
    }
}
