package com.easyfootballmanagement.Mapper;

import com.easyfootballmanagement.Dtos.PlayerDtoRequest;
import com.easyfootballmanagement.Models.Players;

import java.util.ArrayList;
import java.util.List;

public class PlayerToMap {
    public static List<Players> mapToDto(List<PlayerDtoRequest> dto) {
        var result = new ArrayList<Players>();
        dto.stream().forEach(p -> {
            result.add(Players.builder()
                        .number(p.number)
                        .position(p.position)
                        .fullName(p.fullName)
                        .build());
        });
        return result;
    }
}
