package org.netvogue.customer.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.netvogue.customer.service.converters.Converter;
import org.netvogue.customer.service.converters.UserRepresentationToUserConverter;
import org.netvogue.customer.service.converters.UserToUserRepresentationConverter;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Suman : Need to add exception handling. I am planning to write generic module which can be used by all rest services.
 */
@Path("/user")
public class UserResource {

  private UserDao userDao;

  private Converter<UserRepresentation, User> userRepToUserconverter = new UserRepresentationToUserConverter();
  private Converter<User, UserRepresentation> userToUserRepconverter = new UserToUserRepresentationConverter();

  @POST
  @Path("authenticate")
  @Consumes("application/json")
  @Produces("application/json")
  public Response authenticate(final String body) throws Exception {
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

    return Response.ok(writer.toString()).build();
  }

  @POST
  @Path("register")
  @Consumes("application/json")
  @Produces("application/json")
  public Response register(final String body) throws Exception {

    ObjectMapper mapper = new ObjectMapper();

    UserRepresentation userRep = mapper.readValue(new StringReader(body), UserRepresentation.class);

    User user = userRepToUserconverter.convert(userRep);

    userDao.addUser(user);

    return Response.status(201).build();
  }


  @POST
  @Path("find-details")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findUserDetails(final String body) throws Exception {

    ObjectMapper mapper = new ObjectMapper();

    Map<String, String> map = mapper.readValue(new StringReader(body), new TypeReference<Map<String, String>>() {
    });


    UserRepresentation rep = userToUserRepconverter.convert(userDao.getActiveUser(map.get("userName")));

    StringWriter writer = new StringWriter();

    mapper.writeValue(writer, rep);

    return Response.ok(writer.toString()).build();
  }


  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }


}
