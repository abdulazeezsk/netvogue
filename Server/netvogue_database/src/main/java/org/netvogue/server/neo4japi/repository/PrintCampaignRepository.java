package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PrintCampaignRepository extends GraphRepository<PrintCampaign> {

	@Query( "START n=node:printcampaignid(printcampaignid={0}) RETURN n")
	PrintCampaign getPrintCampaign(String printcampaignid);
	
	@Query("START p = node:printcampaignid(printcampaignid={0}) SET p.printcampaignname = {1}, p.description = {2}")
	void editPrintCampaign(String printcampaignid, String name, String desc);
	
	@Query("START p = node:printcampaignid(printcampaignid={0}) SET p.printcampaignname = {1}")
	void editPrintCampaignName(String printcampaignid, String name);
	
	@Query("START p = node:printcampaignid(printcampaignid={0}) SET p.description = {1}")
	void editPrintCampaignDesc(String printcampaignid, String desc);
	
	@Query("START n=node:printcampaignid(printcampaignid={0}) MATCH n-[rels*0..]->p " +
			"FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n MATCH n<-[r]-() DELETE n, r")
	void deletePrintCampaign(String printcampaignid);
	
	@Query( "START n=node:printcampaignid(printcampaignid={0}) " +
			"MATCH n-[:PRINTCAMPAIGNPHOTO]->p RETURN p ORDER BY p.createdDate DESC")
	Iterable<PrintCampaignPhoto> getPhotos(String printcampaignid);
	
	@Query( "START n=node:printcampaignid(printcampaignid={0}) " +
			"MATCH n-[:PRINTCAMPAIGNPHOTO]->p WHERE p.printcampaignphotoname! =~ {1} " +
			"RETURN p ORDER BY p.createdDate DESC")
	Iterable<PrintCampaignPhoto> searchPhotosByName(String printcampaignid, String photoname);
	
	@Query("START p = node:printcampaignphotouniqueid(printcampaignphotouniqueid={0}) MATCH p-[r]-() DELETE p, r")
	void deletePhoto(String printcampaignphotouniqueid);
	
	@Query("START p = node:printcampaignphotouniqueid(printcampaignphotouniqueid={0}) " +
			"SET p.printcampaignphotoname = {1}, p.description = {2}")
	void editPhotoInfo(String printcampaignphotouniqueid, String printcampaignphotoName, String description);
	
	@Query("START p = node:printcampaignphotouniqueid(printcampaignphotouniqueid={0}) SET p.printcampaignphotoname = {1}")
	void editPhotoName(String printcampaignphotouniqueid, String printcampaignphotoname);
	
	@Query("START p = node:printcampaignphotouniqueid(printcampaignphotouniqueid={0}) SET p.description = {1}")
	void editPhotoDescription(String printcampaignphotouniqueid, String description);
}
