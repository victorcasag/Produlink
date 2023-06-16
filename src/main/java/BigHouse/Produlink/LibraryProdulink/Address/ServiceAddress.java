package BigHouse.Produlink.LibraryProdulink.Address;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceAddress {
    //valid cep https://viacep.com.br/ws/88820000/json/

    public String FindAll() throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "http://localhost:8080/api/address/search";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        return responseBody;
    }

    public void InsertAddress(ModelAddress address) throws IOException, JSONException {
        String apiUrl = "http://localhost:8080/api/address/insert";
        String requestBody = new JSONObject()
                .put("city", address.getCity())
                .put("country", address.getCountry())
                .put("neighborhood", address.getNeighborhood())
                .put("number", address.getNumber())
                .put("reference", address.getReference())
                .put("state", address.getState())
                .put("street", address.getStreet())
                .put("zipcode", address.getZipcode())
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

        System.out.println("Código de resposta: " + responseCode);
        System.out.println("Resposta: " + response.toString());

        connection.disconnect();
    }
}
