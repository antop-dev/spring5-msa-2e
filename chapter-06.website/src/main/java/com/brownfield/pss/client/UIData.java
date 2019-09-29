package com.brownfield.pss.client;

import java.util.List;
import java.util.StringJoiner;

public class UIData {
    private final SearchQuery searchQuery = new SearchQuery();
    private List<Flight> flights;
    private Flight selectedFlight;
    private Passenger passenger;
    private String bookingId;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UIData.class.getSimpleName() + "[", "]")
                .add("searchQuery=" + searchQuery)
                .add("flights=" + flights)
                .add("selectedFlight=" + selectedFlight)
                .add("passenger=" + passenger)
                .add("bookingId='" + bookingId + "'")
                .toString();
    }
}