package org.jeffklein.turfwars.codes.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Bean representation of a typical temp code API response.
 */
public class TempCodeApiResponseJackson {

  @JsonProperty
  private Body body;

  @JsonProperty
  private Header header;

  @Override
  public String toString() {
    return "TempCodeApiResponseJackson:{header="+header+"|body="+body+"}";
  }
}

class Header {
  @JsonProperty
  private long ts;

  @JsonProperty
  private int code;

  @JsonProperty
  private String msg;

  @Override
  public String toString() {
    return "Header:{ts="+ts+"|code="+code+"|msg="+msg+"}";
  }
}

class Body {

  @JsonProperty
  private long timestamp;

  @JsonProperty
  private long next_update;

  @JsonProperty
  private List<Code> codes;

  @Override
  public String toString() {
    return "Body:{timestamp="+timestamp+"|next_update="+next_update+"|codes="+codes+"}";
  }
}

class Code {
  private long expires;
  private String code;

  public long getExpires() {
    return expires;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return "Code:{code="+code+"|expires="+expires+"}";
  }
}
