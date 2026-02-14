package org.example.endterm.service;

import org.example.endterm.cache.InMemoryCache;
import org.example.endterm.model.Team;
import org.example.endterm.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repo;
    private final InMemoryCache cache = InMemoryCache.getInstance();

    private static final String ALL_TEAMS_CACHE_KEY = "all_teams";

    public TeamService(TeamRepository repo) {
        this.repo = repo;
    }

    public List<Team> getAllTeams() {
        if (cache.contains(ALL_TEAMS_CACHE_KEY)) {
            return (List<Team>) cache.get(ALL_TEAMS_CACHE_KEY);
        }

        List<Team> teams = repo.getAllTeams();
        cache.put(ALL_TEAMS_CACHE_KEY, teams);
        return teams;
    }

    public List<Team> getTopTeams() {
        List<Team> teams = getAllTeams(); // берём из кеша, если он есть
        teams.sort((a, b) -> Double.compare(b.getTeamRating(), a.getTeamRating()));
        return teams;
    }
}
