package org.netvogue.server.webmvc.converters;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.webmvc.common.Constants;
import org.netvogue.server.webmvc.domain.Linesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinesheetConverter /*implements Converter<org.netvogue.server.neo4japi.domain.Linesheet, Linesheet>*/ {

	@Autowired
	private UploadManager uploadManager;
	
	public Linesheet convert(org.netvogue.server.neo4japi.domain.Linesheet source, String username) {
		Linesheet newSheet = new Linesheet();
		newSheet.setGalleryid(source.getLinesheetid());
		newSheet.setGalleryname(source.getLinesheetname());
		String profilepic = source.getProfilePicLink();
		if(null == profilepic || profilepic.isEmpty()) {
			newSheet.setGallerypic(Constants.LINESHEET_DefaultPic);
		} else {
			String thumblink = uploadManager.getQueryString(profilepic, ImageType.STYLE, 
				Size.SThumb, username);
			newSheet.setGallerypic(thumblink);
			String leftlink = uploadManager.getQueryString(profilepic, ImageType.STYLE, 
					Size.SLeft, username);
			newSheet.setLeftpic(leftlink);
		}
		Category cat =  source.getProductcategory();
		if(null != cat)
			newSheet.setCategory(cat.getProductLine().getDesc());
		Date deliveryDate = source.getDeliveryDate();
		if(null != deliveryDate) {
			if(0 == deliveryDate.compareTo(new Date(0))) 
				newSheet.setDeliverydate(Constants.Immediate);
			else {
				Format formatter = new SimpleDateFormat("MM/dd/yyyy");
				String delivery = formatter.format(deliveryDate);
				newSheet.setDeliverydate(delivery);
			}
		}
		return newSheet;
	}
}
