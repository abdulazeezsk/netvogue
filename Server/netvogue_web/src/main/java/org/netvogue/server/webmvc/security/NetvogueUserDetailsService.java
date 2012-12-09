package org.netvogue.server.webmvc.security;

import org.netvogue.server.neo4japi.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface NetvogueUserDetailsService extends UserDetailsService{
	public User getUserFromSession();
}
