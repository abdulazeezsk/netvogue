package org.netvogue.server.webmvc.converters;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netvogue.server.webmvc.domain.StatusUpdate;
import org.springframework.core.convert.converter.Converter;

public class StatusUpdateConverter implements Converter<org.netvogue.server.neo4japi.domain.StatusUpdate, StatusUpdate> {

	public StatusUpdate convert(org.netvogue.server.neo4japi.domain.StatusUpdate source) {
		StatusUpdate response = new StatusUpdate();
		
		response.setStatusid(source.getStatusid());
		response.setStatus(source.getStatusUpdate());
		Date postedDate = source.getPostedDate();
		if(null != postedDate) {
			Format formatter = new SimpleDateFormat("MM/dd/yyyy");
			String postdate = formatter.format(postedDate);
			response.setPosteddate(postdate);
		}
		return response;
	}

}
