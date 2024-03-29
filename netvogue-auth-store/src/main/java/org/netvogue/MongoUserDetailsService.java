package org.netvogue;

import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.util.Arrays;

public class MongoUserDetailsService implements UserDetailsService, Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3057104547767816568L;

  private UserDao userDao;

  public MongoUserDetailsService(final UserDao userDao) {
    this.userDao = userDao;
  }

  public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
    final User user = userDao.getActiveUser(userName);
    GrantedAuthority authority = new GrantedAuthority() {

      private static final long serialVersionUID = 1L;

      public String getAuthority() {
        return user.getRole().toString();
      }
    };

    NetvogueUserDetails userDetails = new NetvogueUserDetails(user.getUsername(), user.getEncodedPassword(),
        user.getSalt(), user, Arrays.asList(authority));
    return userDetails;
  }

}
