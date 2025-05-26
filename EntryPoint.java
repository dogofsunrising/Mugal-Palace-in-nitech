import java.util.Scanner;

public class EntryPoint {
  public static void main(String[] args) {
    System.out.println("こんにちは、ムガルパレス名工大店へようこそ！");
    System.out.println("これはハリーポッターのクイズゲームです。");

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\nオプションを選択してください:");
      System.out.println("[1] クイズに挑戦する");
      System.out.println("[2] モードを変更する");
      System.out.println("[3] 履歴を表示する");
      System.out.println("[4] 履歴をクリアする");
      System.out.println("[5] 終了");

      System.out.print("番号を入力してください: ");
      // 入力のチェックも兼ねてtry-catchで安全にするのが理想だが、今回は簡易に
    
      String text = scanner.nextLine();

      Object obj = null;

      if (isInteger(text)) {
        obj = Integer.parseInt(text);
      } else if (isDouble(text)) {
          obj = Double.parseDouble(text);
      } else if (isBoolean(text)) {
          obj = Boolean.parseBoolean(text);
      } else {
          obj = text; // 文字列として扱う
      }
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
  private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return s.contains("."); // 明確に小数点がある場合のみdouble扱いにする
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isBoolean(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }
}