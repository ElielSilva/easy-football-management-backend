package br.edu.ifpe.easy_football_management_backend.domain.entity;

import java.util.UUID;

public class Node {
    private UUID id;
    private UUID championshipId;
    private String teamNameHome;
    private UUID idTeamHome;
    private UUID idTeamAway;
    private String teamNameAway;
    private Integer round;
    private Integer numberGoalsHome;
    private Integer numberGoalsAway;
    private MatchStatus status;

    public Node(UUID championshipId, UUID id, UUID idTeamAway, UUID idTeamHome, Integer numberGoalsAway, Integer numberGoalsHome, Integer round, MatchStatus status, String teamNameAway, String teamNameHome) {
        this.championshipId = championshipId;
        this.id = id;
        this.idTeamAway = idTeamAway;
        this.idTeamHome = idTeamHome;
        this.numberGoalsAway = numberGoalsAway;
        this.numberGoalsHome = numberGoalsHome;
        this.round = round;
        this.status = status;
        this.teamNameAway = teamNameAway;
        this.teamNameHome = teamNameHome;
    }

    public String getTeamNameHome() {
        return teamNameHome;
    }

    public void setTeamNameHome(String teamNameHome) {
        this.teamNameHome = teamNameHome;
    }

    public UUID getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(UUID championshipId) {
        this.championshipId = championshipId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdTeamAway() {
        return idTeamAway;
    }

    public void setIdTeamAway(UUID idTeamAway) {
        this.idTeamAway = idTeamAway;
    }

    public UUID getIdTeamHome() {
        return idTeamHome;
    }

    public void setIdTeamHome(UUID idTeamHome) {
        this.idTeamHome = idTeamHome;
    }

    public Integer getNumberGoalsAway() {
        return numberGoalsAway;
    }

    public void setNumberGoalsAway(Integer numberGoalsAway) {
        this.numberGoalsAway = numberGoalsAway;
    }

    public Integer getNumberGoalsHome() {
        return numberGoalsHome;
    }

    public void setNumberGoalsHome(Integer numberGoalsHome) {
        this.numberGoalsHome = numberGoalsHome;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public String getTeamNameAway() {
        return teamNameAway;
    }

    public void setTeamNameAway(String teamNameAway) {
        this.teamNameAway = teamNameAway;
    }
}
