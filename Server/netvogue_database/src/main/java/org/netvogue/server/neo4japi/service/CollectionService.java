package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Collection;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;

public interface CollectionService {

	public ResultStatus SaveCollection(Collection newCollection, String error);
	
	public Collection getCollection(String collectionId);
	public ResultStatus editCollection(String collectionid, String seasonname, String desc, String error);
	public ResultStatus editCollectionSeasonName(String collectionId, String seasonname, String error);
	public ResultStatus deleteCollection(String collectionId, String error);
	
	public Iterable<CollectionPhoto> getPhotos(String collectionId);
	public Iterable<CollectionPhoto> searchPhotoByName(Collection printCampaign, String name);
	public Iterable<CollectionPhoto> searchPhotoByName(String collectionId, String name);
	public ResultStatus editPhotoInfo(String photoId, String seasonname, String desc, String error);
	public ResultStatus editPhotoName(String photoId, String name, String error);
	//public ResultStatus editPhotoSeasonName(String photoId, String name, String error);
	public ResultStatus editPhotoDescription(String photoId, String desc, String error);
	public ResultStatus deletePhoto(String photoId, String error);
}
