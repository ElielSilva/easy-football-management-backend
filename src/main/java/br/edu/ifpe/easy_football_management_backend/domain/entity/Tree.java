package br.edu.ifpe.easy_football_management_backend.domain.entity;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;

public class Tree {
    private Integer numberOfTeams;
    private Node root;

    public Tree(Integer numberOfTeams, Node root) {
        if (numberOfTeams == null || numberOfTeams <= 0 || numberOfTeams % 2 != 0) {
            throw new BusinessException("Number of teams must be a positive integer.");
        }
        this.numberOfTeams = numberOfTeams;
        this.root = root;
    }
}
