package sbootkeyclock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.JwtConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http){
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/cursos/java").hasRole("vizualizar-cursos-java");
          auth.requestMatchers("/cursos/spring").hasRole("vizualizar-cursos-spring-boot");
          auth.requestMatchers("/cursos/abertos").permitAll();
          auth.anyRequest().authenticated();
        })
        .oauth2ResourceServer(
          oauth2 -> {
//          oauth2.jwt(Customizer.withDefaults());
            oauth2.jwt(
              config -> config.jwtAuthenticationConverter(jwtAuthenticationConverter())
            );
          }
        )
        .build();
  }

  private Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
    
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(new KeyclockResourceConverter("cursos-api"));

    return converter;
  }
}