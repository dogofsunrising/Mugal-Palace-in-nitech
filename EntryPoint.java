import java.util.Scanner;

public class EntryPoint {
  public static void main(String[] args) {
    System.out.println("Hello, Mugal Palace in Nitech!");

    Scanner scanner = new Scanner(System.in);

    System.out.println("Please choose an option:");
    System.out.println("[1] Take a quiz");
    System.out.println("[2] Change mode");
    System.out.println("[3] View history");

    System.out.print("数字を入力してください: ");
    int choice = scanner.nextInt(); // ユーザーの数字入力を取得

    // 入力された数字に応じた処理
    switch (choice) {
      case 1:
        System.out.println("クイズを始めます！");
        break;
      case 2:
        System.out.println("モードを変更します。");
        break;
      case 3:
        System.out.println("履歴を表示します。");
        break;
      default:
        System.out.println("無効な入力です。1〜3を選んでください。");
    }

    scanner.close();
  }
}
