package org.jeffklein.turfwars.codes.client;

/**
 * Unchecked exception indicating something bad happened while interacting with the TW API.
 */
public class TurfWarsApiException extends RuntimeException {
    public TurfWarsApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TurfWarsApiException(String msg) {
        this(msg, null);
    }
}
