package maks.molch.dmitr.core.service.entity;

import java.io.Serializable;

public record Carrier(
        String name,
        String phone
) implements Serializable {
}
