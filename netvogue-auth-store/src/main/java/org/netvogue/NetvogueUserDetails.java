package org.netvogue;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class NetvogueUserDetails extends User {


  private static final long serialVersionUID = 6468055483876432771L;

  private long salt;

  private org.netvogue.ecommerce.domain.model.User domainUser;

  public NetvogueUserDetails(final String username, final String password, final long salt, final org.netvogue.ecommerce.domain.model.User domainUser, final Collection<? extends GrantedAuthority> authorities) {
    super(username, password, true, true, true, true, authorities);
    this.salt = salt;
    this.domainUser = domainUser;
}


  public NetvogueUserDetails(final String username, final String password, final long salt, final Collection<? extends GrantedAuthority> authorities) {
      super(username, password, true, true, true, true, authorities);
      this.salt = salt;
  }


  public NetvogueUserDetails(final String username, final String password, final boolean enabled, final boolean accountNonExpired,
                             final boolean credentialsNonExpired, final boolean accountNonLocked, final long salt,
                             final Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.salt = salt;

  }


  public long getSalt() {
    return salt;
  }


  public void setSalt(final long salt) {
    this.salt = salt;
  }


  public org.netvogue.ecommerce.domain.model.User getDomainUser() {
    return domainUser;
  }


  public void setDomainUser(final org.netvogue.ecommerce.domain.model.User domainUser) {
    this.domainUser = domainUser;
  }


}
