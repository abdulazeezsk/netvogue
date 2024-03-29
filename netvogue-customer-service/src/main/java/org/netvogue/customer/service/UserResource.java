package org.netvogue.customer.service;

import static org.netvogue.rest.assertions.Assert.assertNotEmpty;
import static org.netvogue.rest.assertions.Assert.assertNotNull;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.netvogue.customer.service.converter.Converter;
import org.netvogue.customer.service.converters.input.LinesheetDetailsToLinesheetConverter;
import org.netvogue.customer.service.converters.input.LookBookDetailsToLookBookConverter;
import org.netvogue.customer.service.converters.input.StyleDetailsToStyleConverter;
import org.netvogue.customer.service.converters.input.UserRepresentationToUserConverter;
import org.netvogue.customer.service.converters.input.UserToUserRepresentationConverter;
import org.netvogue.customer.service.representations.LinesheetRepresentation;
import org.netvogue.customer.service.representations.LookbookRepresentation;
import org.netvogue.customer.service.representations.StyleRepresentation;
import org.netvogue.customer.service.representations.UserRepresentation;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.netvogue.ecommerce.persistence.StyleDao;
import org.netvogue.ecommerce.persistence.UserDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Suman : Need to add exception handling. I am planning to write generic module which can be used by all rest services.
 */
@Path("/users")
public class UserResource {

  private UserDao userDao;

  private LookbookDao lookbookDao;

  private LinesheetDao linesheetDao;

  private StyleDao styleDao;

  private Converter<UserRepresentation, User> userRepToUserconverter = new UserRepresentationToUserConverter();

  private Converter<User, UserRepresentation> userToUserRepconverter = new UserToUserRepresentationConverter();

  @Context
  private UriInfo uriInfo;

  @POST
  @Path("/{userName}/authenticate")
  @Consumes("application/json")
  @Produces("application/json")
  public Response authenticate(@PathParam("userName")
  final String userName, final String body) throws Exception {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");

    ObjectMapper mapper = new ObjectMapper();

    mapper.readValue(new StringReader(body), new TypeReference<Map<String, String>>() {
    });

    System.out.println("Authenticate username" + userName);
    User user = userDao.getActiveUser(userName);

    Map<String, String> requiredUserProps = new HashMap<String, String>();
    requiredUserProps.put("password", user.getEncodedPassword());
    requiredUserProps.put("salt", String.valueOf(user.getSalt()));
    requiredUserProps.put("role", user.getRole().toString());

    StringWriter writer = new StringWriter();

    mapper.writeValue(writer, requiredUserProps);
    System.out.println("Sent user details for authentication" + userName);
    return Response.ok(writer.toString()).build();
  }

  @PUT
  @Consumes("application/json")
  @Produces("application/json")
  public Response register(final String userDetails) throws Exception {

    assertNotNull(userDetails, "userDetails can not be null");
    assertNotEmpty(userDetails, "userDetails can not be empty");

    ObjectMapper mapper = new ObjectMapper();

    UserRepresentation userRep = mapper.readValue(new StringReader(userDetails), UserRepresentation.class);

    User user = userRepToUserconverter.convert(userRep);

    userDao.addUser(user);

    return Response.status(201).build();
  }

  @GET
  @Path("/{userName}/")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findUserDetails(@PathParam("userName")
  final String userName) throws Exception {

    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");

    ObjectMapper mapper = new ObjectMapper();

    UserRepresentation rep = userToUserRepconverter.convert(userDao.getActiveUser(userName));

    StringWriter writer = new StringWriter();

    mapper.writeValue(writer, rep);

    return Response.ok(writer.toString()).build();
  }

  @POST
  @Path("/{userName}/lookbooks")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addLookbook(@PathParam("userName") final String userName, final LookbookRepresentation lookbookRep) throws Exception {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    LookBookDetailsToLookBookConverter converter = new LookBookDetailsToLookBookConverter();
    Lookbook lookbook = converter.convert(lookbookRep);
    lookbookDao.addLookbook(lookbook);
    /**
     * Suman: returning dummy id (1), but we need to return real id generated by mongo. I am working on it.
     */
    return Response.created(uriInfo.getBaseUriBuilder().path("1").build()).build();
  }

  @POST
  @Path("/{userName}/linesheets")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addLinesheet(@PathParam("userName") final String userName, final LinesheetRepresentation linesheetRep) throws Exception {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");

    LinesheetDetailsToLinesheetConverter converter = new LinesheetDetailsToLinesheetConverter();
    Linesheet linesheet = converter.convert(linesheetRep);
    linesheetDao.addLinesheet(linesheet);

    /**
     * Suman TODO: returning dummy id (1), but we need to return real id generated by mongo. I am working on it.
     */
    return Response.created(uriInfo.getBaseUriBuilder().path("1").build()).build();
  }

  @POST
  @Path("/{userName}/lookbooks/{lookbookId}/styles")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addStyleToLookbook(@PathParam("userName") final String userName, @PathParam("lookbookId")
                                     final String lookbookId, final StyleRepresentation styleRep) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(lookbookId, "lookbookId can not be null");
    assertNotEmpty(lookbookId, "lookbookId can not be empty");

    StyleDetailsToStyleConverter converter = new StyleDetailsToStyleConverter();
    Style style = converter.convert(styleRep);
    styleDao.addStyleToLookbook(lookbookId, style);

    /**
     * Suman TODO: returning dummy id (1), but we need to return real id generated by mongo. I am working on it.
     */
    return Response.created(uriInfo.getBaseUriBuilder().path("1").build()).build();
  }

  @POST
  @Path("/{userName}/linesheets/{linesheetId}/styles")
  @Consumes("application/json")
  @Produces("application/json")
  public Response addStyleToLinesheet(@PathParam("userName") final String userName, @PathParam("linesheetId")
                                      final String linesheetId, final StyleRepresentation styleRep) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(linesheetId, "linesheetId can not be null");
    assertNotEmpty(linesheetId, "linesheetId can not be empty");

    StyleDetailsToStyleConverter converter = new StyleDetailsToStyleConverter();
    Style style = converter.convert(styleRep);
    styleDao.addStyleToLookbook(linesheetId, style);

    /**
     * Suman TODO: returning dummy id (1), but we need to return real id generated by mongo. I am working on it.
     */
    return Response.created(uriInfo.getBaseUriBuilder().path("1").build()).build();
 }

  @DELETE
  @Path("/{userName}/linesheets/{linesheetId}/styles/{styleId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteStyleFromLinesheet(@PathParam("userName")
  final String userName, @PathParam("styleId")
  final String styleId, @PathParam("linesheetId")
  final String linesheetId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(linesheetId, "linesheetId can not be null");
    assertNotEmpty(linesheetId, "linesheetId can not be empty");
    assertNotNull(styleId, "styleId can not be null");
    assertNotEmpty(styleId, "styleId can not be empty");

    return null;
  }

  @POST
  @Path("/{userName}/lookbooks/{lookbookId}/styles/{styleId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteStyleFromLookbook(@PathParam("userName")
  final String userName, @PathParam("styleId")
  final String styleId, @PathParam("lookbookId")
  final String lookbookId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(lookbookId, "lookbookId can not be null");
    assertNotEmpty(lookbookId, "lookbookId can not be empty");
    assertNotNull(styleId, "styleId can not be null");
    assertNotEmpty(styleId, "styleId can not be empty");

    return null;
  }

  @DELETE
  @Path("/{userName}/lookbooks/{lookbookId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteLookbook(@PathParam("userName")
  final String userName, @PathParam("lookbookId")
  final String lookbookId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(lookbookId, "lookbookId can not be null");
    assertNotEmpty(lookbookId, "lookbookId can not be empty");

    return null;
  }

  @DELETE
  @Path("/{userName}/linesheets/{linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response deleteLinesheet(@PathParam("userName")
  final String userName, @PathParam("linesheetId")
  final String linesheetId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(linesheetId, "linesheetId can not be null");
    assertNotEmpty(linesheetId, "linesheetId can not be empty");

    return null;
  }

  @GET
  @Path("/{userName}/lookbooks/")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findLookBooksByUser(@PathParam("userName")
  final String userName) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");

    return null;
  }

  @GET
  @Path("/{userName}/lookbooks/{lookbookId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findLookBookById(@PathParam("userName")
  final String userName, @PathParam("lookbookId")
  final String lookbookId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(lookbookId, "lookbookId can not be null");
    assertNotEmpty(lookbookId, "lookbookId can not be empty");

    return null;
  }

  @GET
  @Path("/{userName}/linesheets/{linesheetId}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findLinesheetById(@PathParam("userName")
  final String userName, @PathParam("linesheetId")
  final String linesheetId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(linesheetId, "linesheetId can not be null");
    assertNotEmpty(linesheetId, "linesheetId can not be empty");

    return null;
  }

  @GET
  @Path("/{userName}/linesheets/{linesheetId}/styles")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findStylesByLinesheet(@PathParam("userName")
  final String userName, @PathParam("linesheetId")
  final String linesheetId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(linesheetId, "linesheetId can not be null");
    assertNotEmpty(linesheetId, "linesheetId can not be empty");

    return null;
  }

  @GET
  @Path("/{userName}/lookbooks/{lookbookId}/styles")
  @Consumes("application/json")
  @Produces("application/json")
  public Response findStylesByLookbook(@PathParam("userName")
  final String userName, @PathParam("lookbookId")
  final String lookbookId) {
    assertNotNull(userName, "userName can not be null");
    assertNotEmpty(userName, "userName can not be empty");
    assertNotNull(lookbookId, "lookbookId can not be null");
    assertNotEmpty(lookbookId, "lookbookId can not be empty");

    return null;
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(final UserDao userDao) {
    this.userDao = userDao;
  }

  public LookbookDao getLookbookDao() {
    return lookbookDao;
  }

  public void setLookbookDao(final LookbookDao lookbookDao) {
    this.lookbookDao = lookbookDao;
  }

  public LinesheetDao getLinesheetDao() {
    return linesheetDao;
  }

  public void setLinesheetDao(final LinesheetDao linesheetDao) {
    this.linesheetDao = linesheetDao;
  }

  public StyleDao getStyleDao() {
    return styleDao;
  }

  public void setStyleDao(final StyleDao styleDao) {
    this.styleDao = styleDao;
  }

  public Converter<UserRepresentation, User> getUserRepToUserconverter() {
    return userRepToUserconverter;
  }

  public void setUserRepToUserconverter(final Converter<UserRepresentation, User> userRepToUserconverter) {
    this.userRepToUserconverter = userRepToUserconverter;
  }

  public Converter<User, UserRepresentation> getUserToUserRepconverter() {
    return userToUserRepconverter;
  }

  public void setUserToUserRepconverter(final Converter<User, UserRepresentation> userToUserRepconverter) {
    this.userToUserRepconverter = userToUserRepconverter;
  }

}
