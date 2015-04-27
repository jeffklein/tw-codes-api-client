package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

/**
 * Tests for the TurfWars TurfWarsApiClient
 */
public class TurfWarsApiClientTest {

    private static Log LOG = LogFactory.getLog(TurfWarsApiClientTest.class);

    private TurfWarsApiClient client = new TurfWarsApiClient();

    @Test
    public void testGetCodes() throws Throwable {
        Set<TempCodeDTO> codes = client.getTempCodes();
        Assert.assertNotNull(codes);
        Assert.assertTrue(codes.size() >= 1);
        TempCodeDTO firstCode = codes.toArray(new TempCodeDTO[codes.size()])[0];
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpirationDate()).startsWith("14"));
        Assert.assertTrue(firstCode.getCode().startsWith("-"));
    }

    @Test
    public void testGetTempCodeApiResponse() throws Throwable {
        TempCodeApiJsonResponse response = client.getTempCodeApiJsonResponse();
        Assert.assertNotNull(response.getTimestamp());
        Assert.assertTrue(String.valueOf(response.getTimestamp()).startsWith("14"));
        Assert.assertNotNull(response.getNextUpdate());
        Assert.assertTrue(response.getTimestamp() < response.getNextUpdate());

        Set<TempCodeDTO> codes = response.getTempCodes();
        Assert.assertNotNull(codes);
        Assert.assertTrue(codes.size() >= 1);
        TempCodeDTO firstCode = codes.toArray(new TempCodeDTO[codes.size()])[0];
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpirationDate()).startsWith("14"));
        Assert.assertTrue(firstCode.getCode().startsWith("-"));
    }

    @Test
    public void testDigestHasCorrectValue() throws NoSuchAlgorithmException {
        String expectedDigest = "b2a73767d7aec111e675955cb0ce63605b4ec48d";
        Assert.assertEquals(client.digest(), expectedDigest);
    }

    @Test
    public void testJsonParserWithStaticContent() throws IOException {
        InputStream inputStream = new ClassPathResource("sample.json").getInputStream();
        TurfWarsApiClientJsonParser parser = new TurfWarsApiClientJsonParser();
        TempCodeApiJsonResponse pojo = parser.deserializeJsonStream(inputStream, new TypeReference<TempCodeApiJsonResponse>() {
        });
        Assert.assertNotNull(pojo);
        LOG.info(pojo.toString());
        Assert.assertEquals(pojo.getTimestamp(), 1428873264L);
        Assert.assertEquals(pojo.getNextUpdate(), 1428894864L);
        Set<TempCodeDTO> codes = pojo.getTempCodes();
        Assert.assertEquals(codes.size(), 4);
        for (TempCodeDTO code : codes) {
            if (code.getCode().equals("-404-930")) {
                Assert.assertEquals(code.getExpirationDate(), 1429027158L);
            }
            if (code.getCode().equals("-799-135")) {
                Assert.assertEquals(code.getExpirationDate(), 1429430755L);
            }
            if (code.getCode().equals("-114-184")) {
                Assert.assertEquals(code.getExpirationDate(), 1429279568L);
            }
            if (code.getCode().equals("-691-374")) {
                Assert.assertEquals(code.getExpirationDate(), 1429180222L);
            }
        }
    }
}

/* sample.json
{"body": {
        "timestamp": 1428873264,
        "next_update": 1428894864,
        "codes":[
            {"expires": 1429027158,"code": "-404-930"},
            {"expires": 1429430755, "code": "-799-135"},
            {"expires": 1429279568, "code": "-114-184"},
            {"expires": 1429180222, "code": "-691-374"}
            ]},
        "header": {
        "ts": 1.42887326446E9,
        "code": 1,
        "msg": "OK"
        }
}
*/