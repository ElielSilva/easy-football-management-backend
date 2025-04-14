package br.edu.ifpe.easy_football_management_backend.features.tournament.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.STATUS;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Tournament;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TournamentRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.tournament.TournamentCreateDTO;
import br.edu.ifpe.easy_football_management_backend.features.users.query.UserQueryHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class TournamentCommandHanhller {
    final TournamentRepository tournamentRepository;
    final UserQueryHandler userQueryHandler;


    public TournamentCommandHanhller(TournamentRepository tournamentRepository, UserQueryHandler userQueryHandler) {
        this.tournamentRepository = tournamentRepository;
        this.userQueryHandler = userQueryHandler;
    }

    public void createTournament(String authHeader, TournamentCreateDTO tournament) {

        Optional<User> userCurrent = userQueryHandler.findUserByEmail(authHeader);
        Tournament tournament1 = Tournament.builder()
                .name(tournament.name())
                .quantity(tournament.quantity())
                .status(tournament.status())
                .award(tournament.award())
                .modality(tournament.modality())
                .url_image(tournament.url_image())
                .created_at(tournament.created_at())
                .userID(userCurrent.get())
                .build();
        tournamentRepository.save(tournament1);
    }
}
