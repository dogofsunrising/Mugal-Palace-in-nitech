package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Character.Character;


public class AnswerDao {
    public void createTable() throws SQLException {
        String sql = """
            create table answer(
            id TEXT PRIMARY KEY,
            name TEXT ,
            description TEXT)
        """;
        try (Connection conn = SQLiteManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    //データベースへデータを挿入するメソッド
    public void insert(Character c) throws SQLException {
        String sql = "INSERT OR IGNORE INTO characters(name, house, wizard) VALUES (?, ?, ?)";
        try (Connection conn = SQLiteManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getId()); // 修正: getId()を使用
            pstmt.setString(2, c.getName()); // 修正: getName()を使用
            pstmt.setString(3, c.getDescription()); // 修正: getDescription()を使用
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
                    rs.getString("id"), // 修正: idカラム名を確認
                    rs.getString("name"),
                    rs.getString("description")
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
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description")
                    );
                }
            }
        }
        return null; // 該当するデータがない場合はnullを返す
    }
}

