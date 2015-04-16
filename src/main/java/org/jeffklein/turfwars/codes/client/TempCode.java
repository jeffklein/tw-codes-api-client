package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Java representation of a turf wars temp code.
 */
public class TempCode {

    @JsonProperty
    private long expires;

    @JsonProperty
    private String code;

    public TempCode() {
    }

    public TempCode(Date expires, String code) {
        this.expires = expires.getTime();
        this.code = code;
    }

    public Date getExpires() {
        return new Date(expires);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{code=" + code + "|expires=" + expires + "}";
    }
}
