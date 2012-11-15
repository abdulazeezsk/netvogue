package org.netvogue.ecommerce.domain.model;


public class User {

  private String email;

  private String password;

  private String username;

  private String firstName;

  private String lastName;

  private String profilePicLink = new String();

  private UserType userType;

  private String primarycontact;

  private String aboutUs;

  private String address;

  private String city;

  private String state;

  private int zipCode;

  private String country;

  private long mobileNo;

  private long telephoneNo1;

  private long telephoneNo2;

  private boolean active = true;

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getProfilePicLink() {
    return profilePicLink;
  }

  public void setProfilePicLink(final String profilePicLink) {
    this.profilePicLink = profilePicLink;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(final UserType userType) {
    this.userType = userType;
  }

  public String getPrimarycontact() {
    return primarycontact;
  }

  public void setPrimarycontact(final String primarycontact) {
    this.primarycontact = primarycontact;
  }

  public String getAboutUs() {
    return aboutUs;
  }

  public void setAboutUs(final String aboutUs) {
    this.aboutUs = aboutUs;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(final int zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(final long mobileNo) {
    this.mobileNo = mobileNo;
  }

  public long getTelephoneNo1() {
    return telephoneNo1;
  }

  public void setTelephoneNo1(final long telephoneNo1) {
    this.telephoneNo1 = telephoneNo1;
  }

  public long getTelephoneNo2() {
    return telephoneNo2;
  }

  public void setTelephoneNo2(final long telephoneNo2) {
    this.telephoneNo2 = telephoneNo2;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(final boolean active) {
    this.active = active;
  }

}
