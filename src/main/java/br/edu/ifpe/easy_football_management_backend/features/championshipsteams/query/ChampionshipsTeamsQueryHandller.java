package br.edu.ifpe.easy_football_management_backend.features.championshipsteams.query;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeams;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeamsRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ChampionshipsTeamsQueryHandller {
    private final ChampionshipsTeamsRepository championshipsTeamsRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;

    public ChampionshipsTeamsQueryHandller(ChampionshipsTeamsRepository championshipsTeamsRepository, TeamRepository teamRepository, TokenService tokenService) {
        this.championshipsTeamsRepository = championshipsTeamsRepository;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
    }

    public void getOne(String authHeader, UUID championshipId, UUID teamId) {
        UUID userId = UUID.fromString(tokenService.extractID(authHeader));

        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(userId);

        if (optionalTeamId.isEmpty()) {
            throw new BusinessException("User does not have a team");
        }

        UUID userTeamId = optionalTeamId.get();

        Optional<ChampionshipsTeams> ChampionshipsTeams = championshipsTeamsRepository.findByChampionship_IdAndTeam_Id(championshipId, teamId);

        if (ChampionshipsTeams.isEmpty()) {
            throw new BusinessException("Team does not belong to this championship");
        }
    }
}
