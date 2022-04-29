package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    TicketRepository repository = new TicketRepository();

    Ticket ticket1 = new Ticket(1, 4000, "DME", "AER", 80);
    Ticket ticket2 = new Ticket(2, 2000, "VKO", "AAQ", 85);
    Ticket ticket3 = new Ticket(3, 1000, "SVO", "LED", 60);
    Ticket ticket4 = new Ticket(4, 3000, "VKO", "AER", 90);


    @Test
    void shouldAddTicketsInRepository() {
        repository.save(ticket1);

        Ticket[] expected = {ticket1};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);

        Ticket expected = ticket2;
        Ticket actual = repository.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);

        repository.removeById(2);

        Ticket[] expected = {ticket1, ticket3, ticket4};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

}