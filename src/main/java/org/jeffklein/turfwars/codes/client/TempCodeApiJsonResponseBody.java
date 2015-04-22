package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * The 'body' section of the JSON.
 */
class TempCodeApiJsonResponseBody {

    @JsonProperty(value = "timestamp")
    private long timestamp;

    @JsonProperty(value = "next_update")
    private long nextUpdate;

    @JsonProperty(value = "codes")
    private Set<TempCode> codes;

    long getTimestamp() {
        return timestamp;
    }

    Set<TempCode> getCodes() {
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
