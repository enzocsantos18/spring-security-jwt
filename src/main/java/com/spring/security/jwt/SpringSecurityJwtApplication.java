package com.spring.security.jwt;

import com.spring.security.jwt.domain.Role;
import com.spring.security.jwt.domain.User;
import com.spring.security.jwt.service.UserService;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SpringSecurityJwtApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityJwtApplication.class, args);
  }


  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }



  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));
      userService.saveRole(new Role(null, "ROLE_MANAGER"));
      userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

      userService.saveUser(new User(null, "Enzo","enzo", "1234", new ArrayList<>()));
      userService.saveUser(new User(null, "Nicolas","nicolas", "1234", new ArrayList<>()));
      userService.saveUser(new User(null, "Soraia","soraia", "1234", new ArrayList<>()));
      userService.saveUser(new User(null, "Napoleao","napoleao", "1234", new ArrayList<>()));

      userService.addRoleToUser("enzo", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("nicolas", "ROLE_ADMIN");
      userService.addRoleToUser("soraia", "ROLE_MANAGER");
      userService.addRoleToUser("napoleao", "ROLE_USER");


    };
  }

}
