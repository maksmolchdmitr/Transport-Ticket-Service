package maks.molch.dmitr.core.pagination;

import java.util.List;

public record Page<T>(
        String nextToken,
        List<T> items
) {
}
