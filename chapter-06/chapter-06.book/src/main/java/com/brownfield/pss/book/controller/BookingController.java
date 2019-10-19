package com.brownfield.pss.book.controller;

import com.brownfield.pss.book.component.BookingComponent;
import com.brownfield.pss.book.entity.BookingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    BookingComponent bookingComponent;

    @Autowired
    BookingController(BookingComponent bookingComponent) {
        this.bookingComponent = bookingComponent;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    long book(@RequestBody BookingRecord record) {
        return bookingComponent.book(record);
    }

    @RequestMapping("/get/{id}")
    BookingRecord getBooking(@PathVariable long id) {
        return bookingComponent.getBooking(id);
    }
}
