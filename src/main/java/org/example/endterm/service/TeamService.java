package org.example.endterm.service;

import org.example.endterm.model.Team;
import org.springframework.stereotype.Service;
import org.example.endterm.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }
}
