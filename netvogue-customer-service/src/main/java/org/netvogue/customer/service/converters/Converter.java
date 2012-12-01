package org.netvogue.customer.service.converters;

public interface Converter<S, T> {

  T convert(S source);
}
