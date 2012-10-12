/**
 * Author: marcbaechinger
 * Copyright 2011. Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.netvogue.server.webmvc.pusher;

import org.netvogue.server.webmvc.pusher.PusherUtil;
import java.net.URL;

/**
 * 
 * @author marcbaechinger
 */
public class PusherChannel {
    
    private PusherTransport transport;
    private String channelName;
    private final String pusherApplicationId 	= "29419";
    private final String pusherApplicationSecret= "7a3174eb11c6333328b8";
    private final String pusherApplicationKey	= "15b40a25fa57725931ad";
    
    public PusherChannel(String channelName) {
        
        this.channelName = channelName;
        this.transport = new HttpClientPusherTransport();
    }
    
    /**
     * Delivers a message to the Pusher API without providing a socket_id
     * @param channel
     * @param event
     * @param jsonData
     * @return
     */
    public PusherResponse pushEvent(String event, String jsonData) throws PusherTransportException{
    	return pushEvent(event, jsonData, "");
    }
    
    /**
     * Delivers a message to the Pusher API
     * @param channel
     * @param event
     * @param jsonData
     * @param socketId
     * @return
     */
    public PusherResponse pushEvent(String event, String jsonData, String socketId) throws PusherTransportException{
    	//Build URI path
    	String uriPath = PusherUtil.buildURIPath(this.channelName, this.pusherApplicationId);
    	//Build query
    	String query = PusherUtil.buildQuery(event, jsonData, socketId, this.pusherApplicationKey);
    	//Generate signature
    	String signature = PusherUtil.buildAuthenticationSignature(uriPath, query, this.pusherApplicationSecret);
    	//Build URI
    	URL url = PusherUtil.buildURI(uriPath, query, signature);
        
        return this.transport.fetch(url, jsonData);
    }
}
