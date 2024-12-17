package com.easyfootballmanagement.Mapper;

import com.easyfootballmanagement.Dtos.TeamsDtoRequest;
import com.easyfootballmanagement.Models.Players;
import com.easyfootballmanagement.Models.Teams;
import com.easyfootballmanagement.Models.Users;

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
