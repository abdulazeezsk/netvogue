package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.repository.GalleryRepository;
import org.netvogue.server.neo4japi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class UserServiceImpl implements UserService{
	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired UserRepository		userRepo;
	@Autowired GalleryRepository	galleryRepo;
	
	public ResultStatus SaveUser(User user, String error){
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(user);
			System.out.println("Updated User Successfully");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + user.getEmail() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus ValidateEmailAndId(String email, Long id) {
		if(email.isEmpty()) {
			return ResultStatus.FAILURE;
		}
		User b = userRepo.findByemailAndId(email, id);
		if(null == b) {
			return ResultStatus.SUCCESS;
		}
		return ResultStatus.USER_EXISTS;
	}
	
	public ResultStatus SaveGallery(Gallery newGallery, String error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newGallery);
			System.out.println("Saved Gallery Successfully with Photos:" + newGallery.getPhotosAdded().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newGallery.getGalleryname() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<Gallery> GetGalleries(User user) {
		if(null != user) {
			return userRepo.getGalleries(user.getUsername());
		}
		return null;
	}
	
	public Gallery GetGallery(String galleryId) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return galleryRepo.getGallery(galleryId);
	}
	
	public Iterable<Photo> GetPhotos(String galleryId) {
		if(!galleryId.isEmpty()) {
			return galleryRepo.getPhotos(galleryId);
		}
		return null;
	}
}
