package org.netvogue.server.webmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggerInterceptor implements HandlerInterceptor {

	@Autowired
	NetvogueUserDetailsService userDetailsService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = userDetailsService.getUserFromSession();
		if (null != user) {
			String username = user.getUsername();
			if (null != username)
				MDC.put("uniqueid", username);
			else
				MDC.put("uniqueid", "Anonymous User");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
