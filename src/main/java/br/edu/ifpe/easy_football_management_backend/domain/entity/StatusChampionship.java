package br.edu.ifpe.easy_football_management_backend.domain.entity;

public enum StatusChampionship {
    IN_PROGRESS("In Progress"),
    FINISHED("Finished"),
    SCHEDULED("Scheduled");

    private final String status;

    StatusChampionship(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
