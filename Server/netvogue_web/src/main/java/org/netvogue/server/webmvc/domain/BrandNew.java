package org.netvogue.server.webmvc.domain;

public class BrandNew {

	private String brandName;
	private String primaryCnt;
	private String website;
	private String emailId;
	private String refToNetvouge;
	private Integer telephone;
	private boolean termsCond;


	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getPrimaryCnt() {
		return primaryCnt;
	}
	public void setPrimaryCnt(String primaryCnt) {
		this.primaryCnt = primaryCnt;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRefToNetvouge() {
		return refToNetvouge;
	}
	public void setRefToNetvouge(String refToNetvouge) {
		this.refToNetvouge = refToNetvouge;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	

	public boolean isTermsCond() {
		return termsCond;
	}
	public void setTermsCond(boolean termsCond) {
		this.termsCond = termsCond;
	}
	@Override
	public String toString() {
		String temp = new String();
		temp = "Brand Name:" + brandName  + "\n";
		temp+= "Primary Contact:" + primaryCnt  + "\n";
		temp+= "Website:" + website + "\n";
		temp+= "Email:" + emailId + "\n";
		temp+= "Referred to Netvogue:"+ refToNetvouge + "\n";
		temp+= "Telephone:" + telephone ;

		return temp;
	}


}
