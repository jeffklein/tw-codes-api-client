package org.jeffklein.turfwars.codes.client;

/**
 * Data Trasfer Object (DTO) to expose to clients of this package.
 *
 * @author jeffklein
 */
public class TempCodeDTO {

    public TempCodeDTO(String tempCode,
                       long expirationDate,
                       long serverBatchPostedDate,
                       long serverNextBatchAvailableDate,
                       int apiResponseCode,
                       String apiResponseMessage) {
        this.expirationDate = expirationDate;
        this.tempCode = tempCode;
        this.serverBatchPostedDate = serverBatchPostedDate;
        this.serverNextBatchAvailableDate = serverNextBatchAvailableDate;
        this.apiResponseCode = apiResponseCode;
        this.apiResponseMessage = apiResponseMessage;
    }

    /**
     * Temp code expiration date expressed as ms since Jan. 1 1970.
     * Assumption is that codes fetched from the API are in UTC.
     */
    private long expirationDate;

    /**
     * The temp code itself in the format '-123-456'
     */
    private String tempCode;

    /**
     * The timestamp (assumed to be UTC) given in the body of the JSON response.
     */
    private long serverBatchPostedDate;

    /**
     * Date when we can expect to get the next update of tempcodes.
     */
    private long serverNextBatchAvailableDate;

    /**
     * The API response code. "1" seems to indicate success.
     */
    private int apiResponseCode;

    /**
     * The header response message (typically "OK" if responseCode = 1)
     */
    private String apiResponseMessage;

    /**
     * Get this temp code's expiration date.
     *
     * @return the date this temp code will expire
     */
    public long getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * Get the value of this temp code's code.
     *
     * @return the tempCode
     */
    public String getTempCode() {
        return tempCode;
    }

    /**
     * Getter for the API response code from the JSON header.
     *
     * @return the API response code from the JSON header.
     */
    public int getApiResponseCode() {
        return apiResponseCode;
    }

    /**
     * Getter for the response message included in "header":"msg".
     *
     * @return the response message.
     */
    public String getApiResponseMessage() {
        return apiResponseMessage;
    }

    /**
     * Get the time that the batch was posted on the server.
     *
     * @return the server timestamp
     */
    public long getServerBatchPostedDate() {
        return serverBatchPostedDate;
    }

    /**
     * Get the time the next batch of temps is expected to be posted to the server.
     *
     * @return the expected time of the next update
     */
    public long getServerNextBatchAvailableDate() {
        return serverNextBatchAvailableDate;
    }

    /**
     * Formats a temp code and it's corresponding expiry date into a String.
     *
     * @return Formatted string representation of this instance
     */
    @Override
    public String toString() {
        return "TempCodeDTO{" +
                "expirationDate=" + expirationDate +
                ", tempCode='" + tempCode + '\'' +
                ", serverBatchPostedDate=" + serverBatchPostedDate +
                ", serverNextBatchAvailableDate=" + serverNextBatchAvailableDate +
                ", apiResponseCode=" + apiResponseCode +
                ", apiResponseMessage='" + apiResponseMessage + '\'' +
                '}';
    }
}
