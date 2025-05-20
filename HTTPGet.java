// Importing all the necessary packages

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Character.Character;
import DAO.CharactersDao;






public class HTTPGet {

  static Character[] characters = new Character[77];

  public static void main(String args[]) throws Exception {
    fetchAndSaveSpells();
  }

  public static void fetchAndSaveSpells() throws Exception {
    // Providing the website URL
    URL url = java.net.URI.create("https://hp-api.onrender.com/api/spells/").toURL();

    // Creating an HTTP connection
    HttpURLConnection MyConn = (HttpURLConnection) url.openConnection();

    // Set the request method to "GET"
    MyConn.setRequestMethod("GET");

    // Collect the response code
    int responseCode = MyConn.getResponseCode();
    System.out.println("GET Response Code :: " + responseCode);

    if (responseCode == HttpURLConnection.HTTP_OK) {
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

      // Create an instance of CharactersDao
      CharactersDao CharacterDao = new CharactersDao();
      // Create the table
      CharacterDao.createTable();

      Gson gson = new Gson();
      // Deserialize the JSON response into an array of Characters objects
      Type characterListType = new TypeToken<Character[]>() {}.getType();
      characters = gson.fromJson(response.toString(), characterListType);
      for (Character string : characters) {
        // Print the Characters details
        System.out.println("ID: " + string.getId());
        System.out.println("Name: " + string.getName());
        System.out.println("Description: " + string.getDescription());
        System.out.println();
      }
      // Insert the data into the database
      for (Character it_character : characters) {
        // Insert each Characters into the database
        CharacterDao.insert(it_character);
      }
    } else {
      System.out.println("Error found !!!");
    }
  }
}
