package org.netvogue.server.webmvc.rest.invoker;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import javax.ws.rs.core.MediaType;

import java.util.Map;

public class RestInvoker {

  private Client client;

  public RestInvoker(final Client client) {
    this.client = client;
  }

  public String invokeGET(final String url, final Map<String, String> headers, final Map<String, String> queryParams) {

    WebResource webResource = client.resource(url);

    ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RuntimeException("error while invoking : " + url + "[ status=" + response.getStatus() + "]");
    }

    String output = response.getEntity(String.class);

    return output;
  }

  public String invokePOST(final String url, final Map<String, String> headers, final String payload) {
    WebResource webResource = client.resource(url);

    Builder builder = webResource.accept(MediaType.APPLICATION_JSON_TYPE);

    for (Map.Entry<String, String> header : headers.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }

    ClientResponse response = builder.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, payload);

    return response.getEntity(String.class);
  }

}
