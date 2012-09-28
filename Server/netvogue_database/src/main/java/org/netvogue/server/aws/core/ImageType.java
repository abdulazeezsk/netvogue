package org.netvogue.server.aws.core;

public enum ImageType {
	
	PROFILE_PIC					("profiles"			,new Size[]{Size.S1,Size.S2,Size.S3,Size.S4}),
	GALLERY						("gallery"			,new Size[]{Size.GThumb,Size.GAdd,Size.GLeft}),
	PRINT_CAMPAIGN				("printcampaign"	,new Size[]{Size.PCThumb,Size.PCAdd,Size.PCLeft}),
	EDITORIAL					("editorial"		,new Size[]{Size.EThumb,Size.EAdd,Size.ELeft}),
	COLLECTION					("collection"		,new Size[]{Size.CThumb,Size.CAdd,Size.CLeft}),
	LINESHEETS_COVER_IMAGE		("linesheets"		,new Size[]{Size.S1}),
	STYLES						("styles"			,new Size[]{Size.S1}),
	STORES_PIC					("storesPic"		,new Size[]{Size.S1}),
	CAMPAIGN_COVER_IMAGE		("campaignCover"	,new Size[]{Size.S1}),
	CAMPAIGN					("campaign"			,new Size[]{Size.S1});
	
	private final Size[] sizes; // all required sizes
	private final String key; // in meters
    
	ImageType(String key,Size[] sizes) {
		this.key = key;
		this.sizes = sizes;
	}
	
	public String getKey() {
		return key;
	}
	
	
	public Size[] getSizes() {
		return sizes;
	}

}
