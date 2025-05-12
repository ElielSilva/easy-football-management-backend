package br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsDTO;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsMapper;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
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

    private final ChampionshipsRepository championshipsRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ChampionshipsTeamsCommandHandller(
            ChampionshipsTeamsRepository championshipsTeamsRepository,
            TeamRepository teamRepository,
            TokenService tokenService,
            ChampionshipsRepository championshipsRepository,
            ApplicationEventPublisher eventPublisher) {
        this.championshipsTeamsRepository = championshipsTeamsRepository;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
        this.championshipsRepository = championshipsRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ChampionshipsTeams create(String authHeader, ChampionshipsTeamsDTO championshipsTeamsDTO) {
        UUID userId = UUID.fromString(tokenService.extractID(authHeader));
        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(userId);
        Championships championships = championshipsRepository
                .findById(championshipsTeamsDTO.championshipsId())
                .orElseThrow(() -> new BusinessException("Team not found"));
        if (optionalTeamId.isEmpty() || !optionalTeamId.get().equals(championshipsTeamsDTO.teamId())){
            throw new BusinessException("Team does not belong to the user");
        }
        Integer countTeamInChampionship = championshipsTeamsRepository.countByTeamContains(championshipsTeamsDTO.teamId());
        if (championshipsTeamsRepository.existsByTeamId(championshipsTeamsDTO.teamId())){
            throw new BusinessException("Team already exists");
        };
        ChampionshipsTeams entity = mapper.toEntity(championshipsTeamsDTO);
        countTeamInChampionship++;
        if (countTeamInChampionship.equals(championships.getQuantityTeams())){
            eventPublisher.publishEvent(new ChampionshipsEvent(championshipsTeamsDTO.championshipsId(), UUID.randomUUID()));
        }
        return championshipsTeamsRepository.save(entity);
    }

    public void deleteTournamentsTeams(String authHeader, UUID ChampionshipsId) {
//        String token = authHeader.substring(7);
        UUID ID = UUID.fromString(tokenService.extractID(authHeader));
        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(ID);
        UUID teamId = optionalTeamId.orElse(null);
        championshipsTeamsRepository.deleteByTeamIdAndChampionshipsId(teamId, ChampionshipsId);
    }
}
