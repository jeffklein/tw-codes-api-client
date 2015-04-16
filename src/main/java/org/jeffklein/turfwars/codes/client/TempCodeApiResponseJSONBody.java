package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The 'body' section of the JSON.
 */
class TempCodeApiResponseJSONBody {

    @JsonProperty(value = "timestamp")
    private long timestamp;

    @JsonProperty(value = "next_update")
    private long nextUpdate;

    @JsonProperty(value = "codes")
    private List<TempCode> codes;

    long getTimestamp() {
        return timestamp;
    }

    List<TempCode> getCodes() {
        return codes;
    }

    long getNextUpdate() {
        return nextUpdate;
    }

    @Override
    public String toString() {
        return "{timestamp=" + timestamp + "|next_update=" + nextUpdate + "|codes=" + codes + "}";
    }
}
