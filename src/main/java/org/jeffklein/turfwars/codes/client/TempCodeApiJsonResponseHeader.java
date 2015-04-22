package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The 'header' section of the JSON.
 * @author jeffklein
 */
class TempCodeApiJsonResponseHeader {

    /**
     *
     */
    @JsonProperty(value = "ts")
    private long headerTimestamp;

    /**
     *
     */
    @JsonProperty(value = "code")
    private int responseCode;

    /**
     *
     */
    @JsonProperty(value = "msg")
    private String responseMessage;

    /**
     * Package scoped getter for the header timestamp ("ts").
     * I'm not sure how this differs from the body timestamp.
     * @return the header timestamp.
     */
    long getHeaderTimestamp() {
        return headerTimestamp;
    }

    /**
     * Package scoped getter for the integer response code.
     * Apparently 1 indicates completion of a successful request.
     * @return the response code ("code") from the header of the JSON.
     */
    int getResponseCode() {
        return responseCode;
    }

    /**
     * Package scoped getter for the header response message ("msg").
     * @return The response message from the JSON header.
     */
    String getResponseMessage() {
        return responseMessage;
    }

    /**
     * String representation to print out values of each instance variable.
     * @return the String representation of the instance variables in this class.
     */
    @Override
    public String toString() {
        return "{ts=" + headerTimestamp + "|code=" + responseCode + "|msg=" + responseMessage + "}";
    }
}
