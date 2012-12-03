package org.netvogue.customer.service.converters.input;

import org.netvogue.customer.service.converter.Converter;
import org.netvogue.customer.service.representations.UserRepresentation;
import org.netvogue.ecommerce.domain.model.Role;
import org.netvogue.ecommerce.domain.model.SubscriptionType;
import org.netvogue.ecommerce.domain.model.User;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class UserRepresentationToUserConverter implements Converter<UserRepresentation, User> {

  @Override
  public User convert(final UserRepresentation source) {
    long salt = System.nanoTime();
    User user = new User();
    user.setAboutUs(source.getAboutUs());
    user.setActive(true);
    user.setAddress(source.getAddress());
    user.setCity(source.getCity());
    user.setCountry(user.getCountry());
    user.setEmail(source.getEmail());
    user.setEncodedPassword(new ShaPasswordEncoder().encodePassword(source.getPassword(), salt));
    user.setFirstName(source.getFirstName());
    user.setLastName(source.getLastName());
    user.setMobileNo(source.getMobileNo());
    user.setTelephoneNo1(source.getTelephoneNo1());
    user.setTelephoneNo2(source.getTelephoneNo2());
    user.setPrimarycontact(source.getPrimarycontact());
    user.setProfilePicLink(source.getProfilePicLink());
    user.setRole(Role.valueOf(source.getRole()));
    user.setUsername(source.getUsername());
    user.setSubscirptionType(SubscriptionType.valueOf(source.getSubscirptionType()));
    user.setZipCode(source.getZipCode());
    return user;
  }

}
