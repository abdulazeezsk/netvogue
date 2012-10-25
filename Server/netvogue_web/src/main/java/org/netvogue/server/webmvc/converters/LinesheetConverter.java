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
import org.springframework.core.convert.converter.Converter;

public class LinesheetConverter implements Converter<org.netvogue.server.neo4japi.domain.Linesheet, Linesheet> {

	@Autowired
	private UploadManager uploadManager;
	
	public Linesheet convert(org.netvogue.server.neo4japi.domain.Linesheet source) {
		Linesheet newSheet = new Linesheet();
		newSheet.setGalleryid(source.getLinesheetid());
		newSheet.setGalleryname(source.getLinesheetname());
		if(0 == source.getStyles().size()) {
			newSheet.setGallerypic(source.getProfilePicLink());
		} else {
			String thumblink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, Size.SThumb);
			newSheet.setGallerypic(thumblink);
		}
		Category cat =  source.getProductcategory();
		if(null != cat)
			newSheet.setCategory(cat.getProductLine().getDesc());
		String leftlink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.STYLE, Size.SLeft);
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
		newSheet.setLeftpic(leftlink);
		return newSheet;
	}
}
