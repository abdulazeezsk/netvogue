package org.netvogue.customer.service.converter;

public interface Converter<S, T> {

  T convert(S source) throws Exception;
}
