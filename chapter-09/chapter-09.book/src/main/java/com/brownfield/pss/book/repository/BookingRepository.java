package com.brownfield.pss.book.repository;


import com.brownfield.pss.book.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {

}
