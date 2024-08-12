package BigHouse.Produlink.LibraryProdulink.Login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceLogin {

    public String FindAll() throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/login/search";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String FindById(int id) throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/login/search/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String FindByUsername(String username) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/login/search/by-name/" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public boolean Exists(String login) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/login/search/exists/" + login;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        return statusCode == 200;
    }



    public void InsertNewLogin(ModelLogin login) throws IOException {
        String apiUrl = "http://localhost:8080/api/login/insert";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String requestBody = mapper.writeValueAsString(login);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
            outputStream.flush();
        }

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
