package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
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
     *
     * @return
     */
    public Date getTimestamp() {
        return new Date(jsonBody.getTimestamp());
    }

    /**
     *
     * @return
     */
    public Date getNextUpdate() {
        return new Date(jsonBody.getNextUpdate());
    }

    /**
     *
     * @return
     */
    public Set<TempCode> getTempCodes() {
        return jsonBody.getCodes();
    }

    /**
     *
     * @return
     */
    public int getApiResponseCode() {
        return jsonHeader.getResponseCode();
    }

    /**
     *
     * @return
     */
    public String getApiResponseMessaage() {
        return jsonHeader.getResponseMessage();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TempCodeApiJsonResponse:{header=" + jsonHeader + "|body=" + jsonBody + "}";
    }
}

