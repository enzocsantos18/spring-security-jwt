package com.spring.security.jwt.service;

import com.spring.security.jwt.domain.Role;
import com.spring.security.jwt.domain.User;
import java.util.List;

public interface UserService {
  User saveUser(User user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  User getUser(String username);
  List<User> getUsers();
}
