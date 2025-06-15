package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ChampionshipsTeamsMapper {

    @Autowired
    protected TeamRepository teamRepository;

    @Autowired
    protected ChampionshipsRepository championshipsRepository;

    public ChampionshipsTeams toEntity(ChampionshipsTeamsDTO dto, Team team, Championships championships) {
        ChampionshipsTeams entity = new ChampionshipsTeams();
        entity.setTeam(team);

        entity.setChampionship(championships);

        return entity;
    }

    public ChampionshipsTeamsDTO toDTO(ChampionshipsTeams entity) {

        return new ChampionshipsTeamsDTO(entity.getTeam().getId(), entity.getChampionship().getId());
    }
}
