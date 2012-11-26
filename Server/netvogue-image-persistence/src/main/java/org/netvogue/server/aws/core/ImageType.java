package org.netvogue.server.aws.core;

public enum ImageType {
	
	PROFILE_PIC					("profiles"			,new Size[]{Size.PThumb,Size.PTop}),
	GALLERY						("gallery"			,new Size[]{Size.GAdd,Size.GThumb,Size.GLeft,Size.GMain}),
	PRINT_CAMPAIGN				("printcampaign"	,new Size[]{Size.PCAdd,Size.PCThumb,Size.PCLeft,Size.PCMain}),
	EDITORIAL					("editorial"		,new Size[]{Size.EAdd,Size.EThumb,Size.ELeft,Size.EMain}),
	COLLECTION					("collection"		,new Size[]{Size.CAdd,Size.CThumb,Size.CLeft,Size.CMain}),
	STYLE						("style"			,new Size[]{Size.SAdd,Size.SThumb,Size.SLeft,Size.SMain});
	
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
