package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Use generics to deserialize some of Nick's JSON to an arbitrary Object type.
 *
 * @author jeffklein
 */
public class TurfWarsApiClientJsonParser {
    /**
     * Use the Jackson JSON ObjectMapper to convert a JSON stream to
     * a POJO of the given generic type T.
     *
     * @param inputStream   the JSON stream we want to parse and map to a POJO
     * @param typeReference Jackson TypeReference class for Object mapping
     * @param <T>           the generic Object type that we will convert the JSON to.
     * @return an Object of Type T populated with the deserialized JSON data stream.
     */
    public <T> T deserializeJsonStream(InputStream inputStream, TypeReference<T> typeReference) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException ioe) {
            throw new TurfWarsApiClientException("An error occurred while deserializing JSON response to a POJO.", ioe);
        }
    }
}
