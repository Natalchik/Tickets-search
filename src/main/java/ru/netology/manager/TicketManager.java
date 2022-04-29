package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;


public class TicketManager {
    private TicketRepository repository;


    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] findAll(String departureAirport, String arrivalAirport) {
        return searchByAirports(departureAirport, arrivalAirport);
    }
public Ticket findById(int id){
        return repository.findById(id);}
    public void removeById(int id){
        repository.removeById(id);
}

    public Ticket[] searchByAirports(String departureAirport, String arrivalAirport) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, departureAirport, arrivalAirport)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
//   public Ticket[] findAll(String from, String to) {
//        Ticket[] result = new Ticket[0];
//        for (Ticket ticket : repository.getALL()) {
//            if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
//                Ticket[] tmp = new Ticket[result.length + 1];
//                System.arraycopy(result, 0, tmp, 0, result.length);
//                tmp[tmp.length - 1] = ticket;
//                result = tmp;
        }
        Arrays.sort(result);
        return result;
    }


    public boolean matches(Ticket ticket, String departureAirport, String arrivalAirport) {
        return ticket.getDepartureAirport().contains(departureAirport) && ticket.getArrivalAirport().contains(arrivalAirport);
    }
}

