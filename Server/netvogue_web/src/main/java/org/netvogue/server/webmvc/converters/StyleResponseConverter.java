package org.netvogue.server.webmvc.converters;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.common.ProductLineSizes;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.StyleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StyleResponseConverter /*implements Converter<Style, StyleResponse>*/{

	@Autowired
	private UploadManager uploadManager;
	
	public StyleResponse convert(Style source, String username) {
		StyleResponse response = new StyleResponse();
		response.setStyleid(source.getStyleid());
		response.setStylename(source.getStylename());
		response.setStyleno(source.getStyleno());
		response.setFabrication(source.getFabrication());
		response.setDescription(source.getDescription());
		response.setPrice(source.getPrice());
		response.setAvailableColors(source.getAvailableColors());
		
		//Profile pic
		String thumbprofilepic =  uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, 
				Size.SThumb, username);
		String leftprofilelink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, 
				Size.SLeft, username);
		response.setProfilethumbpic(thumbprofilepic);
		response.setProfileleftpic(leftprofilelink);

		//Copy sizes
		Set<ProductLineSizes> availableSizes	= source.getAvailableSizes();
		Set<String>	sizes = new HashSet<String>();
		if(null != availableSizes) {
			for(ProductLineSizes size: availableSizes) {
				sizes.add(size.toString());
			}
			response.setAvailableSizes(sizes);
		}
		
		Set<PhotoWeb> imageslinks = new LinkedHashSet<PhotoWeb>();
		//Copy Images
		Set<String> images = source.getAvailableImages();
		if(null != images) {
			for(String image: images){
				String thumblink = uploadManager.getQueryString(image, ImageType.STYLE, Size.SThumb, username);
				String mainlink = uploadManager.getQueryString(image, ImageType.STYLE, username);
				String leftlink = uploadManager.getQueryString(image, ImageType.STYLE, Size.SLeft, username);
				String addlink = uploadManager.getQueryString(image, ImageType.STYLE, Size.SAdd, username);
				PhotoWeb imagelinks = new PhotoWeb(image, mainlink, thumblink, leftlink, addlink);
				imageslinks.add(imagelinks);
			}
			response.setAvailableImages(imageslinks);
		}
		return response;
	}

}
