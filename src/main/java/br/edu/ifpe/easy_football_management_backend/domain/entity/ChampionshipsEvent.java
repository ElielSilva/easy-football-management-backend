package br.edu.ifpe.easy_football_management_backend.domain.entity;

import java.util.UUID;

public class ChampionshipsEvent {
    private UUID championshipsId;
    private UUID eventId;

    public ChampionshipsEvent(UUID championshipsId, UUID eventId) {
        this.championshipsId = championshipsId;
        this.eventId = eventId;
    }

    public UUID getChampionshipsId() {
        return championshipsId;
    }

    public void setChampionshipsId(UUID championshipsId) {
        this.championshipsId = championshipsId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
