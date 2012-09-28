package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;

public interface EditorialService {

	public ResultStatus SaveEditorial(Editorial newEditorial, String error);
	
	public Editorial getEditorial(String editorialId);
	public ResultStatus editEditorial(String editorialid, String name, String desc, String error);
	public ResultStatus editEditorialName(String editorialId, String name, String error);
	public ResultStatus deleteEditorial(String editorialId, String error);
	
	public Iterable<EditorialPhoto> getPhotos(String editorialId);
	public Iterable<EditorialPhoto> searchPhotoByName(Editorial editorial, String name);
	public Iterable<EditorialPhoto> searchPhotoByName(String editorialId, String name);
	public ResultStatus editPhotoInfo(String photoId, String name, String desc, String error);
	public ResultStatus editPhotoName(String photoId, String name, String error);
	public ResultStatus editPhotoDescription(String photoId, String name, String error);
	public ResultStatus deletePhoto(String photoId, String error);
}
