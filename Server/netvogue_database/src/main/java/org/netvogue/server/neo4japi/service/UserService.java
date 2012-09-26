package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.neo4japi.domain.User;

public interface UserService {
	ResultStatus SaveUser(User user, String error);
	ResultStatus ValidateEmailAndId(String email, Long id);

	//Gallery related
	public ResultStatus SaveGallery(Gallery newGallery, String error);
	public Gallery GetGallery(String GalleryId);
	public Iterable<Gallery> GetGalleries(User user);
	public Iterable<Photo> GetPhotos(String galleryId);
	
	//Photos inside Gallery
	//public ResultStatus AddNewPhotos(Gallery gallery, String error); //Gallery where photos were added
}
