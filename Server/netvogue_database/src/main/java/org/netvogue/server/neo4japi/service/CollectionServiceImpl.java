package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.Collection;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.neo4japi.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class CollectionServiceImpl implements CollectionService {

	@Autowired Neo4jTemplate			neo4jTemplate;
	@Autowired CollectionRepository		collectionRepo;

	public ResultStatus SaveCollection(Collection newCollection, StringBuffer error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newCollection);
			System.out.println("Saved Collection Successfully with Photos:" + newCollection.getPhotosAdded().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newCollection.getCollectionname() + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Collection getCollection(String collectionid) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return collectionRepo.getCollection(collectionid);
	}
	
	public ResultStatus editCollection(String collectionid, String seasonname, String desc, StringBuffer error) {
		try {
			collectionRepo.editCollection(collectionid, seasonname, desc);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing Collection" + collectionid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editCollectionSeasonName(String collectionid, String seasonname, StringBuffer error) {
		try {
			collectionRepo.editCollectionSeasonName(collectionid, seasonname);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing Collection" + collectionid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus setProfilepic(String collectionid, String uniqueid, StringBuffer error) {
		try {
			collectionRepo.setProfilepic(collectionid, uniqueid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while setting profile pic for Collection" + collectionid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus deleteCollection(String collectionid, StringBuffer error)  {
		try {
			collectionRepo.deleteCollection(collectionid);
			System.out.println("deleted collection:" + collectionid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting collection:" + collectionid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<CollectionPhotoData> getPhotos(String collectionid) {
		if(!collectionid.isEmpty()) {
			return collectionRepo.getPhotos(collectionid);
		}
		return null;
	}
	
	public Iterable<CollectionPhotoData> searchPhotoByName(Collection collection, String seasonname) {
		return searchPhotoByName(collection.getCollectionid(), seasonname);
	}
	
	public Iterable<CollectionPhotoData> searchPhotoByName(String collectionid, String name) {
		return collectionRepo.searchPhotosByName(collectionid, Utils.SerializeQueryParamForSearch(name));
	}
	
	public ResultStatus editPhotoInfo(String photoId, String name, String seasonname, StringBuffer error) {
		if(null == photoId || photoId.isEmpty()) {
			error.append("photoid is empty");
			return ResultStatus.FAILURE;
		} else if(null == name || null == seasonname) {
			error.append("name/season name is empty");
			return ResultStatus.FAILURE;
		}
		try {
			collectionRepo.editPhotoInfo(photoId, name, seasonname);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error) {
		try {
			collectionRepo.editPhotoName(photoId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoDescription(String photoId, String desc, StringBuffer error) {
		try {
			collectionRepo.editPhotoDescription(photoId, desc);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo desc" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	
	public ResultStatus deletePhoto(String photoId, StringBuffer error)  {
		if(null == photoId || photoId.isEmpty()){
			error.append("Photoid is empty");
			return ResultStatus.FAILURE;
		}
		try {
			collectionRepo.deletePhoto(photoId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting photo" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
}
