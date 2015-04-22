package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Java representation of a turf wars temp code.
 * @author jeffklein
 */
public class TempCode {

    /**
     * Temp code expiration date expressed as ms since Jan. 1 1970.
     * Assumption is that codes fetched from the API are in UTC.
     */
    @JsonProperty
    private long expires;

    /**
     * The temp code itself in the format '-123-456'
     */
    @JsonProperty
    private String code;

    /**
     * Get this temp code's expiration date.
     *
     * @return the date this temp code will expire
     */
    public Date getExpires() {
        return new Date(expires);
    }

    /**
     * Get this temp code's code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Formats a temp code and it's corresponding expiry date into a String.
     *
     * @return Formatted string representation of this instance
     */
    @Override
    public String toString() {
        return "{code=" + code + "|expires=" + expires + "}";
    }
}
