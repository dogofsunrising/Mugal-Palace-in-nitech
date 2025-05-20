import java.util.Scanner;

public class QuizManager {
  public static void startQuiz() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Quiz Time!");
    System.out.println("Q1: What is the capital of Japan?");
    System.out.println("[1] Tokyo");
    System.out.println("[2] Osaka");
    System.out.println("[3] Kyoto");

    System.out.print("Your answer: ");
    int answer = scanner.nextInt();

    if (answer == 1) {
      System.out.println("Correct!");
    } else {
      System.out.println("Wrong! The correct answer is Tokyo.");
    }

    scanner.close();
  }
}
