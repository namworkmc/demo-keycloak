package com.example.demokeycloak.service;

import com.example.demokeycloak.model.dto.RoleDto;
import com.example.demokeycloak.model.dto.UserDto;
import java.util.List;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

  List<UserRepresentation> getUsers();

  UserRepresentation getUserByUsername(String username);

  UserRepresentation getUserById(String id);

  List<RoleRepresentation> getUserRolesByUsername(String username);

  List<RoleRepresentation> listAllRealmRoles();

  UserRepresentation createUser(UserDto userDto);

  RoleRepresentation createRole(RoleDto roleDto);

  UserRepresentation updateUserById(String id, UserDto userDto);

  RoleRepresentation updateRoleById(RoleDto roleDto);

  void deleteUserById(String id);

  void deleteRoleByName(String name);
}
