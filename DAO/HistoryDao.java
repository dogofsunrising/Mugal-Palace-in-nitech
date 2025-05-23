package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    // 履歴テーブル作成
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS history(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                question TEXT,
                shown_name TEXT,
                correct_name TEXT,
                user_answer TEXT,
                is_correct INTEGER,
                mode TEXT,
                answered_at DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """;
        try (Connection conn = SQLiteManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // 履歴を追加
    public void insert(String question, String shownName, String correctName, String userAnswer, boolean isCorrect, String mode) throws SQLException {
        String sql = "INSERT INTO history(question, shown_name, correct_name, user_answer, is_correct, mode) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = SQLiteManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, shownName);
            pstmt.setString(3, correctName);
            pstmt.setString(4, userAnswer);
            pstmt.setInt(5, isCorrect ? 1 : 0);
            pstmt.setString(6, mode);
            pstmt.executeUpdate();
        }
    }

    // 履歴を全件取得
    public List<HistoryRecord> getAll() throws SQLException {
        List<HistoryRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM history ORDER BY answered_at DESC";
        try (Connection conn = SQLiteManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new HistoryRecord(
                    rs.getInt("id"),
                    rs.getString("question"),
                    rs.getString("shown_name"),
                    rs.getString("correct_name"),
                    rs.getString("user_answer"),
                    rs.getInt("is_correct") == 1,
                    rs.getString("mode"),
                    rs.getString("answered_at")
                ));
            }
        }
        return list;
    }

    // 履歴レコードを表す内部クラス
    public static class HistoryRecord {
        public int id;
        public String question;
        public String shownName;
        public String correctName;
        public String userAnswer;
        public boolean isCorrect;
        public String mode;
        public String answeredAt;
        public HistoryRecord(int id, String question, String shownName, String correctName, String userAnswer, boolean isCorrect, String mode, String answeredAt) {
            this.id = id;
            this.question = question;
            this.shownName = shownName;
            this.correctName = correctName;
            this.userAnswer = userAnswer;
            this.isCorrect = isCorrect;
            this.mode = mode;
            this.answeredAt = answeredAt;
        }
    }
}
