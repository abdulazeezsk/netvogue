package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.Stylesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StylesheetConverter implements Converter<org.netvogue.server.neo4japi.domain.Stylesheet, Stylesheet> {

	@Autowired
	private UploadManager uploadManager;
	
	public Stylesheet convert(org.netvogue.server.neo4japi.domain.Stylesheet source) {
		Stylesheet newSheet = new Stylesheet();
		newSheet.setGalleryid(source.getStylesheetid());
		newSheet.setGalleryname(source.getStylesheetname());
		if(0 == source.getStyles().size()) {
			newSheet.setGallerypic(source.getProfilePicLink());
		} else {
			String thumblink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, Size.SThumb);
			newSheet.setGallerypic(thumblink);
		}
		newSheet.setCategory(source.getProductcategory().getProductLine().getDesc());
		String leftlink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, Size.SLeft);
		newSheet.setLeftpic(leftlink);
		return newSheet;
	}

}
