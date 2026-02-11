package org.example.endterm.controller;

import org.example.endterm.model.Team;
import org.example.endterm.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }
    @GetMapping("/top")
    public List<Team> getTopTeams() {
        return teamService.getTopTeams();
    }
}