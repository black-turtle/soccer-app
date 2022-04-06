package com.toptal.soccer;

import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Transfer;
import com.toptal.soccer.data.model.User;
import com.toptal.soccer.service.PlayerService;
import com.toptal.soccer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SoccerGameApplication implements CommandLineRunner {
  private final UserService userService;
  private final PlayerService playerService;

  public static void main(String[] args) {
    SpringApplication.run(SoccerGameApplication.class, args);
  }

  @Override
  public void run(String... args) {
    // this method will be invoked after spring context is initialized, so we can
    // create initial dummy data here

    int numOfUsers = 10;
    for (int i = 1; i <= numOfUsers; i++) {
      String clientName = "user" + i;
      User user = new User();
      user.setUsername(clientName);
      user.setPassword(clientName);
      user.setEmail(clientName + "@email.com");
      userService.signup(user);
    }

    // user signup automatically creates a new user, a new team and 20 new players
    // so, just create some transfer data
    List<Player> players = playerService.getAllPlayers();
    for (int i = 0; i < players.size(); i++) {
      if (i % 2 == 0) {
        Player player = players.get(i);
        Transfer transfer = Transfer.createNewTransfer();
        player.setTransfer(transfer);
        transfer.setPlayer(player);
        transfer.setPrice(2000000L);
        playerService.saveOrUpdate(player);
      }
    }
  }

  // Spring managed beans
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
