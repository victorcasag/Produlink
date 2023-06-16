package BigHouse.Produlink.LibraryProdulink.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceLogin {

    public String SearchLoginByUsername(String username) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/login/search/by-name/" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        return responseBody;
    }

    public void InsertNewLogin(ModelLogin login) throws IOException, JSONException {
        String apiUrl = "http://localhost:8080/api/login/insert";
        String requestBody = new JSONObject()
                .put("login", login.getLogin())
                .put("name", login.getName())
                .put("password", login.getPassword())
                .put("role", login.getRole())
                .toString();

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
            outputStream.flush();
        }

        int responseCode = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        connection.disconnect();
    }
}
