package com.example.demokeycloak.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
class DemoUserStorageProviderTest {

  private final RestTemplate restTemplate = new RestTemplate();

  int serverPort = 80;

  @Test
  void getUserByUsername() throws InterruptedException {
    log.info("Server port: {}", serverPort);

    String baseUrl = "http://localhost:" + serverPort;
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/auth", String.class);
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    log.info("Keycloak test server available at {}/auth", baseUrl);
    log.info("To test a custom provider user login, go to {}/auth/realms/baeldung/account",
        baseUrl);

    Thread.sleep(15 * 60 * 1000);
  }
}