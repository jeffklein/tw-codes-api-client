package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TurfWars Temp Code API Client reimplemented in Java.
 * The original implementation was done in PHP by Nick.
 */
@Component
public class TurfWarsApiClient {

    private static ResourceBundle bundle = ResourceBundle.getBundle("tw-api");

    public static final String URL_INVITE_CODES = bundle.getString("url.invite.codes");

    public static final String MY_API_KEY = bundle.getString("my.api.key");

    public static final String MY_API_SECRET = bundle.getString("my.api.secret");

    public List<TempCode> getTempCodes() throws TurfWarsApiException {
        return getTempCodeApiResponse().getTempCodes();
    }

    public TempCodeApiJsonResponse getTempCodeApiResponse() throws TurfWarsApiException {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(URL_INVITE_CODES)
                    .header("X-api-key", MY_API_KEY)
                    .header("X-api-digest", digest())
                    .asJson();
        } catch (UnirestException e) {
            throw new TurfWarsApiException("Problem while fetching temp codes from URL: " + URL_INVITE_CODES, e);
        }

        return new TurfWarsApiClientJsonParser().deserializeJsonStream(
                response.getRawBody(),
                new TypeReference<TempCodeApiJsonResponse>() {}
        );
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
