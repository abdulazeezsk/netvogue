package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;
import org.netvogue.server.neo4japi.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class EditorialServiceImpl implements EditorialService {

	@Autowired Neo4jTemplate			neo4jTemplate;
	@Autowired EditorialRepository		editorialRepo;

	public ResultStatus SaveEditorial(Editorial newEditorial, StringBuffer error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newEditorial);
			System.out.println("Saved Editorial Successfully with Photos:" + newEditorial.getPhotosAdded().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newEditorial.getEditorialname() + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Editorial getEditorial(String editorialid) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return editorialRepo.getEditorial(editorialid);
	}
	
	public ResultStatus editEditorial(String editorialid, String name, String desc, StringBuffer error) {
		try {
			editorialRepo.editEditorial(editorialid, name, desc);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing editorial" + editorialid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editEditorialName(String editorialid, String name, StringBuffer error) {
		try {
			editorialRepo.editEditorialName(editorialid, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing editorial" + editorialid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus setProfilepic(String editorialid, String uniqueid, StringBuffer error) {
		try {
			editorialRepo.setProfilepic(editorialid, uniqueid);
			System.out.println("Profile pic has been set for editorial:" + editorialid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while setting cover pic for editorial:" + editorialid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	public ResultStatus deleteEditorial(String editorialid, StringBuffer error)  {
		try {
			editorialRepo.deleteEditorial(editorialid);
			System.out.println("deleted editorial:" + editorialid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting editorial:" + editorialid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<EditorialPhoto> getPhotos(String editorialId) {
		if(!editorialId.isEmpty()) {
			return editorialRepo.getPhotos(editorialId);
		}
		return null;
	}
	
	public Iterable<EditorialPhoto> searchPhotoByName(Editorial editorial, String name) {
		return searchPhotoByName(editorial.getEditorialid(), name);
	}
	
	public Iterable<EditorialPhoto> searchPhotoByName(String editorialId, String name) {
		return editorialRepo.searchPhotosByName(editorialId, Utils.SerializePropertyParamForSearch(name));
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
			editorialRepo.editPhotoInfo(photoId, name, seasonname);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error) {
		try {
			editorialRepo.editPhotoName(photoId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoDescription(String photoId, String desc, StringBuffer error) {
		try {
			editorialRepo.editPhotoDescription(photoId, desc);
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
			editorialRepo.deletePhoto(photoId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting photo" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
}
