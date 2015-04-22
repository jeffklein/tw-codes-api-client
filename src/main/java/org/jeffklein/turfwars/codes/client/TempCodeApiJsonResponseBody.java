package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * The 'body' section of the JSON.
 * @author jeffklein
 */
class TempCodeApiJsonResponseBody {

    /**
     * The timestamp (assumed to be UTC) given in the body of the JSONResponse.
     */
    @JsonProperty(value = "timestamp")
    private long timestamp;

    /**
     * Date when we can expect to get the next update of tempcodes.
     */
    @JsonProperty(value = "next_update")
    private long nextUpdate;

    /**
     * Representation of the JSON array called "codes".
     * This is the bulk of each response.
     */
    @JsonProperty(value = "codes")
    private Set<TempCode> codes;

    /**
     * Package scoped getter for the body "timestamp".
     * I'm not sure how this differs from the header timestamp.
     * @return the body timestamp.
     */
    long getTimestamp() {
        return timestamp;
    }

    /**
     * Package scoped getter for the set of temp codes in the JSON response.
     * @return A java.util.Set of each temp codes returned with this JSON response.
     */
    Set<TempCode> getCodes() {
        return codes;
    }

    /**
     * Package scoped getter for the date we can expect the next update to be available.
     * @return the date (as ms since the epoch) of the next temp code update
     */
    long getNextUpdate() {
        return nextUpdate;
    }

    /**
     * String representation to print out values of each instance variable.
     * @return the String representation of the instance variables in this class.
     */
    @Override
    public String toString() {
        return "{timestamp=" + timestamp + "|next_update=" + nextUpdate + "|codes=" + codes + "}";
    }
}
