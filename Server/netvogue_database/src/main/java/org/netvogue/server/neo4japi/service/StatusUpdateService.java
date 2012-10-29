package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.StatusUpdate;

public interface StatusUpdateService {
	Iterable<StatusUpdate> getMyStatusUpdates(String username, int pagenumber);
	Iterable<StatusUpdateData> getAllStatusUpdates(String username, int pagenumber);
	
	StatusUpdate newStatusUpdate(String username, String statusupdate, StringBuffer error);
	ResultStatus editStatusUpdate(String id, String message, StringBuffer error);
	ResultStatus deleteStatusUpdate(String id, StringBuffer error);
	
}
