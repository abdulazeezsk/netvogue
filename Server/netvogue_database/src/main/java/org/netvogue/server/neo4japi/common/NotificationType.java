package org.netvogue.server.neo4japi.common;

public enum NotificationType {
	NETWORK_REQUEST,
	//ADD_COLLECTION_REQUEST_TO_BOUTIQUE,
	ADD_COLLECTION_REQUEST_TO_BRAND,
	ORDER_ADD_REQUEST,
	ORDER_REJECT,
	ORDER_CONFIRMATION,
	BRAND_LISTED //This would be sent to Brand id any Boutique list his brand as carried. Brand can raise complaint if he finds 
				 // Boutique as spam
}
