package br.edu.ifpe.easy_football_management_backend.features.classification.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.apache.coyote.BadRequestException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassificationHandler {

    private final ChampionshipsRepository championshipsRepository;
    private final TeamRepository teamRepository;
    private final ChampionshipsTeamsRepository championshipsTeamsRepository;

    public ClassificationHandler(ChampionshipsRepository championshipsRepository, TeamRepository teamRepository, ChampionshipsTeamsRepository championshipsTeamsRepository) {
        this.championshipsRepository = championshipsRepository;
        this.teamRepository = teamRepository;
        this.championshipsTeamsRepository = championshipsTeamsRepository;
    }

    @EventListener
    public void GenerateClassificationEvent(ChampionshipsEvent event) {
        try {
            var championships = championshipsRepository.findById(event.getChampionshipsId())
                    .orElseThrow(() -> new BadRequestException("Championship not found"));
            var teams = championshipsRepository.findByChampionshipsContaining(championships);
            // TODO: chamar o metodo para gerar os rounds e fazer o insert na base
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Result> generateRounds(List<Team> teams) {
        List<Result> results = new ArrayList<>();
        return results;
    }
}
