package com.brownfield.pss.fares.repository;

import com.brownfield.pss.fares.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaresRepository extends JpaRepository<Fare, Long> {
    Fare getFareByFlightNumberAndFlightDate(String flightNumber, String flightDate);
}
