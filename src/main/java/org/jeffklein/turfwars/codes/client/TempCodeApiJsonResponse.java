package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Bean representation of a typical temp code API JSON response.
 *
 * @author jeffklein
 */
public class TempCodeApiJsonResponse {

    /**
     * JavaBean representation of the "body" section of the JSON.
     */
    @JsonProperty(value = "body")
    private TempCodeApiJsonResponseBody jsonBody;

    /**
     * JavaBean representation of the "header" section of the JSON.
     */
    @JsonProperty(value = "header")
    private TempCodeApiJsonResponseHeader jsonHeader;

    /**
     * Getter for the "body":"timestamp" JSON node.
     *
     * @return the timestamp from the Turf Wars server
     */
    public long getTimestamp() {
        return jsonBody.getTimestamp();
    }

    /**
     * Getter for the "body":"next_update" JSON node.
     *
     * @return the time the next update is expected.
     */
    public long getNextUpdate() {
        return jsonBody.getNextUpdate();
    }

    /**
     * The set of temp codes from the "body":"codes" JSON array.
     *
     * @return the set of codes included with this API response.
     */
    public Set<TempCodeDTO> getTempCodes() {
        Set<TempCodeDTO> tempCodeDTOs = new HashSet<TempCodeDTO>();
        for (TempCodeApiJsonResponseBody.Code code : jsonBody.getCodes()) {
            TempCodeDTO dto = new TempCodeDTO(
                    code.getCode(),
                    code.getExpires(),
                    getTimestamp(),
                    getNextUpdate(),
                    getApiResponseCode(),
                    getApiResponseMessaage());
            tempCodeDTOs.add(dto);
        }
        return tempCodeDTOs;
    }

    /**
     * Getter for the API response code from the JSON header.
     *
     * @return the API response code from the JSON header.
     */
    public int getApiResponseCode() {
        return jsonHeader.getResponseCode();
    }

    /**
     * Getter for the response message included in "header":"msg".
     *
     * @return the response message.
     */
    public String getApiResponseMessaage() {
        return jsonHeader.getResponseMessage();
    }

    /**
     * Dump the fields of this JSON response in humanly readable form.
     *
     * @return the names and values of each variable returned with this JSON response.
     */
    @Override
    public String toString() {
        return "{header=" + jsonHeader + "|body=" + jsonBody + "}";
    }

    /**
     * The 'body' section of the JSON.
     *
     * @author jeffklein
     */
    static class TempCodeApiJsonResponseBody {

        /**
         * The timestamp (assumed to be UTC) given in the body of the JSONResponse.
         */
        @JsonProperty(value = "timestamp")
        private long timestamp;

        /**
         * Date when we can expect to get the next update of tempcodes.
         */
        @JsonProperty(value = "next_update")
        private long nextUpdate;

        /**
         * Representation of the JSON array called "codes".
         * This is the bulk of each response.
         */
        @JsonProperty(value = "codes")
        private Set<Code> codes;

        /**
         * Package scoped getter for the body "timestamp".
         * I'm not sure how this differs from the header timestamp.
         *
         * @return the body timestamp.
         */
        long getTimestamp() {
            return timestamp;
        }

        /**
         * Package scoped getter for the set of temp codes in the JSON response.
         *
         * @return A java.util.Set of each temp codes returned with this JSON response.
         */
        Set<Code> getCodes() {
            return codes;
        }

        /**
         * Package scoped getter for the date we can expect the next update to be available.
         *
         * @return the date (as ms since the epoch) of the next temp code update
         */
        long getNextUpdate() {
            return nextUpdate;
        }

        /**
         * String representation to print out values of each instance variable.
         *
         * @return the String representation of the instance variables in this class.
         */
        @Override
        public String toString() {
            return "{timestamp=" + timestamp + "|next_update=" + nextUpdate + "|codes=" + codes + "}";
        }

        /**
         * A "code" element of the "body":"codes" JSON array (see sample.json for example)
         *
         * @author jeffklein
         */
        public static class Code {

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
            public long getExpires() {
                return this.expires;
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
    }

    /**
     * The 'header' section of the JSON.
     *
     * @author jeffklein
     */
    static class TempCodeApiJsonResponseHeader {

        /**
         * The value of the header timestamp (which seems to be the same as the body timestamp)
         */
        @JsonProperty(value = "ts")
        private long headerTimestamp;

        /**
         * The API response code. "1" seems to indicate success.
         */
        @JsonProperty(value = "code")
        private int responseCode;

        /**
         * The header response message (typically "OK" if responseCode = 1)
         */
        @JsonProperty(value = "msg")
        private String responseMessage;

        /**
         * Package scoped getter for the header timestamp ("ts").
         * I'm not sure how this differs from the body timestamp.
         *
         * @return the header timestamp.
         */
        long getHeaderTimestamp() {
            return headerTimestamp;
        }

        /**
         * Package scoped getter for the integer response code.
         * Apparently 1 indicates completion of a successful request.
         *
         * @return the response code ("code") from the header of the JSON.
         */
        int getResponseCode() {
            return responseCode;
        }

        /**
         * Package scoped getter for the header response message ("msg").
         *
         * @return The response message from the JSON header.
         */
        String getResponseMessage() {
            return responseMessage;
        }

        /**
         * String representation to print out values of each instance variable.
         *
         * @return the String representation of the instance variables in this class.
         */
        @Override
        public String toString() {
            return "{ts=" + headerTimestamp + "|code=" + responseCode + "|msg=" + responseMessage + "}";
        }
    }
}

