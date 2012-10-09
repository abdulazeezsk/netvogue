package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.NetworkStatus;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Network;
import org.netvogue.server.neo4japi.domain.Notification;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.repository.NetworkRepository;
import org.netvogue.server.neo4japi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class NetworkServiceImpl implements NetworkService {

	@Autowired NetworkRepository 	networkRepo;
	@Autowired UserRepository		userRepo;	
	@Autowired Neo4jTemplate		neo4jTemplate; 	 
	
	@Override
	public ResultStatus SaveNetwork(Network network, String error) {
		try {
			neo4jTemplate.save(network);
			System.out.println("Saved Network:");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while creating network" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	public ResultStatus SaveUser(User user, String error) {
		try {
			neo4jTemplate.save(user);
			System.out.println("Saved Network:");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while creating network" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	@Override
	public Iterable<Network> getNetworks(String username) {
		return networkRepo.getNetworks(username);
	}

	@Override
	public ResultStatus CreateNetwork(String userBy, String userTo, String error) {
		if(userBy.isEmpty() || userTo.isEmpty()) {
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			//networkRepo.CreateNetwork(userBy, userTo);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	//Again worst logic -- Three calls to database
	// 1- to check network 2-To get user 3-To save network
	//Can't we do this in one network -- work on networkRepo.CreateNetwork
	@Override
	public ResultStatus CreateNetwork(User userBy, String userTo, Notification notification, String error) {
		if(null == userBy || userTo.isEmpty()) {
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			System.out.println("Create network in DB");
			//networkRepo.CreateNetwork(userBy, userTo);
			User otherUser =  userRepo.findByusername(userTo);
			System.out.println("Got otheruser in DB" + userTo);
			if(null == otherUser) {
				System.out.println("Bad other username");
				error ="Bad other username";
				return ResultStatus.FAILURE;
			}
			System.out.println("Get network from DB" + userTo);
			Network network = networkRepo.getNetwork(userBy.getUsername(), userTo);
			System.out.println("Got network from DB" + userTo);
			if(null == network) {
				System.out.println("network is null" + userTo);
				network = new Network(userBy,otherUser);
			} else {
				System.out.println("network already exists" + userTo);
				if(network.getStatus() == NetworkStatus.BREAKUP) {
					network.setStatus(NetworkStatus.PENDING);
				} else {
					System.out.println("We shouldn't get any request in this case, something seriously wrong");
					error = "We shouldn't get any request in this case, something seriously wrong";
					return ResultStatus.FAILURE;
				}				
			}
			System.out.println("Create notification in DB");
			notification = new Notification(userBy);
			notification.setDesc("You got new network request");
			otherUser.addNotification(notification);
			System.out.println("Save notification in DB");
			//Change them to batch operations
			SaveUser(otherUser, error);
			return SaveNetwork(network, error);
		} catch(Exception e) {
			System.out.println("Error while creating network" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	//Again worst logic -- Three calls to database
	// 1- to confirm network 2-To get user 3-To save notification
	//Can't we do this in one network -- work on networkRepo.CreateNetwork
	@Override
	public ResultStatus ConfirmNetwork(User confirmedBy, String confirmTo, Notification notification, String error) {
		if(confirmedBy == null || confirmTo.isEmpty()) {
			System.out.println("Usernames cant be empty");
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			User otherUser =  userRepo.findByusername(confirmTo);
			System.out.println("found user:");
			if(null == otherUser) {
				System.out.println("bad user:");
				error ="Bad other username";
				return ResultStatus.FAILURE;
			}
			notification = new Notification(confirmedBy, NetworkStatus.CONFIRMED);
			notification.setDesc("Your network request is accepted");
			otherUser.addNotification(notification);
			networkRepo.ConfirmNetwork(confirmedBy.getUsername(), confirmTo);
			return SaveUser(otherUser, error);
			//networkRepo.ConfirmNetwork(usernameBy, usernameTo);
		} catch(Exception e) {
			System.out.println("Exception:" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	@Override
	public ResultStatus DiscardNetwork(String usernameBy, String usernameTo, String error) {
		if(usernameBy.isEmpty() || usernameTo.isEmpty()) {
			System.out.println("Usernames cant be empty");
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			networkRepo.DiscardNetwork(usernameBy, usernameTo);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("Exception:" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}

	@Override
	public ResultStatus DeleteNetwork(String usernameBy, String usernameTo, String error) {
		if(usernameBy.isEmpty() || usernameTo.isEmpty()) {
			System.out.println("Usernames cant be empty");
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			networkRepo.DeleteNetwork(usernameBy, usernameTo);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("Exception:" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	@Override
	public ResultStatus BlockNetwork(String usernameBy, String usernameTo, String error) {
		if(usernameBy.isEmpty() || usernameTo.isEmpty()) {
			System.out.println("Usernames cant be empty");
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			networkRepo.BlockNetwork(usernameBy, usernameTo);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("Exception:" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	@Override
	public ResultStatus UnblockNetwork(String usernameBy, String usernameTo, String error) {
		if(usernameBy.isEmpty() || usernameTo.isEmpty()) {
			System.out.println("Usernames cant be empty");
			error = "Usernames can't be empty";
			return ResultStatus.FAILURE;
		}
		try {
			networkRepo.UnblockNetwork(usernameBy, usernameTo);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("Exception:" + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
}
