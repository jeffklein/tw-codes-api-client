package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jklein on 4/16/15.
 */
public class TempCodeApiClientJsonParser<T> {
/*  public T deserializeJsonStream(InputStream inputStream, TypeReference<T> typeReference) {
    T pojo;
    try {
      ObjectMapper mapper = new ObjectMapper();
      pojo = mapper.readValue(inputStream, typeReference {
      });
    } catch (IOException ioe) {
      throw new TurfWarsApiException("An error occurred while deserializing JSON response to a POJO. JSON message:\n"
          + response.getBody().getObject().toString(2), ioe);
    }
    return pojo;
  }*/
}
