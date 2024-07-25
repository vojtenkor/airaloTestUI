package org.hometest.airalo.api;

import com.google.gson.Gson;
import org.hometest.airalo.api.helpers.GetTokenHelper;
import org.hometest.airalo.api.helpers.OrderRequestHelper;
import org.hometest.airalo.api.helpers.PostTokenResponseHelper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestsHelper {

    private static HttpResponse<String> getStringHttpResponse(HttpRequest httpRequest) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public String getToken(String url, String clientId, String clientSecret, String grant_type) throws URISyntaxException, IOException, InterruptedException {
        GetTokenHelper getTokenHelper = new GetTokenHelper();
        getTokenHelper.setClient_id(clientId);
        getTokenHelper.setClient_secret(clientSecret);
        getTokenHelper.setGrant_type(grant_type);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(getTokenHelper);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();
        HttpResponse<String> response = getStringHttpResponse(httpRequest);
        PostTokenResponseHelper postTokenResponseHelper = gson.fromJson(response.body(), PostTokenResponseHelper.class);
        return postTokenResponseHelper.getData().get("access_token").toString();
    }

    public HttpResponse<String> sendPost(String url, String access_token, String quantity, String package_id, String type, String description) throws IOException, InterruptedException, URISyntaxException {
        OrderRequestHelper orderRequestHelper = new OrderRequestHelper();
        orderRequestHelper.setQuantity(quantity);
        orderRequestHelper.setPackage_id(package_id);
        orderRequestHelper.setType(type);
        orderRequestHelper.setDescription(description);

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(orderRequestHelper);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + access_token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        return getStringHttpResponse(httpRequest);
    }

    public HttpResponse<String> sendGet(String url, String access_token) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + access_token)
                .build();

        return getStringHttpResponse(httpRequest);
    }
}
