package org.netvogue.customer.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.netvogue.customer.service.converters.Converter;
import org.netvogue.customer.service.converters.UserRepresentationToUserConverter;
import org.netvogue.customer.service.converters.UserToUserRepresentationConverter;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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


  @GET
  @Path("/${userName}/findDqetails")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findUserDetails(@PathParam("userName") final String userName) throws Exception {

    ObjectMapper mapper = new ObjectMapper();

    UserRepresentation rep = userToUserRepconverter.convert(userDao.getActiveUser(userName));

    StringWriter writer = new StringWriter();

    mapper.writeValue(writer, rep);

    return Response.ok(writer.toString()).build();
  }

  @POST
  @Path("/${userName}/addLookBook")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addLookbook(@PathParam("userName") final String userName, final String lookbookDetails) {

    return null;
  }

  @POST
  @Path("/${userName}/addLineSheet")
  @Consumes("application/json")
  @Produces("application/json")
 public Response addLinesheet(@PathParam("userName") final String userName, final String linesheetDetails) {

    return null;
 }

  @POST
  @Path("/${userName}/addStyleToLookbook/${lookbookId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addStyleToLookbook(@PathParam("userName") final String userName,
                                     @PathParam("lookbookId") final String lookbookId,
                                     final String styleDetails) {

   return null;
 }


  @POST
  @Path("/${userName}/addStyleToLinesheet/${linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
 public Response addStyleToLinesheet(@PathParam("userName") final String userName,
                                     @PathParam("linesheetId") final String linesheetId,
                                     final String styleDetails) {
   return null;
 }

  @POST
  @Path("/${userName}/deleteStyle/${styleId}/FromLinesheet/${linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteStyleFromLinesheet(@PathParam("userName") final String userName,
                                           @PathParam("styleId") final String styleId,
                                           @PathParam("linesheetId") final String linesheetId) {
   return null;
  }

  @POST
  @Path("/${userName}/deleteStyle/${styleId}/FromLookbook/${lookbookId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteStyleFromLookbook(@PathParam("userName") final String userName,
                                          @PathParam("styleId") final String styleId,
                                          @PathParam("lookbookId") final String lookbookId) {
   return null;
  }


  @POST
  @Path("/${userName}/deleteLookbook/${lookbookId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteLookbook(@PathParam("userName") final String userName,
                                 @PathParam("lookbookId") final String lookbookId) {
   return null;
  }


  @POST
  @Path("/${userName}/deleteLinesheet/${linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteLinesheet(@PathParam("userName") final String userName,
                                  @PathParam("linesheetId") final String lookbookId) {
   return null;
  }

  @GET
  @Path("/${userName}/findLookBooks")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findLookBooksByUser(@PathParam("userName")  final String userName) {

    return null;
  }

  @GET
  @Path("/${userName}/findLinesheets")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findLinesheetsByUser(@PathParam("userName")  final String userName) {

    return null;
  }

  @GET
  @Path("/${userName}/findStylesByLinesheet/${linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findStylesByLinesheet(@PathParam("userName")  final String userName, @PathParam("linesheetId")  final String linesheetId) {

    return null;
  }

  @GET
  @Path("/${userName}/findStylesByLookbook/${linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findStylesByLookbook(@PathParam("userName")  final String userName, @PathParam("linesheetId")  final String linesheetId) {

    return null;
  }


  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }


}
