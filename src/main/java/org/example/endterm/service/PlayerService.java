package org.example.endterm.service;

import org.example.endterm.cache.InMemoryCache;
import org.example.endterm.model.Player;
import org.example.endterm.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repo;
    private final InMemoryCache cache = InMemoryCache.getInstance();

    private static final String TOP_PLAYERS_CACHE_KEY = "top_players";
    private static final String TOP_KILLS_CACHE_KEY = "top_kills";

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public List<Player> getAllPlayers() {
        return repo.getAllPlayers();
    }

    public List<Player> getTopPlayers() {
        if (cache.contains(TOP_PLAYERS_CACHE_KEY)) {
            return (List<Player>) cache.get(TOP_PLAYERS_CACHE_KEY);
        }

        List<Player> players = repo.getAllPlayers();
        players.sort((a, b) -> Double.compare(b.getRating(), a.getRating()));

        cache.put(TOP_PLAYERS_CACHE_KEY, players);
        return players;
    }

    public List<Player> getKills() {
        if (cache.contains(TOP_KILLS_CACHE_KEY)) {
            return (List<Player>) cache.get(TOP_KILLS_CACHE_KEY);
        }

        List<Player> players = repo.getAllPlayers();
        players.sort((a, b) -> Integer.compare(b.getKills(), a.getKills()));

        cache.put(TOP_KILLS_CACHE_KEY, players);
        return players;
    }

    public double calculateKD(Player p) {
        if (p.getDeaths() == 0) return p.getKills();
        return (double) p.getKills() / p.getDeaths();
    }
}
