package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.StatusUpdate;

public interface StatusUpdateService {
	Iterable<StatusUpdate> getMyStatusUpdates(String username);
	Iterable<StatusUpdate> getAllStatusUpdates(String username);
	
	ResultStatus newStatusUpdate(String username, String statusupdate, String error);
	ResultStatus editStatusUpdate(String id, String message, String error);
	ResultStatus deleteStatusUpdate(String id, String error);
	
}
