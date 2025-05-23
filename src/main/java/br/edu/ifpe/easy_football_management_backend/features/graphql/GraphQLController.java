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


    public GraphQLController(UserRepository userRepository,
                             TeamRepository teamRepository,
                             ChampionshipsRepository championshipRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.championshipRepository = championshipRepository;
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

    // Resolvers para relações
//    @SchemaMapping(typeName = "User", field = "team")
//    public Team userTeam(User user) {
//        return teamRepository.findByUser(user);
//    }

//    @SchemaMapping(typeName = "Team", field = "user")
//    public User teamUser(Team team) {
//        return team.getUser();
//    }
//
//    @SchemaMapping(typeName = "Team", field = "championships")
//    public List<Championships> teamChampionships(Team team) {
//        return teamRepository.findByTeamsContaining(team);
//    }

    @SchemaMapping(typeName = "Championships", field = "teams")
    public List<Team> championshipTeams(Championships championship) {
        System.out.println("championshipTeams");
        return championshipRepository.findByChampionshipsContaining(championship);
    }

//    @SchemaMapping(typeName = "Championship", field = "user")
//    public User championshipUser(Championships championship) {
//        System.out.println("championship users");
//        return championship.getUser();
//    }
}

