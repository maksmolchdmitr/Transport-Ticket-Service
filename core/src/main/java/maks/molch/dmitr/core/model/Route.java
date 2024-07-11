package maks.molch.dmitr.core.model;

public record Route(
        String departure,
        String arrival,
        Carrier carrier,
        int durationInMinutes,
        int seatCount
) {
}
