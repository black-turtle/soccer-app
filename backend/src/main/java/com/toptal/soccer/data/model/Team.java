package com.toptal.soccer.data.model;

import com.toptal.soccer.constants.Country;
import com.toptal.soccer.constants.DbConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = DbConstants.Tables.TEAMS)
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Size(min = 3, max = 255, message = "Team name should be between 3 and 255 characters")
  private String name;

  private Country country;

  @PositiveOrZero private Long budget;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private User user;

  @OneToMany(fetch = FetchType.LAZY)
  @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
  @JoinColumn(name = "team_id")
  private List<Player> players;

  public static Team createNewTeam() {
    Team team = new Team();
    team.setName("My Team");
    team.setCountry(Country.ENGLAND);
    team.setBudget(5000000L);
    return team;
  }
}
