package org.netvogue.server.neo4japi.common;

public enum NetworkStatus {
	PENDING,	//Request Sent
	CONFIRMED, //Network request is confirmed
	DISCARD,   //It can be confirmed later, still shows in network page to confirm
	BREAKUP, //Delete, fresh request must be sent, doesn't come in network page
	BLOCK, //Cant sent any more request. Once unblocked, must go to CONFIRMED stage.
	NONE, 
	ADDEDTOPROFILE //Not required anymore
}
