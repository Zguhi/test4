// src/main/java/com/example/apfast/Entity/Role.java
package com.example.apfast.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
@IdClass(RoleId.class)
public class Role {
  @Id
  @Column(name = "user_id")
  private String userId;

  @Id
  @Column(name = "role")
  private String role;

  public Role(String userId, String role) {
    this.userId = userId;
    this.role = role;
  }
}