package maks.molch.dmitr.core.model;

public record Route(
        String departure,
        String arrival,
        String carrierName,
        int durationInMinutes,
        int seatCount
) {
}
