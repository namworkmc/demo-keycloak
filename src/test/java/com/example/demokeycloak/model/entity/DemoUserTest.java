package com.example.demokeycloak.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DemoUserTest {
  @Test
  void testDemoUser() {
    Long created = System.currentTimeMillis();
    List<String> roles = Collections.singletonList("stoneage");
    List<DemoUser> users = Arrays.asList(
        new DemoUser("1", "Fred", "Flintstone", true, created, roles),
        new DemoUser("2", "Wilma", "Flintstone", true, created, roles),
        new DemoUser("3", "Pebbles", "Flintstone", true, created, roles),
        new DemoUser("4", "Barney", "Rubble", true, created, roles),
        new DemoUser("5", "Betty", "Rubble", true, created, Collections.emptyList()),
        new DemoUser("6", "Bam Bam", "Rubble", false, created, Collections.emptyList())
    );

    assertEquals(6, users.size());
  }
}