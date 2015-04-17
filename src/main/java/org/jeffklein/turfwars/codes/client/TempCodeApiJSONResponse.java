package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Bean representation of a typical temp code API response.
 */
public class TempCodeApiJsonResponse {

    @JsonProperty(value = "body")
    private TempCodeApiJsonResponseBody jsonBody;

    @JsonProperty(value = "header")
    private TempCodeApiJsonResponseHeader jsonHeader;

    public Date getTimestamp() {
        return new Date(jsonBody.getTimestamp());
    }

    public Date getNextUpdate() {
        return new Date(jsonBody.getNextUpdate());
    }

    public List<TempCode> getTempCodes() {
        return jsonBody.getCodes();
    }

    public int getApiResponseCode() {
        return jsonHeader.getResponseCode();
    }

    public String getApiResponseMessaage() {
        return jsonHeader.getResponseMessage();
    }

    @Override
    public String toString() {
        return "TempCodeApiJsonResponse:{header=" + jsonHeader + "|body=" + jsonBody + "}";
    }
}

