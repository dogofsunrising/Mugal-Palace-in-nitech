import java.util.Scanner;

public class EntryPoint {
  public static void main(String[] args) {
    System.out.println("Hello, Mugal Palace in Nitech!");
    System.out.println("This is a Harry Potter quiz game.");

    Scanner scanner = new Scanner(System.in);

    System.out.println("Please choose an option:");
    System.out.println("[1] Take a quiz");
    System.out.println("[2] Change mode");
    System.out.println("[3] View history");
    System.out.println("[4] Finish the game");

    System.out.print("数字を入力してください: ");
    int choice = scanner.nextInt(); // ユーザーの数字入力を取得


    // 入力された数字に応じた処理
    switch (choice) {
      case 1:
        QuizManager.startQuiz();
        break;
      case 2:
        System.out.println("モードを変更します。");
        break;
      case 3:
        System.out.println("履歴を表示します。");
        break;
      case 4:
        System.out.println("終了しました");
        break;
      default:
        System.out.println("無効な入力です。1〜4を選んでください。");
    }

    scanner.close();
  }
}
