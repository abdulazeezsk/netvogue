package org.netvogue.server.webmvc.controllers;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.EmailNotifications;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountSettingsController {

  @Autowired
  NetvogueUserDetailsService userDetailsService;

  @Autowired
  UserService userService;

  @RequestMapping(value = "/{userId}/unsubscribe", method = RequestMethod.GET)
  public String unsubscribeNotifications(@PathVariable
  String userId, @RequestParam
  String nid) throws Exception {
    String redirectPage = null;
    StringBuffer error = null;
    System.out.println("User Id in AccountSettingsController: " + userId + " nid: " + nid);

    User user = userDetailsService.getUserFromSession();
    if (null != user && userId.equals(user.getUserId())) {
      redirectPage = "Account_Settings";
    } else {
      user = userService.getUserByUserId(userId);
      // This page needs to be designed - Pavan
      if (null != user) {
        redirectPage = "Notification";
      } else {
        return "Error";
      }
    }
    EmailNotifications emailNotifications = user.getEmailnotifications();
    if(null == emailNotifications) {
      emailNotifications = new EmailNotifications();
    }
    //this code has to be changed to use pid request parameter.
    //left it this way as there is only letter type which can be unsubscribed.
    emailNotifications.setNetworkRequestFlag(false);
    user.setEmailnotifications(emailNotifications);
    error = new StringBuffer();
    if (ResultStatus.FAILURE == userService.SaveUser(user, error)) {
      redirectPage = "Error";
    }
    return redirectPage;
  }

}
