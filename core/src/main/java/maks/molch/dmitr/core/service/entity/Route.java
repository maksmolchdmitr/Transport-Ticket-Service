package maks.molch.dmitr.core.service.entity;

public record Route(
        String departure,
        String arrival,
        String carrierName,
        int durationInMinutes,
        int seatCount
) {
}
