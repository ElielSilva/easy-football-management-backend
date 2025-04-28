package br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsDTO;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsMapper;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ChampionshipsTeamsCommandHandller {
    private final ChampionshipsTeamsRepository championshipsTeamsRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;
    @Autowired
    private ChampionshipsTeamsMapper mapper;

    public ChampionshipsTeamsCommandHandller(ChampionshipsTeamsRepository championshipsTeamsRepository, ChampionshipsTeamsRepository tournamentsTeamsRepository, ChampionshipsTeamsRepository tournamentsTeamsRepository1, ChampionshipsTeamsRepository championshipsTeamsRepository1, TeamRepository teamRepository, TokenService tokenService) {
        this.championshipsTeamsRepository = championshipsTeamsRepository1;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
    }

    public ChampionshipsTeams create(String authHeader, ChampionshipsTeamsDTO championshipsTeamsDTO) {
//        String token = authHeader.substring(7);
//        UUID ID = UUID.fromString(tokenService.extractID(token));
//        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(ID);
//        UUID teamId = optionalTeamId.orElse(null);
        ChampionshipsTeams entity = mapper.toEntity(championshipsTeamsDTO);

        ChampionshipsTeams championshipsTeamsSaved = championshipsTeamsRepository.save(entity);
        return championshipsTeamsSaved;
    }
//
//    public void deleteTournamentsTeams(String authHeader, String ChampionshipsId) {
//        String token = authHeader.substring(7);
//        UUID ID = UUID.fromString(tokenService.extractID(token));
//        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(ID);
//        UUID teamId = optionalTeamId.orElse(null);
//        championshipsTeamsRepository.deleteByTeamIdAndChampionshipsId(teamId, UUID.fromString(ChampionshipsId));
//    }
}
