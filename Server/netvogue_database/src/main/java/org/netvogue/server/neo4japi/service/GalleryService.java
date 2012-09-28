package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;

public interface GalleryService {

	public ResultStatus SaveGallery(Gallery newGallery, String error);
	
	public Gallery GetGallery(String GalleryId);
	public ResultStatus editGalleryName(String galleryId, String name, String error);
	public ResultStatus deleteGallery(String galleryId, String error);
	
	public Iterable<Photo> GetPhotos(String galleryId);
	public Iterable<Photo> searchPhotoByName(Gallery gallery, String name);
	public Iterable<Photo> searchPhotoByName(String galleryid, String name);
	public ResultStatus editPhotoInfo(String photoId, String name, String seasonname, String error);
	public ResultStatus editPhotoName(String photoId, String name, String error);
	public ResultStatus editPhotoSeasonName(String photoId, String name, String error);
	public ResultStatus deletePhoto(String photoId, String error);
	
}
