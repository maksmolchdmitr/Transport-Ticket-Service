package maks.molch.dmitr.core.repo;

public record PurchasedTicketsUniqueId(
        String userLogin,
        int ticketId
) {
}
