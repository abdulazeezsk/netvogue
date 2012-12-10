package org.netvogue.server.webmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;

public class NetvogueUserDetailsServiceImpl implements NetvogueUserDetailsService {

	@Autowired BoutiqueService 		boutiqueService;
	@Autowired ConversionService	conversionService; 

	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		final User user = boutiqueService.GetUser(loginName);
        if (user==null) throw new UsernameNotFoundException("Username not found: "+loginName);
        
		UserDetails userDetails = new NetvogueUserDetails(user);
		return userDetails;
	}
	
	public User getUserFromSession() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if(null == authentication) return null;
        Object principal = authentication.getPrincipal();
        if(null == principal) {
        	return null;
        }
        
        if (principal instanceof NetvogueUserDetails) {
        	NetvogueUserDetails userDetails = (NetvogueUserDetails) principal;
            return userDetails.getUser();
        }
        return null;
    }
	
	public User register(Boutique boutiqueNew) {
		/*String email	= boutiqueNew.getEmail();
		String name		= boutiqueNew.getStoreName();
		String password	= boutiqueNew.getPassword();
		
		if (email==null || email.isEmpty()) throw new RuntimeException("No Email provided");
		if (name==null || name.isEmpty()) throw new RuntimeException("No name provided.");
        if (password==null || password.isEmpty()) throw new RuntimeException("No password provided.");
        
		Boutique newUser = null;
		if(ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())) {
        	boutiqueService.AddNewBoutique(conversionService.convert(boutiqueNew, Boutique.class));
		} else {
			throw new RuntimeException("Boutique already registered with : "+email);
		} */
        
        //setUserInSession(newUser);
        return null;
    }

    public void setUserInSession(User user) {
        SecurityContext context = SecurityContextHolder.getContext();
        NetvogueUserDetails userDetails = new NetvogueUserDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(),userDetails.getAuthorities());
        context.setAuthentication(authentication);
        System.out.println("User has been set");
    }
}
