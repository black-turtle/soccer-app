package com.toptal.soccer.data.model;

import com.toptal.soccer.constants.Country;
import com.toptal.soccer.constants.DbConstants;
import com.toptal.soccer.constants.PlayerType;
import com.toptal.soccer.utils.PlayerUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = DbConstants.Tables.PLAYERS)
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Size(min = 3, max = 255, message = "Player first name should be between 3 and 255 characters")
  private String firstName;

  @Size(min = 3, max = 255, message = "Player last name should be between 3 and 255 characters")
  private String lastName;

  private Country country;

  private LocalDate dateOfBirth;

  @NonNull @Positive private Long value;

  @NonNull private PlayerType type;

  @Version private Long version;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private Team team;

  @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  private Transfer transfer;

  public static Player createNewPlayer() {
    Player player = new Player();
    player.setFirstName("player");
    player.setLastName("player");
    player.setCountry(Country.getRandomCountry());
    player.setDateOfBirth(PlayerUtils.getRandomDateOfBirth());
    player.setValue(1000000L);
    player.setType(PlayerType.DEFENDER);
    return player;
  }
}
