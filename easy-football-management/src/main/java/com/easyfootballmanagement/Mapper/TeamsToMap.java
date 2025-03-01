package com.easyfootballmanagement.Mapper;

import com.easyfootballmanagement.Dtos.TeamsDtoRequest;
import com.easyfootballmanagement.domain.entities.Players;
import com.easyfootballmanagement.domain.entities.Teams;
import com.easyfootballmanagement.domain.entities.Users;

import java.util.List;

public class TeamsToMap {
    public static Teams mapToTeams(TeamsDtoRequest dto) {
        return Teams.builder().name(dto.name).urlImg(dto.urlImg).build();
    }

    public static Teams mapToTeamAddFildPlayes(List<Players> players, Teams t) {
        var currentPlayers = t.getPlayers();
        players.stream().forEach(c -> {
            if (!currentPlayers.contains(c)) currentPlayers.add(c);
        });
        return  t.builder().players(currentPlayers).build();
    }

    public static Teams mapToTeamAddFildPlayes(Users users, Teams t) {
        return  t.builder().users(users).build();
    }

}
