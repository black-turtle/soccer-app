package com.toptal.soccer.data.model;

import com.toptal.soccer.constants.DbConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = DbConstants.Tables.USERS)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Size(min = 3, max = 255, message = "User Name should be between 3 and 255 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @Size(min = 3, max = 255, message = "Password should be between 3 and 255 characters")
  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  @Email
  private String email;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private Team team;
}
