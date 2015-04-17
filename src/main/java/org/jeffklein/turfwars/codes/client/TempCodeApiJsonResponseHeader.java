package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The 'header' section of the JSON.
 */
class TempCodeApiJsonResponseHeader {
    @JsonProperty(value = "ts")
    private long headerTimestamp;

    @JsonProperty(value = "code")
    private int responseCode;

    @JsonProperty(value = "msg")
    private String responseMessage;

    long getHeaderTimestamp() {
        return headerTimestamp;
    }

    int getResponseCode() {
        return responseCode;
    }

    String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public String toString() {
        return "{ts=" + headerTimestamp + "|code=" + responseCode + "|msg=" + responseMessage + "}";
    }
}
