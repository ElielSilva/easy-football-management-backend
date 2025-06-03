package br.edu.ifpe.easy_football_management_backend.domain.entity;

public enum STATUS {
    IN_PROGRESS("InProgress"),
    FINISHED("Finished"),
    CREATE("CREATE");

    private final String STATUS;

    STATUS(String status) {
        this.STATUS = status;
    }

    public String getStatus() {
        return STATUS;
    }
}
