import java.util.Scanner;

import DAO.HistoryDao;

public class EntryPoint {
  public static void main(String[] args) {
    System.out.println("こんにちは、ムガルパレス名工大店へようこそ！");
    System.out.println("これはハリーポッターのクイズゲームです。");

    Scanner scanner = new Scanner(System.in);
    int choice = 0;

    while (true) {
      choice = 0; // 初期化
      System.out.println("\nオプションを選択してください:");
      System.out.println("[1] クイズに挑戦する");
      System.out.println("[2] モードを変更する");
      System.out.println("[3] 履歴を表示する");
      System.out.println("[4] 履歴をクリアする");
      System.out.println("[5] 終了");

      System.out.print("番号を入力してください: ");
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
            QuizHistory.clearHistory();
            break;// または break + while 条件に false
          case 5:
            System.out.println("ゲームを終了します。ご利用ありがとうございました！");
            scanner.close();
            return; // または break + while 条件に false
          default:
            System.out.println("無効な入力です。1から5を選んでください。");
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
