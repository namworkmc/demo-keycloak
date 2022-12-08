package com.example.demokeycloak;

import com.example.demokeycloak.model.entity.DemoUser;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoKeycloakApplicationTests {

  @Test
  void contextLoads() {
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

    Assertions.assertEquals(6, users.size());
  }
}
