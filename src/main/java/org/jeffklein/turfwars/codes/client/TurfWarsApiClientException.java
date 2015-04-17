package org.jeffklein.turfwars.codes.client;

/**
 * Unchecked exception indicating something bad happened while interacting with the TW API.
 */
public class TurfWarsApiClientException extends RuntimeException {
    public TurfWarsApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TurfWarsApiClientException(String msg) {
        this(msg, null);
    }
}
