package org.netvogue.server.neo4japi.service;

import java.util.Date;
import java.util.UUID;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.StatusUpdate;
import org.netvogue.server.neo4japi.repository.StatusUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusUpdateServiceImpl implements StatusUpdateService {

	@Autowired StatusUpdateRepository	statusRepo;
	
	@Override
	public Iterable<StatusUpdate> getMyStatusUpdates(String username) {
		if(username.isEmpty())
			return null;
		return statusRepo.getMyStatusUpdates(username);
	}

	@Override
	public Iterable<StatusUpdate> getAllStatusUpdates(String username) {
		return statusRepo.getAllStatusUpdates(username);
	}

	@Override
	public ResultStatus newStatusUpdate(String username, String statusupdate, String error) {
		// TODO Auto-generated method stub
		try {
			String statusid = UUID.randomUUID().toString();
			Date createddate = new Date();
			statusRepo.newStatusUpdate(username, statusid, statusupdate, createddate);
			return ResultStatus.SUCCESS;
		} catch (Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	@Override
	public ResultStatus editStatusUpdate(String id, String message, String error) {
		try {
			statusRepo.editStatusUpdate(id, message);
			return ResultStatus.SUCCESS;
		} catch (Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	@Override
	public ResultStatus deleteStatusUpdate(String id, String error) {
		try {
			statusRepo.deleteStatusUpdate(id);
			return ResultStatus.SUCCESS;
		} catch (Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

}
