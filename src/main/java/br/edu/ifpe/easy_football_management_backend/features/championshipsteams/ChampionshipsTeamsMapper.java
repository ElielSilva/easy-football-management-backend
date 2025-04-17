package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.mapstruct.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ChampionshipsTeamsMapper {

    @Autowired
    protected TeamRepository teamRepository;

    @Autowired
    protected ChampionshipsRepository championshipsRepository;

    public ChampionshipsTeams toEntity(ChampionshipsTeamsDTO dto) {
        ChampionshipsTeams entity = new ChampionshipsTeams();
        entity.setTeam(teamRepository.findById(dto.teamId())
                .orElseThrow(() -> new RuntimeException("Time não encontrado")));

        entity.setChampionships(championshipsRepository.findById(dto.championshipsId())
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado")));

        return entity;
    }

    public ChampionshipsTeamsDTO toDTO(ChampionshipsTeams entity) {

        return new ChampionshipsTeamsDTO(entity.getTeam().getId(),entity.getChampionships().getId());
    }
}
