package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.Constants;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.StatusUpdate;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.repository.StatusUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class StatusUpdateServiceImpl implements StatusUpdateService {

	@Autowired Neo4jTemplate			neo4jTemplate;
	@Autowired StatusUpdateRepository	statusRepo;
	
	public ResultStatus SaveStatusUpdate(StatusUpdate newUpdate, StringBuffer error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newUpdate);
			System.out.println("Saved update Successfully:");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	@Override
	public Iterable<StatusUpdate> getMyStatusUpdates(String username, int pagenumber) {
		if(username.isEmpty())
			return null;
		return statusRepo.getMyStatusUpdates(username,
				Constants.UPDATEPAGE_LIMIT * pagenumber, Constants.UPDATEPAGE_LIMIT);
	}

	@Override
	public Iterable<StatusUpdateData> getAllStatusUpdates(String username, int pagenumber) {
		return statusRepo.getAllStatusUpdates(username, 
				Constants.UPDATEPAGE_LIMIT * pagenumber, Constants.UPDATEPAGE_LIMIT);
	}

	@Override
	/*public ResultStatus newStatusUpdate(String username, String statusupdate, String error) {
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
	}*/
	public StatusUpdate newStatusUpdate(String username, String statusupdate, StringBuffer error) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Creating new status update" + username);
			StatusUpdateData userupdate = statusRepo.getlatestStatusUpdate(username);
			if(null == userupdate) {
				System.out.println("no user found");
				error.append("No user found");
				//return ResultStatus.FAILURE;
				return null;
			}
			System.out.println("Got the latest update");
			StatusUpdate newUpdate = new StatusUpdate(statusupdate);
			User user = userupdate.getUser();
			if(null == user) {
				System.out.println("Something seriouslt wrong in adding new status update");
				error.append("Something seriously wrong in adding new status update");
				//return ResultStatus.FAILURE;
				return null;
			}
			newUpdate.setAddedBy(user);
			StatusUpdate oldupdate = userupdate.getUpdate();
			if(null != oldupdate) {
				newUpdate.setStatus(oldupdate);
				
			}
			System.out.println("Saving new status update");
			if(ResultStatus.FAILURE == SaveStatusUpdate(newUpdate, error)) {
				return null;
			}
			return newUpdate;
		} catch (Exception e) {
			error.append(e.toString());
			//return ResultStatus.FAILURE;
			return null;
		}
	}

	@Override
	public ResultStatus editStatusUpdate(String id, String message, StringBuffer error) {
		try {
			statusRepo.editStatusUpdate(id, message);
			return ResultStatus.SUCCESS;
		} catch (Exception e) {
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}

	@Override
	public ResultStatus deleteStatusUpdate(String id, StringBuffer error) {
		try {
			statusRepo.deleteStatusUpdate(id);
			return ResultStatus.SUCCESS;
		} catch (Exception e) {
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}

}
