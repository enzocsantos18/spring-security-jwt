package com.spring.security.jwt.service;

import com.spring.security.jwt.domain.Role;
import com.spring.security.jwt.domain.User;
import com.spring.security.jwt.repository.RoleRepository;
import com.spring.security.jwt.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    User user = userRepository.findByUsername(username);
    Role role = roleRepository.findByName(roleName);

    user.getRoles().add(role);

    saveUser(user);

  }

  @Override
  public User getUser(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }
}
