package sbootkeyclock.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeyclockResourceConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

  private final String clientId;
  
  public KeyclockResourceConverter(String clientId) {
    this.clientId = clientId;
  }

  @Override
  public Collection<GrantedAuthority> convert(Jwt source) {
    
    Map<String, Object> resourceAccess =  source.getClaimAsMap("resource_access");

    if(resourceAccess == null || !resourceAccess.containsKey(clientId)) {
      return Collections.emptyList();
    }

    Map<String, Object> client = (Map<String, Object>) resourceAccess.get(clientId);
    Collection<String> roles = (Collection<String>) client.get("roles");

    return roles
          .stream().map(
            role -> new SimpleGrantedAuthority("ROLE_" + roles)
          ).collect(Collectors.toList());
  }
}
