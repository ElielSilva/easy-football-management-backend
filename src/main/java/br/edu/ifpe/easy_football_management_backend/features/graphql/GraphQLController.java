package br.edu.ifpe.easy_football_management_backend.features.graphql;

import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class GraphQLController {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ChampionshipsRepository championshipRepository;
    private final PlayerRepository playerRepository;


    public GraphQLController(UserRepository userRepository,
                             TeamRepository teamRepository,
                             ChampionshipsRepository championshipRepository,
                             PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.championshipRepository = championshipRepository;
        this.playerRepository = playerRepository;
    }

    @QueryMapping
    public List<User> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User user(@Argument UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Team> teams() {
        System.out.println("teams");
        return teamRepository.findAll();
    }

    @QueryMapping
    public Player player(@Argument UUID id) {
        return playerRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Player> players() {
        return playerRepository.findAll();
    }

    @SchemaMapping(typeName = "Team", field = "players")
    public List<Player> playersTeams(Team team) {
        return playerRepository.findByTeamId(team.getId());
    }

    @QueryMapping
    public Team team(@Argument UUID id) {
        return teamRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Championships> championships() {
        return championshipRepository.findAll();
    }

    @QueryMapping
    public Championships championship(@Argument UUID id) {
        return championshipRepository.findById(id).orElse(null);
    }

    @SchemaMapping(typeName = "Championships", field = "teams")
    public List<Team> championshipTeams(Championships championship) {
        return championshipRepository.findByChampionshipsContaining(championship);
    }

//    @SchemaMapping(typeName = "Championship", field = "user")
//    public User championshipUser(Championships championship) {
//        System.out.println("championship users");
//        return championship.getUser();
//    }
}

