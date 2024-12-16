package com.easyfootballmanagement.Mapper;

import com.easyfootballmanagement.Dtos.TeamsDtoRequest;
import com.easyfootballmanagement.Dtos.TeamsFullDtoRequest;
import com.easyfootballmanagement.Models.Players;
import com.easyfootballmanagement.Models.Teams;
import com.easyfootballmanagement.Models.Users;
import org.apache.catalina.User;

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

    public static Teams mapToFullDto(TeamsFullDtoRequest dto) {
        return Teams.builder().users(UserToMap.mapToDtoForUser(dto.users)).players().build();
    }
}
