package org.jeffklein.turfwars.codes.client;

/**
 * Unchecked exception indicating something bad happened while interacting with the TW API.
 *
 * @author jeffklein
 */
public class TurfWarsApiClientException extends RuntimeException {
    /**
     * 2 argument constructor
     *
     * @param message an error message
     * @param cause   the original exception, if one exists
     */
    public TurfWarsApiClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Single argument constructor
     *
     * @param msg an error message
     */
    public TurfWarsApiClientException(String msg) {
        this(msg, null);
    }
}
