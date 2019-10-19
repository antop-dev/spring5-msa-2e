package com.brownfield.pss.checkin.repository;

import com.brownfield.pss.checkin.entity.CheckInRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckInRecord, Long> {

}
