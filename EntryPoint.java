import java.util.Scanner;

import DAO.HistoryDao;

public class EntryPoint {
  public static void main(String[] args) {
    System.out.println("Hello, Mugal Palace in Nitech!");
    System.out.println("This is a Harry Potter quiz game.");

    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    while (true) {
      choice = 0; // 初期化
      System.out.println("\nPlease choose an option:");
      System.out.println("[1] Take a quiz");
      System.out.println("[2] Change mode");
      System.out.println("[3] View history");
      System.out.println("[4] Clear history");
      System.out.println("[5] Exit");

      System.out.print("Enter a number: ");
      // 入力のチェックも兼ねてtry-catchで安全にするのが理想だが、今回は簡易に
      

      choice = scanner.nextInt();
      Object obj = choice;
      switch (obj) {
        case Integer i:
        switch (i) {
          case 1:
            QuizManager.startQuiz(scanner);
            break;
          case 2:
            QuizManager.changeMode(scanner);
            break;
          case 3:
            System.out.println("履歴を表示します。");
            QuizHistory.showHistory();
            break;
          case 4:
            System.out.println("clear history");
            HistoryDao.clearTable();
            return; // または break + while 条件に false
          case 5:
            System.out.println("Exiting the game. Goodbye!");
            scanner.close();
            return; // または break + while 条件に false
          default:
            System.out.println("無効な入力です。1〜4を選んでください。");
        }
          break;
      
          case String s:
          System.out.println("無効な入力です。数字を入力してください。");
          break;
        default:
          break;
      }
    }
  }
}
