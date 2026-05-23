#CODSOFT TASK 4
#QUIZ APPLICATION WITH TIMER
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class QuizProgram {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static final int QUESTION_TIME_LIMIT_SECONDS = 10;

    public static void main(String[] args) {
        displayWelcomeMessage();
        try {
            startQuiz();
        } catch (TimeLimitExceededException e) {
            System.out.println("\nTime's up! Quiz terminated.");
        }
        displayResult();
    }

    public static void displayWelcomeMessage() {
        System.out.println("Welcome to the Quiz Program!");
        System.out.println("You have " + QUESTION_TIME_LIMIT_SECONDS + " seconds to answer each question.");
        System.out.println("Let's begin...\n");
    }

    public static void startQuiz() throws TimeLimitExceededException {
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, "A"),
            new Question("Which planet is known as the Red Planet?", new String[]{"A. Venus", "B. Mars", "C. Jupiter", "D. Saturn"}, "B"),
            new Question("What is the powerhouse of the cell?", new String[]{"A. Nucleus", "B. Mitochondria", "C. Ribosome", "D. Golgi apparatus"}, "B")
        };

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ":");
            displayQuestion(questions[i]);
            String userAnswer = getUserAnswerWithTimer(questions[i]);
            checkAnswer(questions[i], userAnswer);
            System.out.println();
        }
    }

    public static void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
    }

    public static String getUserAnswerWithTimer(Question question) throws TimeLimitExceededException {
        System.out.print("Your answer (within " + QUESTION_TIME_LIMIT_SECONDS + " seconds): ");
        Thread timerThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(QUESTION_TIME_LIMIT_SECONDS);
                throw new TimeLimitExceededException();
            } catch (InterruptedException e) {
                // Timer thread interrupted because time's up
            }
        });
        timerThread.start();
        String userAnswer = scanner.nextLine().toUpperCase();
        timerThread.interrupt(); // Stop the timer thread
        return userAnswer.isEmpty() ? null : userAnswer; // Return null if user did not input anything
    }

    public static void checkAnswer(Question question, String userAnswer) {
        if (userAnswer == null) {
            throw new TimeLimitExceededException();
        }
        if (userAnswer.equals(question.getCorrectAnswer())) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer is " + question.getCorrectAnswer());
        }
    }

    public static void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + Question.getTotalQuestions());
    }
}

class Question {
    private String question;
    private String[] options;
    private String correctAnswer;
    private static int totalQuestions = 0;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer.toUpperCase();
        totalQuestions++;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public static int getTotalQuestions() {
        return totalQuestions;
    }
}

class TimeLimitExceededException extends RuntimeException {
    public TimeLimitExceededException() {
        super("Time limit exceeded!");
    }
}
