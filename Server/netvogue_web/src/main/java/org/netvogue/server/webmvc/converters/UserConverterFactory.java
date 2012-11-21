package org.netvogue.server.webmvc.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.netvogue.server.common.ProductLines;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Brand;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.webmvc.domain.BoutiqueNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class UserConverterFactory implements ConverterFactory<BoutiqueNew, User> {
	
	@Autowired BoutiqueService  boutiqueServiceTemp;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends User> Converter<BoutiqueNew, T> getConverter(Class<T> targetType) {
		return new UserConverter(targetType, boutiqueServiceTemp);
	}
	
	public class UserConverter<T extends User> implements Converter<BoutiqueNew, T>{

		BoutiqueService  boutiqueService;
		private Class<T> userType;
		
		public UserConverter(Class<T> usertype, BoutiqueService boutiqueService) { 
			this.userType 			= usertype;
			this.boutiqueService	= boutiqueService;
		}
		 
		@SuppressWarnings("unchecked")
		public T convert(BoutiqueNew boutiqueNew) {
			if (boutiqueNew == null){
				throw new ConversionFailedException(TypeDescriptor.valueOf(BoutiqueNew.class), 
					TypeDescriptor.valueOf(Boutique.class), boutiqueNew, null);
			}
			User serviceUser;
			if(this.userType == Boutique.class) {
				serviceUser = new Boutique(boutiqueNew.getEmail(), boutiqueNew.getPassword());
			} else {
				serviceUser = new Brand(boutiqueNew.getEmail(), boutiqueNew.getPassword());
			}
			serviceUser.setName(boutiqueNew.getName());
			serviceUser.setUsername(boutiqueNew.getUsername());
			serviceUser.setPrimarycontact(boutiqueNew.getPrimarycontact());
			serviceUser.setMobileNo(Long.parseLong(boutiqueNew.getMobile()));
			/*serviceUser.setCountry(boutiqueNew.getCountry());
			serviceUser.setState(boutiqueNew.getState());
			serviceUser.setCity(boutiqueNew.getCity());
			serviceUser.setAddress(boutiqueNew.getAddress());
			serviceUser.setZipCode(Integer.parseInt(boutiqueNew.getZipcode()));
			serviceUser.setTelephoneNo1(Long.parseLong(boutiqueNew.getTelephone()));
			serviceUser.setWebsite(boutiqueNew.getWebsite());
			serviceUser.setYearofEst(boutiqueNew.getEstdyear());
			serviceUser.setFromPrice(boutiqueNew.getFromprice().longValue());
			serviceUser.setToPrice(boutiqueNew.getToprice().longValue());
			List<String>	products		= new ArrayList<String>(Arrays.asList(boutiqueNew.getProductlines()));
			for(String productline: products) {
				ProductLines productLine = ProductLines.getValueOf(productline);
				Category cat = boutiqueService.getOrCreateCategory(productLine);
				serviceUser.updateCategories(cat);
			}
			List<String> 	usersCarried = new ArrayList<String>(Arrays.asList(boutiqueNew.getBrandsselected()));
			for(String userName: usersCarried) {
				User user = boutiqueService.GetOrCreateUser(userName);
				serviceUser.updateUsersCarried(user);
			}*/
			return (T) serviceUser;
		}
	}

}
