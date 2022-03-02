package com.spring.security.jwt.service;

import com.spring.security.jwt.domain.Role;
import com.spring.security.jwt.domain.User;
import com.spring.security.jwt.repository.RoleRepository;
import com.spring.security.jwt.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public User saveUser(User user) {
    String password = new Argon2PasswordEncoder().encode(user.getPassword());
    user.setPassword(password);
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Usuário não encontrado");
    }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }
}
