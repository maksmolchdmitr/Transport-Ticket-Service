package maks.molch.dmitr.core.service.entity;

import java.io.Serializable;

public record FullRoute(
        int id,
        String departure,
        String arrival,
        Carrier carrier,
        int durationInMinutes,
        int seatCount
) implements Serializable {
}
