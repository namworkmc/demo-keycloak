package com.example.demokeycloak.service.impl;

import com.example.demokeycloak.model.dto.RoleDto;
import com.example.demokeycloak.model.dto.UserDto;
import com.example.demokeycloak.service.KeycloakService;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

  private final RealmResource realmResource;

  @Override
  public List<UserRepresentation> getUsers() {
    return realmResource
        .users()
        .list();
  }

  @Override
  public UserRepresentation getUserByUsername(String username) {
    return realmResource
        .users()
        .search(username)
        .stream()
        .findFirst()
        .orElse(null);
  }

  @Override
  public UserRepresentation getUserById(String id) {
    return realmResource
        .users()
        .get(id)
        .toRepresentation();
  }

  @Override
  public List<RoleRepresentation> getUserRolesByUsername(String username) {
    return realmResource
        .users()
        .search(username)
        .stream()
        .findFirst()
        .map(userRepresentation -> realmResource
            .users()
            .get(userRepresentation.getId())
            .roles()
            .realmLevel()
            .listAll())
        .orElse(null);
  }

  @Override
  public List<RoleRepresentation> listAllRealmRoles() {
    return realmResource
        .roles()
        .list();
  }

  @Override
  public UserRepresentation createUser(UserDto userDto) {
    UsersResource userResource = realmResource.users();

    // Password
    CredentialRepresentation credential = new CredentialRepresentation();
    credential.setType(CredentialRepresentation.PASSWORD);
    credential.setValue(userDto.getPassword());
    credential.setTemporary(false);

    UserRepresentation user = new UserRepresentation();
    user.setUsername(userDto.getUsername());
    user.setCredentials(Collections.singletonList(credential));
    user.setEnabled(true);

    userResource.create(user);
    return user;
  }

  @Override
  public RoleRepresentation createRole(RoleDto roleDto) {
    if (realmResource.roles().get(roleDto.getRoleName()) != null) {
      return null;
    }

    realmResource.roles().create(new RoleRepresentation(roleDto.getRoleName(), roleDto.getDescription(), roleDto.isScopeParamRequired()));
    return realmResource.roles().get(roleDto.getRoleName()).toRepresentation();
  }

  @Override
  public UserRepresentation updateUserById(String id, UserDto userDto) {
    UserRepresentation user = getUserById(id);

    // Password
    CredentialRepresentation credential = new CredentialRepresentation();
    credential.setType(CredentialRepresentation.PASSWORD);
    credential.setValue(userDto.getPassword());
    credential.setTemporary(false);

    user.setUsername(userDto.getUsername() == null ? user.getUsername() : userDto.getUsername());
    user.setFirstName(userDto.getFirstName() == null ? user.getFirstName() : userDto.getFirstName());
    user.setLastName(userDto.getLastName() == null ? user.getLastName() : userDto.getLastName());
    user.setEmail(userDto.getEmail() == null ? user.getEmail() : userDto.getEmail());
    user.setCredentials(Collections.singletonList(credential));
    user.setEnabled(true);

    return user;
  }

  @Override
  public RoleRepresentation updateRoleById(RoleDto roleDto) {
    RoleRepresentation role = realmResource.roles().get(roleDto.getRoleName()).toRepresentation();
    role.setDescription(roleDto.getDescription());
    return role;
  }

  @Override
  public void deleteUserById(String id) {
    UsersResource userResource = realmResource.users();
    userResource.get(id).remove();
  }

  @Override
  public void deleteRoleByName(String name) {
    realmResource.roles().get(name).remove();
  }
}
