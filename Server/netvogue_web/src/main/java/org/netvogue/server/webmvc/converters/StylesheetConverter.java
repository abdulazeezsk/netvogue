package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.common.Constants;
import org.netvogue.server.webmvc.domain.Stylesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StylesheetConverter /*implements Converter<org.netvogue.server.neo4japi.domain.Stylesheet, Stylesheet>*/ {

	@Autowired
	private UploadManager uploadManager;
	
	public Stylesheet convert(org.netvogue.server.neo4japi.domain.Stylesheet source, String username) {
		Stylesheet newSheet = new Stylesheet();
		newSheet.setGalleryid(source.getStylesheetid());
		newSheet.setGalleryname(source.getStylesheetname());
		if(null == source.getProfilePicLink()) {
			newSheet.setGallerypic(Constants.STYLESHEET_DefaultPic);
		} else {
			String thumblink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, 
					Size.SThumb, username);
			newSheet.setGallerypic(thumblink);
			String leftlink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, 
					Size.SLeft, username);
			newSheet.setLeftpic(leftlink);
		}
		newSheet.setCategory(source.getProductcategory().getProductLine().getDesc());
		return newSheet;
	}

}
