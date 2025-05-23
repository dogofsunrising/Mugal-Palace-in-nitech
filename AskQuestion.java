import java.util.Scanner;
import DAO.CharactersDao;
import java.sql.SQLException;
import java.util.List;
import Character.Character;
import java.util.Random;
import DAO.HistoryDao;

public class AskQuestion {

    public void start(Scanner scanner,String mode) {
        List<Character> list = getCharacterList();
        if (list == null || list.isEmpty()) {
            System.out.println("データベースに問題がありません。");
            return;
        }
        try {
            askQuiz(list, scanner, mode);
        } finally {
           
        }
    }

    // 出題形式の入力（不正な入力は再入力）
    private static String getModeInput(Scanner scanner) {
        String mode;
        while (true) {
            System.out.println("出題形式を選んでください: 1=〇✕, 2=4択");
            mode = scanner.nextLine();
            if (mode.equals("1") || mode.equals("2")) {
                break;
            } else {
                System.out.println("1 または 2 を入力してください。");
            }
        }
        return mode;
    }

    // modeに応じてクイズを出題する関数
    private static void askQuiz(List<Character> list, Scanner scanner, String mode) {
        HistoryDao historyDao = new HistoryDao();
        try {
            historyDao.createTable();
        } catch (SQLException e) {
            System.out.println("履歴テーブル作成エラー: " + e.getMessage());
        }
        if (mode.equals("2")) {
            FourChoiceQuestion q = createFourChoiceQuestion(list);
            int userAnswer = askFourChoiceQuestion(q, scanner);
            boolean isCorrect = (userAnswer == q.correctIndex);
            showFourChoiceResult(userAnswer, q.correctIndex, q.correctName);
            // 履歴保存
            try {
                historyDao.insert(
                    q.description,
                    q.choices.get(userAnswer),
                    q.correctName,
                    String.valueOf(userAnswer + 1),
                    isCorrect,
                    "4択"
                );
            } catch (SQLException e) {
                System.out.println("履歴保存エラー: " + e.getMessage());
            }
        } else {
            QuestionPair pair = createQuestionPair(list);
            boolean userAnswer = askQuestion(pair, scanner);
            showResult(userAnswer, pair.isCorrectPair, pair.correctName);
            // 履歴保存
            try {
                historyDao.insert(
                    pair.description,
                    pair.shownName,
                    pair.correctName,
                    userAnswer ? "y" : "n",
                    userAnswer == pair.isCorrectPair,
                    "〇×"
                );
            } catch (SQLException e) {
                System.out.println("履歴保存エラー: " + e.getMessage());
            }
        }
    }

    // データベースからキャラクターリストを取得
    private static List<Character> getCharacterList() {
        CharactersDao answerDao = new CharactersDao();
        try {
            return answerDao.getAll();
        } catch (SQLException e) {
            System.out.println("データベースエラー: " + e.getMessage());
            return null;
        }
    }

    // 問題の説明と名前のペアを作成
    private static QuestionPair createQuestionPair(List<Character> list) {
        Random rand = new Random();
        Character question = list.get(rand.nextInt(list.size()));
        String description = question.getDescription();
        String correctName = question.getName();
        String shownName;
        boolean isCorrectPair = rand.nextBoolean();
        if (isCorrectPair || list.size() == 1) {
            shownName = correctName;
        } else {
            Character other;
            do {
                other = list.get(rand.nextInt(list.size()));
            } while (other.getName().equals(correctName));
            shownName = other.getName();
        }
        return new QuestionPair(description, shownName, isCorrectPair,correctName);
    }

    // 4択問題の生成
    private static FourChoiceQuestion createFourChoiceQuestion(List<Character> list) {
        Random rand = new Random();
        Character question = list.get(rand.nextInt(list.size()));
        String description = question.getDescription();
        String correctName = question.getName();
        // 正解+3つの不正解を用意
        List<String> choices = new java.util.ArrayList<>();
        choices.add(correctName);
        while (choices.size() < 4) {
            String name = list.get(rand.nextInt(list.size())).getName();
            if (!choices.contains(name)) {
                choices.add(name);
            }
        }
        // シャッフル
        java.util.Collections.shuffle(choices);
        int correctIndex = choices.indexOf(correctName);
        return new FourChoiceQuestion(description, choices, correctIndex, correctName);
    }

    // 4択問題の出題
    private static int askFourChoiceQuestion(FourChoiceQuestion q, Scanner scanner) {
        System.out.println("説明: " + q.description);
        for (int i = 0; i < q.choices.size(); i++) {
            System.out.println((i+1) + ": " + q.choices.get(i));
        }
        System.out.print("正しい名前の番号を選んでください（1-4）: ");
        int ans = -1;
        while (true) {
            String input = scanner.nextLine();
            try {
                ans = Integer.parseInt(input);
                if (ans >= 1 && ans <= 4) {
                    break;
                } else {
                    System.out.print("1-4で入力してください: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("数字で1-4を入力してください: ");
            }
        }
        return ans-1;
    }

    // 4択の結果表示
    private static void showFourChoiceResult(int userIndex, int correctIndex, String correctName) {
        if (userIndex == correctIndex) {
            System.out.println("正解！");
        } else {
            System.out.println("不正解。正解は " + (correctIndex+1) + ": " + correctName + " です。");
        }
    }

    // 問題を出題し、ユーザーの回答を取得
    private static boolean askQuestion(QuestionPair pair, Scanner scanner) {
        System.out.println("説明: " + pair.description);
        System.out.println("名前: " + pair.shownName);
        System.out.print("この説明と名前の組み合わせは正しいですか？（〇: y / ×: n）: ");
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.print("y または n で入力してください: ");
            }
        }
        return input.equalsIgnoreCase("y");
    }

    // 結果を表示
    private static void showResult(boolean userAnswer, boolean isCorrectPair,String correctName) {
        if (userAnswer == isCorrectPair) {
            System.out.println("正解！");
        } else {
            System.out.println("不正解。正解は " + (isCorrectPair ? "〇" : "×") + " です。");
            if(!isCorrectPair) {
                System.out.println("説明と名前の組み合わせは"+ correctName + "です。");
            }
        }
    }

    // 問題ペアを表す内部クラス
    private static class QuestionPair {
        String description;
        String shownName;
        boolean isCorrectPair;
        String correctName;
        QuestionPair(String description, String shownName, boolean isCorrectPair,String correctName) {
            this.description = description;
            this.shownName = shownName;
            this.isCorrectPair = isCorrectPair;
            this.correctName = correctName;
        }
    }

    // 4択問題を表す内部クラス
    private static class FourChoiceQuestion {
        String description;
        List<String> choices;
        int correctIndex;
        String correctName;
        FourChoiceQuestion(String description, List<String> choices, int correctIndex, String correctName) {
            this.description = description;
            this.choices = choices;
            this.correctIndex = correctIndex;
            this.correctName = correctName;
        }
    }
}
