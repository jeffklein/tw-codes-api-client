package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * TurfWars Temp Code API Client reimplemented in Java.
 * The original implementation was done in PHP by Nick.
 * @author jeffklein
 */
public class TurfWarsApiClient {

    /**
     * TODO: move this api key crap into a separate git repo and make it a dependency
     */
    private static ResourceBundle bundle = ResourceBundle.getBundle("tw-api");
    public static final String URL_INVITE_CODES = bundle.getString("url.invite.codes");
    public static final String MY_API_KEY = bundle.getString("my.api.key");
    public static final String MY_API_SECRET = bundle.getString("my.api.secret");

    /**
     * Make a webservice call to Nick's site to request another batch of temp codes;
     * @return a Set of temp codes returned via turfwarsapp.com.
     * @throws TurfWarsApiClientException if anything goes wrong
     */
    public Set<TempCode> getTempCodes() throws TurfWarsApiClientException {
        return getTempCodeApiResponse().getTempCodes();
    }

    /**
     * Make the actual webservice call to fetch a new batch of temp codes.
     * @return a POJO that represents all the data downloaded from the temp code api request
     * @throws TurfWarsApiClientException if anything goes wrong
     */
    protected TempCodeApiJsonResponse getTempCodeApiResponse() throws TurfWarsApiClientException {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(URL_INVITE_CODES)
                    .header("X-api-key", MY_API_KEY)
                    .header("X-api-digest", digest())
                    .asJson();
        } catch (UnirestException e) {
            throw new TurfWarsApiClientException("Problem while fetching temp codes from URL: " + URL_INVITE_CODES, e);
        }

        return new TurfWarsApiClientJsonParser().deserializeJsonStream(
                response.getRawBody(),
                new TypeReference<TempCodeApiJsonResponse>() {
                }
        );
    }

    /**
     * This is the secret sauce for authenticating against Nick's webservice.
     * Note the boobies in the code. Nice one, Nick! hehe
     * @return The encrypted authentication key for the temp code api.
     */
    protected String digest() {
        String digest;
        try {
            digest = sha1(MY_API_KEY + "(.)(.)" + MY_API_SECRET);
        } catch (NoSuchAlgorithmException e) {
            throw new TurfWarsApiClientException("Unable to generate SHA1 hash.", e);
        }
        return digest;
    }

    /**
     * Encrypt the given string using the SHA1 algorithm.
     * @param input the string to encrypt
     * @return the encrypted string
     * @throws NoSuchAlgorithmException this should never happen lol
     */
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
