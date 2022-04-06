package com.toptal.soccer.data.model;

import com.toptal.soccer.constants.DbConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = DbConstants.Tables.TRANSFER_LIST)
public class Transfer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Positive private Long price;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private Player player;

  public static Transfer createNewTransfer() {
    return new Transfer();
  }
}
