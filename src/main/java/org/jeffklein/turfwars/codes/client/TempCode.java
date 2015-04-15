package org.jeffklein.turfwars.codes.client;

import java.util.Date;

/**
 * TODO: redo this with Jackson annotations
 */
public class TempCode {
    private Date expires;
    private String code;

    public TempCode(Date expires, String code) {
        this.expires = expires;
        this.code = code;
    }

    public Date getExpires() {
        return expires;
    }

    public String getCode() {
        return code;
    }
}
