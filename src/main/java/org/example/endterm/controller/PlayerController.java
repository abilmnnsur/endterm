package org.example.endterm.controller;

import org.example.endterm.model.Player;
import org.example.endterm.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }
    @GetMapping("/top")
    public List<Player> getTopPlayers() {
        return playerService.getTopPlayers();
    }
    @GetMapping("/top/kills")
    public List<Player> getTopByKills() {
        return playerService.getKills();
    }
}