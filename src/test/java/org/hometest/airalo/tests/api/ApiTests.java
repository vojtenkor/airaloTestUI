package org.hometest.airalo.tests.api;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.hometest.airalo.BaseTest;
import org.hometest.airalo.api.RequestsHelper;
import org.hometest.airalo.api.helpers.GetTokenResponseHelper;
import org.hometest.airalo.api.helpers.PostTokenResponseHelper;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiTests extends BaseTest {

    @Test
    public void postOrder() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("running postOrder test");
        RequestsHelper requestsHelper = new RequestsHelper();
        String access_token = requestsHelper.getToken(prop.getProperty("api.url") + "/token",
                prop.getProperty("client.id"), prop.getProperty("client.secret"), prop.getProperty("grant.type"));
        HttpResponse<String> httpResponse = requestsHelper.sendPost(prop.getProperty("api.url") + "/orders", access_token,
                "6", "merhaba-7days-1gb", "sim", "Order new eSim");
        softAssertions.assertThat(httpResponse.statusCode())
                .as(String.format("Status code is '%s' and not equal to 200", httpResponse.statusCode()))
                .isEqualTo(200);

        Gson gson = new Gson();
        PostTokenResponseHelper postTokenResponseHelper = gson.fromJson(httpResponse.body(), PostTokenResponseHelper.class);

        softAssertions.assertThat(postTokenResponseHelper.getData().get("currency")).isEqualTo("USD");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("package_id")).isEqualTo("merhaba-7days-1gb");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("quantity")).isEqualTo(6D);
        softAssertions.assertThat(postTokenResponseHelper.getData().get("type")).isEqualTo("sim");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("description")).isEqualTo("Order new eSim");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("esim_type")).isEqualTo("Prepaid");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("validity")).isEqualTo(7D);
        softAssertions.assertThat(postTokenResponseHelper.getData().get("package")).isEqualTo("Merhaba-1 GB - 7 Days");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("data")).isEqualTo("1 GB");
        softAssertions.assertThat(postTokenResponseHelper.getData().get("price")).isEqualTo(4.5D);
        softAssertions.assertThat(postTokenResponseHelper.getData().get("net_price")).isEqualTo(3.6D);

        List<LinkedTreeMap<String, Object>> sims
                = (List<LinkedTreeMap<String, Object>>) postTokenResponseHelper.getData().get("sims");
        sims.stream().forEach(e -> {
            softAssertions.assertThat(e.get("lpa")).isEqualTo("lpa.airalo.com");
            softAssertions.assertThat(e.get("matching_id")).isEqualTo("TEST");
            softAssertions.assertThat(e.get("qrcode")).isEqualTo("LPA:1$lpa.airalo.com$TEST");
            softAssertions.assertThat(e.get("apn_type")).isEqualTo("manual");
            softAssertions.assertThat(e.get("apn_value")).isEqualTo("airalo2");
            softAssertions.assertThat(e.get("is_roaming")).isEqualTo(true);
            softAssertions.assertThat(e.get("direct_apple_installation_url"))
                    .isEqualTo("https://esimsetup.apple.com/esim_qrcode_provisioning?carddata=LPA:1$lpa.airalo.com$TEST");

        });
        softAssertions.assertAll();
    }

    @Test
    public void getOrder() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("running getOrder test");
        RequestsHelper requestsHelper = new RequestsHelper();
        String access_token = requestsHelper.getToken(prop.getProperty("api.url") + "/token",
                prop.getProperty("client.id"), prop.getProperty("client.secret"), prop.getProperty("grant.type"));

        HttpResponse<String> httpResponse = requestsHelper.sendGet(prop.getProperty("api.url")
                + "/sims?include=order%2Corder.status%2Corder.user&limit=2", access_token);
        softAssertions.assertThat(httpResponse.statusCode())
                .as(String.format("Status code is '%s' and not equal to 200", httpResponse.statusCode()))
                .isEqualTo(200);

        Gson gson = new Gson();
        GetTokenResponseHelper getTokenResponseHelper = gson.fromJson(httpResponse.body(), GetTokenResponseHelper.class);

        getTokenResponseHelper.getData().stream().forEach(e -> {
            softAssertions.assertThat(e.get("matching_id")).isEqualTo("TEST");
            softAssertions.assertThat(e.get("is_roaming")).isEqualTo(true);
            softAssertions.assertThat(e.get("qrcode")).isEqualTo("LPA:1$lpa.airalo.com$TEST");
            softAssertions.assertThat(e.get("apn_type")).isEqualTo("manual");
            softAssertions.assertThat(e.get("apn_value")).isEqualTo("airalo2");
            softAssertions.assertThat(e.get("lpa")).isEqualTo("lpa.airalo.com");
        });

        softAssertions.assertAll();
    }
}
