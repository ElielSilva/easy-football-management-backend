package br.edu.ifpe.easy_football_management_backend.features.tournamentsteams.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Tournament;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TournamentRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TournamentsTeamsRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.auth.UserRepository;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.stereotype.Component;

@Component
public class TournamentsTeamsCommandHandller {
    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final TournamentsTeamsRepository tournamentsTeamsRepository;
    private final TokenService tokenService;
//
    public TournamentsTeamsCommandHandller(UserRepository userRepository, TournamentRepository tournamentRepository, TournamentsTeamsRepository tournamentsTeamsRepository, TokenService tokenService) {
        this.userRepository = userRepository;

        this.tournamentRepository = tournamentRepository;
        this.tournamentsTeamsRepository = tournamentsTeamsRepository;
        this.tokenService = tokenService;
    }

    public void createdTournamentsTeamsController(String authHeader, String tournamentId) {
        String token = authHeader.substring(7);
        String email = tokenService.extractEmail(token);
        User user = userRepository.findByEmail(email);
        Tournament tournament = tournamentRepository.findById(tournamentId);


    }
//
//    public boolean deleteTournamentsTeams() {
//
//    }
}
