package com.spring.security.jwt.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String name;
//  @ManyToMany(fetch = FetchType.LAZY)
//  private Collection<User> users = new ArrayList<>();
}
