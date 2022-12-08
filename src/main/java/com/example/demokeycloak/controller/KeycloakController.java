package com.example.demokeycloak.controller;

import com.example.demokeycloak.model.dto.RoleDto;
import com.example.demokeycloak.model.dto.UserDto;
import com.example.demokeycloak.service.KeycloakService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/keycloak")
public class KeycloakController {

  private KeycloakService keycloakService;

  @GetMapping("users")
  public List<UserRepresentation> getUsers() {
    return keycloakService.getUsers();
  }

  @GetMapping("users/username")
  public UserRepresentation getUserByUsername(@RequestBody UserDto userDto) {
    return keycloakService.getUserByUsername(userDto.getUsername());
  }

  @GetMapping("users/{id}")
  public UserRepresentation getUserById(@PathVariable String id) {
    return keycloakService.getUserById(id);
  }

  @GetMapping("users/username/roles")
  public List<RoleRepresentation> getUserRolesByUsername(@RequestBody UserDto userDto) {
    return keycloakService.getUserRolesByUsername(userDto.getUsername());
  }

  @GetMapping("roles")
  public List<RoleRepresentation> listAllRealmRoles() {
    return keycloakService.listAllRealmRoles();
  }

  @PostMapping("users")
  public UserRepresentation createUser(@RequestBody UserDto userDto) {
    return keycloakService.createUser(userDto);
  }

  @PostMapping("roles")
  public RoleRepresentation createRole(@RequestBody RoleDto roleDto) {
    return keycloakService.createRole(roleDto);
  }

  @PutMapping("users/{id}")
  public UserRepresentation updateUserById(@PathVariable String id, @RequestBody UserDto userDto) {
    return keycloakService.updateUserById(id, userDto);
  }

  @PutMapping("roles")
  public RoleRepresentation updateRoleById(@RequestBody RoleDto roleDto) {
    return keycloakService.updateRoleById(roleDto);
  }

  @DeleteMapping("users/{id}")
  public void deleteUserById(@PathVariable String id) {
    keycloakService.deleteUserById(id);
  }

  @DeleteMapping("roles/{name}")
  public void deleteRoleByName(@PathVariable String name) {
    keycloakService.deleteRoleByName(name);
  }
}
