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
import java.util.Date;
import java.util.Set;

/**
 * Tests for the TurfWars TurfWarsApiClient
 */
public class TurfWarsApiClientTest {

    private static Log LOG = LogFactory.getLog(TurfWarsApiClientTest.class);

    private TurfWarsApiClient client = new TurfWarsApiClient();

    @Test
    public void testGetCodes() throws Throwable {
        Set<TempCode> codes = client.getTempCodes();
        Assert.assertNotNull(codes);
        Assert.assertTrue(codes.size() >= 1);
        TempCode firstCode = codes.toArray(new TempCode[codes.size()])[0];
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpires().getTime()).startsWith("14"));
        Assert.assertTrue(firstCode.getCode().startsWith("-"));
    }

    @Test
    public void testGetTempCodeApiResponse() throws Throwable {
        TempCodeApiJsonResponse response = client.getTempCodeApiResponse();
        Assert.assertNotNull(response.getTimestamp());
        Assert.assertTrue(String.valueOf(response.getTimestamp().getTime()).startsWith("14"));
        Assert.assertNotNull(response.getNextUpdate());
        Assert.assertTrue(response.getTimestamp().before(response.getNextUpdate()));

        Set<TempCode> codes = response.getTempCodes();
        Assert.assertNotNull(codes);
        Assert.assertTrue(codes.size() >= 1);
        TempCode firstCode = codes.toArray(new TempCode[codes.size()])[0];
        Assert.assertNotNull(firstCode);
        Assert.assertTrue(String.valueOf(firstCode.getExpires().getTime()).startsWith("14"));
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
        TempCodeApiJsonResponse pojo = parser.deserializeJsonStream(inputStream, new TypeReference<TempCodeApiJsonResponse>(){});
        Assert.assertNotNull(pojo);
        LOG.info(pojo.toString());
        Assert.assertEquals(pojo.getTimestamp(), new Date(1428873264));
        Assert.assertEquals(pojo.getNextUpdate(), new Date(1428894864));
        Set<TempCode> codes = pojo.getTempCodes();
        Assert.assertEquals(codes.size(), 4);
        for (TempCode code : codes) {
            if (code.getCode().equals("-404-930")) {
                Assert.assertEquals(code.getExpires(), new Date(1429027158));
            }
            if (code.getCode().equals("-799-135")) {
                Assert.assertEquals(code.getExpires(), new Date(1429430755));
            }
            if (code.getCode().equals("-114-184")) {
                Assert.assertEquals(code.getExpires(), new Date(1429279568));
            }
            if (code.getCode().equals("-691-374")) {
                Assert.assertEquals(code.getExpires(), new Date(1429180222));
            }
        }
    }
}

/* sample.json
{
    "body": {
        "timestamp": 1428873264,
        "next_update": 1428894864,
        "codes": [
            {
                "expires": 1429027158,
                "code": "-404-930"
            },
            {
                "expires": 1429430755,
                "code": "-799-135"
            },
            {
                "expires": 1429279568,
                "code": "-114-184"
            },
            {
                "expires": 1429180222,
                "code": "-691-374"
            }
        ]
    },
    "header": {
        "ts": 1.42887326446E9,
        "code": 1,
        "msg": "OK"
    }
}
*/