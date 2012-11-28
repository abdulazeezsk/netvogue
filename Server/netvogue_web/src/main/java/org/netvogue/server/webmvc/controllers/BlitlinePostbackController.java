package org.netvogue.server.webmvc.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.netvogue.server.blitline.model.response.BlitlineResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlitlinePostbackController {

	@RequestMapping(value = "bl/response", method = RequestMethod.POST)
	public void processPostbackResponse(
			@RequestBody BlitlineResponse blitlineResponse) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out
					.println("Enter this block please Post method ******************&&&&&&&&&&&&&&&&&&&");
			System.out.println("Time while receiving: "
					+ System.currentTimeMillis());
			System.out.println("Postback response: "
					+ mapper.writeValueAsString(blitlineResponse));
		} catch (Exception e) {
			System.out.println("Error in Blitline Postback controller");
			e.printStackTrace();
		}

	}
}
