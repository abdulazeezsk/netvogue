package org.netvogue.server.aws.core;

public enum ImageType {
	
	PROFILE_PIC					("profiles"			,new Size[]{Size.S1,Size.S2,Size.S3,Size.S4}),
	GALLERY						("gallery"			,new Size[]{Size.GThumb,Size.GAdd,Size.GLeft,Size.GMain}),
	PRINT_CAMPAIGN				("printcampaign"	,new Size[]{Size.PCThumb,Size.PCAdd,Size.PCLeft,Size.PCMain}),
	EDITORIAL					("editorial"		,new Size[]{Size.EThumb,Size.EAdd,Size.ELeft,Size.EMain}),
	COLLECTION					("collection"		,new Size[]{Size.CThumb,Size.CAdd,Size.CLeft,Size.CMain}),
	STYLE						("style"			,new Size[]{Size.SThumb,Size.SAdd,Size.SLeft,Size.SMain});
	
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
