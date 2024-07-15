package maks.molch.dmitr.core.service.entity;

public record FullRoute(
        int id,
        String departure,
        String arrival,
        Carrier carrier,
        int durationInMinutes,
        int seatCount
) {
}
