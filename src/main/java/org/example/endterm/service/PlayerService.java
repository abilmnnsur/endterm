package org.example.endterm.service;

import org.example.endterm.model.Player;
import org.example.endterm.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public List<Player> getAllPlayers() {
        return repo.getAllPlayers();
    }

    public List<Player> getTopPlayers() {
        List<Player> players = repo.getAllPlayers();
        players.sort((a, b) -> Double.compare(b.getRating(), a.getRating()));
        return players;
    }

    public double calculateKD(Player p) {
        if (p.getDeaths() == 0) return p.getKills();
        return (double) p.getKills() / p.getDeaths();
    }

    public List<Player> getKills() {
        List<Player> players = repo.getAllPlayers();
        players.sort((a, b) -> Integer.compare(b.getKills(), a.getKills()));
        return players;
    }
}
