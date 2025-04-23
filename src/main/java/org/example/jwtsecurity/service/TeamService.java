package org.example.jwtsecurity.service;

import lombok.RequiredArgsConstructor;
import org.example.jwtsecurity.model.dto.TeamRequestDTO;
import org.example.jwtsecurity.model.entity.Team;
import org.example.jwtsecurity.model.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team saveTeam(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.name());
        team.setLocation(dto.location());
        team.setManager(dto.manager());
        team.setCaptain(dto.captain());
        return teamRepository.save(team);
    }
}