package com.example.demokeycloak.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

  private String id;
  private String username;
  private String password;
  private String email;
}
