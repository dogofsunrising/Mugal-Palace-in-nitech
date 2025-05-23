import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetTest {

    @Test
    public void testGetResponseCode(URL url,int responseCode) throws Exception {
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        
        // 200(OK)であることを確認
        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        System.out.println("GET Response Code :: " + responseCode);
        
    }
}