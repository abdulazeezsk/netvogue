package org.netvogue.server.webmvc.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

//import org.netvogue.server.webmvc.domain.String;

public class BoutiqueNew {

	@NotEmpty
	@Size(max=25)
	private String 	email;
	
	@NotEmpty
	@Size(max=10)
	private String 	password;
	
	@NotEmpty
	@Size(max=25)
	private String	username;
	
	@NotEmpty
	@Size(max=25)
	private String 	name;
	
	@NotEmpty
	@Size(max=25)
	private String 	country;
	
	@NotEmpty
	@Size(max=25)
	private String 	state;
	
	@NotEmpty
	@Size(max=25)
	private String 	city;
	
	@NotEmpty
	@Size(max=200)
	private String 	address;
	
	@NotEmpty
	@Size(min=6,max=6)
	private String 	zipcode;
	
	@NotEmpty
	@Size(min=10,max=10)
	private String mobile;
	
	@NotEmpty
	@Size(min=8,max=8)
	private String 	telephone;
	
	@NotEmpty
	@Size(max=50)
	private String 	website;
	
	@NotNull
	@Min(1900)
	@Max(2014)
	private Integer estdyear;
	
	private Long fromprice;
	private Long toprice;
	
	private String[] productlines;
	private String[] brandsselected;

     public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getEstdyear() {
		return estdyear;
	}

	public void setEstdyear(Integer estdyear) {
		this.estdyear = estdyear;
	}

	public String[] getProductlines() {
		return productlines;
	}

	public void setProductlines(String[] productlines) {
		this.productlines = productlines;
	}

	public String[] getBrandsselected() {
		return brandsselected;
	}

	public void setBrandsselected(String[] brandsselected) {
		this.brandsselected = brandsselected;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getFromprice() {
		return fromprice;
	}

	public void setFromprice(Long fromprice) {
		this.fromprice = fromprice;
	}

	public Long getToprice() {
		return toprice;
	}

	public void setToprice(Long toprice) {
		this.toprice = toprice;
	}

	@Override
	public String toString() {
		String temp = new String();
		temp = "\nEmail:" 		+ email 	+ "\n";
		temp = "Username:" 		+ username 	+ "\n";
		temp+= "Boutique Name:" + name + "\n";
		temp+= "Country:" 		+ country 	+ "\n";
		temp+= "State:" 		+ state 	+ "\n";
		temp+= "City:" 			+ city 		+ "\n";
		temp+= "Address:" 		+ address 	+ "\n";
		temp+= "Zip Code:" 		+ zipcode 	+ "\n";
		temp+= "Mobile:" 		+ mobile 	+ "\n";
		temp+= "Telephone:" 	+ telephone + "\n";
		temp+= "Website:" 		+ website 	+ "\n";
		temp+= "Year of Est:" 	+ estdyear  + "\n";
		temp+= "fromyear"		+ fromprice	+ "\n";
		temp+= "toyear"			+ toprice	+ "\n";
		temp+= "ProductLines:"	+ "\n";
		for(int i=0; i < productlines.length; i++) {
			temp+= "\t" + productlines[i].toString() 	+ "\n";
		}
		temp+= "BrandsSelected:"+ "\n";
		for(int i=0; i < brandsselected.length; i++) {
			temp+= "\t" + brandsselected[i].toString() 	+ "\n";
		}
		temp = "Email:" 		+ email;
		return temp;
	}
}