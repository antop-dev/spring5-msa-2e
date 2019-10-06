package com.brownfield.pss.checkin.controller;

import com.brownfield.pss.checkin.component.CheckinComponent;
import com.brownfield.pss.checkin.entity.CheckInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/checkin")
public class CheckInController {

    CheckinComponent checkInComponent;

    @Autowired
    CheckInController(CheckinComponent checkInComponent) {
        this.checkInComponent = checkInComponent;
    }

    @RequestMapping("/get/{id}")
    CheckInRecord getCheckIn(@PathVariable long id) {
        return checkInComponent.getCheckInRecord(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    long checkIn(@RequestBody CheckInRecord checkIn) {
        return checkInComponent.checkIn(checkIn);
    }

}
