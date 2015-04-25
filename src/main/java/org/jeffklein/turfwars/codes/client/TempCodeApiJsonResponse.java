package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * Bean representation of a typical temp code API JSON response.
 * @author jeffklein
 */
public class TempCodeApiJsonResponse {

    /**
     * JavaBean representation of the "body" section of the JSON.
     */
    @JsonProperty(value = "body")
    private TempCodeApiJsonResponseBody jsonBody;

    /**
     * JavaBean representation of the "header" section of the JSON.
     */
    @JsonProperty(value = "header")
    private TempCodeApiJsonResponseHeader jsonHeader;

    /**
     * Getter for the "body":"timestamp" JSON node.
     * @return the timestamp from the Turf Wars server
     */
    public long getTimestamp() {
        return jsonBody.getTimestamp();
    }

    /**
     * Getter for the "body":"next_update" JSON node.
     * @return the time the next update is expected.
     */
    public long getNextUpdate() {
        return jsonBody.getNextUpdate();
    }

    /**
     * The set of temp codes from the "body":"codes" JSON array.
     * @return the set of codes included with this API response.
     */
    public Set<TempCode> getTempCodes() {
        return jsonBody.getCodes();
    }

    /**
     * Getter for the API response code from the JSON header.
     * @return the API response code from the JSON header.
     */
    public int getApiResponseCode() {
        return jsonHeader.getResponseCode();
    }

    /**
     * Getter for the response message included in "header":"msg".
     * @return the response message.
     */
    public String getApiResponseMessaage() {
        return jsonHeader.getResponseMessage();
    }

    /**
     * Dump the fields of this JSON response in humanly readable form.
     * @return the names and values of each variable returned with this JSON response.
     */
    @Override
    public String toString() {
        return "{header=" + jsonHeader + "|body=" + jsonBody + "}";
    }
}

