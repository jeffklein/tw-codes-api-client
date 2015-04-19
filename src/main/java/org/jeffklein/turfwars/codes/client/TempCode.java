package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Java representation of a turf wars temp code.
 *
 *  @param  expires the expiry date of a temp code
 *  @param  code    the string representing a single temp code
 */
public class TempCode {

    @JsonProperty
    private long expires;

    @JsonProperty
    private String code;

    public TempCode() {
    }

    /**
     * pairs a temp code with its expiration date
     *
     */

    public TempCode(Date expires, String code) {
        this.expires = expires.getTime();
        this.code = code;
    }

    /**
     *  determines a temp codes expiration date
     *
     *  @return the date a temp code will expire
     */

    public Date getExpires() {
        return new Date(expires);
    }

    /**
     *  specifies a temp code
     *
     *  @return the temp code itself
     */

    public String getCode() {
        return code;
    }

    /**
     *  formats a temp code and it's corresponding expiration date into a string
     *
     *  @return
     */

    @Override
    public String toString() {
        return "{code=" + code + "|expires=" + expires + "}";
    }
}
