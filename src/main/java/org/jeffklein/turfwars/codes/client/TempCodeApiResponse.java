package org.jeffklein.turfwars.codes.client;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jeff
 * Date: 4/12/15
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TempCodeApiResponse {
  private Date timestamp;
  private Date nextUpdate;
  private List<TempCode> codes;

  public TempCodeApiResponse(Date timestamp, Date nextUpdate, List<TempCode> codes) {
    this.timestamp = timestamp;
    this.nextUpdate = nextUpdate;
    this.codes = codes;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Date getNextUpdate() {
    return nextUpdate;
  }

  public List<TempCode> getCodes() {
    return codes;
  }
}
