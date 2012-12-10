package org.netvogue.server.webmvc.security;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.webmvc.rest.invoker.RestInvoker;
import org.netvogue.server.webmvc.rest.invoker.RestServiceContext;
import org.netvogue.server.webmvc.rest.invoker.UserRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MongoUserDetailsService /*implements NetvogueUserDetailsService, Serializable*/ {

	/**
   *
   */
	/*private static final long serialVersionUID = 3057104547767816568L;

	private RestInvoker restInvoker;

	private RestServiceContext restServiceContext;

	public MongoUserDetailsService(final RestInvoker restInvoker,
			final RestServiceContext restServiceContext) {
		this.restInvoker = restInvoker;
		this.restServiceContext = restServiceContext;
	}

	public UserDetails loadUserByUsername(final String userName)
			throws UsernameNotFoundException {
		String response = restInvoker.invokeGET(restServiceContext.getBaseUrl()
				+ "/" + userName, new HashMap<String, String>(),
				new HashMap<String, String>());

		ObjectMapper mapper = new ObjectMapper();

		final UserRepresentation user;

		try {
			user = mapper.readValue(new StringReader(response),
					UserRepresentation.class);
		} catch (final Exception e) {
			throw new RuntimeException(
					"exception while un-marshalling user details:", e);
		}

		GrantedAuthority authority = new GrantedAuthority() {

			private static final long serialVersionUID = 1L;

			public String getAuthority() {
				return user.getRole();
			}
		};

		NetvogueUserDetails userDetails = new NetvogueUserDetails(
				user.getUsername(), user.getPassword(), user.getSalt(), user,
				Arrays.asList(authority));
		return userDetails;
	}

	@Override
	public User getUserFromSession() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (null == authentication)
			return null;
		Object principal = authentication.getPrincipal();
		if (null == principal) {
			return null;
		}

		if (principal instanceof NetvogueUserDetails) {
			NetvogueUserDetails userDetails = (NetvogueUserDetails) principal;
			User user = new User();
			user.setUsername(userDetails.getDomainUser().getUsername());
			user.setEmail(userDetails.getDomainUser().getEmail());
			return user;
		}
		return null;
	}*/

}
