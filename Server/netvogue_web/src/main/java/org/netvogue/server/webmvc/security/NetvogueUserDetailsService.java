package org.netvogue.server.webmvc.security;

import org.netvogue.server.webmvc.domain.BoutiqueNew;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.User;

public interface NetvogueUserDetailsService extends UserDetailsService{
	public User getUserFromSession();
	public User register(Boutique newBoutique);
	public void setUserInSession(User user);
}
