package org.jeffklein.tw.tempcodes.client;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Tests for the TurfWars TempCodeApiClient
 */
public class TempCodeApiTest {

    private TempCodeApiClient client = new TempCodeApiClient();

    @Test
    public void testGetCodes() throws Throwable {
        List<TempCode> codeList = client.getCodes();
        Assert.assertNotNull(codeList);
        Assert.assertTrue(codeList.size() >= 1);
        TempCode firstCode = codeList.get(0);
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpires().getTime()).startsWith("14"));
        Assert.assertTrue(firstCode.getCode().startsWith("-"));
    }

    @Test
    public void testGetTempCodeApiResponse() throws Throwable {
        TempCodeApiResponse response = client.getTempCodeApiResponse();
        Assert.assertNotNull(response.getTimestamp());
        Assert.assertTrue(String.valueOf(response.getTimestamp().getTime()).startsWith("14"));
        Assert.assertNotNull(response.getNextUpdate());
        Assert.assertTrue(response.getTimestamp().before(response.getNextUpdate()));

        List<TempCode> codeList = response.getCodes();
        Assert.assertNotNull(codeList);
        Assert.assertTrue(codeList.size() >= 1);
        TempCode firstCode = codeList.get(0);
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpires().getTime()).startsWith("14"));
        Assert.assertTrue(firstCode.getCode().startsWith("-"));
    }

    @Test
    public void testGetBodyJson() throws Throwable {
        JSONObject bodyJson = client.getBodyJson();
        Assert.assertNotNull(bodyJson.getLong("timestamp"));
        Assert.assertTrue(String.valueOf(bodyJson.getLong("timestamp")).startsWith("14"));
        Assert.assertNotNull(bodyJson.getLong("next_update"));
        Assert.assertTrue(bodyJson.getLong("timestamp") < bodyJson.getLong("next_update"));

        JSONArray codesArray = bodyJson.getJSONArray("codes");
        Assert.assertNotNull(codesArray);
        Assert.assertTrue(codesArray.length() >= 1);
        JSONObject firstCode = codesArray.getJSONObject(0);
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getLong("expires")).startsWith("14"));
        Assert.assertTrue(firstCode.getString("code").startsWith("-"));
    }

    @Test
    public void testDigestHasCorrectValue() throws NoSuchAlgorithmException {
        String expectedDigest = "b2a73767d7aec111e675955cb0ce63605b4ec48d";
        Assert.assertEquals(client.digest(), expectedDigest);
    }
}
