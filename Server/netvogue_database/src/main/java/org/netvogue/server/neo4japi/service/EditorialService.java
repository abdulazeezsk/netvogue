package org.netvogue.server.neo4japi.service;

import java.util.List;

import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;

public interface EditorialService {

	public ResultStatus SaveEditorial(Editorial newEditorial, StringBuffer error);
	
	public Editorial getEditorial(String editorialId);
	public ResultStatus editEditorial(String editorialid, String name, String desc, StringBuffer error);
	public ResultStatus editEditorialName(String editorialId, String name, StringBuffer error);
	public ResultStatus setProfilepic(String editorialid, String uniqueid, StringBuffer error);
	public List<String> deleteEditorial(String editorialId, StringBuffer error);
	
	public Iterable<EditorialPhoto> getPhotos(String editorialId, int pagenumber);
	public Iterable<EditorialPhoto> searchPhotoByName(Editorial editorial, String name, int pagenumber);
	public Iterable<EditorialPhoto> searchPhotoByName(String editorialId, String name, int pagenumber);
	public ResultStatus editPhotoInfo(String photoId, String name, String desc, StringBuffer error);
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error);
	public ResultStatus editPhotoDescription(String photoId, String name, StringBuffer error);
	public ResultStatus deletePhoto(String photoId, StringBuffer error);
}
