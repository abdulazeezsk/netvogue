package org.netvogue.server.mandrill.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.MandrillMessage;
import org.netvogue.server.mandrill.model.MandrillRecipient;
import org.netvogue.server.mandrill.model.MandrillTemplatedMessageRequest;
import org.netvogue.server.mandrill.model.MergeVar;
import org.netvogue.server.mandrill.model.TemplateContent;
import org.netvogue.server.mandrill.model.response.message.SendMessageResponse;
import org.netvogue.server.mandrill.request.MandrillMessagesRequest;
import org.netvogue.server.mandrill.request.MandrillRESTRequest;
import org.netvogue.server.neo4japi.domain.User;

public class EmailUtil {

  private static MandrillRESTRequest request = new MandrillRESTRequest();

  private static MandrillConfiguration config = new MandrillConfiguration();

  private static MandrillMessagesRequest messagesRequest = new MandrillMessagesRequest();

  private static ObjectMapper mapper = new ObjectMapper();

  private static HttpClient client;

  private static Properties props = new Properties();

  static {
    mandrillConfiguration();
  }

  public static void mandrillConfiguration() {
    try {
      props.load(EmailUtil.class.getClassLoader().getResourceAsStream("mandrill.properties"));
    } catch (IOException io) {
      System.out.println("Exception in reading applicaton properties: " + io.getMessage());
      config.setApiKey(props.getProperty("apiKey"));
      config.setApiVersion("1.0");
      config.setBaseURL("https://mandrillapp.com/api");
      request.setConfig(config);
      client = new DefaultHttpClient();
      request.setHttpClient(client);
      request.setObjectMapper(mapper);
      messagesRequest.setRequest(request);
    }

  }

  public static boolean sendConfirmationEmail(User user) {
    SendMessageResponse messageResponse = null;

    MandrillTemplatedMessageRequest request = new MandrillTemplatedMessageRequest();
    MandrillMessage message = new MandrillMessage();
    Map<String, String> headers = new HashMap<String, String>();
    message.setFrom_email(props.getProperty("email.from"));
    message.setFrom_name("Veawe Support");
    message.setHeaders(headers);
    message.setSubject("Registration Confirmation Email");
    MandrillRecipient[] recipients = new MandrillRecipient[] { new MandrillRecipient(user.getName(), user.getEmail()) };
    message.setTo(recipients);
    message.setTrack_clicks(true);
    message.setTrack_opens(true);
    String[] tags = new String[] { "tag1", "tag2", "tag3" };
    message.setTags(tags);
    request.setMessage(message);
    List<TemplateContent> content = new ArrayList<TemplateContent>();
    request.setTemplate_content(content);
    request.setTemplate_name("Registration Template");
    List<MergeVar> globalMergeVars = new ArrayList<MergeVar>();
    globalMergeVars.add(new MergeVar("USERNAME", user.getUsername()));
    String registrationURL = "http://localhost:8080/confirmRegisteration/" + user.getUserId();
    globalMergeVars.add(new MergeVar("REGISTRATION_URL", registrationURL));
    message.setGlobal_merge_vars(globalMergeVars);
    try {
      messageResponse = messagesRequest.sendTemplatedMessage(request);
      return true;
    } catch (RequestFailedException e) {
      // List<MessageResponse> messageResponses = messageResponse.getList();

      e.printStackTrace();

      return false;
    }

  }

}
