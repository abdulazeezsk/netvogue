package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.neo4japi.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class GalleryServiceImpl implements GalleryService {
	
	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired GalleryRepository	galleryRepo;

	public ResultStatus SaveGallery(Gallery newGallery, StringBuffer error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newGallery);
			System.out.println("Saved Gallery Successfully with Photos:" + newGallery.getPhotosAdded().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newGallery.getGalleryname() + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Gallery GetGallery(String galleryId) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return galleryRepo.getGallery(galleryId);
	}
	
	public ResultStatus editGalleryName(String galleryId, String name, StringBuffer error) {
		try {
			System.out.println("Editing gallery with id" + galleryId + " - name: " + name);
			galleryRepo.editGalleryName(galleryId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing gallery" + galleryId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus deleteGallery(String galleryId, StringBuffer error)  {
		try {
			galleryRepo.deleteGallery(galleryId);
			System.out.println("deleted gallery:" + galleryId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting gallery:" + galleryId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus setProfilepic(String galleryid, String uniqueid, StringBuffer error) {
		try {
			galleryRepo.setProfilepic(galleryid, uniqueid);
			System.out.println("Cover pic has been set:" + galleryid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while setting gallery cover pic:" + galleryid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<Photo> GetPhotos(String galleryId) {
		if(!galleryId.isEmpty()) {
			return galleryRepo.getPhotos(galleryId);
		}
		return null;
	}
	
	public Iterable<Photo> searchPhotoByName(Gallery gallery, String name) {
		return searchPhotoByName(gallery.getGalleryid(), name);
	}
	
	public Iterable<Photo> searchPhotoByName(String galleryid, String name) {
		return galleryRepo.searchPhotosByName(galleryid, Utils.SerializeQueryParamForSearch(name));
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
			galleryRepo.editPhotoInfo(photoId, name, seasonname);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error) {
		try {
			galleryRepo.editPhotoName(photoId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoSeasonName(String photoId, String name, StringBuffer error) {
		try {
			galleryRepo.editPhotoSeasonName(photoId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
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
			galleryRepo.deletePhoto(photoId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting photo" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
}
