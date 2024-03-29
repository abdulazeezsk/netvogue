package org.netvogue.server.webmvc.controllers;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.blitline.util.BlitlineUtil;
import org.netvogue.server.common.ProductLines;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.common.Constants;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.StyleData;
import org.netvogue.server.neo4japi.service.StylesheetData;
import org.netvogue.server.neo4japi.service.StylesheetService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.converters.ImageURLsConverter;
import org.netvogue.server.webmvc.converters.StyleResponseConverter;
import org.netvogue.server.webmvc.converters.StylesheetConverter;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.StyleJSONResponse;
import org.netvogue.server.webmvc.domain.StyleRequest;
import org.netvogue.server.webmvc.domain.StyleResponse;
import org.netvogue.server.webmvc.domain.Styles;
import org.netvogue.server.webmvc.domain.Stylesheet;
import org.netvogue.server.webmvc.domain.StylesheetJsonRequest;
import org.netvogue.server.webmvc.domain.Stylesheets;
import org.netvogue.server.webmvc.domain.UploadedFileResponse;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
//This must be implemented as queue, as maximum number of images are only four - Azeez
//@SessionAttributes("AddedPhotos") Implement this, for now, am sending all id's to client and getting them back again
public class StyleSheetController {

	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired BoutiqueService  			boutiqueService;
	@Autowired UserService 					userService;
	@Autowired ImageURLsConverter			imageURLsConverter;
	@Autowired StylesheetService			stylesheetService;
	@Autowired StylesheetConverter			stylesheetConverter;
	@Autowired StyleResponseConverter		styleConverter;
	@Autowired ConversionService			conversionService;

	@Autowired
	private UploadManager uploadManager;

	@RequestMapping(value="getstylesheets", method=RequestMethod.GET)
	public @ResponseBody Stylesheets GetStylesheets(
			@RequestParam(value="pagenumber", required=false, defaultValue="0") int pagenumber,
						@RequestParam(value="stylesheetname", required=false, defaultValue="") String stylesheetname,
						@RequestParam(value="category", required=false, defaultValue="") String categories) {
		System.out.println("Get Stylesheets: " + stylesheetname);
		Stylesheets stylesheets = new Stylesheets();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(USER_TYPE.BRAND != loggedinUser.getUserType()) {
			return stylesheets;
		}

		if(0 == pagenumber) {
			stylesheets.setName(loggedinUser.getName());
			String profilepic = loggedinUser.getProfilePicLink();
			if(null != profilepic && !profilepic.isEmpty()) {
        stylesheets.setProfilepic(imageURLsConverter.convert(profilepic, loggedinUser.getUsername()));
      }
		}
		Set<Stylesheet> stylesheetTemp = new LinkedHashSet<Stylesheet>();
		Iterable<StylesheetData> dbStylesheets;
		if(stylesheetname.isEmpty() && categories.isEmpty()) {
			dbStylesheets = userService.getStylesheets(loggedinUser, pagenumber);
		} else {
			Set<String> productlines = new HashSet<String>();
			if(null != categories && !categories.isEmpty()) {
				List<String> categoriesafter =  Arrays.asList(categories.split(","));
				for(String productline: categoriesafter) {
					ProductLines productLine = ProductLines.getValueOf(productline);
					if(null != productLine) {
						System.out.println("product line is:" + productLine.toString());
						productlines.add(productLine.toString());
					} else {
            System.out.println("product line is null");
          }
				}
			}
			dbStylesheets = userService.searchStylesheets(loggedinUser, stylesheetname, productlines, pagenumber);
		}
		if(null == dbStylesheets) {
			return stylesheets;
		}
		Iterator<StylesheetData> first = dbStylesheets.iterator();
		while ( first.hasNext() ){
			StylesheetData dbStylesheet = first.next() ;
			Stylesheet newResponse = stylesheetConverter.convert(dbStylesheet.getStylesheet(), loggedinUser.getUsername());
			newResponse.setBrandname(dbStylesheet.getName());
			stylesheetTemp.add(newResponse);
		}
		stylesheets.setStylesheets(stylesheetTemp);

		return stylesheets;
	}

	@RequestMapping(value="stylesheet/getstyles", method=RequestMethod.GET)
	public @ResponseBody Styles GetStyles(@RequestParam("stylesheetid") String stylesheetid,
						@RequestParam(value="pagenumber", required=false, defaultValue="0") int pagenumber,
						@RequestParam(value="styleno", required=false, defaultValue = "") String styleno,
						@RequestParam(value="fabrication", required=false, defaultValue = "") String fabrication,
						@RequestParam(value="fromprice", required=false, defaultValue = "0") long fromPrice,
						@RequestParam(value="toprice", required=false, defaultValue = "0") long toPrice
											 ) {
		System.out.println("Get Styles: " + stylesheetid + ":" + pagenumber +
				"\n Styleno:" + styleno +
				"\n fabrication" + fabrication +
				"\n fromprice" + fromPrice +
				"\n toprice" + toPrice);

		Styles styles = new Styles();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(stylesheetid.isEmpty() || USER_TYPE.BRAND != loggedinUser.getUserType()) {
			return styles;
		}

		if(0 == pagenumber) {
			styles.setName(loggedinUser.getName());
			String profilepic = loggedinUser.getProfilePicLink();
			if(null != profilepic && !profilepic.isEmpty()) {
        styles.setProfilepic(imageURLsConverter.convert(loggedinUser.getProfilePicLink(), loggedinUser.getUsername()));
      }
		}

		//This must be stored in session attributes from last query..shoudn't get it from database every time - Azeez
		org.netvogue.server.neo4japi.domain.Stylesheet s = stylesheetService.getStylesheet(stylesheetid);
		if(null == s) {
      return styles;
    }
		styles.setStylesheetname(s.getStylesheetname());
		Set<StyleResponse> stylesTemp = new LinkedHashSet<StyleResponse>();
		Iterable<StyleData> dbStyles;
		if(	(null == styleno || styleno.isEmpty()) &&
		    (null == fabrication || fabrication.isEmpty()) &&
			0 == fromPrice && 0 == toPrice
			) {
			dbStyles = stylesheetService.getStyles(stylesheetid, pagenumber);
		} else {
			dbStyles = stylesheetService.searchStyles(stylesheetid, styleno, fabrication,
										fromPrice, toPrice, pagenumber);
		}
		if(null == dbStyles) {
			return styles;
		}
		Iterator<StyleData> first = dbStyles.iterator();
		while ( first.hasNext() ){
			StyleData dbStyle = first.next() ;
			StyleResponse newResponse = styleConverter.convert(dbStyle.getStyle(), loggedinUser.getUsername());
			styles.setBrandname(dbStyle.getName());
			stylesTemp.add(newResponse);
		}
		styles.setStyles(stylesTemp);

		return styles;
	}

	@RequestMapping(value="stylesheet/getstylesbycat", method=RequestMethod.GET)
	public @ResponseBody Styles GetStylesByCategory(
										  @RequestParam("category") String category,
										  @RequestParam(value="searchquery", required=false) String searchquery
											 ) {
		System.out.println("Get Styles: " + category);
		Styles styles = new Styles();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(category.isEmpty()|| USER_TYPE.BRAND != loggedinUser.getUserType()) {
			return styles;
		}

		styles.setName(loggedinUser.getName());
		styles.setProfilepic(imageURLsConverter.convert(loggedinUser.getProfilePicLink(), loggedinUser.getUsername()));
		//This must be stored in session attributes from last query..shoudn't get it from database every time - Azeez
		Set<StyleResponse> stylesTemp = new LinkedHashSet<StyleResponse>();
		Iterable<StyleData> dbStyles;
		ProductLines productline = ProductLines.getValueOf(category);
		if(null == searchquery || searchquery.isEmpty()) {
			dbStyles = stylesheetService.getStylesbyCategory(loggedinUser.getUsername(), productline.toString());
		} else {
			//Change this after implementing query
			//dbStyles = collectionService.searchPhotoByName(stylesheetid, photoname);
			dbStyles = stylesheetService.getStylesbyCategory(loggedinUser.getUsername(), category);
		}
		if(null == dbStyles) {
			return styles;
		}
		Iterator<StyleData> first = dbStyles.iterator();
		while ( first.hasNext() ){
			StyleData dbStyle = first.next() ;
			StyleResponse newResponse = styleConverter.convert(dbStyle.getStyle(), loggedinUser.getUsername());
			styles.setBrandname(dbStyle.getName());
			stylesTemp.add(newResponse);
		}
		System.out.println("No:of Styles: " + stylesTemp.size());
		styles.setStyles(stylesTemp);

		return styles;
	}

	@RequestMapping(value="stylesheet/create", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreateStylesheet(@RequestBody StylesheetJsonRequest request) {
		System.out.println("Create Stylesheet");
		String error = "";
		JsonResponse response = new JsonResponse();

		User loggedinUser = userDetailsService.getUserFromSession();
		if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
			response.setError("Only brands can create stylesheets");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Stylesheet newStylesheet = new org.netvogue.server.neo4japi.domain.Stylesheet(request.getName(), loggedinUser);

		if(null != request.getCategory()) {
			ProductLines productLine = ProductLines.getValueOf(request.getCategory());
			Category cat = boutiqueService.getOrCreateCategory(productLine);
			newStylesheet.setProductcategory(cat);
		}
		if(ResultStatus.SUCCESS == stylesheetService.SaveStylesheet(newStylesheet, error)) {
			response.setStatus(true);
			response.setIdcreated(newStylesheet.getStylesheetid());
		} else {
      response.setError(error);
    }

		return response;
	}

	@RequestMapping(value="stylesheet/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditStylesheet(@RequestBody StylesheetJsonRequest request) {
		System.out.println("Edit Stylesheet");
		String error = "";
		JsonResponse response = new JsonResponse();

		if(null == request.getId() || request.getId().isEmpty()) {
			response.setError("stylesheet Id is empty");
			return response;
		} else if(null == request.getName()) {
			response.setError("new name is empty");
			return response;
		}

		if(ResultStatus.SUCCESS == stylesheetService.editStylesheet(request.getId(), request.getName(), error)) {
      response.setStatus(true);
    } else {
      response.setError(error);
    }
		return response;
	}

	//Think about the categories as well
	@RequestMapping(value="stylesheet/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeleteStylesheet(@RequestBody String stylesheetId) {
		System.out.println("Delete Stylesheet:"+ stylesheetId);
		String error = "";
		JsonResponse response = new JsonResponse();

		if(null == stylesheetId || stylesheetId.isEmpty()) {
			response.setError("Stylesheetid is empty");
			return response;
		}
		User user = userDetailsService.getUserFromSession();
		if (null == user) {
			response.setError("user info missing");
			return response;
		}
		List<String> idsList = stylesheetService.deleteStylesheet(stylesheetId, error);
		response.setStatus(true);
		//Make sure that styles inside this stylesheet are not part of any linesheets
		if(null != idsList && idsList.size() > 0) {
			//This is different to other ones...here each String itself is an array separated by commas
			//parse that into individual strings and delete
			ResultStatus status = uploadManager.deletePhotosList(idsList,
					ImageType.STYLE, user.getUsername());
			System.out.println("Result Status of deleting SS from S3: "
					+ status.toString());
		} else {
      response.setError(error);
    }

		return response;
	}

	@RequestMapping(value ="stylesheet/createstyle",method=RequestMethod.POST)
	public @ResponseBody StyleJSONResponse CreateStyle(@RequestBody StyleRequest newStyle) throws Exception {

			System.out.println("Create new Style" + newStyle.getStylename());
			String error = "";
			StyleJSONResponse response = new StyleJSONResponse();

			User loggedinUser = userDetailsService.getUserFromSession();
			if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
				response.setError("Only brands can create styles");
				return response;
			}
			org.netvogue.server.neo4japi.domain.Stylesheet stylesheet = stylesheetService.getStylesheet(newStyle.getStylesheetid());
			if(null == stylesheet) {
				response.setError("No stylesheet present with this id");
				return response;
			}

			Style createdStyle = conversionService.convert(newStyle, Style.class);
			stylesheet.addStyles(createdStyle);
			if(ResultStatus.SUCCESS == stylesheetService.SaveStylesheet(stylesheet, error)) {
				response.setStatus(true);
				response.setStyle(styleConverter.convert(createdStyle, loggedinUser.getUsername()));
			} else {
        response.setError(error);
      }

			return response;
	}

/*	@RequestMapping(value="stylesheet/addstyleimages", method=RequestMethod.POST)
	public @ResponseBody UploadedFileResponse AddImagestoStyle(Model model,
			@RequestParam("files[]") List<MultipartFile> fileuploads, @RequestParam("stylesheetid") String stylesheetId) {
		System.out.println("Add photos: Stylesheet Id:" + stylesheetId + "No:of Photos:" + fileuploads.size());
		User user = userDetailsService.getUserFromSession();
		if(null == user) {
      return null;
    }

		UploadedFileResponse response = new UploadedFileResponse();

		if(stylesheetId.isEmpty()) {
			response.setError("Stylesheet Id is empty");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Stylesheet stylesheet = stylesheetService.getStylesheet(stylesheetId);
		if(null == stylesheet) {
			response.setError("No stylesheet present with this id");
			return response;
		}

		List<PhotoWeb> JSONFileData= new ArrayList<PhotoWeb>();

		for ( MultipartFile fileupload : fileuploads ) {
			System.out.println("Came here" + fileupload.getOriginalFilename());
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.STYLE, user.getUsername());
			PhotoWeb newPhoto = new PhotoWeb();
			String thumburl = uploadManager.getQueryString(
					(String)uploadMap.get(UploadManager.FILE_ID), ImageType.STYLE, Size.SThumb, user.getUsername());
			System.out.println("Image path is/Thumnail url is" + thumburl);
			newPhoto.setThumbnail_url(thumburl);
			String lefturl = uploadManager.getQueryString(
					(String)uploadMap.get(UploadManager.FILE_ID), ImageType.STYLE, Size.SLeft, user.getUsername());
			newPhoto.setLeft_url(lefturl);
			newPhoto.setUniqueid((String)uploadMap.get(UploadManager.FILE_ID));
			JSONFileData.add(newPhoto);
			if(JSONFileData.size() == Constants.MAX_IMAGES_IN_STYLE) {
				break;
			}
		}
		response.setFilesuploaded(JSONFileData);
		response.setStatus(true);
		return response;
	}*/

  @RequestMapping(value = "stylesheet/addstyleimages", method = RequestMethod.POST)
  public @ResponseBody
  UploadedFileResponse AddImagestoStyle(Model model, @RequestParam("files[]")
  List<MultipartFile> fileuploads, @RequestParam("stylesheetid")
  String stylesheetId) {
    String userName = null;
    System.out.println("Add photos: Stylesheet Id:" + stylesheetId + "No:of Photos:" + fileuploads.size());
    User user = userDetailsService.getUserFromSession();
    if (null == user) {
      return null;
    }
    userName = user.getUsername();
    UploadedFileResponse response = new UploadedFileResponse();

    if (stylesheetId.isEmpty()) {
      response.setError("Stylesheet Id is empty");
      return response;
    }
    org.netvogue.server.neo4japi.domain.Stylesheet stylesheet = stylesheetService.getStylesheet(stylesheetId);
    if (null == stylesheet) {
      response.setError("No stylesheet present with this id");
      return response;
    }

    List<PhotoWeb> JSONFileData = new ArrayList<PhotoWeb>();

    for (MultipartFile fileupload : fileuploads) {
      System.out.println("Came here" + fileupload.getOriginalFilename());
      Map<String, Object> uploadMap = uploadManager.processUpload(fileupload, ImageType.STYLE, userName);
      try {
        String key = userName+ "/" + ImageType.STYLE.getKey() + "/" + (String)uploadMap.get("fileId");
        BlitlineUtil.sendBlitlineRequest((String)uploadMap.get("queryString"),key,ImageType.STYLE, "pad_resize_to_fit" );
      } catch (Exception e) {
        e.printStackTrace();
      }
      PhotoWeb newPhoto = new PhotoWeb();
      String thumburl = uploadManager.getQueryString((String) uploadMap.get(UploadManager.FILE_ID), ImageType.STYLE,
          Size.SThumb, user.getUsername());
      String addurl = uploadManager.getQueryString((String) uploadMap.get(UploadManager.FILE_ID), ImageType.STYLE,
              Size.SAdd, user.getUsername());
      System.out.println("Image path is/Thumnail url is" + thumburl);
      newPhoto.setThumbnail_url(thumburl);
      newPhoto.setAdd_url(addurl);
      String lefturl = uploadManager.getQueryString((String) uploadMap.get(UploadManager.FILE_ID), ImageType.STYLE,
          Size.SLeft, user.getUsername());
      newPhoto.setLeft_url(lefturl);
      newPhoto.setUniqueid((String) uploadMap.get(UploadManager.FILE_ID));
      JSONFileData.add(newPhoto);
      if (JSONFileData.size() == Constants.MAX_IMAGES_IN_STYLE) {
        break;
      }
    }
    response.setFilesuploaded(JSONFileData);
    response.setStatus(true);
    return response;
  }

	//Check if there is better of response -- Azeez
	@RequestMapping(value ="stylesheet/editstyle",method=RequestMethod.POST)
	public @ResponseBody StyleJSONResponse EditStyle(@RequestBody StyleRequest newStyle) throws Exception {

			System.out.println("Edit Style" + newStyle.getStylename() + newStyle.getStyleid());
			String error = "";
			StyleJSONResponse response = new StyleJSONResponse();

			User loggedinUser = userDetailsService.getUserFromSession();
			if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
				response.setError("Only brands can create and edit styles");
				return response;
			}
			org.netvogue.server.neo4japi.domain.Stylesheet stylesheet = stylesheetService.getStylesheet(newStyle.getStylesheetid());
			if(null == stylesheet) {
				response.setError("No stylesheet present with this id");
				return response;
			}

			//Other way of doing this is using Cypher query. Use map to send all the properties of node
			Style styleToEdit = conversionService.convert(newStyle, Style.class);
			Set<Style> allStyles = stylesheet.getStyles();
			System.out.println("number of styles:" + allStyles.size());
			String styleId = newStyle.getStyleid();
			boolean foundStyle = false;
			for(Style style: allStyles) {
				System.out.println("available styles:" + style.getStyleid());
				if(style.getStyleid().equals(styleId)) {
					style.Copy(styleToEdit);
					foundStyle = true;
					if(ResultStatus.SUCCESS == stylesheetService.SaveStyle(style, error)) {
						response.setStatus(true);
						response.setStyle(conversionService.convert(style, StyleResponse.class));
					} else {
            response.setError(error);
          }
					break;
				}
			}
			if(!foundStyle) {
				response.setError("No style present with this id");
				return response;
			}

			return response;
	}
}
