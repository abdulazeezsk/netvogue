package org.netvogue.server.neo4japi.service;

import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Network;
import org.netvogue.server.neo4japi.domain.Notification;
import org.netvogue.server.neo4japi.domain.User;

public interface NetworkService {
	ResultStatus SaveNetwork(Network network, String error); //This can be used for creating, confirmation, deleting network
	Iterable<Network> getNetworks(String username, boolean onlyconfirmed, int pagenumber);

	User CreateNetwork(User userBy, String userTo, Notification notification, String error);
	ResultStatus CreateNetwork(String userBy, String userTo, String error);
	//Params order doesn't matter, as we can traverse in any direction and we only need to set relationship type
	ResultStatus ConfirmNetwork(User confirmedBy, String confirmTo, Notification notification, String error);
	//ResultStatus ConfirmNetwork(String usernameBy, String usernameTo, Notification notification,String error);
	ResultStatus DiscardNetwork(String usernameBy, String usernameTo, String error);
	ResultStatus DeleteNetwork(String usernameBy, String usernameTo, String error);
	ResultStatus BlockNetwork(String usernameBy, String usernameTo, String error);
	ResultStatus UnblockNetwork(String usernameBy, String usernameTo, String error);
}
