package br.edu.ifpe.easy_football_management_backend.features.tournament;

import br.edu.ifpe.easy_football_management_backend.domain.entity.MODALITY;
import br.edu.ifpe.easy_football_management_backend.domain.entity.STATUS;

import java.util.Date;
import java.util.UUID;

public record TournamentCreateDTO(
        String name,
        Integer quantity,
        STATUS status,
        Float award,
        MODALITY modality,
        String url_image,
        Date created_at,
        UUID user_id
) {
}
