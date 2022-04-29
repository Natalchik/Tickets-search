package ru.netology.manager;


import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    Ticket ticket1 = new Ticket(1, 4000, "DME", "AER", 80);
    Ticket ticket2 = new Ticket(2, 2000, "VKO", "AAQ", 85);
    Ticket ticket3 = new Ticket(3, 1000, "SVO", "LED", 60);
    Ticket ticket4 = new Ticket(4, 3000, "VKO", "AER", 90);
    Ticket ticket5 = new Ticket(5, 3200, "VKO", "AER", 90);

    @org.junit.jupiter.api.Test
    void shouldAddTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);

        Ticket[] expected = {ticket4, ticket5};
        Ticket[] actual = manager.findAll("VKO", "AER");
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldSearchTicketByAirports() {

        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);


        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.findAll("DME", "AER");
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldNoSearchTicketIfNoSuchDepartureAirport() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("AER", "LED");
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void shouldNoSearchTicketIfNoSuchArrivalAirport() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.findAll("SVO", "QQA");
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void shouldNoSearchTicketInEmptyRepository() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("DME", "AER");
        assertArrayEquals(expected, actual);
    }
}