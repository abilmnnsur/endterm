package org.example.endterm.service;

import org.example.endterm.model.Team;
import org.example.endterm.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repo;

    public TeamService(TeamRepository repo) {
        this.repo = repo;
    }

    public List<Team> getTopTeams() {
        List<Team> teams = repo.getAllTeams();
        teams.sort((a, b) -> Double.compare(b.getTeamRating(), a.getTeamRating()));
        return teams;
    }

    public List<Team> getAllTeams() {
        return repo.getAllTeams();
    }
}
