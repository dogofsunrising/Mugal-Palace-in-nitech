package DAO.main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnswerDao {
    public void createTable() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS characters (
                name TEXT PRIMARY KEY,
                house TEXT,
                wizard INTEGER
            )
        """;
        try (Connection conn = SQLiteManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(Character c) throws SQLException {
        String sql = "INSERT OR IGNORE INTO characters(name, house, wizard) VALUES (?, ?, ?)";
        try (Connection conn = SQLiteManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.name());
            pstmt.setString(2, c.house());
            pstmt.setBoolean(3, c.wizard());
            pstmt.executeUpdate();
        }
    }

    public List<Character> getAll() throws SQLException {
        List<Character> list = new ArrayList<>();
        String sql = "SELECT * FROM characters";
        try (Connection conn = SQLiteManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Character(
                    rs.getString("name"),//id
                    rs.getString("house"),//name
                    rs.getBoolean("wizard")//description
                ));
            }
        }
        return list;
    }

    public Character getByName(String name) throws SQLException {
        String sql = "SELECT * FROM characters WHERE name = ?";
        try (Connection conn = SQLiteManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Character(
                        rs.getString("name"),
                        rs.getString("house"),
                        rs.getBoolean("wizard")
                    );
                }
            }
        }
        return null; // 該当するデータがない場合はnullを返す
    }
}
