package maks.molch.dmitr.core.model;

public record FullRoute(
        int id,
        String departure,
        String arrival,
        Carrier carrier,
        int durationInMinutes,
        int seatCount
) {
}
