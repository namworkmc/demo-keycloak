package com.example.demokeycloak.config;

import lombok.AllArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

  private final KeycloakSpringBootProperties keycloakSpringBootProperties;

  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder
        .builder()
        .realm(keycloakSpringBootProperties.getRealm())
        .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
        .clientId(keycloakSpringBootProperties.getResource())
        .username("admin")
        .password("admin")
        .build();
  }

  @Bean
  public RealmResource realmResource() {
    return keycloak().realm(keycloakSpringBootProperties.getRealm());
  }
}
