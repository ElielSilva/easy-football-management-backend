package br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsDTO;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.ChampionshipsTeamsMapper;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import jakarta.transaction.Transactional;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ChampionshipsTeamsCommandHandller {
    private final ChampionshipsTeamsRepository championshipsTeamsRepository;
    private final TeamRepository teamRepository;
    private final TokenService tokenService;
    private final ResultRepository resultRepository;

    private final RedissonClient redissonClient;
    private final ChampionshipsRepository championshipsRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private ChampionshipsTeamsMapper mapper;

    public ChampionshipsTeamsCommandHandller(
            ChampionshipsTeamsRepository championshipsTeamsRepository,
            TeamRepository teamRepository,
            TokenService tokenService,
            ResultRepository resultRepository,
            RedissonClient redissonClient,
            ChampionshipsRepository championshipsRepository,
            ApplicationEventPublisher eventPublisher) {
        this.championshipsTeamsRepository = championshipsTeamsRepository;
        this.teamRepository = teamRepository;
        this.tokenService = tokenService;
        this.resultRepository = resultRepository;
        this.redissonClient = redissonClient;
        this.championshipsRepository = championshipsRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ChampionshipsTeams create(String authHeader, ChampionshipsTeamsDTO championshipsTeamsDTO) {
        String keyLock = "ChampionshipsTeamsCommandHandller.create.";
        var lock = redissonClient.getLock(keyLock);
        lock.lock();
        UUID userId = UUID.fromString(tokenService.extractID(authHeader));
        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(userId);
        Championships championships = championshipsRepository
                .findById(championshipsTeamsDTO.championshipsId())
                .orElseThrow(() -> new NotFoundException("Champions not found"));
        if (optionalTeamId.isEmpty() || !optionalTeamId.get().equals(championshipsTeamsDTO.teamId())) {
            throw new BusinessException("Team does not belong to the user");
        }
        Integer countTeamInChampionship = championshipsTeamsRepository.countByTeamContains(championshipsTeamsDTO.championshipsId());
        if (championshipsTeamsRepository.existsByTeamId(championshipsTeamsDTO.teamId())) {
            throw new BusinessException("Team already exists");
        }
        if (countTeamInChampionship >= championships.getQuantityTeams()) {
            throw new BusinessException("Championship already has the maximum number of teams");
        }
        ChampionshipsTeams entity = mapper.toEntity(championshipsTeamsDTO, teamRepository.findById(championshipsTeamsDTO.teamId())
                .orElseThrow(() -> new BusinessException("Teams not found")), championships);
        countTeamInChampionship++;
        var result = championshipsTeamsRepository.save(entity);
        if (countTeamInChampionship.equals(championships.getQuantityTeams())) {
            eventPublisher.publishEvent(new ChampionshipsEvent(championshipsTeamsDTO.championshipsId(), UUID.randomUUID()));
        }
        lock.unlock();
        return result;
    }

    public void deleteTournamentsTeams(String authHeader, UUID ChampionshipsId) {
        UUID ID = UUID.fromString(tokenService.extractID(authHeader));
        Optional<UUID> optionalTeamId = teamRepository.findFirstTeamIdByUserId(ID);
        UUID teamId = optionalTeamId.orElseThrow(() -> new NotFoundException("Team not found"));
        String keyLock = "ChampionshipsTeamsCommandHandller.create.";
        var lock = redissonClient.getLock(keyLock);
        lock.lock();
        Integer countTeamInChampionship = championshipsTeamsRepository.countByTeamContains(ChampionshipsId);
        var championship = championshipsRepository.findById(ChampionshipsId).orElseThrow(() -> new NotFoundException("Championship not found"));
        boolean isExist = championshipsTeamsRepository.existsByTeamId(teamId);
        if (countTeamInChampionship.equals(0)) {
            throw new BusinessException("Championship does not have any teams");
        }
        if (isExist && countTeamInChampionship.equals(championship.getQuantityTeams())) {
            resultRepository.deleteByChampionshipsId(ChampionshipsId);
        }
        championshipsTeamsRepository.deleteByTeamIdAndChampionshipsId(teamId, ChampionshipsId);
        lock.unlock();
    }
}
