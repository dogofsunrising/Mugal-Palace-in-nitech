// Importing all the necessary packages

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Character.Character;
import DAO.AnswerDao;
import Character.Character;





public class HTTPGet {

  static Character[] characters = new Character[77];
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

      // Create an instance of AnswerDao
      AnswerDao answerDao = new AnswerDao();
      // Create the table
      answerDao.createTable();
      // Insert the data into the database
      for (int i = 0; i < characters.length; i++) {
        // Assuming the response is in JSON format and you have a method to parse it
        // For example, if you have a method parseCharacterFromJson(String json)
        // characters[i] = parseCharacterFromJson(response.toString());
        // Here, you would need to implement the parsing logic based on the actual JSON structure
        // For demonstration, let's assume you have a method to parse the JSON
        // and create Character objects
        // characters[i] = parseCharacterFromJson(response.toString());
        // Insert the character into the database
        // answerDao.insert(characters[i]);
      }

      // Show the output
      System.out.println(response.toString());
    } else {
      System.out.println("Error found !!!");
    }
  }
}