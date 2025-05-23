import java.util.List;
import java.util.Scanner;
import Character.Character;

public class QuizManager {

  private static int mode = 0; // 0: 4択問題モード, 1: 2択問題モード
  
  public static void startQuiz(Scanner scanner) {
    AskQuestion askQuestion = new AskQuestion();
    askQuestion.start(scanner,mode == 0 ? "2" : "1");
    
  }

  public static void changeMode(Scanner scanner) {
    System.out.println("\n--- Change Quiz Mode ---");
    System.out.println("Current mode: " + (mode == 0 ? "4択モード" : "2択モード"));
    System.out.println("Select new mode:");
    System.out.println("[0] 4択問題モード");
    System.out.println("[1] 2択問題モード");
    System.out.print("Your choice: ");

    if (scanner.hasNextInt()) {
      int newMode = scanner.nextInt();
      scanner.nextLine();
      if (newMode == 0 || newMode == 1) {
        mode = newMode;
        System.out.println("モードを " + (mode == 0 ? "4択問題モード" : "2択問題モード") + " に変更しました。");
      } else {
        System.out.println("無効なモード番号です。0または1を入力してください。");
      }
    } else {
      System.out.println("数字を入力してください。");
      scanner.nextLine();
    }
  }
}
