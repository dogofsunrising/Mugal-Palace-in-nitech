import static org.junit;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetTest {

    @Test
    public void testGetResponseCode() throws Exception {
        URL url = new URL("https://hp-api.onrender.com/api/spells/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        // 200(OK)であることを確認
        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
    }
}