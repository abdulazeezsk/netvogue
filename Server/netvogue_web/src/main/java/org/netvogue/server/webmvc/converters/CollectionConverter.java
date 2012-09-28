package org.netvogue.server.webmvc.converters;

import org.netvogue.server.webmvc.domain.Collection;
import org.springframework.core.convert.converter.Converter;

public class CollectionConverter implements Converter<org.netvogue.server.neo4japi.domain.Collection, Collection> {

	public Collection convert(org.netvogue.server.neo4japi.domain.Collection source) {
		Collection newCollection = new Collection();
		newCollection.setGalleryid(source.getCollectionid());
		newCollection.setGalleryname(source.getCollectionname());
		newCollection.setGallerypic(source.getProfilePicLink());
		newCollection.setGallerydesc(source.getDescription());
		return newCollection;
	}

}
