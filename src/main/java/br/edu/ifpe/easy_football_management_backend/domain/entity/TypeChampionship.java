package br.edu.ifpe.easy_football_management_backend.domain.entity;

public enum TypeChampionship {
    LEAGUE("League"),
    CUP("Cup");

    private final String type;

    TypeChampionship(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
