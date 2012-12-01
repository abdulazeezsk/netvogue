package org.netvogue.customer.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/user")
public class UserResource {

  private UserDao userDao;

  @POST
  @Path("authenticate")
  @Consumes("application/json")
  @Produces("application/json")
  public String authenticate(final String body) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    Map<String, String> map = mapper.readValue(new StringReader(body), new TypeReference<Map<String, String>>() {
    });

    User user = userDao.getActiveUser(map.get("userName"));

    Map<String, String> requiredUserProps = new HashMap<String, String>();
    requiredUserProps.put("userName", user.getUsername());
    requiredUserProps.put("password", user.getEncodedPassword());
    requiredUserProps.put("salt", String.valueOf(user.getSalt()));
    requiredUserProps.put("role", user.getRole().toString());

    StringWriter writer = new StringWriter();

    mapper.writeValue(writer, requiredUserProps);

    return writer.toString();
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }

}
