package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Collection;

public interface CollectionService {

	public ResultStatus SaveCollection(Collection newCollection, StringBuffer error);
	
	public Collection getCollection(String collectionId);
	public ResultStatus editCollection(String collectionid, String seasonname, String desc, StringBuffer error);
	public ResultStatus editCollectionSeasonName(String collectionId, String seasonname, StringBuffer error);
	public ResultStatus setProfilepic(String collectionid, String uniqueid, StringBuffer error);
	public ResultStatus deleteCollection(String collectionId, Iterable<String> photosids, StringBuffer error);
	
	public Iterable<CollectionPhotoData> getPhotos(String collectionId, int pagenumber);
	public Iterable<CollectionPhotoData> searchPhotoByName(Collection printCampaign, String name, int pagenumber);
	public Iterable<CollectionPhotoData> searchPhotoByName(String collectionId, String name, int pagenumber);
	public ResultStatus editPhotoInfo(String photoId, String seasonname, String desc, StringBuffer error);
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error);
	//public ResultStatus editPhotoSeasonName(String photoId, String name, String error);
	public ResultStatus editPhotoDescription(String photoId, String desc, StringBuffer error);
	public ResultStatus deletePhoto(String photoId, StringBuffer error);
}
