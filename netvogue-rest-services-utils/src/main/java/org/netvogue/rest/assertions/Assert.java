package org.netvogue.rest.assertions;

import org.netvogue.rest.exceptions.NetvogueBusinessException;

import javax.ws.rs.core.Response.Status;

public class Assert {

  public static void assertNotNull(final String param, final String message) {
    if (param == null) {
      throw new NetvogueBusinessException(message, Status.BAD_REQUEST);
    }
  }

  public static void assertNotEmpty(final String param, final String message) {
    if (param != null && param.isEmpty()) {
      throw new NetvogueBusinessException(message, Status.BAD_REQUEST);
    }
  }

}
