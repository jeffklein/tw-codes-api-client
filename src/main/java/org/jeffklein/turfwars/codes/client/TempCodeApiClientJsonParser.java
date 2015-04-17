package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Use generics to deserialize some of Nick's JSON to an arbitrary Object type.
 */
public class TempCodeApiClientJsonParser {
    public <T> T deserializeJsonStream(InputStream inputStream, TypeReference<T> typeReference) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return (T) mapper.readValue(inputStream, typeReference);
        } catch (IOException ioe) {
            throw new TurfWarsApiException("An error occurred while deserializing JSON response to a POJO.", ioe);
        }
    }
}
