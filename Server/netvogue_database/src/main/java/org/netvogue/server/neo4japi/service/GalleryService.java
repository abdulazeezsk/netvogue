package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;

public interface GalleryService {

	public ResultStatus SaveGallery(Gallery newGallery, StringBuffer error);
	
	public Gallery GetGallery(String GalleryId);
	public ResultStatus editGalleryName(String galleryId, String name, StringBuffer error);
	public ResultStatus deleteGallery(String galleryId, StringBuffer error);
	public ResultStatus setProfilepic(String galleryid, String uniqueid, StringBuffer error);
	
	public Iterable<Photo> GetPhotos(String galleryId);
	public Iterable<Photo> searchPhotoByName(Gallery gallery, String name);
	public Iterable<Photo> searchPhotoByName(String galleryid, String name);
	public ResultStatus editPhotoInfo(String photoId, String name, String seasonname, StringBuffer error);
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error);
	public ResultStatus editPhotoSeasonName(String photoId, String name, StringBuffer error);
	public ResultStatus deletePhoto(String photoId, StringBuffer error);
	
}
