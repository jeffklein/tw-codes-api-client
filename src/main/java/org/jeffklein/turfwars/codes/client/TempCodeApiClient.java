package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TurfWars Temp Code API Client reimplemented in Java.
 * The original implementation was done in PHP by Nick.
 */
@Component
public class TempCodeApiClient {

    private static ResourceBundle bundle = ResourceBundle.getBundle("tw-api");

    public static final String URL_INVITE_CODES = bundle.getString("url.invite.codes");

    public static final String MY_API_KEY = bundle.getString("my.api.key");

    public static final String MY_API_SECRET = bundle.getString("my.api.secret");

    public List<TempCode> getTempCodes() throws TurfWarsApiException {
        return getTempCodeApiResponse().getTempCodes();
    }

    public TempCodeApiResponse getTempCodeApiResponse() throws TurfWarsApiException {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(URL_INVITE_CODES)
                    .header("X-api-key", MY_API_KEY)
                    .header("X-api-digest", digest())
                    .asJson();
        } catch (UnirestException e) {
            throw new TurfWarsApiException("Problem while fetching temp codes from URL: " + URL_INVITE_CODES, e);
        }

        InputStream inputStream = response.getRawBody();
        TempCodeApiResponse pojo;
        try {
            ObjectMapper mapper = new ObjectMapper();
            pojo = mapper.readValue(inputStream, new TypeReference<TempCodeApiResponse>() {
            });
        } catch (IOException ioe) {
            throw new TurfWarsApiException("An error occurred while deserializing JSON response to a POJO. JSON message:\n"
                    + response.getBody().getObject().toString(2), ioe);
        }
        return pojo;
    }

    protected String digest() {
        String digest;
        try {
            digest = sha1(MY_API_KEY + "(.)(.)" + MY_API_SECRET);
        } catch (NoSuchAlgorithmException e) {
            throw new TurfWarsApiException("Unable to generate SHA1 hash.", e);
        }
        return digest;
    }

    private String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(java.lang.Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
