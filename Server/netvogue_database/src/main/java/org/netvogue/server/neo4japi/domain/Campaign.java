package org.netvogue.server.neo4japi.domain;

import java.util.List;

import org.netvogue.server.neo4japi.common.PrivacySetting;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Campaign {

	@GraphId
	Long id;
	
	@Indexed(indexType = IndexType.FULLTEXT, unique=true)
	String campaignId;
	
	@Indexed(indexName="campaign", indexType = IndexType.FULLTEXT)
	String Title;
	
	@Indexed(indexName="campaign", indexType = IndexType.FULLTEXT)
	String Desc;
	
	PrivacySetting privacySetting;
	
	String coverPic;
	
	List<String> imagesUploadedLinks;
}
