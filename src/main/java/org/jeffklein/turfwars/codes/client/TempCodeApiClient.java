package org.jeffklein.turfwars.codes.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TurfWars Temp Code API Client reimplemented in Java.
 * The original implementation was done in PHP by Nick.
 */
public class TempCodeApiClient {
    public static final String url_invite_codes = "https://turfwarsapp.com/api/invite_codes";

    public static final String my_api_key = "c7a60f0dc66d197de7d2c1e6fcbde589";

    public static final String my_api_secret = "cd6f0c3ac654c2da30143fff85e9d037";

    public List<TempCode> getCodes() throws NoSuchAlgorithmException, UnirestException, TurfWarsApiException {
        return getTempCodeApiResponse().getCodes();
    }

    protected TempCodeApiResponse getTempCodeApiResponse() throws TurfWarsApiException {
        JSONObject bodyJson = getBodyJson();
        Date timestamp = new Date(bodyJson.getLong("timestamp"));
        Date nextUpdate = new Date(bodyJson.getLong("next_update"));

        List<TempCode> tempCodes = new ArrayList<TempCode>();
        JSONArray jsonCodes = bodyJson.getJSONArray("codes");
        for (int i = 0 ; i < jsonCodes.length() ; i++) {
            JSONObject jsonCode = jsonCodes.getJSONObject(i);
            Date expires = new Date(jsonCode.getLong("expires"));
            String code = jsonCode.getString("code");
            TempCode tempCode = new TempCode(expires, code);
            tempCodes.add(tempCode);
        }
        TempCodeApiResponse responseWrapper = new TempCodeApiResponse(timestamp, nextUpdate, tempCodes);
        return responseWrapper;
    }

    protected JSONObject getBodyJson() throws TurfWarsApiException {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(url_invite_codes)
                    .header("X-api-key", my_api_key)
                    .header("X-api-digest", digest())
                    .asJson();
        }
        catch (UnirestException e) {
            throw new TurfWarsApiException("Problem while getting URL: "+url_invite_codes, e);
        }
        JsonNode node = response.getBody();
        JSONObject jsonObj = node.getObject();
        JSONObject headerObj = jsonObj.getJSONObject("header");
        int headerResponseCode = headerObj.getInt("code");
        if (headerResponseCode != 1) {
            throw new TurfWarsApiException("API Error #"+headerResponseCode+": "+headerObj.getString("msg")+" when contacting API");
        }
        return jsonObj.getJSONObject("body");
    }

    protected String digest() {
        String digest;
        try {
            digest = sha1(my_api_key+"(.)(.)"+my_api_secret);
        }
        catch (NoSuchAlgorithmException e) {
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
