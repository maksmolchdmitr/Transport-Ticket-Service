package maks.molch.dmitr.core.service.filter;

import java.util.Optional;

public record RouteFilter(
        Optional<String> departure,
        Optional<String> arrival,
        Optional<String> carrierName
) {
}
