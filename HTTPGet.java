// Importing all the necessary packages

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.main.AnswerDao;

                  




public class HTTPGet {
  public static void main(String args[]) throws Exception {
    // Providing the website URL
    URL url = new URL("https://hp-api.onrender.com/api/spells/");

    // Creating an HTTP connection
    HttpURLConnection MyConn = (HttpURLConnection) url.openConnection();

    // Set the request method to "GET"
    MyConn.setRequestMethod("GET");

    // Collect the response code
    int responseCode = MyConn.getResponseCode();
    System.out.println("GET Response Code :: " + responseCode);

    if (responseCode == MyConn.HTTP_OK) {
      // Create a reader with the input stream reader.
      BufferedReader in = new BufferedReader(new InputStreamReader(MyConn.getInputStream()));
      String inputLine;

      // Create a string buffer
      StringBuffer response = new StringBuffer();

      // Write each of the input line
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      } 
      in.close();
      // Show the output
      System.out.println(response.toString());
    } else {
      System.out.println("Error found !!!");
    }
  }
}