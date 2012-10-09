package org.netvogue.server.webmvc.converters;

import org.netvogue.server.webmvc.domain.Network;
import org.springframework.core.convert.converter.Converter;

public class NetworkConverter implements Converter<org.netvogue.server.neo4japi.domain.Network, Network> {

	public Network convert(org.netvogue.server.neo4japi.domain.Network source) {
		Network network = new Network();
		
		network.setNetworkstatus(source.getStatus().toString());
		return network;
	}

}
