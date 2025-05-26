import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;

public class EntryPointTest {
    @Test
    public void testMainPrintsWelcomeMessage() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes()); // 5で即終了
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        EntryPoint.main(new String[]{});

        String output = out.toString();
        assertTrue(output.contains("こんにちは、ムガルパレス名工大店へようこそ！"));
        assertTrue(output.contains("これはハリーポッターのクイズゲームです。"));
        assertTrue(output.contains("ゲームを終了します。ご利用ありがとうございました！"));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testInvalidInputShowsErrorMessage() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("a\n5\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        EntryPoint.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("数字を入力してください") || output.contains("無効な入力です"));
        assertTrue(output.contains("ゲームを終了します。ご利用ありがとうございました！"));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testClearHistoryOption() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n3\n5\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        EntryPoint.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("履歴をクリアしました。"));
        assertTrue(output.contains("履歴はまだありません。"));
        assertTrue(output.contains("ゲームを終了します。ご利用ありがとうございました！"));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testShowHistoryAndAnswer() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1\n3\n5\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        EntryPoint.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("正しい名前の番号を選んでください（1-4）: "));
        assertTrue(output.contains("正解！") || output.contains("不正解。正解は"));
        assertTrue(output.contains("履歴を表示します。"));
        assertTrue(output.contains("--- クイズの履歴 ---"));
        assertTrue(output.contains("ゲームを終了します。ご利用ありがとうございました！"));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testChangeModeOption() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n0\n2\n1\n5\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        EntryPoint.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("クイズモードの変更"));
        assertTrue(output.contains("モードを 4択問題モード に変更しました。") || output.contains("4択問題モード"));
        assertTrue(output.contains("モードを 2択問題モード に変更しました。") || output.contains("2択問題モード"));
        assertTrue(output.contains("ゲームを終了します。ご利用ありがとうございました！"));

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    
}
