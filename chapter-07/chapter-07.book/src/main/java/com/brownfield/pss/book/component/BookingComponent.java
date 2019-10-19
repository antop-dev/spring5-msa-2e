package com.brownfield.pss.book.component;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.entity.Inventory;
import com.brownfield.pss.book.entity.Passenger;
import com.brownfield.pss.book.repository.BookingRepository;
import com.brownfield.pss.book.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class BookingComponent {
    private static final Logger logger = LoggerFactory.getLogger(BookingComponent.class);
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Sender sender;

    public long book(BookingRecord record) {
        logger.info("calling fares to get fare");
        //call fares to get fare
        validateFareReactively(record);

        //check fare
        logger.info("calling inventory to get inventory");
        //check inventory
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(), record.getFlightDate());
        if (!inventory.isAvailable(record.getPassengers().size())) {
            throw new BookingException("No more seats available");
        }
        logger.info("successfully checked inventory" + inventory);
        logger.info("calling inventory to update inventory");
        //update inventory
        inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
        inventoryRepository.saveAndFlush(inventory);
        logger.info("sucessfully updated inventory");
        //save booking
        record.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = record.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(record));
        record.setBookingDate(new Date());
        long id = bookingRepository.save(record).getId();
        logger.info("Successfully saved booking");
        //send a message to search to update inventory
        logger.info("sending a booking event");
        Map<String, Object> bookingDetails = new HashMap<String, Object>();
        bookingDetails.put("FLIGHT_NUMBER", record.getFlightNumber());
        bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());
        sender.send(bookingDetails);
        logger.info("booking event successfully delivered " + bookingDetails);
        return id;
    }

    public BookingRecord getBooking(long id) {
        return bookingRepository.findById(new Long(id)).get();
    }

    public void updateStatus(String status, long bookingId) {
//        BookingRecord record = bookingRepository.findById(new Long(bookingId)).get();
        logger.info("update booking {} status {}", bookingId, status);
        bookingRepository.findById(new Long(bookingId)).ifPresent(r -> r.setStatus(status));
    }

    public void validateFareReactively(BookingRecord record) {
//        Fare fare = restTemplate.getForObject("http://fares-service/fares/get?flightNumber=" + record.getFlightNumber() + "&flightDate=" + record.getFlightDate(), Fare.class);
        Fare fare = restTemplate.getForObject("http://fares-api-gateway/api/fares/get?flightNumber=" + record.getFlightNumber() + "&flightDate=" + record.getFlightDate(), Fare.class);
        checkFare(record.getFare(), fare.getFare());
    }

    private void checkFare(String requestedFare, String actualfare) {
        logger.info("calling fares to get fare (reactively collected) " + actualfare);
        if (!requestedFare.equals(actualfare))
            throw new BookingException("fare is tampered");
    }
}
