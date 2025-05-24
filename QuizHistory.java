import java.util.ArrayList;
import java.util.List;
import DAO.HistoryDao;
import java.sql.SQLException;

public class QuizHistory {

  // 履歴を表示するメソッド
  public static void showHistory() {
    System.out.println("\n--- Quiz History ---");
    HistoryDao historyDao = new HistoryDao();
    try {
      java.util.List<HistoryDao.HistoryRecord> historyList = historyDao.getAll();
      if (historyList.isEmpty()) {
        System.out.println("履歴はまだありません。");
        return;
      }
      for (HistoryDao.HistoryRecord record : historyList) {
        System.out.println("[" + record.answeredAt + "] " +
          "問題: " + record.question +
          " | 表示名: " + record.shownName +
          " | 正解: " + record.correctName +
          " | 回答: " + record.userAnswer +
          " | 正誤: " + (record.isCorrect ? "○" : "×") +
          " | モード: " + record.mode);
      }
    } catch (SQLException e) {
      System.out.println("履歴の取得に失敗しました: " + e.getMessage());
    }
  }

  public static void clearHistory() {
    try {
      HistoryDao.clearTable();
      System.out.println("履歴をクリアしました。");
    } catch (SQLException e) {
      System.out.println("履歴のクリアに失敗しました: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    showHistory();
  }
}
